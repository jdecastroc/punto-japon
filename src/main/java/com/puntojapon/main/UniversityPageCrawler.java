/**
 * 
 */
package com.puntojapon.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Jorge de Castro
 *
 */
public class UniversityPageCrawler {

	public static String crawlUniversityPage(String id) throws Exception {

		//TODO Adaptar para que sirva para universidades, escuelas de posgrado y fp
		// Create the College List of Universities
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		String jsonUniversity = "";
		University university = null;

		System.out.println("Estoy -> http://www.jpss.jp/en/univ/" + id + "/");
		String url = "http://www.jpss.jp/en/univ/" + id + "/";

		// Main info of each uni
		// String id = "";
		String japaneseName = "";
		String name = "";
		String prefecture = "";
		String type = "";
		String collegeType = "Universidad";
		String imageUrl = "";
		String guideUrl = "";
		String title = "";
		String description = "";
		String officialUrl = "";

		// Crawler
		// TODO Change userAgent when application finished
		Document document = Jsoup.connect(url).userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0)
				.get();

		Elements text = document.select("div#schoolContainer");
		for (Element element : text) {
			// Getting prefecture and type
			Element getPrefecture = element.select("div.titleWrapper > p").first();
			String prefectureDirty = getPrefecture.text();

			prefectureDirty = prefectureDirty.replaceAll("&" + "nbsp;", " ");
			prefectureDirty = prefectureDirty.replaceAll(String.valueOf((char) 160), "");
			prefecture = prefectureDirty.split("/")[1];
			type = prefectureDirty.split("/")[2];
			// type = translate(type);

			// Japanese name and english name
			Element getJapaneseName = element.select("div.titleWrapper > h2 > span").first();
			japaneseName = getJapaneseName.text().trim();

			Element getName = getJapaneseName.nextElementSibling();
			name = getName.text().trim();

			// Getting guide link
			if (element.select("div.dlBtn > a").first() != null) {
				Element getGuideUrl = element.select("div.dlBtn > a").first();
				guideUrl = getGuideUrl.attr("href");
				// guideUrl = guideUrl.replace("/en", "");
			}

			// Getting image link
			if (element.select("div#SchoolHead > div.leftBlock > img").first() != null) {
				Element getImageUrl = element.select("div#SchoolHead > div.leftBlock > img").first();
				imageUrl = getImageUrl.attr("src");
			}

			// Getting title
			if (element.select("blockquote > h4.lText").first() != null) {
				Element getTitle = element.select("blockquote > h4.lText").first();
				title = getTitle.text();
			}

			// Getting description
			if (element.select("blockquote > p").first() != null) {
				Element getDescription = element.select("blockquote > p").first();
				description = getDescription.text();
			}

			// Official URL
			if (element.select("div.bottomOsLink > p > a").first() != null) {
				Element getOfficialUrl = element.select("div.bottomOsLink > p > a")
						.first();
				
				officialUrl = getOfficialUrl.text();
			}

			CollegeFacultyList facultyList = new CollegeFacultyList(name);

			Elements facultiesDiv = element.select("div#SchoolMenu > ul.clearFix.padT15 > li");
			for (Element facultyContent : facultiesDiv) {
				// System.out.println("Entro a algun sitio");
				Element facultiesRaw = facultyContent.select("a").first();

				String facultyTitle = facultiesRaw.attr("title");
				String facultyHref = facultiesRaw.attr("href");

				// Usar traductor o no
				// facultyTitle = Translator.translate(facultyTitle);

				facultyList.addCollegeFaculty(new CollegeFaculty(facultyTitle, facultyHref));
			}

			university = new University(id, japaneseName, name, prefecture, type, collegeType, guideUrl, imageUrl,
					title, description, facultyList, officialUrl);
			// Traduccion
			// university.getFacultyList().translateCollegeFacultyList();
		}
		jsonUniversity = gson.toJson(university);
		return jsonUniversity;
	}
}
