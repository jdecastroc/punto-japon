/**
 * Language School Crawler
 * @author jdecastroc
 * @version 1.0, 13 Apr 2016
 *
 */

package com.puntojapon.languageSchools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.*;

public class SchoolCrawler {

	public String getSchoolInfo(String idSchool) throws Exception {

		// Create the School json structure
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		SchoolInfo languageSchool = new SchoolInfo();

		String url = "http://www.nisshinkyo.org/search/college.php?lng=2&id=" + idSchool;
		System.out.println("Voy a -> " + url);

		// Crawler
		// TODO Change userAgent when application finished
		Document document = Jsoup.connect(url).userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0)
				.get();

		// Removing span HTML tags
		document.select("span.bold").remove();
		document.select("span.blue").remove();

		Elements text = document.select("div#mainPad");

		for (Element element : text) {
			// English title
			if (element.select("h2.collegeTitle") != null) {
				languageSchool.setName(element.select("h2.collegeTitle").first().text().trim());
			}

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
			if (element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr > td > span.lsp10") != null) {
				languageSchool.setAddress(
						element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr > td > span.lsp10")
								.first().text().trim());
			}

			// Phone and How to arrive
			if (element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr").first().nextElementSibling()
					.select("td").first().nextElementSibling() != null) {

				languageSchool.setPhone(element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr")
						.first().nextElementSibling().select("td").first().nextElementSibling().text().trim());

				languageSchool
						.setHowToArrive(element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr")
								.first().nextElementSibling().select("td").first().nextElementSibling()
								.nextElementSibling().text().trim());
			}

			// Fax

			if (element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr").first().nextElementSibling()
					.nextElementSibling().select("td").first().nextElementSibling() != null) {

				languageSchool.setFax(element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr")
						.first().nextElementSibling().nextElementSibling().select("td").first().nextElementSibling()
						.text().trim());

			}

			// URL
			if (element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr").first().nextElementSibling()
					.nextElementSibling().nextElementSibling().select("td").first().nextElementSibling() != null) {

				languageSchool
						.setOfficialUrl(element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr")
								.first().nextElementSibling().nextElementSibling().nextElementSibling().select("td")
								.first().nextElementSibling().select("a").text().trim());
			}

			// Email
			if (element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr").first().nextElementSibling()
					.nextElementSibling().nextElementSibling().nextElementSibling().select("td").first()
					.nextElementSibling() != null) {

				languageSchool.setEmail(element.select("table.tableStyle04 > tbody > tr > td > table > tbody > tr")
						.first().nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling()
						.select("td").first().nextElementSibling().text().trim());

			}

			// Second table info

			// Type of establishment
			if (element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
					.select("td > table > tbody > tr").first() != null) {

				languageSchool.setEmail(element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
						.select("td > table > tbody > tr").first().select("td").first().nextElementSibling().text()
						.trim());

			}

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

				languageSchool
						.setStartingDate(element.select("table.tableStyle04 > tbody > tr").first().nextElementSibling()
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
					if (regtd.text().contains(" ")) {
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

			// N1 results
			if ((resultsExamintationTable.select("tr").get(1).select("td.center").get(0) != null)
					&& (resultsExamintationTable.select("tr").get(2).select("td.center").get(0) != null)) {

				languageSchool.getN1Stadistics()
						.put(Integer.parseInt(
								resultsExamintationTable.select("tr").get(1).select("td.center").get(0).text().trim()),
						Integer.parseInt(
								resultsExamintationTable.select("tr").get(2).select("td.center").get(0).text().trim()));

			}

			// N2 results
			if ((resultsExamintationTable.select("tr").get(1).select("td.center").get(1) != null)
					&& (resultsExamintationTable.select("tr").get(2).select("td.center").get(1) != null)) {

				languageSchool.getN2Stadistics()
						.put(Integer.parseInt(
								resultsExamintationTable.select("tr").get(1).select("td.center").get(1).text().trim()),
						Integer.parseInt(
								resultsExamintationTable.select("tr").get(2).select("td.center").get(1).text().trim()));

			}

			// N3 results
			if ((resultsExamintationTable.select("tr").get(1).select("td.center").get(2) != null)
					&& (resultsExamintationTable.select("tr").get(2).select("td.center").get(2) != null)) {

				languageSchool.getN3Stadistics()
						.put(Integer.parseInt(
								resultsExamintationTable.select("tr").get(1).select("td.center").get(2).text().trim()),
						Integer.parseInt(
								resultsExamintationTable.select("tr").get(2).select("td.center").get(2).text().trim()));

			}

			// N4 results
			if ((resultsExamintationTable.select("tr").get(1).select("td.center").get(3) != null)
					&& (resultsExamintationTable.select("tr").get(2).select("td.center").get(3) != null)) {

				languageSchool.getN4Stadistics()
						.put(Integer.parseInt(
								resultsExamintationTable.select("tr").get(1).select("td.center").get(3).text().trim()),
						Integer.parseInt(
								resultsExamintationTable.select("tr").get(2).select("td.center").get(3).text().trim()));

			}

			// N5 results
			if ((resultsExamintationTable.select("tr").get(1).select("td.center").get(4) != null)
					&& (resultsExamintationTable.select("tr").get(2).select("td.center").get(4) != null)) {

				languageSchool.getN5Stadistics()
						.put(Integer.parseInt(
								resultsExamintationTable.select("tr").get(1).select("td.center").get(4).text().trim()),
						Integer.parseInt(
								resultsExamintationTable.select("tr").get(2).select("td.center").get(4).text().trim()));

			}

			// Students Destination
			Element destinationTable = document.select("table").get(8);

			// Graduate School destination
			if ((destinationTable.select("tr").get(0).select("th.center").get(0) != null)
					&& (destinationTable.select("tr").get(1).select("td.center").get(0) != null)) {

				languageSchool.getStudentsDestination().put(
						destinationTable.select("tr").get(0).select("th.center").get(0).text().trim(), Integer.parseInt(
								destinationTable.select("tr").get(1).select("td.center").get(0).text().trim()));
			}

			// University destination
			if ((destinationTable.select("tr").get(0).select("th.center").get(1) != null)
					&& (destinationTable.select("tr").get(1).select("td.center").get(1) != null)) {

				languageSchool.getStudentsDestination().put(
						destinationTable.select("tr").get(0).select("th.center").get(1).text().trim(), Integer.parseInt(
								destinationTable.select("tr").get(1).select("td.center").get(1).text().trim()));
			}

			// Junior College destination
			if ((destinationTable.select("tr").get(0).select("th.center").get(2) != null)
					&& (destinationTable.select("tr").get(1).select("td.center").get(2) != null)) {

				languageSchool.getStudentsDestination().put(
						destinationTable.select("tr").get(0).select("th.center").get(2).text().trim(), Integer.parseInt(
								destinationTable.select("tr").get(1).select("td.center").get(2).text().trim()));
			}

			// Technical School destination
			if ((destinationTable.select("tr").get(0).select("th.center").get(3) != null)
					&& (destinationTable.select("tr").get(1).select("td.center").get(3) != null)) {

				languageSchool.getStudentsDestination().put(
						destinationTable.select("tr").get(0).select("th.center").get(3).text().trim(), Integer.parseInt(
								destinationTable.select("tr").get(1).select("td.center").get(3).text().trim()));
			}

			// Special Training School destination
			if ((destinationTable.select("tr").get(0).select("th.center").get(4) != null)
					&& (destinationTable.select("tr").get(1).select("td.center").get(4) != null)) {

				languageSchool.getStudentsDestination().put(
						destinationTable.select("tr").get(0).select("th.center").get(4).text().trim(), Integer.parseInt(
								destinationTable.select("tr").get(1).select("td.center").get(4).text().trim()));
			}

			// Training School destination
			if ((destinationTable.select("tr").get(0).select("th.center").get(5) != null)
					&& (destinationTable.select("tr").get(1).select("td.center").get(5) != null)) {

				languageSchool.getStudentsDestination().put(
						destinationTable.select("tr").get(0).select("th.center").get(5).text().trim(), Integer.parseInt(
								destinationTable.select("tr").get(1).select("td.center").get(5).text().trim()));
			}

			// Other destination
			if ((destinationTable.select("tr").get(0).select("th.center").get(6) != null)
					&& (destinationTable.select("tr").get(1).select("td.center").get(6) != null)) {

				languageSchool.getStudentsDestination().put(
						destinationTable.select("tr").get(0).select("th.center").get(6).text().trim(), Integer.parseInt(
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
			languageSchool.setMapUrl("https://www.google.com/maps/search/" + languageSchool.getName());
			
		}
		return gson.toJson(languageSchool);
	}

}
