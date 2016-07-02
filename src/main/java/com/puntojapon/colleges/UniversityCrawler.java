/**
 * University Crawler
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
import com.puntojapon.common.RandomUserAgent;

/**
 * Provides all the crawlers related with the university searchs and also
 * university faculties search
 * 
 * @author jdecastroc
 * 
 * @see com.puntojapon.colleges.CollegeCrawler
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
	 * @param universitiesList
	 *            ->Specifies the college list which is gonna be used in the
	 *            recursive function
	 * @param jsonUniversitiesList
	 *            ->Final string which is going to be converted to json format
	 *            to retrieve the crawled data
	 * @param counter
	 *            ->College counter for search details
	 * 
	 * @return the json string with the universities crawled
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getCollegeList(java.lang.String,
	 *      java.lang.String[], com.puntojapon.colleges.CollegeList,
	 *      java.lang.String, int)
	 */
	@Override
	public String getCollegeList(String url, String[] prefectureSearchName, CollegeList universitiesList,
			String jsonUniversitiesList, int counter, String remoteIp) throws Exception {

		// Create the College List of Universities
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		boolean next = true; // Used to exit recursion

		for (String p : prefectureSearchName) {
			if (matchStringOnArray(UrlBuilder.PREFECTURES_LIST, p) == false) {
				universitiesList.setSearchState(false);
				jsonUniversitiesList = gson.toJson(universitiesList);
				System.out.println("ERROR: prefectura no encontrada " + p);
				return jsonUniversitiesList;
			} else {
				System.out.println("Crawleando... " + url);
			}
		}

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
		String officialUrl = "";

		String nextPageString = "";

		// Crawler
		Document document = Jsoup.connect(url).userAgent(RandomUserAgent.getRandomUserAgent())
				.timeout((int) Math.random() * 5).get();

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
					description, facultyList, officialUrl);
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
					jsonUniversitiesList, counter, remoteIp);
		}
	}

	/**
	 * Crawl the basic information of a faculty returning the info in json
	 * format
	 * 
	 * @author jdecastroc
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @return the json string with the university information crawled
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getCollege(java.lang.String)
	 */
	@Override
	public String getCollege(String id) {

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		University university = new University();

		university.setId("http://www.jpss.jp/en/univ/" + id + "/");
		try {
			System.out.println("Estoy -> " + university.getId());

			Document document = Jsoup.connect(university.getId()).userAgent(RandomUserAgent.getRandomUserAgent())
					.timeout((int) Math.random() * 5).get();

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
		} catch (Exception e) {
			university.setSearch(false);
			System.out.println(e);
		}
		return gson.toJson(university);
	}

	/**
	 * Crawl the information related to the faculty admissions returning the
	 * info in json format
	 * 
	 * @author jdecastroc
	 * 
	 * @param universityParent
	 *            -> id of the faculty university
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @return the json string with the faculty admissions information
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getFacultyAdmissions(java.lang.
	 *      String, java.lang.String)
	 */
	@Override
	public String getFacultyAdmissions(String universityParent, String id) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		Faculty faculty = new Faculty(universityParent, id, "admissions");
		try {
			System.out.println(
					"Voy a -> " + "http://www.jpss.jp/en/univ/" + faculty.getParent() + "/" + faculty.getId() + "/");
			Document document = Jsoup
					.connect("http://www.jpss.jp/en/univ/" + faculty.getParent() + "/" + faculty.getId() + "/")
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

			// Check whether there is information retrieved
			if (faculty.getFacultyAdmissions().getLastUpdare().equals("")
					&& faculty.getFacultyAdmissions().getRowTable().isEmpty()) {
				faculty.setSearch(false);
			}

		} catch (Exception e) {
			faculty.setSearch(false);
			System.out.println(e);
		}
		return gson.toJson(faculty);
	}

	/**
	 * Crawl the information related to the faculty information returning the
	 * info in json format
	 * 
	 * @author jdecastroc
	 * 
	 * @param universityParent
	 *            -> id of the faculty university
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @return the json string with the faculty basic information
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getFacultyInfo(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String getFacultyInfo(String universityParent, String id) {

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		Faculty faculty = new Faculty(universityParent, id, "info");
		try {
			System.out.println("Voy a -> " + "http://www.jpss.jp/en/univ/" + faculty.getParent() + "/" + faculty.getId()
					+ "/info/");
			Document document = Jsoup
					.connect("http://www.jpss.jp/en/univ/" + faculty.getParent() + "/" + faculty.getId() + "/info/")
					.userAgent(RandomUserAgent.getRandomUserAgent()).timeout((int) Math.random() * 5).get();

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

			if (faculty.getFacultyInfo().getDepartmentList().isEmpty()
					&& faculty.getFacultyInfo().getObjectTable().isEmpty()) {
				faculty.setSearch(false);
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
	 * @param universityParent
	 *            -> id of the faculty university
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @return the json string with the faculty support information
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getFacultySupport(java.lang.
	 *      String, java.lang.String)
	 */
	@Override
	public String getFacultySupport(String universityParent, String id) {

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		// Specify which content of a faculty it's going to be crawled
		Faculty faculty = new Faculty(universityParent, id, "support");
		try {
			System.out.println("Voy a -> " + "http://www.jpss.jp/en/univ/" + faculty.getParent() + "/" + faculty.getId()
					+ "/support/");
			Document document = Jsoup
					.connect("http://www.jpss.jp/en/univ/" + faculty.getParent() + "/" + faculty.getId() + "/support/")
					.userAgent(RandomUserAgent.getRandomUserAgent()).timeout((int) Math.random() * 5).get();

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

			if (faculty.getFacultySupport().getObjectTable().isEmpty()
					&& faculty.getFacultySupport().getSupportTitle().equals("")) {
				faculty.setSearch(false);
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
	 * @param universityParent
	 *            -> id of the faculty university
	 * 
	 * @param id
	 *            -> id of the faculty
	 * 
	 * @return the json string with the faculty facilities information
	 * 
	 * @see com.puntojapon.colleges.CollegeCrawler#getFacultyFacilities(java.lang.
	 *      String, java.lang.String)
	 */
	@Override
	public String getFacultyFacilities(String universityParent, String id) {

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		// Specify which content of a faculty it's going to be crawled
		Faculty faculty = new Faculty(universityParent, id, "facilities");
		try {
			System.out.println("Voy a -> " + "http://www.jpss.jp/en/univ/" + faculty.getParent() + "/" + faculty.getId()
					+ "/facilities/");
			Document document = Jsoup
					.connect("http://www.jpss.jp/en/univ/" + faculty.getParent() + "/" + faculty.getId()
							+ "/facilities/")
					.userAgent(RandomUserAgent.getRandomUserAgent()).timeout((int) Math.random() * 5).get();

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
				if (element.select("div#DepBody > div.facilitiesCnt > div.faciliObjCnt > div.faciliObjCount3")
						.first() != null) {
					Elements images = element
							.select("div#DepBody > div.facilitiesCnt > div.faciliObjCnt > div.faciliObjCount3 > div");
					for (Element image : images) {
						if (image.select("div.imgBlock > img") != null) {
							faculty.getFacultyFacilities().addImage(image.select("img").attr("src"));
						}
					}
				}
			}

			if (faculty.getFacultyFacilities().getObjectTable().isEmpty()
					&& faculty.getFacultyFacilities().getFacilitiesTitle().equals("")) {
				faculty.setSearch(false);
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
	 * @param universityParent
	 *            -> id of the faculty university
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
	public String getFacultyAccess(String universityParent, String id) {

		String name = "";
		String lng = "";
		String lat = "";
		String address = "";
		String nearbyPlaces = "";
		String otherInfo = "";

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		// Specify which content of a faculty it's going to be crawled
		Faculty faculty = new Faculty(universityParent, id, "access");
		try {
			System.out.println("Voy a -> " + "http://www.jpss.jp/en/univ/" + faculty.getParent() + "/" + faculty.getId()
					+ "/access/");
			Document document = Jsoup
					.connect("http://www.jpss.jp/en/univ/" + faculty.getParent() + "/" + faculty.getId() + "/access/")
					.userAgent(RandomUserAgent.getRandomUserAgent()).timeout((int) Math.random() * 5).get();

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
						if (!getOtherInfo.text().trim().equals(nearbyPlaces))
							otherInfo = getOtherInfo.text().trim();
					}

					if (!name.equals("") && !lng.equals("") && !lat.equals("")) {
						faculty.getFacultyAccess().addMap(name, lng, lat, address, nearbyPlaces, otherInfo);
					}
				}
			}

			if (faculty.getFacultyAccess().getMaps().isEmpty()
					&& faculty.getFacultyAccess().getAccessTitle().equals("")) {
				faculty.setSearch(false);
			}

		} catch (Exception e) {
			faculty.setSearch(false);
			System.out.println(e);
		}
		return gson.toJson(faculty);
	}

}
