/**
 * 
 */
package com.puntojapon.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.*;
import java.io.IOException;
import com.google.gson.*;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * @author Jorge de Castro
 *
 */

public class UniversityCrawler {

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
	public static String translate(String toTranslate) throws Exception {
		// String textTranslated = translate.translate(toTranslate,
		// Language.ENGLISH, Language.SPANISH);
		// Replace client_id and client_secret with your own.
		Translate.setClientId("02bdc5f1-c690-4b58-b5e8-7f7f2ffe54bb");
		Translate.setClientSecret("1HntNtmSEzsUgPCygbDX3pNGl3JvzpobDb+Si8mSVW4=");

		String textTranslated = Translate.execute(toTranslate, Language.SPANISH);
		return textTranslated;
	}

	// Crawl all the pages of university
	public static String crawlUniversities(String url, String listSearchType, String listName,
			CollegeList universitiesList, String jsonUniversitiesList) throws Exception {

		// Create the College List of Universities
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		int numberUniFound = 0;
		boolean next = true;

		// Main info of each uni
		String id = "";
		String japaneseName = "";
		String name = "";
		String prefecture = "";
		String type = "";
		String collegeType = "Universidad";
		boolean guideLinkState = false;
		boolean imageLinkState = false;
		String title = "";
		String description = "";

		String nextPageString = "";

		// Crawler
		// String url = "http://www.jpss.jp/en/search/?tb=1&search_x=1"; //URL
		// for universities search
		// TODO Change userAgent when application finished
		Document document = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(0).get();

		// Next page
		Elements textNextPage = document.select("div.pager > a, div.pager > span");
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
				.select("div[class=resultObj univResultObj], div[class=resultObjFree univResultObjFree]");
		for (Element element : text) { // Take all the uni divs

			// Getting university ID
			Element getId = element.select("div.univName > a").first();
			id = getId.attr("href").trim();

			// Getting university japanese name
			Element getJapaneseName = element.select("div.univName > a > span").first();
			japaneseName = getJapaneseName.text().trim();

			// Getting university name
			Element getName = getJapaneseName.nextElementSibling();
			name = getName.text().trim();

			// Getting prefecture and type
			Element getPrefecture = element.select("div.univTitle > p.mText").first();
			String prefectureDirty = getPrefecture.text();

			prefectureDirty = prefectureDirty.replaceAll("&" + "nbsp;", " ");
			prefectureDirty = prefectureDirty.replaceAll(String.valueOf((char) 160), "");
			prefecture = prefectureDirty.split("/")[0];
			type = prefectureDirty.split("/")[1];
			// type = translate(type);

			// Checking img link
			if (element.select("div.leftBlock > a > img").first() != null) {
				imageLinkState = true;
			} else {
				imageLinkState = false;
			}

			// Checking guide link
			if (element.select("div.dlBtn > a > img").first() != null) {
				guideLinkState = true;
			} else {
				guideLinkState = false;
			}

			// Getting title and description

			if (element.select("div.rightBlock > p").first() != null) {
				Element getTitle = element.select("div.rightBlock > p").first();
				title = getTitle.text().trim();
				//title = translate(title);

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

					// Mejora la velocidad de search
//					if (listSearchType == "prefecture" && prefecture.contains(listName)) {
//						facultyTitle = translate(facultyTitle);
//					}
//
//					if (listSearchType == "typeStudies") {
//
//						for (int i = 0; i < facultyList.getCollegeFacultySize(); i++) {
//							if (facultyList.getCollegeFacultyAt(i).getFacultyName().contains(listName)) {
//								facultyTitle = translate(facultyTitle);
//							}
//						}
//					}
//
//					if (listSearchType == "typeUni" && type.equals(listName)) {
//						facultyTitle = translate(facultyTitle);
//					}

					facultyList.addCollegeFaculty(new CollegeFaculty(facultyTitle, facultyHref));

				}
			}

			if (listSearchType == "prefecture" && prefecture.contains(listName)) {
				University university = new University(id, japaneseName, name, prefecture, type, collegeType,
						guideLinkState, imageLinkState, title, description, facultyList);
				universitiesList.addCollege(university);

			}
			if (listSearchType == "typeStudies") {

				for (int i = 0; i < facultyList.getCollegeFacultySize(); i++) {
					if (facultyList.getCollegeFacultyAt(i).getFacultyName().contains(listName)) {
						University university = new University(id, japaneseName, name, prefecture, type, collegeType,
								guideLinkState, imageLinkState, title, description, facultyList);
						universitiesList.addCollege(university);
					}
				}
			}
			if (listSearchType == "typeUni" && type.equals(listName)) {
				University university = new University(id, japaneseName, name, prefecture, type, collegeType,
						guideLinkState, imageLinkState, title, description, facultyList);
				universitiesList.addCollege(university);
			}
			if (listSearchType == "all") {
				University university = new University(id, japaneseName, name, prefecture, type, collegeType,
						guideLinkState, imageLinkState, title, description, facultyList);
				universitiesList.addCollege(university);
			}
		}
		// Si no hay nexpage, paramos, si si hay, seguimos con su URL
		System.out.println(nextPageString);
		jsonUniversitiesList = gson.toJson(universitiesList);
		if (next == false) { // no ha encontrado mas universidades en esta
								// pagina
			return jsonUniversitiesList;
		} else {
			return jsonUniversitiesList = crawlUniversities(nextPageString, listSearchType, listName, universitiesList,
					jsonUniversitiesList);
		}
	}

	public static void main(String[] args) throws Exception {
		// tipo de busqueda (prefecture, typeStudies, typeUni = Type of
		// university
		// National Public Private Japanese school of foreign university)
		// Permite hacer una primera búsqueda por encima. Te muestra las
		// universidades. Si se quiere info avanzada
		// queda por TODO
		// String url = "http://www.jpss.jp/en/search/?tb=1&search_x=1";
		String url = "http://www.jpss.jp/en/search/?tb=1&search_x=1";
		String listSearchType = "prefecture";
		CollegeList universitiesList = new CollegeList(listSearchType);

		String returnJson = "";
		String jsonUniversitiesList = crawlUniversities(url, listSearchType, "Tokyo", universitiesList, returnJson);
		System.out.println("Links: " + jsonUniversitiesList);
	}
}
