/**
 * GradSchool Crawler
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

/**
 * Provides all the crawlers related with the gradSchool searchs and also
 * gradSchools faculties search
 * 
 * @author jdecastroc
 * 
 * @see com.puntojapon.colleges.CollegeCrawler
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
	 * @param GradSchoolsList
	 *            ->Specifies the college list which is gonna be used in the
	 *            recursive function
	 * @param jsonGradSchoolsList
	 *            ->Final string which is going to be converted to json format
	 *            to retrieve the crawled data
	 * @param counter
	 *            ->College counter for search details
	 * 
	 * @return the json string with the gradSchools crawled
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getCollegeList(java.lang.String,
	 *      java.lang.String[], com.puntojapon.colleges.CollegeList,
	 *      java.lang.String, int)
	 */

	@Override
	public String getCollegeList(String url, String[] prefectureSearchName, CollegeList gradSchoolsList,
			String jsongradSchoolsList, int counter) throws Exception {

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		boolean next = true; // Used to exit recursion
		System.out.println("Estoy -> " + url);

		// Main info of each gradSchool
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

	/**
	 * Crawl the basic information of a gradSchool returning the info in json
	 * format
	 * 
	 * @author jdecastroc
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @return the string object with the information of the college
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getCollege(java.lang.String)
	 */
	@Override
	public String getCollege(String id) {

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		GradSchool gradSchool = new GradSchool();
		try {
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
		} catch (Exception e) {
			gradSchool.setSearch(false);
			System.out.println(e);
		}
		return gson.toJson(gradSchool);
	}

	/**
	 * Crawl the information related to the faculty admissions returning the
	 * info in json format
	 * 
	 * @author jdecastroc
	 * 
	 * @param gradSchoolParent
	 *            -> id of the gradSchool faculty
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @return the json string with the faculty admissions information
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getFacultyAdmissions(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String getFacultyAdmissions(String gradSchoolParent, String id) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		Faculty faculty = new Faculty(gradSchoolParent, id, "admissions");

		try {
			System.out.println(
					"Voy a -> " + "http://www.jpss.jp/en/grad/" + faculty.getParent() + "/" + faculty.getId() + "/");

			Document document = Jsoup
					.connect("http://www.jpss.jp/en/grad/" + faculty.getParent() + "/" + faculty.getId() + "/")
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
		} catch (Exception e) {
			faculty.setSearch(false);
			System.out.println(e);
		}
		return gson.toJson(faculty);
	}

	/**
	 * Crawl the information related to the faculty info returning the info in
	 * json format
	 * 
	 * @author jdecastroc
	 * 
	 * @param gradSchoolParent
	 *            -> id of the gradSchool faculty
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @return the json string with the faculty info
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getFacultyInfo(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String getFacultyInfo(String gradSchoolParent, String id) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		Faculty faculty = new Faculty(gradSchoolParent, id, "info");

		try {
			System.out.println("Voy a -> " + "http://www.jpss.jp/en/grad/" + faculty.getParent() + "/" + faculty.getId()
					+ "/info/");
			Document document = Jsoup
					.connect("http://www.jpss.jp/en/grad/" + faculty.getParent() + "/" + faculty.getId() + "/info/")
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
				if (element.select("div#DepBody > div.leftBlock > div.InfoUnivCnt > div.depObj").first() != null) {// TODO
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
				if (element
						.select("div#DepBody2 > div.infoCntB > div.defaultTitle > div.verticalBorder > h4") != null) {
					String year = "";
					Element getRegStudentsTitle = element
							.select("div#DepBody2 > div.infoCntB > div.defaultTitle > div.verticalBorder > h4").first();

					if (element.select(
							"div#DepBody2 > div.infoCntB > div.defaultTitle > div.verticalBorder > h5") != null) {
						Element getRegStudentsYear = element
								.select("div#DepBody2 > div.infoCntB > div.defaultTitle > div.verticalBorder > h5")
								.first();
						year = getRegStudentsYear.text().trim();
					}

					faculty.getFacultyInfo().setRegStudentsTitle(getRegStudentsTitle.text().trim() + " " + year);
				}

				if (element.select("div#DepBody2 > div.infoCntB > div.infoCntBIn > div.whiteBase") != null) {
					Elements rows = element
							.select("div#DepBody2 > div.infoCntB > div.infoCntBIn > div.whiteBase > div");
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
		} catch (Exception e) {
			faculty.setSearch(false);
			System.out.println(e);
		}
		return gson.toJson(faculty);
	}

	/**
	 * Crawl the information related to the faculty support returning the info
	 * in json format
	 * 
	 * @author jdecastroc
	 * 
	 * @param gradSchoolParent
	 *            -> id of the gradSchool faculty
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @return the json string with the faculty support information
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getFacultySupport(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String getFacultySupport(String gradSchoolParent, String id) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		// Specify which content of a faculty it's going to be crawled
		Faculty faculty = new Faculty(gradSchoolParent, id, "support");

		try {
			System.out.println("Voy a -> " + "http://www.jpss.jp/en/grad/" + faculty.getParent() + "/" + faculty.getId()
					+ "/support/");
			Document document = Jsoup
					.connect("http://www.jpss.jp/en/grad/" + faculty.getParent() + "/" + faculty.getId() + "/support/")
					.userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0).get();

			Elements text = document.select("div#DepCnt");

			faculty.setHeaderContent(document, faculty, text);

			for (Element element : text) {
				// Tab title
				if (element.select("div#DepBody > div.sportCnt > div.leftBlock > div.defaultTitle > h4")
						.first() != null) {
					Element getTitle = element
							.select("div#DepBody > div.sportCnt > div.leftBlock > div.defaultTitle > h4").first();
					faculty.getFacultySupport().setSupportTitle(getTitle.text().trim());
				}

				// Info content
				if (element.select("div#DepBody > div.sportCnt > div.leftBlock > div.depObj").first() != null) {
					String objectRegister = "";
					String objectContent = "";

					Elements objectTable = element.select("div#DepBody > div.sportCnt > div.leftBlock > div.depObj");
					for (Element object : objectTable) {

						if (object.select("div.verticalBorder > h5").first() != null) {
							Element getObjectRegister = object.select("h5").first();
							objectRegister = getObjectRegister.text().trim();
						}
						if (object.select("div.depObjIn > div.grayBase > p").first() != null) {
							Element getObjectContent = object.select("div.depObjIn > div.grayBase > p").first();
							objectContent = getObjectContent.text().trim();
						}
						faculty.getFacultySupport().addObject(objectRegister, objectContent);
					}
				}

				// Images
				if (element.select("div#DepBody > div.sportCnt > div.rightBlock > div") != null) {
					Elements images = element.select("div#DepBody > div.sportCnt > div.rightBlock > div");
					for (Element image : images) {
						if (image.select("img") != null) {
							faculty.getFacultySupport().addImage(image.select("img").attr("src"));
						}
					}
				}

			}
		} catch (Exception e) {
			faculty.setSearch(false);
			System.out.println(e);
		}
		return gson.toJson(faculty);
	}

	/**
	 * Crawl the information related to the faculty facilities returning the
	 * info in json format
	 * 
	 * @author jdecastroc
	 * 
	 * @param gradSchoolParent
	 *            -> id of the gradSchool faculty
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @return the json string with the faculty facilities information
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getFacultyFacilities(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String getFacultyFacilities(String gradSchoolParent, String id) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		// Specify which content of a faculty it's going to be crawled
		Faculty faculty = new Faculty(gradSchoolParent, id, "facilities");
		try {
			System.out.println("Voy a -> " + "http://www.jpss.jp/en/grad/" + faculty.getParent() + "/" + faculty.getId()
					+ "/facilities/");
			Document document = Jsoup
					.connect("http://www.jpss.jp/en/grad/" + faculty.getParent() + "/" + faculty.getId()
							+ "/facilities/")
					.userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0).get();

			Elements text = document.select("div#DepCnt");

			faculty.setHeaderContent(document, faculty, text);

			for (Element element : text) {
				// Tab title
				if (element.select("div#DepBody > div.facilitiesCnt > div.defaultTitle > h4").first() != null) {
					Element getTitle = element.select("div#DepBody > div.facilitiesCnt > div.defaultTitle > h4")
							.first();
					faculty.getFacultyFacilities().setFacilitiestTitle(getTitle.text().trim());
				}

				// Info content
				if (element.select("div#DepBody > div.facilitiesCnt > div.faciliObj").first() != null) {
					String objectRegister = "";
					String objectContent = "";

					Elements objectTable = element.select("div#DepBody > div.facilitiesCnt > div.faciliObj");
					for (Element object : objectTable) {

						if (object.select("div.verticalBorder > h5").first() != null) {
							Element getObjectRegister = object.select("h5").first();
							objectRegister = getObjectRegister.text().trim();
						}
						if (object.select("div.faciliObjIn > div.grayBase > p").first() != null) {
							Element getObjectContent = object.select("div.faciliObjIn > div.grayBase > p").first();
							objectContent = getObjectContent.text().trim();
						}
						faculty.getFacultyFacilities().addObject(objectRegister, objectContent);
					}
				}

				// Images
				if (element.select("div#DepBody > div.facilitiesCnt > div.faciliObjCnt > div.faciliObjCount0")
						.first() != null) {
					Elements images = element
							.select("div#DepBody > div.facilitiesCnt > div.faciliObjCnt > div.faciliObjCount0 > div");
					for (Element image : images) {
						if (image.select("div.imgBlock > img") != null) {
							faculty.getFacultyFacilities().addImage(image.select("img").attr("src"));
						}
					}
				}
			}
		} catch (Exception e) {
			faculty.setSearch(false);
			System.out.println(e);
		}
		return gson.toJson(faculty);
	}

	/**
	 * Crawl the information related to the faculty access returning the info in
	 * json format
	 * 
	 * @author jdecastroc
	 * 
	 * @param gradSchoolParent
	 *            -> id of the gradSchool faculty
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @return the json string with the faculty access information
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getFacultyAccess(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String getFacultyAccess(String gradSchoolParent, String id) {
		String name = "";
		String lng = "";
		String lat = "";
		String address = "";
		String nearbyPlaces = "";
		String otherInfo = "";

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		// Specify which content of a faculty it's going to be crawled
		Faculty faculty = new Faculty(gradSchoolParent, id, "access");
		try {
			System.out.println("Voy a -> " + "http://www.jpss.jp/en/grad/" + faculty.getParent() + "/" + faculty.getId()
					+ "/access/");
			Document document = Jsoup
					.connect("http://www.jpss.jp/en/grad/" + faculty.getParent() + "/" + faculty.getId() + "/access/")
					.userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0).get();

			Elements text = document.select("div#DepCnt");

			faculty.setHeaderContent(document, faculty, text);

			for (Element element : text) {
				// Tab title
				if (element.select("div#DepBody > div.accessCnt > div.defaultTitle > h4").first() != null) {
					Element getTitle = element.select("div#DepBody > div.accessCnt > div.defaultTitle > h4").first();
					faculty.getFacultyAccess().setAccessTitle(getTitle.text().trim());
				}

				// Map
				Elements maps = element.select("div#DepBody > div.accessCnt > div");

				for (Element map : maps) {

					if (map.select("div.mapCnt > div.mapCanvas").first() != null) {
						Element getMap = map.select("div.mapCnt > div.mapCanvas").first();
						name = getMap.attr("data-name");
						lng = getMap.attr("data-lng");
						lat = getMap.attr("data-lat");
					}

					// Address
					if (map.select("div.accessData > div.accessDataTop > "
							+ "div.accessDataTopIn > dl.clearFix > dd.padT10 > span").first() != null) {

						Element getAddress = map.select("div.accessData > div.accessDataTop > "
								+ "div.accessDataTopIn > dl.clearFix > dd.padT10 > span").first();
						address = getAddress.text().trim();
					}

					// Nearby Places
					if (map.select("div.accessData > div.accessDataOther > " + "dl.clearFix > dd.padT10")
							.first() != null) {

						Element getNearbyPlaces = map
								.select("div.accessData > div.accessDataOther > " + "dl.clearFix > dd.padT10").first();
						nearbyPlaces = getNearbyPlaces.text().trim();
					}

					// Other info
					if (map.select("div.accessDataOther > dl.clearFix > dd.padT10").last() != null) {
						Element getOtherInfo = map.select("div.accessDataOther > dl.clearFix > dd.padT10").last();
						otherInfo = getOtherInfo.text().trim();
					}

					if (!name.equals("") && !lng.equals("") && !lat.equals("")) {
						faculty.getFacultyAccess().addMap(name, lng, lat, address, nearbyPlaces, otherInfo);
					}
				}
			}
		} catch (Exception e) {
			faculty.setSearch(false);
			System.out.println(e);
		}
		return gson.toJson(faculty);
	}
}
