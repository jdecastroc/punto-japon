package com.puntojapon.main;
/**
 * 
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.*;


/**
 * @author Jorge de Castro
 *
 */

public class GradSchoolCrawler {

	int collegeCounter;
	
	//builder
	public GradSchoolCrawler(){
		setCollegeCounter(0);
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
			if (element.select("div.leftBlock > a > img").first() != null){
				imageUrl = "http://www.jpss.jp/uploads" + id + "main.jpg";
				imageUrl = imageUrl.replace("/en", "");
			} else {
				imageUrl = "";
			}


			// Getting guide link
			if (element.select("div.dlBtn > a > img").first() != null) {
				guideUrl = "http://www.jpss.jp/uploads" + id + "guide.zip";
				guideUrl = guideUrl.replace("/en", "");
			} else {
				guideUrl = "";
			}

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
					//facultyTitle = Translator.translate(facultyTitle);
					facultyList.addCollegeFaculty(new CollegeFaculty(facultyTitle, facultyHref));

				}
			}
			
			GradSchool gradSchool = new GradSchool(id, japaneseName, name, prefecture, type, collegeType,
					guideUrl, imageUrl, title, description, facultyList,"");
			// university.getFacultyList().translateCollegeFacultyList();
			gradSchoolsList.addCollege(gradSchool);
			counter++;
		}
		setCollegeCounter(counter);
		gradSchoolsList.setSearchFound(getCollegeCounter());
		if (getCollegeCounter() != 0)
			gradSchoolsList.setSearchState(true);
		else
			gradSchoolsList.setSearchState(false);
		jsongradSchoolsList = gson.toJson(gradSchoolsList);
		if (next == false) { // no ha encontrado mas universidades en esta
								// pagina
			System.out.println("Colegios de posgrado: " + getCollegeCounter());
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
