package com.puntojapon.main;
/**
 * 
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.regex.*;
//import java.io.IOException;
import com.google.gson.*;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * @author Jorge de Castro
 *
 */

public class GradSchoolCrawler {

	/**
	 * Allow to compare a specified string and a found
	 * 
	 * @author hoppy93
	 * @param pattern
	 *            ->Specifies the string you want to compare with the matcher
	 * @param matcher
	 *            ->Specifies the string you found while parsing
	 */
	public static boolean match(String pattern, String matcher) {
		Pattern h = Pattern.compile(pattern);
		Matcher m = h.matcher(matcher);
		return m.find();
	}

	// TODO HACER TRANSLATOR A MANO METIENDO TODAS LAS SUBJECTS
	// public static String translate(String toTranslate) throws Exception {
	// // String textTranslated = translate.translate(toTranslate,
	// // Language.ENGLISH, Language.SPANISH);
	// // Replace client_id and client_secret with your own.
	// Translate.setClientId("02bdc5f1-c690-4b58-b5e8-7f7f2ffe54bb");
	// Translate.setClientSecret("1HntNtmSEzsUgPCygbDX3pNGl3JvzpobDb+Si8mSVW4=");
	//
	// String textTranslated = Translate.execute(toTranslate, Language.SPANISH);
	// return textTranslated;
	// }

	int collegeCounter;
	
	//builder
	public GradSchoolCrawler(){
		this.collegeCounter = 0;
	};
	
	// Crawl all the pages of Graduate schools
	public String crawlGradSchools(String url, String[] prefectureSearchName, String typeStudies,
			CollegeList gradSchoolsList, String jsongradSchoolsList, int counter) throws Exception {

		// Create the College List of Universities
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		boolean next = true; //Used to exit recursion
		System.out.println("Estoy -> " + url);
		
		// Main info of each uni
		String id = "";
		String japaneseName = "";
		String name = "";
		String prefecture = "";
		String type = "";
		String collegeType = "Escuela de posgrado";
		String imageUrl = "";
		String guideUrl = "";
		String title = "";
		String description = "";

		String nextPageString = "";

		// Crawler
		// TODO Change userAgent when application finished
		Document document = Jsoup.connect(url).userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0).get();

		// Next page
		
		Elements textNextPage = document.select("div.pager > a, div.pager > span");
		if (textNextPage.isEmpty()) //Check if there is no next Page
			next = false;
		for (Element element : textNextPage) {
			if (element.text().equals(">")) {
				if (element.hasAttr("href")) {
					nextPageString = element.attr("href");
					nextPageString = "http://www.jpss.jp" + nextPageString;
				} else {
					next = false;
				}
			}
		}

		Elements text = document
				.select("div[class=resultObj gradResultObj], div[class=resultObjFree gradResultObjFree]");
		for (Element element : text) { // Take all the uni divs

			// Getting university ID
			Element getId = element.select("div.univName > a").first();
			id = getId.attr("href").trim();

			// Getting university japanese name
			Element getJapaneseName = element.select("div.univName > a > span").first();
			japaneseName = getJapaneseName.text().trim();

			// Getting university name
			//Element getName = getJapaneseName.nextElementSibling();
			if(getJapaneseName.nextElementSibling() != null){
			Element getName = getJapaneseName.nextElementSibling();
			name = getName.text().trim();
			}

			// Getting prefecture and type
			Element getPrefecture = element.select("div.univTitle > p.mText").first();
			String prefectureDirty = getPrefecture.text();

			prefectureDirty = prefectureDirty.replaceAll("&" + "nbsp;", " ");
			prefectureDirty = prefectureDirty.replaceAll(String.valueOf((char) 160), "");
			prefecture = prefectureDirty.split("/")[0];
			type = prefectureDirty.split("/")[1];
			// type = translate(type);

			// Getting img link
			if (element.select("div.leftBlock > a > img").first() != null) //{
				imageUrl = "http://www.jpss.jp/uploads" + id + "main.jpg";
				imageUrl = imageUrl.replace("/en", "");


			// Getting guide link
			if (element.select("div.dlBtn > a > img").first() != null) //{
				guideUrl = "http://www.jpss.jp/uploads" + id + "guide.zip";
				guideUrl = guideUrl.replace("/en", "");

			// Getting title and description
			if (element.select("div.rightBlock > p").first() != null) {
				Element getTitle = element.select("div.rightBlock > p").first();
				title = getTitle.text().trim();
				// title = translate(title);

				Element getDescription = getTitle.nextElementSibling();
				description = getDescription.text().trim();
			}

			// Getting facultaties and add them to the CollegeFacultyList
			CollegeFacultyList facultyList = new CollegeFacultyList(name);

			Elements facultiesDiv = element.select("div.depList.clearFix");
			for (Element facultyContent : facultiesDiv) {
				Elements facultiesRaw = facultyContent.select("a");

				for (Element faculty : facultiesRaw) {
					String facultyTitle = faculty.attr("title");
					String facultyHref = faculty.attr("href");

					// Usar traductor o no
					//facultyTitle = Translator.translate(facultyTitle);

					facultyList.addCollegeFaculty(new CollegeFaculty(facultyTitle, facultyHref));

				}
			}
			
			//TODO Limpiar esta parte de abajo
			//all
			if (prefectureSearchName[0].equals("")) {
				GradSchool gradSchool = new GradSchool(id, japaneseName, name, prefecture, type, collegeType,
						guideUrl, imageUrl, title, description, facultyList,"");
				// university.getFacultyList().translateCollegeFacultyList();
				gradSchoolsList.addCollege(gradSchool);
				counter++;
			}
			
			//1 prefecture chosen
			if (!prefectureSearchName[0].equals("") && prefectureSearchName.length >= 46){
				GradSchool gradSchool = new GradSchool(id, japaneseName, name, prefecture, type, collegeType,
						guideUrl, imageUrl, title, description, facultyList,"");
				// university.getFacultyList().translateCollegeFacultyList();
				gradSchoolsList.addCollege(gradSchool);
				counter++;
			}
			
			//More than 1 prefecture
			if (!prefectureSearchName[0].equals("") && typeStudies.equals("")
					&& prefectureSearchName.length < 46) {

				GradSchool gradSchool = new GradSchool(id, japaneseName, name, prefecture, type, collegeType,
						guideUrl, imageUrl, title, description, facultyList,"");
				//Traduccion
				// university.getFacultyList().translateCollegeFacultyList();
				gradSchoolsList.addCollege(gradSchool);
				counter++;

			}
		}
		jsongradSchoolsList = gson.toJson(gradSchoolsList);
		if (next == false) { // no ha encontrado mas universidades en esta
								// pagina
			System.out.println("Colegios de posgrado: " + counter);
			return jsongradSchoolsList;
		} else {
			return jsongradSchoolsList = crawlGradSchools(nextPageString, prefectureSearchName, typeStudies, 
					gradSchoolsList, jsongradSchoolsList, counter);
		}
	}

	public int getCollegeCounter() {
		return collegeCounter;
	}

	public void setCollegeCounter(int collegeCounter) {
		this.collegeCounter = collegeCounter;
	}
}
