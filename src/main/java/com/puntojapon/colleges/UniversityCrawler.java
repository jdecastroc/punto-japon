/**
 * 
 */
package com.puntojapon.colleges;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.*;

/**
 * @author jdecastroc
 *
 */

public class UniversityCrawler extends CollegeCrawler {

	/**
	 * Crawl all the pages of university
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
	public String getCollegeList(String url, String[] prefectureSearchName, CollegeList universitiesList,
			String jsonUniversitiesList, int counter) throws Exception {

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

			University university = new University(id, japaneseName, name, prefecture, type, guideUrl, imageUrl, title,
					description, facultyList, "");
			// university.getFacultyList().translateCollegeFacultyList();
			universitiesList.addCollege(university);
			counter++;

		}
		setCollegeCounter(counter);
		universitiesList.setSearchFound(getCollegeCounter());
		if (getCollegeCounter() != 0)
			universitiesList.setSearchState(true);
		else
			universitiesList.setSearchState(false);
		jsonUniversitiesList = gson.toJson(universitiesList);
		if (next == false) { // no ha encontrado mas universidades en esta
								// pagina
			System.out.println("Universidades: " + getCollegeCounter());
			return jsonUniversitiesList;
		} else {
			return jsonUniversitiesList = getCollegeList(nextPageString, prefectureSearchName, universitiesList,
					jsonUniversitiesList, counter);
		}
	}

	@Override
	public String getCollege(String id) throws Exception {

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		University university = new University();

		university.setId("http://www.jpss.jp/en/univ/" + id + "/");
		System.out.println("Estoy -> " + university.getId());

		// TODO Change userAgent when application finished
		Document document = Jsoup.connect(university.getId())
				.userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0).get();

		Elements text = document.select("div#schoolContainer");
		for (Element element : text) {
			// Getting prefecture and type for universities and GradSchools
			if (element.select("div.titleWrapper > p").first() != null) {
				Element getPrefecture = element.select("div.titleWrapper > p").first();
				String prefectureDirty = getPrefecture.text();

				prefectureDirty = prefectureDirty.replaceAll("&" + "nbsp;", " ");
				prefectureDirty = prefectureDirty.replaceAll(String.valueOf((char) 160), "");
				university.setPrefecture(prefectureDirty.split("/")[1]);
				university.setType(prefectureDirty.split("/")[2]);
				// type = translate(type);
			}
			// Japanese name and english name for uni and grad schools
			if (element.select("div.titleWrapper > h2 > span").first() != null) {
				Element getJapaneseName = element.select("div.titleWrapper > h2 > span").first();
				university.setJapaneseName(getJapaneseName.text().trim());

				Element getName = getJapaneseName.nextElementSibling();
				university.setName(getName.text().trim());
			}
			// Getting guide link
			if (element.select("div.dlBtn > a").first() != null) {
				Element getGuideUrl = element.select("div.dlBtn > a").first();
				university.setGuideUrl(getGuideUrl.attr("href"));
				// guideUrl = guideUrl.replace("/en", "");
			}

			// Getting image link
			if (element.select("div#SchoolHead > div.leftBlock > img").first() != null) {
				Element getImageUrl = element.select("div#SchoolHead > div.leftBlock > img").first();
				university.setImageUrl(getImageUrl.attr("src"));
			}

			// Getting title
			if (element.select("blockquote > h4.lText").first() != null) {
				Element getTitle = element.select("blockquote > h4.lText").first();
				university.setTitle(getTitle.text());
			}

			// Getting description
			if (element.select("blockquote > p").first() != null) {
				Element getDescription = element.select("blockquote > p").first();
				university.setDescription(getDescription.text());
			}

			// Official URL
			if (element.select("div.bottomOsLink > p > a").first() != null) {
				Element getOfficialUrl = element.select("div.bottomOsLink > p > a").first();

				university.setOfficialUrl(getOfficialUrl.text());
			}
			// CollegeList
			CollegeFacultyList facultyList = new CollegeFacultyList(university.getName());

			Elements facultiesDiv = element.select("div#SchoolMenu > ul.clearFix.padT15 > li");
			for (Element facultyContent : facultiesDiv) {
				Element facultiesRaw = facultyContent.select("a").first();

				String facultyTitle = facultiesRaw.attr("title");
				String facultyHref = facultiesRaw.attr("href");

				facultyList.addCollegeFaculty(new CollegeFaculty(facultyTitle, facultyHref));
			}
			university.setFaculties(facultyList);
		}

		return gson.toJson(university);
	}

	@Override
	public String getFacultyAdmissions(String universityParent, String id) throws Exception {
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		Faculty faculty = new Faculty(universityParent, id, "admissions");

		System.out.println("Voy a -> " + "http://www.jpss.jp/en/univ/" + faculty.getUniversityParent() + "/"
				+ faculty.getId() + "/");
		Document document = Jsoup
				.connect("http://www.jpss.jp/en/univ/" + faculty.getUniversityParent() + "/" + faculty.getId() + "/")
				.userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0).get();

		Elements text = document.select("div#DepCnt");

		faculty.setHeaderContent(document, faculty, text);

		// Table content
		for (Element element : text) {
			if (element.select("div#DepBody > div.unvExamTable > table > tbody > tr").first() != null) {
				Elements rowTable = element.select("div#DepBody > div.unvExamTable > table > tbody > tr");
				for (Element row : rowTable) {

					Element getRowRegister = row.select("th").first();
					Element getRowContent = row.select("td").first();
					faculty.getFacultyAdmissions().addRowTableInfo(getRowRegister.text().trim(),
							getRowContent.text().trim());
				}
			}

			// Last update
			if (element.select("div#DepBody > div.unvExamTable > p") != null) {
				Element getLastUpdate = element.select("div#DepBody > div.unvExamTable > p").first();
				faculty.getFacultyAdmissions().setLastUpdate(getLastUpdate.text());
			}

			// Websites
			if (element.select("div#DepBody > div.unvExamTable.padT15").first() != null) {
				Elements rowWebLinks = element.select("div#DepBody > div.unvExamTable.padT15 > table > tbody > tr");
				for (Element row : rowWebLinks) {
					Element getRowContent = row.select("th").first();
					Element getRowRegister = row.select("td > a").first();
					faculty.getFacultyAdmissions().addWebLink(getRowContent.text().trim(),
							getRowRegister.text().trim());
				}
			}
		}
		return gson.toJson(faculty);
	}

	@Override
	public String getFacultyInfo(String universityParent, String id) throws Exception {

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		Faculty faculty = new Faculty(universityParent, id, "info");

		System.out.println("Voy a -> " + "http://www.jpss.jp/en/univ/" + faculty.getUniversityParent() + "/"
				+ faculty.getId() + "/info/");
		Document document = Jsoup.connect(
				"http://www.jpss.jp/en/univ/" + faculty.getUniversityParent() + "/" + faculty.getId() + "/info/")
				.userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0).get();

		Elements text = document.select("div#DepCnt");

		faculty.setHeaderContent(document, faculty, text);

		for (Element element : text) {
			// Tab title
			if (element.select("div#DepBody > div.leftBlock > div.InfoUnivCnt > div.defaultTitle > h4")
					.first() != null) {
				Element getTitle = element
						.select("div#DepBody > div.leftBlock > div.InfoUnivCnt > div.defaultTitle > h4").first();
				faculty.getFacultyInfo().setTitle(getTitle.text().trim());
			}

			// Info content
			if (element.select("div#DepBody > div.leftBlock > div.InfoUnivCnt").first() != null) {
				String objectRegister = "";
				String objectContent = "";

				Elements objectTable = element.select("div#DepBody > div.leftBlock > div.InfoUnivCnt > div.depObj");
				for (Element object : objectTable) {

					if (object.select("h5").first() != null) {
						Element getObjectRegister = object.select("h5").first();
						objectRegister = getObjectRegister.text().trim();
					}
					if (object.select("div.depObjIn > div.whiteBase > p.padB20").first() != null) {
						Element getObjectContent = object.select("div.depObjIn > div.whiteBase > p.padB20").first();
						objectContent = getObjectContent.text().trim();
					}

					faculty.getFacultyInfo().addObject(objectRegister, objectContent);
				}
			}

			// Images
			if (element.select("div#DepBody > div.rightBlock > div") != null) {
				Elements images = element.select("div#DepBody > div.rightBlock > div");
				for (Element image : images) {
					if (image.select("img") != null) {
						faculty.getFacultyInfo().addImage(image.select("img").attr("src"));
					}
				}
			}

			// Registered International Students
			if (element.select("div#DepBody2 > div.infoCntB > div.defaultTitle > div.verticalBorder > h4") != null) {
				String year = "";
				Element getRegStudentsTitle = element
						.select("div#DepBody2 > div.infoCntB > div.defaultTitle > div.verticalBorder > h4").first();

				if (element
						.select("div#DepBody2 > div.infoCntB > div.defaultTitle > div.verticalBorder > h5") != null) {
					Element getRegStudentsYear = element
							.select("div#DepBody2 > div.infoCntB > div.defaultTitle > div.verticalBorder > h5").first();
					year = getRegStudentsYear.text().trim();
				}

				faculty.getFacultyInfo().setRegStudentsTitle(getRegStudentsTitle.text().trim() + " " + year);
			}

			if (element.select("div#DepBody2 > div.infoCntB > div.infoCntBIn > div.whiteBase") != null) {
				Elements rows = element.select("div#DepBody2 > div.infoCntB > div.infoCntBIn > div.whiteBase > div");
				for (Element row : rows) {
					if (row.select("span.countryName").first() != null
							&& row.select("span.peopleCount").first() != null) {
						Element getCountryName = row.select("span.countryName").first();
						Element getPeopleCount = row.select("span.peopleCount").first();
						faculty.getFacultyInfo().addStudentInfo(getCountryName.text().trim(),
								getPeopleCount.text().trim());
					}
				}
			}

			// Department info
			if (element.select("div#DepBody2 > div.infoCntC").first() != null) {
				String departTitle = "";
				String departContent = "";
				Elements tabs = element.select("div#DepBody2 > div.infoCntC > div");

				for (Element tab : tabs) {

					if (tab.select("h5").first() != null) {
						if (tab.select("h5").first().parent().className().equals("departTitle clearFix")) {
							Element getDepartTitle = tab.select("h5").first();
							departTitle = getDepartTitle.text().trim();
						}
					}
					if (tab.select("div.infoPointIn > p").first() != null) {
						Element getDepartContent = tab.select("div.infoPointIn > p").first();
						departContent = getDepartContent.text().trim();
					}
					if (!departTitle.equals("") && !departContent.equals("")) {
						faculty.getFacultyInfo().addDepartmentInfo(departTitle, departContent);
						// Reset
						departTitle = "";
						departContent = "";
					}
				}
			}
		}
		return gson.toJson(faculty);
	}

}
