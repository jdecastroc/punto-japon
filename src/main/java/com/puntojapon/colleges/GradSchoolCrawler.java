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

public class GradSchoolCrawler extends CollegeCrawler {

	/**
	 * Crawl all the pages of GradSchools
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
	public String getCollegeList(String url, String[] prefectureSearchName, CollegeList gradSchoolsList,
			String jsongradSchoolsList, int counter) throws Exception {

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
			type = prefectureDirty.split("/")[1];
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

			GradSchool gradSchool = new GradSchool(id, japaneseName, name, prefecture, type, guideUrl, imageUrl, title,
					description, facultyList, "");
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
			return jsongradSchoolsList = getCollegeList(nextPageString, prefectureSearchName, gradSchoolsList,
					jsongradSchoolsList, counter);
		}
	}

	@Override
	public String getCollege(String id) throws Exception {

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		GradSchool gradSchool = new GradSchool();

		gradSchool.setId("http://www.jpss.jp/en/grad/" + id + "/");
		System.out.println("Estoy -> " + gradSchool.getId());

		// TODO Change userAgent when application finished
		Document document = Jsoup.connect(gradSchool.getId())
				.userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0).get();

		Elements text = document.select("div#schoolContainer");
		for (Element element : text) {
			// Getting prefecture and type for universities and GradSchools
			if (element.select("div.titleWrapper > p").first() != null) {
				Element getPrefecture = element.select("div.titleWrapper > p").first();
				String prefectureDirty = getPrefecture.text();

				prefectureDirty = prefectureDirty.replaceAll("&" + "nbsp;", " ");
				prefectureDirty = prefectureDirty.replaceAll(String.valueOf((char) 160), "");
				gradSchool.setPrefecture(prefectureDirty.split("/")[1]);
				gradSchool.setType(prefectureDirty.split("/")[2]);
				// type = translate(type);
			}
			// Japanese name and english name for uni and grad schools
			if (element.select("div.titleWrapper > h2 > span").first() != null) {
				Element getJapaneseName = element.select("div.titleWrapper > h2 > span").first();
				gradSchool.setJapaneseName(getJapaneseName.text().trim());

				Element getName = getJapaneseName.nextElementSibling();
				gradSchool.setName(getName.text().trim());
			}
			// Getting guide link
			if (element.select("div.dlBtn > a").first() != null) {
				Element getGuideUrl = element.select("div.dlBtn > a").first();
				gradSchool.setGuideUrl(getGuideUrl.attr("href"));
				// guideUrl = guideUrl.replace("/en", "");
			}

			// Getting image link
			if (element.select("div#SchoolHead > div.leftBlock > img").first() != null) {
				Element getImageUrl = element.select("div#SchoolHead > div.leftBlock > img").first();
				gradSchool.setImageUrl(getImageUrl.attr("src"));
			}

			// Getting title
			if (element.select("blockquote > h4.llText").first() != null) {
				Element getTitle = element.select("blockquote > h4.llText").first();
				gradSchool.setTitle(getTitle.text());
			}

			// Getting description
			if (element.select("blockquote > p").first() != null) {
				Element getDescription = element.select("blockquote > p").first();
				gradSchool.setDescription(getDescription.text());
			}

			// Official URL
			if (element.select("div.bottomOsLink >  a").first() != null) {
				Element getOfficialUrl = element.select("div.bottomOsLink > a").first();

				gradSchool.setOfficialUrl(getOfficialUrl.text());
			}
			// CollegeList
			CollegeFacultyList facultyList = new CollegeFacultyList(gradSchool.getName());

			Elements facultiesDiv = element.select("div#SchoolMenu > ul.clearFix.padT15 > li");
			for (Element facultyContent : facultiesDiv) {
				Element facultiesRaw = facultyContent.select("a").first();

				String facultyTitle = facultiesRaw.attr("title");
				String facultyHref = facultiesRaw.attr("href");

				facultyList.addCollegeFaculty(new CollegeFaculty(facultyTitle, facultyHref));
			}
			gradSchool.setFaculties(facultyList);
		}

		return gson.toJson(gradSchool);
	}

	@Override
	public String getFaculty() {
		// TODO Auto-generated method stub
		return null;
	}
}
