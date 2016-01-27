/**
 * 
 */
package com.puntojapon.main;

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

	
	// Crawl all the pages of university
	public static String crawlUniversities(String url, String[] prefectureSearchName, String typeStudies, String typeUni,
			CollegeList universitiesList, String jsonUniversitiesList, int counter) throws Exception {

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
		String collegeType = "Universidad";
		String imageUrl = "";
		String guideUrl = "";
		String title = "";
		String description = "";

		String nextPageString = "";

		// Crawler
		// String url = "http://www.jpss.jp/en/search/?tb=1&search_x=1"; //URL
		// for universities search
		// TODO Change userAgent when application finished
		// TODO Speed-up esto de alguna manera, con un if fuera que compruebe directamente si la uni pertenece a lo que buscamos
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
			//(Arrays.asList(prefectureSearchName).contains(prefecture)
			
			//all
			if (prefectureSearchName[0].equals("")) {
				University university = new University(id, japaneseName, name, prefecture, type, collegeType,
						guideUrl, imageUrl, title, description, facultyList);
				// university.getFacultyList().translateCollegeFacultyList();
				universitiesList.addCollege(university);
				counter++;
			}
			
			// Prefectura 
			// TODO
			// Las prefecturas tipo Kanagawa, Tokyo, Shizuoka que van juntas no se cogen. Esto es porque es un String literal. habría
			// que partirlo y comprobar que, si existe la primera prefectura, se meta o bien hacer un if que meta todo a saco para el array de
			// 47 prefecturas. Es importante tener en cuenta que cada prefectura tiene un numero asociado en la URL (1-2-3-4)
			// Este if funciona correctamente para el "ALL" PERO NO PARA el resto. Por ej Tokyo, Kanagawa en la URL no cojera
			// Tokyo, Kanagawa / Private hay que hacer que compare la prefectura con el primero. Hbara que hacerlo por los numeros"
			if (!prefectureSearchName[0].equals("") && prefectureSearchName.length >= 46){
				University university = new University(id, japaneseName, name, prefecture, type, collegeType,
						guideUrl, imageUrl, title, description, facultyList);
				// university.getFacultyList().translateCollegeFacultyList();
				universitiesList.addCollege(university);
				counter++;
			}
//			boolean hasPrefecture = false;
//			String[] prefectureList = prefecture.split(" ");
			
//			for (int i = 0; i < prefectureList.length; i++){
//				//System.out.println(prefectureList[i]);
//				if (!Arrays.asList(prefectureSearchName).contains(prefectureList[i])){
//						hasPrefecture = false;
//				} else { 
//					hasPrefecture = true;
//				}
//			}
			
			if (!prefectureSearchName[0].equals("") && typeStudies.equals("")
					&& typeUni.equals("") && prefectureSearchName.length < 46) {

				University university = new University(id, japaneseName, name, prefecture, type, collegeType,
						guideUrl, imageUrl, title, description, facultyList);
				// university.getFacultyList().translateCollegeFacultyList();
				universitiesList.addCollege(university);
				counter++;

			}
			// Prefectura + TypeStudies
			if (!prefectureSearchName[0].equals("") && Arrays.asList(prefectureSearchName).contains(prefecture) && !typeStudies.equals("")
					&& typeUni.equals("")) {

				for (int i = 0; i < facultyList.getCollegeFacultySize(); i++) {
					if (facultyList.getCollegeFacultyAt(i).getFacultyName().contains(typeStudies)) {
						University university = new University(id, japaneseName, name, prefecture, type, collegeType,
								guideUrl, imageUrl, title, description, facultyList);
						// university.getFacultyList().translateCollegeFacultyList();
						universitiesList.addCollege(university);
						counter++;
					}
				}
			}
			// Prefectura + typeUni
			if (!prefectureSearchName[0].equals("") && Arrays.asList(prefectureSearchName).contains(prefecture) && typeStudies.equals("")
					&& !typeUni.equals("")) {
				if (type.equals(typeUni)) {
					University university = new University(id, japaneseName, name, prefecture, type, collegeType,
							guideUrl, imageUrl, title, description, facultyList);
					// university.getFacultyList().translateCollegeFacultyList();
					universitiesList.addCollege(university);
					counter++;
				}
			}
			// typeStudies
			if (!typeStudies.equals("") && prefectureSearchName[0].equals("") && typeUni.equals("")) {

				for (int i = 0; i < facultyList.getCollegeFacultySize(); i++) {
					if (facultyList.getCollegeFacultyAt(i).getFacultyName().contains(typeStudies)) {
						University university = new University(id, japaneseName, name, prefecture, type, collegeType,
								guideUrl, imageUrl, title, description, facultyList);
						// university.getFacultyList().translateCollegeFacultyList();
						universitiesList.addCollege(university);
						counter++;
					}
				}
			}
			// typeStudies + typeUni
			if (!typeStudies.equals("") && prefectureSearchName[0].equals("") && !typeUni.equals("")) {

				for (int i = 0; i < facultyList.getCollegeFacultySize(); i++) {
					if (facultyList.getCollegeFacultyAt(i).getFacultyName().contains(typeStudies)
							&& type.equals(typeUni)) {
						University university = new University(id, japaneseName, name, prefecture, type, collegeType,
								guideUrl, imageUrl, title, description, facultyList);
						// university.getFacultyList().translateCollegeFacultyList();
						universitiesList.addCollege(university);
						counter++;
					}
				}
			}
			// typeUni
			if (!typeUni.equals("") && type.equals(typeUni) && prefectureSearchName[0].equals("")
					&& typeStudies.equals("")) {
				University university = new University(id, japaneseName, name, prefecture, type, collegeType,
						guideUrl, imageUrl, title, description, facultyList);
				// university.getFacultyList().translateCollegeFacultyList();
				universitiesList.addCollege(university);
				counter++;
			}

			// prefectura + typeStudies + typeUni
			if (prefectureSearchName[0].equals("") && typeStudies.equals("") && typeUni.equals("")) {
				University university = new University(id, japaneseName, name, prefecture, type, collegeType,
						guideUrl, imageUrl, title, description, facultyList);
				// university.getFacultyList().translateCollegeFacultyList();
				universitiesList.addCollege(university);
				counter++;
			}
		}

		// Si no hay nexpage, paramos, si si hay, seguimos con su URL
		// Si estoy recorriendo todas las paginas no paro hasta que next = false
		// Si estoy recorriendo una pagina paro cuando acabe
		
		
		
		System.out.println(nextPageString);
		jsonUniversitiesList = gson.toJson(universitiesList);
		if (next == false) { // no ha encontrado mas universidades en esta
								// pagina
			System.out.println("Universidades: " + counter);
			return jsonUniversitiesList;
		} else {
			return jsonUniversitiesList = crawlUniversities(nextPageString, prefectureSearchName, typeStudies, typeUni,
					universitiesList, jsonUniversitiesList, counter);
		}
	}

//	public static void main(String[] args) throws Exception {
//		// tipo de busqueda (prefecture, typeStudies, typeUni = Type of
//		// university
//		// National Public Private Japanese school of foreign university)
//		// Permite hacer una primera búsqueda por encima. Te muestra las
//		// universidades. Si se quiere info avanzada
//		// queda por TODO y ver SI ES MAS RAPIDO PONER /Tokyo en PREFECTURAS
//		// String url = "http://www.jpss.jp/en/search/?tb=1&search_x=1";
//		// String url = "http://www.jpss.jp/en/search/?tb=1&search_x=1";
//		// //Lento, busca Tokyo en las 31
//		String url = "http://www.jpss.jp/en/search/Prefecture/Iwate/"; // Mucho
//																		// mas
//																		// rápido
//		// String listSearchType = "prefecture";
//		String prefecture = "Iwate";
//		String typeStudies = "";
//		String typeUni = "";
//		CollegeList universitiesList = new CollegeList(prefecture + typeStudies + typeUni);
//
//		String returnJson = "";
//		String jsonUniversitiesList = crawlUniversities(url, prefecture, typeStudies, typeUni, universitiesList,
//				returnJson);
//		System.out.println("Links: " + jsonUniversitiesList);
//	}
}
