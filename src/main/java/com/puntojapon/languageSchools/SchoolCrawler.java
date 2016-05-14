/**
 * Language School Crawler
 * @author jdecastroc
 * @version 1.0, 20 Apr 2016
 *
 */

package com.puntojapon.languageSchools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.*;
import com.puntojapon.common.RandomUserAgent;

/**
 * SchoolCrawler crawls the nisshinkyo webpage getting the information related to the Japanese language schools in Japan
 * 
 * @author jdecastroc
 *
 */
public class SchoolCrawler {

	/**
	 * getSchoolList crawl a list of Japanese language schools page based on the user prefecture choice
	 * 
	 * @param englishArea -> Prefecture where to search the language schools
	 * @return json file with the list of the Japanese language schools
	 * @throws Exception
	 */
	public String getSchoolList(String englishArea) throws Exception {

		// Create the School json structure
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		SchoolList schoolList = new SchoolList(englishArea);

		// Area translator english -> Japanese for the url
		String area = JapanAreas.areaTranslator(englishArea.trim().toLowerCase());

		// Area type to determine how many prefectures are going to be crawled
		String areaType = "area";
		if (englishArea.equals("1") || englishArea.equals("2") || englishArea.equals("4") || englishArea.equals("5")
				|| englishArea.equals("6") || englishArea.equals("7")) {

			area = englishArea;
			areaType = "area_cd";
			switch (area) {
			case "1":
				schoolList.setSchoolArea("Hokkaido, Tohoku");
				break;
			case "2":
				schoolList.setSchoolArea("Kanto, Koshin-Etsu");
				break;
			case "4":
				schoolList.setSchoolArea("Tokai, Hokuriku");
				break;
			case "5":
				schoolList.setSchoolArea("Kinki");
				break;
			case "6":
				schoolList.setSchoolArea("Chugoku, Shikoku");
				break;
			case "7":
				schoolList.setSchoolArea("Kyusyu, Okinawa");
				break;
			}
		}

		// Area type
		String url = "http://www.nisshinkyo.org/search/area.php?lng=2&" + areaType + "=" + area + "#terms";

		System.out.println("Voy a -> " + url);

		// Language School List crawler
		// TODO Change userAgent when application finished
		Document document = Jsoup.connect(url).userAgent(RandomUserAgent.getRandomUserAgent()).timeout((int)Math.random() * 5)
				.get();

		Elements text = document.select("div#mainBox > table.termsDetail > tbody > tr");

		for (Element element : text) {

			if (!element.select("th").hasClass("title") && element.select("th") != null) {

				// Set school id, name and address
				schoolList.addSchool(element.select("th > a").attr("href").trim().replace("college.php?lng=2&id=", ""),
						element.select("th > a").text().trim(), element.select("td").text().trim());

			}
		}

		return gson.toJson(schoolList);
	}

	/**
	 * getSchoolInfo crawl a Japanese language school page based on id of the Japanese school
	 * 
	 * @param idSchool -> id of the school to which information is going to be crawled and retrieved
	 * @return the information of the Japanese language school in a json format
	 * @throws Exception
	 */
	public String getSchoolInfo(String idSchool) throws Exception {

		// Create the School json structure
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		SchoolInfo languageSchool = new SchoolInfo();

		String url = "http://www.nisshinkyo.org/search/college.php?lng=2&id=" + idSchool;
		System.out.println("Voy a -> " + url);

		// Crawler
		// TODO Change userAgent when application finished
		Document document = Jsoup.connect(url).userAgent(RandomUserAgent.getRandomUserAgent()).timeout((int)Math.random() * 5)
				.get();

		// Removing span HTML tags
		document.select("span.bold").remove();
		document.select("span.blue").remove();

		Elements text = document.select("div#mainPad");

		for (Element element : text) {
			// English title
			// /!\ The college title crawled is a Japanese space. Take care
			// dealing with it.
			if (element.select("h2.collegeTitle") != null
					&& !element.select("h2.collegeTitle").first().text().trim().equals("　")) {
				languageSchool.setName(element.select("h2.collegeTitle").first().text().trim());
			}

			if (languageSchool.isSearch()) {

				// Japanese title
				if (element.select("p.bsp10") != null) {
					languageSchool.setJapaneseName(element.select("p.bsp10").first().text().trim());
				}

				// TODO Don't know why is the number (ID)
				if (element.select("div.floatBox > div.floL > p.bsp10") != null) {
					languageSchool.setId(element.select("div.floatBox > div.floL > p.bsp10").first().text().trim());
				}

				// First table info

				// Address
				if (element.select(
						"table.tableStyle04 > tbody > tr > td > table > tbody > tr > td > span.lsp10") != null) {
					languageSchool.setAddress(element
							.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr > td > span.lsp10")
							.first().text().trim());
				}

				// Phone and How to arrive
				if (element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr").first()
						.nextElementSibling().select("td").first().nextElementSibling() != null) {

					languageSchool.setPhone(element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr")
							.first().nextElementSibling().select("td").first().nextElementSibling().text().trim());

					languageSchool
							.setHowToArrive(element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr")
									.first().nextElementSibling().select("td").first().nextElementSibling()
									.nextElementSibling().text().trim());
				}

				// Fax

				if (element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr").first()
						.nextElementSibling().nextElementSibling().select("td").first().nextElementSibling() != null) {

					languageSchool.setFax(element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr")
							.first().nextElementSibling().nextElementSibling().select("td").first().nextElementSibling()
							.text().trim());

				}

				// URL
				if (element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr").first()
						.nextElementSibling().nextElementSibling().nextElementSibling().select("td").first()
						.nextElementSibling() != null) {

					languageSchool
							.setOfficialUrl(element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr")
									.first().nextElementSibling().nextElementSibling().nextElementSibling().select("td")
									.first().nextElementSibling().select("a").text().trim());
				}

				// Email
				if (element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr").first()
						.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling()
						.select("td").first().nextElementSibling() != null) {

					languageSchool.setEmail(element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr")
							.first().nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling()
							.select("td").first().nextElementSibling().text().trim());

				}

				// Second table info
				// Type of establishment
				if (element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
						.select("td > table > tbody > tr").first() != null) {

					languageSchool.setEstablishmentType(element.select("table.tableStyle04 > tbody > tr").first()
							.nextElementSibling().select("td > table > tbody > tr").first().select("td").first()
							.nextElementSibling().text().trim());

				}

				// Representative name
				if (element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
						.select("td > table > tbody > tr").first().nextElementSibling() != null) {

					languageSchool.setRepresentativeName(element.select("table.tableStyle04 > tbody > tr").first()
							.nextElementSibling().select("td > table > tbody > tr").first().nextElementSibling()
							.select("td").first().nextElementSibling().text().trim());

				}

				// Principal name
				if (element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
						.select("td > table > tbody > tr").first().nextElementSibling().nextElementSibling() != null) {

					languageSchool.setPrincipalName(element.select("table.tableStyle04 > tbody > tr").first()
							.nextElementSibling().select("td > table > tbody > tr").first().nextElementSibling()
							.nextElementSibling().select("td").first().nextElementSibling().text().trim());

				}

				// Starting date
				if (element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
						.select("td > table > tbody > tr").first().nextElementSibling().nextElementSibling()
						.nextElementSibling() != null) {

					languageSchool.setStartingDate(
							element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
									.select("td > table > tbody > tr").first().nextElementSibling().nextElementSibling()
									.nextElementSibling().select("td").first().nextElementSibling().text().trim());

				}

				// Term of validity
				if (element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
						.select("td > table > tbody > tr").first().nextElementSibling().nextElementSibling()
						.nextElementSibling().nextElementSibling() != null) {

					languageSchool.setValidityTerm(element.select("table.tableStyle04 > tbody > tr").first()
							.nextElementSibling().select("td > table > tbody > tr").first().nextElementSibling()
							.nextElementSibling().nextElementSibling().nextElementSibling().select("td").first()
							.nextElementSibling().text().trim());

				}

				// Number of teachers
				if (element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
						.select("td > table > tbody > tr").first().nextElementSibling().nextElementSibling()
						.nextElementSibling().nextElementSibling().nextElementSibling() != null) {

					languageSchool.setTeachersNumber(element.select("table.tableStyle04 > tbody > tr").first()
							.nextElementSibling().select("td > table > tbody > tr").first().nextElementSibling()
							.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling()
							.select("td").first().nextElementSibling().text().trim());

				}

				// Capacity
				if (element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
						.select("td > table > tbody > tr").first().nextElementSibling().nextElementSibling()
						.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling() != null) {

					languageSchool.setCapacity(element.select("table.tableStyle04 > tbody > tr").first()
							.nextElementSibling().select("td > table > tbody > tr").first().nextElementSibling()
							.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling()
							.nextElementSibling().select("td").first().nextElementSibling().text().trim());

				}

				// Accommodations
				if (element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
						.select("td > table > tbody > tr").first().nextElementSibling().nextElementSibling()
						.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling()
						.nextElementSibling() != null) {

					languageSchool.setAccommodations(
							element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
									.select("td > table > tbody > tr").first().nextElementSibling().nextElementSibling()
									.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling()
									.nextElementSibling().select("td").first().nextElementSibling().text().trim());

				}

				// School status
				if (element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
						.select("td > table > tbody > tr").first().nextElementSibling().nextElementSibling()
						.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling()
						.nextElementSibling().nextElementSibling() != null) {

					languageSchool.setSchoolStatus(element.select("table.tableStyle04 > tbody > tr").first()
							.nextElementSibling().select("td > table > tbody > tr").first().nextElementSibling()
							.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling()
							.nextElementSibling().nextElementSibling().nextElementSibling().select("td").first()
							.nextElementSibling().text().trim());

				}

				// Third table

				// Admission requirements
				if (element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling().nextElementSibling()
						.select("td > table > tbody > tr").first() != null) {

					languageSchool.setAdmissionRequirements(element.select("table.tableStyle04 > tbody > tr").first()
							.nextElementSibling().nextElementSibling().select("td > table > tbody > tr").first()
							.select("td").first().nextElementSibling().text().trim());

				}

				// Selection of students
				if (element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling().nextElementSibling()
						.select("td > table > tbody > tr").first().nextElementSibling() != null) {

					languageSchool.setSelectionProcess(element.select("table.tableStyle04 > tbody > tr").first()
							.nextElementSibling().nextElementSibling().select("td > table > tbody > tr").first()
							.nextElementSibling().select("td").first().nextElementSibling().text().trim());

				}

				// Number of registered students
				Element registeredStudentstable = document.select("table").get(4);
				Elements registeredStudents = registeredStudentstable.select("tr");
				String regText = "";

				for (Element register : registeredStudents) {
					Elements regtdList = register.select("td");
					for (Element regtd : regtdList) {
						if (regtd.text().contains(" ") && languageSchool.isSearch()) {
							regText = regtd.text().trim();
							String[] regParts = regText.split(" ");

							// TODO Improve names split fix for those countries
							if (!regParts[0].equals("") && !regParts[1].equals("")) {
								if (regParts[0].equals("Sri")) {
									regParts[0] = "Sri Lanka";
									regParts[1] = regParts[2];
								}
								if (regParts[0].equals("Saudi")) {
									regParts[0] = ("Saudi Arabia");
									regParts[1] = regParts[2];
								}
								languageSchool.getRegisteredStudents().put(regParts[0], Integer.parseInt(regParts[1]));
							}
						}
					}
				}

				// Courses info
				Element table = document.select("table").get(5);
				Elements courses = table.select("tr");

				for (Element courseElement : courses) {

					CourseInfo course = new CourseInfo();

					if (courseElement.select("th").first() == null) {

						// Course name
						if (courseElement.select("td").first() != null) {

							course.setCourse(courseElement.select("td").first().text().trim());
						}

						// Course purpose
						if (courseElement.select("td").get(1) != null) {

							course.setPurpose(courseElement.select("td").get(1).text().trim());
						}

						// Length of course
						if (courseElement.select("td").get(2) != null) {

							course.setLength(courseElement.select("td").get(2).text().trim());
						}

						// Class hours
						if (courseElement.select("td").get(3) != null) {

							course.setClassHours(courseElement.select("td").get(3).text().trim());
						}

						// Weeks
						if (courseElement.select("td").get(4) != null) {

							course.setWeeks(courseElement.select("td").get(4).text().trim());
						}

						// Month admission
						if (courseElement.select("td").get(5) != null) {

							course.setMonthAdmission(courseElement.select("td").get(5).text().trim());
						}

						// Selection fee
						if (courseElement.select("td").get(6) != null) {

							course.setSelectionFee(courseElement.select("td").get(6).text().trim());
						}

						// Admission fee
						if (courseElement.select("td").get(7) != null) {

							course.setAdmissionFee(courseElement.select("td").get(7).text().trim());
						}

						// Tuition fee
						if (courseElement.select("td").get(8) != null) {

							course.setTuitionFee(courseElement.select("td").get(8).text().trim());
						}

						// Others fee
						if (courseElement.select("td").get(9) != null) {

							course.setOthersFee(courseElement.select("td").get(9).text().trim());
						}

						// Total fee
						if (courseElement.select("td").get(10) != null) {

							course.setTotalFee(courseElement.select("td").get(10).text().trim());
						}

						// Pushing courses
						if (!course.getCourse().equals("")) {
							languageSchool.addCourse(course);
						}
					}
				}

				// Others Courses
				if (element.select("table").get(5).select("tr").get(11).select("td").first() != null) {

					languageSchool.setOtherCourses(
							element.select("table").get(5).select("tr").get(11).select("td").first().text().trim());
				}

				// Results of exams
				Element resultsExamintationTable = document.select("table").get(6);
				int examinees, certified;

				// N1 results
				if ((resultsExamintationTable.select("tr").get(1).select("td.center").get(0) != null)
						&& (resultsExamintationTable.select("tr").get(2).select("td.center").get(0) != null)) {
					
					examinees = Integer.parseInt(resultsExamintationTable.select("tr").get(1).select("td.center")
							.get(0).text().trim());
					certified = Integer.parseInt(resultsExamintationTable.select("tr").get(2).select("td.center")
							.get(0).text().trim());
					
					languageSchool.getN1Stadistics().put("Examinees", examinees);
					languageSchool.getN1Stadistics().put("Certified", certified);
					languageSchool.getN1Stadistics().put("Failed", examinees - certified);

				}

				// N2 results
				if ((resultsExamintationTable.select("tr").get(1).select("td.center").get(1) != null)
						&& (resultsExamintationTable.select("tr").get(2).select("td.center").get(1) != null)) {

					examinees = Integer.parseInt(resultsExamintationTable.select("tr").get(1).select("td.center")
							.get(1).text().trim());
					certified = Integer.parseInt(resultsExamintationTable.select("tr").get(2).select("td.center")
							.get(1).text().trim());
					
					languageSchool.getN2Stadistics().put("Examinees", examinees);
					languageSchool.getN2Stadistics().put("Certified", certified);
					languageSchool.getN2Stadistics().put("Failed", examinees - certified);

				}

				// N3 results
				if ((resultsExamintationTable.select("tr").get(1).select("td.center").get(2) != null)
						&& (resultsExamintationTable.select("tr").get(2).select("td.center").get(2) != null)) {

					examinees = Integer.parseInt(resultsExamintationTable.select("tr").get(1).select("td.center")
							.get(2).text().trim());
					certified = Integer.parseInt(resultsExamintationTable.select("tr").get(2).select("td.center")
							.get(2).text().trim());
					
					languageSchool.getN3Stadistics().put("Examinees", examinees);
					languageSchool.getN3Stadistics().put("Certified", certified);
					languageSchool.getN3Stadistics().put("Failed", examinees - certified);

				}

				// N4 results
				if ((resultsExamintationTable.select("tr").get(1).select("td.center").get(3) != null)
						&& (resultsExamintationTable.select("tr").get(2).select("td.center").get(3) != null)) {

					examinees = Integer.parseInt(resultsExamintationTable.select("tr").get(1).select("td.center")
							.get(3).text().trim());
					certified = Integer.parseInt(resultsExamintationTable.select("tr").get(2).select("td.center")
							.get(3).text().trim());
					
					languageSchool.getN4Stadistics().put("Examinees", examinees);
					languageSchool.getN4Stadistics().put("Certified", certified);
					languageSchool.getN4Stadistics().put("Failed", examinees - certified);

				}

				// N5 results
				if ((resultsExamintationTable.select("tr").get(1).select("td.center").get(4) != null)
						&& (resultsExamintationTable.select("tr").get(2).select("td.center").get(4) != null)) {

					examinees = Integer.parseInt(resultsExamintationTable.select("tr").get(1).select("td.center")
							.get(4).text().trim());
					certified = Integer.parseInt(resultsExamintationTable.select("tr").get(2).select("td.center")
							.get(4).text().trim());
					
					languageSchool.getN5Stadistics().put("Examinees", examinees);
					languageSchool.getN5Stadistics().put("Certified", certified);
					languageSchool.getN5Stadistics().put("Failed", examinees - certified);

				}

				// Students Destination
				Element destinationTable = document.select("table").get(8);

				// Graduate School destination
				if ((destinationTable.select("tr").get(0).select("th.center").get(0) != null)
						&& (destinationTable.select("tr").get(1).select("td.center").get(0) != null)) {

					languageSchool.getStudentsDestination().put(
							destinationTable.select("tr").get(0).select("th.center").get(0).text().trim(),
							Integer.parseInt(
									destinationTable.select("tr").get(1).select("td.center").get(0).text().trim()));
				}

				// University destination
				if ((destinationTable.select("tr").get(0).select("th.center").get(1) != null)
						&& (destinationTable.select("tr").get(1).select("td.center").get(1) != null)) {

					languageSchool.getStudentsDestination().put(
							destinationTable.select("tr").get(0).select("th.center").get(1).text().trim(),
							Integer.parseInt(
									destinationTable.select("tr").get(1).select("td.center").get(1).text().trim()));
				}

				// Junior College destination
				if ((destinationTable.select("tr").get(0).select("th.center").get(2) != null)
						&& (destinationTable.select("tr").get(1).select("td.center").get(2) != null)) {

					languageSchool.getStudentsDestination().put(
							destinationTable.select("tr").get(0).select("th.center").get(2).text().trim(),
							Integer.parseInt(
									destinationTable.select("tr").get(1).select("td.center").get(2).text().trim()));
				}

				// Technical School destination
				if ((destinationTable.select("tr").get(0).select("th.center").get(3) != null)
						&& (destinationTable.select("tr").get(1).select("td.center").get(3) != null)) {

					languageSchool.getStudentsDestination().put(
							destinationTable.select("tr").get(0).select("th.center").get(3).text().trim(),
							Integer.parseInt(
									destinationTable.select("tr").get(1).select("td.center").get(3).text().trim()));
				}

				// Special Training School destination
				if ((destinationTable.select("tr").get(0).select("th.center").get(4) != null)
						&& (destinationTable.select("tr").get(1).select("td.center").get(4) != null)) {

					languageSchool.getStudentsDestination().put(
							destinationTable.select("tr").get(0).select("th.center").get(4).text().trim(),
							Integer.parseInt(
									destinationTable.select("tr").get(1).select("td.center").get(4).text().trim()));
				}

				// Training School destination
				if ((destinationTable.select("tr").get(0).select("th.center").get(5) != null)
						&& (destinationTable.select("tr").get(1).select("td.center").get(5) != null)) {

					languageSchool.getStudentsDestination().put(
							destinationTable.select("tr").get(0).select("th.center").get(5).text().trim(),
							Integer.parseInt(
									destinationTable.select("tr").get(1).select("td.center").get(5).text().trim()));
				}

				// Other destination
				if ((destinationTable.select("tr").get(0).select("th.center").get(6) != null)
						&& (destinationTable.select("tr").get(1).select("td.center").get(6) != null)) {

					languageSchool.getStudentsDestination().put(
							destinationTable.select("tr").get(0).select("th.center").get(6).text().trim(),
							Integer.parseInt(
									destinationTable.select("tr").get(1).select("td.center").get(6).text().trim()));
				}

				// School features
				Elements featuresTable = element.select("table").get(9).select("tr");

				for (Element features : featuresTable) {
					languageSchool.getSchoolFeatures().put(
							Integer.parseInt(features.select("th.center").first().text().trim()),
							features.select("td").first().text().trim());
				}

				// School map base on google maps API query
				languageSchool.setMapUrl("https://www.google.com/maps/search/" + languageSchool.getJapaneseName());

			}

		}

		return gson.toJson(languageSchool);
	}

}
