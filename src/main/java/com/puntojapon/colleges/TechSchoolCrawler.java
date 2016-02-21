/**
 * TechSchool Crawler
 * @author jdecastroc
 * @version 2.0, 21 Feb 2016
 *
 */

package com.puntojapon.colleges;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.*;
import com.puntojapon.colleges.Faculty.FacultyCourse;

/**
 * Provides all the crawlers related with the Tech Schools searchs and also
 * Tech Schools faculties search
 * 
 * @author jdecastroc
 * 
 * @see com.puntojapon.colleges.CollegeCrawler
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
	 * @param TechSchoolsList
	 *            ->Specifies the college list which is gonna be used in the
	 *            recursive function
	 * @param jsonTechSchoolsList
	 *            ->Final string which is going to be converted to json format
	 *            to retrieve the crawled data
	 * @param counter
	 *            ->College counter for search details
	 *            
	 * @see com.puntojapon.colleges.CollegeCrawler#getCollegeList(java.lang.String, java.lang.String[], com.puntojapon.colleges.CollegeList, java.lang.String, int)
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

	/**
	 * Crawl the information related to the faculty returning the main info in
	 * json format
	 * 
	 * @author jdecastroc
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getCollege(java.lang.String)
	 */
	@Override
	public String getCollege(String id) {

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		TechSchool techSchool = new TechSchool();

		techSchool.setId("http://www.jpss.jp/en/tech/" + id + "/");
		System.out.println("Estoy -> " + techSchool.getId());
		try {
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
		} catch (Exception e) {
			techSchool.setSearch(false);
			System.out.println(e);
		}
		return gson.toJson(techSchool);

	}

	/**
	 * Crawl the information related to the tech faculty admissions returning
	 * the info in json format
	 * 
	 * @author jdecastroc
	 * 
	 * @param techSchoolParent
	 *            -> id of the faculty tech School
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getFacultyAdmissions(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String getFacultyAdmissions(String techSchoolParent, String id) {

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		Faculty faculty = new Faculty(techSchoolParent, id, "essentialInfo");
		try {
			System.out.println("Voy a -> " + "http://www.jpss.jp/en/tech/" + faculty.getParent() + "/");
			Document document = Jsoup.connect("http://www.jpss.jp/en/tech/" + faculty.getParent() + "/")
					.userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0).get();

			Elements headMenu = document.select("div#DepMenu > ul.clearFix > li");

			for (Element object : headMenu) {
				if (object.select("a").first() != null) {
					Element getMenuTitle = object.select("a").first();
					faculty.addLink(getMenuTitle.attr("href"));
				}
			}

			Elements infoContent = document.select("div.techInfo");

			// Table content
			for (Element info : infoContent) {

				// Title
				if (info.select("div.infoCnt > div.defaultTitle > h4").first() != null) {
					Element getTitle = info.select("div.infoCnt > div.defaultTitle > h4").first();
					faculty.getFacultyTechEssentialInfo().setTitle(getTitle.text().trim());
				}

				// Adress title
				if (info.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr > th > h5")
						.first() != null) {
					Element addressTitle = info
							.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr > th > h5")
							.first();
					faculty.getFacultyTechEssentialInfo().getAddressInfo().setRegister(addressTitle.text().trim());
				}

				// Adress settings
				// Address
				if (info.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr > td > span")
						.attr("itemprop", "address").first() != null) {
					Element addressCont = info
							.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr > td > span")
							.attr("itemprop", "address").first();
					faculty.getFacultyTechEssentialInfo().getAddressInfo()
							.setContent("Direccion: " + addressCont.text().trim());
				}
				// Telephone
				if (info.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr > td > span")
						.get(1) != null) {
					Element telephoneCont = info
							.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr > td > span")
							.get(1);
					faculty.getFacultyTechEssentialInfo().getAddressInfo()
							.setContent(faculty.getFacultyTechEssentialInfo().getAddressInfo().getContent()
									+ ", Telefono: " + telephoneCont.text().trim());
				}
				// Email
				if (info.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr > td > span > a")
						.first() != null) {
					Element emailCont = info
							.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr > td > span > a")
							.first();
					faculty.getFacultyTechEssentialInfo().getAddressInfo()
							.setContent(faculty.getFacultyTechEssentialInfo().getAddressInfo().getContent()
									+ ", Email: " + emailCont.text().trim());
				}

				// Registered international students
				// Title
				if (info.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr").get(2) != null) {
					Element regTitle = info.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr")
							.get(2).select("h5").first();
					faculty.getFacultyTechEssentialInfo().getRegisterStudents().setRegister(regTitle.text().trim());
				}

				// Year and number
				if (info.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr").get(3)
						.select("td > h6").first() != null) {
					Element getYear = info.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr")
							.get(3).select("td > h6").first();

					if (info.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr").get(3)
							.select("td > p").first() != null) {
						Element getStudents = info
								.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr").get(3)
								.select("td > p").first();
						faculty.getFacultyTechEssentialInfo().getRegisterStudents().setContent(getYear.text().trim(),
								getStudents.text().trim());
					}
				}

				// Information entrance exam
				if (info.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr").get(4)
						.select("th > h5").first() != null) {
					Element getTitleInfo = info
							.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr").get(4)
							.select("th > h5").first();
					faculty.getFacultyTechEssentialInfo().getEntranceInfo().setRegister(getTitleInfo.text().trim());
				}

				// Loop
				Elements admissions = info.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr")
						.get(5).select("td > p");
				String register = "";
				String content = "";
				int i = 0; // Important to match register and content due to a
							// bug
							// in jsoup
				for (Element admission : admissions) {

					if (admission.select("p") != null) {
						register = info.select("div.infoCnt > div.unvExamTable > table.noRBorderTh > tbody > tr").get(5)
								.select("td > h6").get(i).text().trim();
						content = admission.select("p").text().trim();
						faculty.getFacultyTechEssentialInfo().getEntranceInfo().setContent(register, content);
						// Reset
						register = "";
						content = "";
					}
					i++;
				}

			}
		} catch (Exception e) {
			faculty.setSearch(false);
			System.out.println(e);
		}
		return gson.toJson(faculty);
	}

	/**
	 * Crawl the information related to the tech faculty info returning the info
	 * in json format
	 * 
	 * @author jdecastroc
	 * 
	 * @param techSchoolParent
	 *            -> id of the faculty tech School
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getFacultyInfo(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String getFacultyInfo(String techSchoolParent, String id) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		Faculty faculty = new Faculty(techSchoolParent, id, "techInfo");

		try {
			System.out.println("Voy a -> " + "http://www.jpss.jp/en/tech/" + faculty.getParent() + "/faculty/");
			Document document = Jsoup.connect("http://www.jpss.jp/en/tech/" + faculty.getParent() + "/faculty/")
					.userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0).get();

			Elements headMenu = document.select("div#DepMenu > ul.clearFix > li");

			for (Element object : headMenu) {
				if (object.select("a").first() != null) {
					Element getMenuTitle = object.select("a").first();
					faculty.addLink(getMenuTitle.attr("href"));
				}
			}

			Elements infoContent = document.select("div.infoCntC > div.padB20");

			// Table content
			for (Element info : infoContent) {
				FacultyCourse course = new FacultyCourse();

				// Course name
				if (info.select("h5").first() != null) {
					String getCourseName = info.select("h5").first().text().trim();
					course.setCourseName(getCourseName);
				}

				// Course description
				if (info.select("p").first() != null) {
					String getCourseDescription = info.select("p").first().text().trim();
					course.setCourseDescription(getCourseDescription);
				}

				// Work permitted TODO doesn't work
				if (info.select("p").first().nextElementSibling().nextElementSibling() != null) {
					String getWorkPermitted = info.select("p").first().nextElementSibling().nextElementSibling().text()
							.trim();
					course.setCourseWorkPermitted(getWorkPermitted);
				}

				// Admission info
				Elements objects = info.select("table > tbody > tr");
				String getRegister = "";
				String getContent = "";
				for (Element object : objects) {

					if (object.select("th").first() != null && object.select("td").first() != null) {
						getRegister = object.select("th").first().text().trim();
						getContent = object.select("td").first().text().trim();
						course.addCourseAdmissionInfo(getRegister, getContent);
						getRegister = "";
						getContent = "";
					}
				}
				faculty.getFacultyTechInfo().addCourse(course);

			}
		} catch (Exception e) {
			faculty.setSearch(false);
			System.out.println(e);
		}
		return gson.toJson(faculty);
	}

	/**
	 * Crawl the information related to the tech faculty support returning the
	 * info in json format
	 * 
	 * @author jdecastroc
	 * 
	 * @param techSchoolParent
	 *            -> id of the faculty tech School
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getFacultySupport(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String getFacultySupport(String techSchoolParent, String id) {

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		Faculty faculty = new Faculty(techSchoolParent, id, "techSupport");

		System.out.println("Voy a -> " + "http://www.jpss.jp/en/tech/" + faculty.getParent() + "/support/");

		try {
			Document document = Jsoup.connect("http://www.jpss.jp/en/tech/" + faculty.getParent() + "/support/")
					.userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0).get();

			Elements headMenu = document.select("div#DepMenu > ul.clearFix > li");

			for (Element object : headMenu) {
				if (object.select("a").first() != null) {
					Element getMenuTitle = object.select("a").first();
					faculty.addLink(getMenuTitle.attr("href"));
				}
			}

			Elements infoContent = document.select("div#DepBody > div.techSport > div.sportCnt > div.depObjFull");
			String getRegister = "";
			String getContent = "";
			// Table content
			for (Element info : infoContent) {
				if (info.select("h5").first() != null && info.select("p").first() != null) {
					getRegister = info.select("h5").first().text().trim();
					getContent = info.select("p").first().text().trim();
					faculty.getFacultyTechSupport().addObjectInfo(getRegister, getContent);
					getRegister = "";
					getContent = "";
				}
			}
		} catch (Exception e) {
			faculty.setSearch(false);
			System.out.println(e);
		}
		return gson.toJson(faculty);
	}

	@Override
	public String getFacultyFacilities(String parent, String id){
		// Not implemented yet in the crawled page
		return null;
	}

	@Override
	public String getFacultyAccess(String parent, String id){
		// Not implemented yet in the crawled page
		return null;
	}
}