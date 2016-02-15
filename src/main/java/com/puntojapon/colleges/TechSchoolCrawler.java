package com.puntojapon.colleges;

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

public class TechSchoolCrawler extends CollegeCrawler {

	/**
	 * Crawl all the pages of tech Schools
	 * 
	 * @author jdecastroc
	 * @param url
	 *            ->Specifies the string you want to compare with the matcher
	 * @param prefectureSearchName
	 *            ->Specifies the string you found while parsing
	 * @param typeStudies
	 *            ->Specifies the string you found while parsing
	 */
	@Override
	public String getCollegeList(String url, String[] prefectureSearchName, CollegeList TechSchoolsList,
			String jsonTechSchoolsList, int counter) throws Exception {

		// Create the College List of Universities
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		boolean next = true; // Used to exit recursion
		System.out.println("Estoy -> " + url);

		// Main info of each uni
		String id = "";
		String japaneseName = "";
		String name = "";
		String prefecture = "";
		String type = "";
		String imageUrl = "";
		String guideUrl = "";
		String title = "";
		String description = "";

		String nextPageString = "";

		// Crawler
		// TODO Change userAgent when application finished
		Document document = Jsoup.connect(url).userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0)
				.get();

		// Next page
		Elements textNextPage = document.select("div.pager > a, div.pager > span");
		if (textNextPage.isEmpty()) // Check if there is no next Page
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

		Elements text = document.select("div[class=resultObj]");
		for (Element element : text) { // Take all the uni divs

			// Getting university ID
			Element getId = element.select("div.univName > a").first();
			id = getId.attr("href").trim();

			// Getting university japanese name
			Element getJapaneseName = element.select("div.univName > a > span").first();
			japaneseName = getJapaneseName.text().trim();

			// Getting university name
			// Element getName = getJapaneseName.nextElementSibling();
			if (getJapaneseName.nextElementSibling() != null) {
				Element getName = getJapaneseName.nextElementSibling();
				name = getName.text().trim();
			}

			// Getting prefecture and type
			Element getPrefecture = element.select("div.univTitle > p.mText").first();
			String prefectureDirty = getPrefecture.text();

			prefectureDirty = prefectureDirty.replaceAll("&" + "nbsp;", " ");
			prefectureDirty = prefectureDirty.replaceAll(String.valueOf((char) 160), "");
			prefecture = prefectureDirty.split("/")[0];
			// type = translate(type);

			// Getting img link
			if (element.select("div.leftBlock > a > img").first() != null) {
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
					// facultyTitle = Translator.translate(facultyTitle);
					facultyList.addCollegeFaculty(new CollegeFaculty(facultyTitle, facultyHref));

				}
			}

			TechSchool TechSchool = new TechSchool(id, japaneseName, name, prefecture, type, guideUrl, imageUrl, title,
					description, facultyList, "");
			// university.getFacultyList().translateCollegeFacultyList();
			TechSchoolsList.addCollege(TechSchool);
			counter++;
		}
		setCollegeCounter(counter);
		TechSchoolsList.setSearchFound(getCollegeCounter());
		if (getCollegeCounter() != 0)
			TechSchoolsList.setSearchState(true);
		else
			TechSchoolsList.setSearchState(false);
		jsonTechSchoolsList = gson.toJson(TechSchoolsList);
		if (next == false) { // no ha encontrado mas universidades en esta
								// pagina
			System.out.println("Colegios profesionales: " + getCollegeCounter());
			return jsonTechSchoolsList;
		} else {
			return jsonTechSchoolsList = getCollegeList(nextPageString, prefectureSearchName, TechSchoolsList,
					jsonTechSchoolsList, counter);
		}
	}

	@Override
	public String getCollege(String id) throws Exception {

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		TechSchool techSchool = new TechSchool();

		techSchool.setId("http://www.jpss.jp/en/tech/" + id + "/");
		System.out.println("Estoy -> " + techSchool.getId());

		// TODO Change userAgent when application finished
		Document document = Jsoup.connect(techSchool.getId())
				.userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0).get();

		Elements text = document.select("div#schoolContainer");
		for (Element element : text) {

			// Getting prefecture and type for FP
			if (element.select("div#SchoolName > p").first() != null) {

				Element getType = element.select("div#SchoolName > p > span").first();
				techSchool.setType(getType.text());
				Element getPrefecture = element.select("div#SchoolName > p").first();
				String prefectureDirty = getPrefecture.text();

				prefectureDirty = prefectureDirty.replaceAll("&" + "nbsp;", " ");
				prefectureDirty = prefectureDirty.replaceAll(String.valueOf((char) 160), "");
				techSchool.setPrefecture(prefectureDirty.split("/")[1]);
			}
			// Japanese name and english name if FP
			if (element.select("div#SchoolName > h2 > span").first() != null) {
				Element getJapaneseName = element.select("div#SchoolName > h2 > span").first();
				techSchool.setJapaneseName(getJapaneseName.text().trim());

				Element getName = getJapaneseName.nextElementSibling();
				techSchool.setName(getName.text().trim());
			}
			// Getting guide link
			if (element.select("div.dlBtn > a").first() != null) {
				Element getGuideUrl = element.select("div.dlBtn > a").first();
				techSchool.setGuideUrl(getGuideUrl.attr("href"));
				// guideUrl = guideUrl.replace("/en", "");
			}

			// Getting image link
			if (element.select("div#SchoolHead > div.leftBlock > img").first() != null) {
				Element getImageUrl = element.select("div#SchoolHead > div.leftBlock > img").first();
				techSchool.setImageUrl(getImageUrl.attr("src"));
			}

			// Getting title
			if (element.select("blockquote > h4.llText").first() != null) {
				Element getTitle = element.select("blockquote > h4.llText").first();
				techSchool.setTitle(getTitle.text());
			}

			// Getting description
			if (element.select("blockquote > p").first() != null) {
				Element getDescription = element.select("blockquote > p").first();
				techSchool.setDescription(getDescription.text());
			}
			// For fp pages URL
			if (element.select("div#OsLink > p > a").first() != null) {
				Element getOfficialUrl = element.select("div#OsLink > p > a").first();

				techSchool.setOfficialUrl(getOfficialUrl.text());
			}

			CollegeFacultyList facultyList = new CollegeFacultyList(techSchool.getName());

			Elements facultiesDiv = element.select("div#SchoolMenu > ul.clearFix.padT15 > li");
			for (Element facultyContent : facultiesDiv) {
				Element facultiesRaw = facultyContent.select("a").first();

				String facultyTitle = facultiesRaw.attr("title");
				String facultyHref = facultiesRaw.attr("href");

				facultyList.addCollegeFaculty(new CollegeFaculty(facultyTitle, facultyHref));
			}
			techSchool.setFaculties(facultyList);
		}

		return gson.toJson(techSchool);

	}

	@Override
	public String getFaculty() {
		// TODO Auto-generated method stub
		return null;
	}
}