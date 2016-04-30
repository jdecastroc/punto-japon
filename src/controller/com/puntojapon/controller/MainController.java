/**
 * RESTFul service
 * 
 * @author: Jorge de Castro
 * @version: 24/04/2016/A
 * @see <a href = "https://bitbucket.org/jdecastroc/punto-japon" /> Bitbucket
 *      repository </a>
 */
package com.puntojapon.controller;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.puntojapon.articles.ElasticSearch;
import com.puntojapon.articles.KettleTransformation;
import com.puntojapon.articles.SearchArticles;
import com.puntojapon.colleges.CollegeList;
import com.puntojapon.colleges.GradSchoolCrawler;
import com.puntojapon.colleges.GradSchoolUrlBuilder;
import com.puntojapon.colleges.TechSchoolCrawler;
import com.puntojapon.colleges.TechSchoolUrlBuilder;
import com.puntojapon.colleges.UniversityCrawler;
import com.puntojapon.colleges.UniversityUrlBuilder;
import com.puntojapon.languageSchools.SchoolCrawler;
import com.puntojapon.work.JobsCrawler;

//import eu.bitwalker.useragentutils.UserAgent;

/**
 * MainController works, like the name says, as the main controller of the REST
 * application. It provides to call the different program functions in order to
 * retrieve information provided in a well-formed json
 * 
 * @author jdecastroc
 *
 */
@RestController
public class MainController {

	/**
	 * SearchUni take the information given by the user in the mapped URL and
	 * use it to retrieve a list of Japanese universities
	 * 
	 * @param prefecture
	 *            -> list of prefectures provided by the user
	 * @param typeStudiesList
	 *            -> list of type of studies provided by the user to search the
	 *            universities that match
	 * @param nameUni
	 *            -> name of the universities to search
	 * @param typeUni
	 *            -> type of universities to search
	 * @param admisionMonth
	 *            -> admission month of the universities to search
	 * @param deadLine
	 *            -> dead line to give the admission documents of the
	 *            universities to search
	 * @param eju
	 *            -> specifies whether or not is necessary to take the Japanese
	 *            exam to enter the universities to search
	 * @param engExam
	 *            -> specifies whether or not is necessary to take the English
	 *            exam to enter the universities to search
	 * @param admisionUni
	 *            -> specifies the admission procedure of the universities to
	 *            search
	 * @return all the information related to the universities which matched
	 *         with the different parameters
	 * @throws Exception
	 */
	@RequestMapping(value = "/universidades/{prefecture}/{typeStudies}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String searchUni(@PathVariable("prefecture") String[] prefecture,
			@PathVariable("typeStudies") String[] typeStudiesList, @RequestParam(value = "nameUni") String nameUni,
			@RequestParam(value = "typeUni") String[] typeUni,
			@RequestParam(value = "admisionMonth") String[] admisionMonth,
			@RequestParam(value = "deadLine") String[] deadLine, @RequestParam(value = "eju") String[] eju,
			@RequestParam(value = "engExam") String[] engExam,
			@RequestParam(value = "admisionUni") String[] admisionUni) throws Exception {

		String universityList = "";
		String url = "";
		CollegeList universitiesList = new CollegeList(
				String.join(" ", prefecture) + " " + String.join(" ", typeStudiesList) + String.join(" ", typeUni));
		String returnJson = "";
		int counter = 0;

		UniversityUrlBuilder search = new UniversityUrlBuilder(nameUni, prefecture, typeStudiesList, typeUni,
				admisionMonth, deadLine, eju, engExam, admisionUni);
		System.out.println("Url final BUSQUEDA = " + search.getSearchUrl(prefecture));
		url = search.getSearchUrl(prefecture);

		UniversityCrawler crawler = new UniversityCrawler();
		universityList = crawler.getCollegeList(url, prefecture, universitiesList, returnJson, counter);

		return universityList;
	}

	/**
	 * SearchGradSchool take the information given by the user in the mapped URL
	 * and use it to retrieve a list of Japanese GradSchools
	 * 
	 * @param prefecture
	 *            -> list of prefectures provided by the user
	 * @param nameGrad
	 *            -> name of the GradSchool to search
	 * @param typeGrad
	 *            -> type of GradSchool to search
	 * @param typeCourse
	 *            -> Specifies the nature of the course the user want to search
	 * @param englishCourse
	 *            -> Specifies whether or not is necessary to take a previous
	 *            English exam
	 * @return all the information related to the GradSchools which matched with
	 *         the different parameters
	 * @throws Exception
	 */
	@RequestMapping(value = "/posgrado/{prefecture}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String searchGradSchool(@PathVariable("prefecture") String[] prefecture,
			@RequestParam(value = "nameGrad") String nameGrad, @RequestParam(value = "typeGrad") String[] typeGrad,
			@RequestParam(value = "typeCourse") String[] typeCourse,
			@RequestParam(value = "englishCourse") String[] englishCourse) throws Exception {

		String gradSchoolList = "";
		String url = "";
		CollegeList gradSchoolsList = new CollegeList(String.join(" ", prefecture) + String.join(" ", typeGrad));
		String returnJson = "";
		int counter = 0;

		GradSchoolUrlBuilder search = new GradSchoolUrlBuilder(nameGrad, prefecture, typeGrad, typeCourse,
				englishCourse);
		System.out.println("Url final BUSQUEDA = " + search.getSearchUrl(prefecture));
		url = search.getSearchUrl(prefecture);

		GradSchoolCrawler crawler = new GradSchoolCrawler();
		gradSchoolList = crawler.getCollegeList(url, prefecture, gradSchoolsList, returnJson, counter);

		return gradSchoolList;
	}

	/**
	 * SearchFpSchool take the information given by the user in the mapped URL
	 * and use it to retrieve a list of Japanese TechSchools
	 * 
	 * @param prefecture
	 *            -> list of prefectures provided by the user
	 * @param nameTech
	 *            -> name of the TechSchool to search
	 * @return all the information related to the TechSchools which matched with
	 *         the different parameters
	 * @throws Exception
	 */
	@RequestMapping(value = "/fp/{prefecture}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String searchTechSchool(@PathVariable("prefecture") String[] prefecture,
			@RequestParam(value = "nameTech") String nameTech) throws Exception {

		String techSchoolList = "";
		String url = "";
		CollegeList techSchoolsList = new CollegeList(String.join(" ", prefecture));
		String returnJson = "";
		int counter = 0;

		TechSchoolUrlBuilder search = new TechSchoolUrlBuilder(nameTech, prefecture);
		System.out.println("Url final BUSQUEDA = " + search.getSearchUrl(prefecture));
		url = search.getSearchUrl(prefecture);
		TechSchoolCrawler crawler = new TechSchoolCrawler();
		techSchoolList = crawler.getCollegeList(url, prefecture, techSchoolsList, returnJson, counter);

		return techSchoolList;
	}

	/**
	 * showUniversities retrieve all the information of a university given a
	 * proper university id
	 * 
	 * @param id
	 *            -> id of the university which information is going to be
	 *            provided
	 * @return all the information related to the university which matched with
	 *         the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/universidades/id/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showUniversity(@PathVariable("id") String id) throws Exception {
		String universityInfo = "";
		UniversityCrawler crawler = new UniversityCrawler();
		universityInfo = crawler.getCollege(id);

		return universityInfo;
	}

	/**
	 * showGradSchool retrieve all the information of a GradSchool given a
	 * proper university id
	 * 
	 * @param id
	 *            -> id of the GradSchool which information is going to be
	 *            provided
	 * @return all the information related to the GradSchool which matched with
	 *         the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/posgrado/id/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showGradSchool(@PathVariable("id") String id) throws Exception {
		String gradSchoolInfo = "";
		GradSchoolCrawler crawler = new GradSchoolCrawler();
		gradSchoolInfo = crawler.getCollege(id);

		return gradSchoolInfo;
	}

	/**
	 * showTechSchool retrieve all the information of a TechSchool given a
	 * proper university id
	 * 
	 * @param id
	 *            -> id of the techSchool which information is going to be
	 *            provided
	 * @return all the information related to the techSchool which matched with
	 *         the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/fp/id/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showTechSchool(@PathVariable("id") String id) throws Exception {
		String techSchoolInfo = "";
		TechSchoolCrawler crawler = new TechSchoolCrawler();
		techSchoolInfo = crawler.getCollege(id);

		return techSchoolInfo;
	}

	/**
	 * University Faculty Admissions Page
	 * 
	 * showUniversityFacultyAdmissions retrieve the admissions page of the
	 * university faculty related to the given university and faculty id
	 * 
	 * @param idUniversity
	 *            -> id of the university which information is going to be
	 *            provided
	 * @param idFaculty
	 *            -> id of the faculty related to the university which admission
	 *            page is going to be retrieved
	 * @return The admission information related to the facilty which matched
	 *         with the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/universidades/id/{idUniversity}/{idFaculty}/admissions", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showUniversityFacultyAdmissions(@PathVariable("idUniversity") String idUniversity,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyAdmissions = "";
		UniversityCrawler crawler = new UniversityCrawler();
		facultyAdmissions = crawler.getFacultyAdmissions(idUniversity, idFaculty);

		return facultyAdmissions;
	}

	/**
	 * University Faculty Info Page
	 * 
	 * showUniversityFacultyInfo retrieve the main info page of the university
	 * faculty related to the given university and faculty id
	 * 
	 * @param idUniversity
	 *            -> id of the university which information is going to be
	 *            provided
	 * @param idFaculty
	 *            -> id of the faculty related to the university which info page
	 *            is going to be retrieved
	 * @return The main information related to the faculty which matched with
	 *         the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/universidades/id/{idUniversity}/{idFaculty}/info", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showUniversityFacultyInfo(@PathVariable("idUniversity") String idUniversity,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyInfo = "";
		UniversityCrawler crawler = new UniversityCrawler();
		facultyInfo = crawler.getFacultyInfo(idUniversity, idFaculty);

		return facultyInfo;
	}

	/**
	 * University Faculty Student Support Page
	 * 
	 * showUniversityFacultyInfo retrieve the student support page of the
	 * university related to the given university and faculty id
	 * 
	 * @param idUniversity
	 *            -> id of the university which information is going to be
	 *            provided
	 * @param idFaculty
	 *            -> id of the faculty related to the university which student
	 *            support page is going to be retrieved
	 * @return The main faculty support information related to the faculty which
	 *         matched with the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/universidades/id/{idUniversity}/{idFaculty}/support", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showUniversityFacultySupport(@PathVariable("idUniversity") String idUniversity,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultySupport = "";
		UniversityCrawler crawler = new UniversityCrawler();
		facultySupport = crawler.getFacultySupport(idUniversity, idFaculty);

		return facultySupport;
	}

	/**
	 * University Faculty Facilities Page
	 * 
	 * showUniversityFacultyFacilities retrieve the faculty facilities page of
	 * the university faculty related to the given university and faculty id
	 * 
	 * @param idUniversity
	 *            -> id of the university which information is going to be
	 *            provided
	 * @param idFaculty
	 *            -> id of the faculty related to the university which faculty
	 *            facilities page is going to be retrieved
	 * @return The faculty facilities information related to the faculty which
	 *         matched with the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/universidades/id/{idUniversity}/{idFaculty}/facilities", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showUniversityFacultyFacilities(@PathVariable("idUniversity") String idUniversity,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyFacilities = "";
		UniversityCrawler crawler = new UniversityCrawler();
		facultyFacilities = crawler.getFacultyFacilities(idUniversity, idFaculty);

		return facultyFacilities;
	}

	/**
	 * University Faculty Access Page
	 * 
	 * showUniversityFacultyAccess retrieve the faculty access page of the
	 * university faculty related to the given university and faculty id
	 * 
	 * @param idUniversity
	 *            -> id of the university which information is going to be
	 *            provided
	 * @param idFaculty
	 *            -> id of the faculty related to the university which faculty
	 *            access page is going to be retrieved
	 * @return The faculty access information related to the faculty which
	 *         matched with the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/universidades/id/{idUniversity}/{idFaculty}/access", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showUniversityFacultyAccess(@PathVariable("idUniversity") String idUniversity,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyAccess = "";
		UniversityCrawler crawler = new UniversityCrawler();
		facultyAccess = crawler.getFacultyAccess(idUniversity, idFaculty);

		return facultyAccess;
	}

	/**
	 * Grad School Faculty Admissions Page
	 * 
	 * showGradSchoolFacultyAdmissions retrieve the faculty access page of the
	 * grad school faculty related to the given grad school and faculty id
	 * 
	 * @param idGraduate
	 *            -> id of the gradSchool which information is going to be
	 *            provided
	 * @param idFaculty
	 *            -> id of the faculty related to the gradSchool which faculty
	 *            access page is going to be retrieved
	 * @return The faculty access information related to the faculty which
	 *         matched with the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/posgrado/id/{idGraduate}/{idFaculty}/admissions", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showGradSchoolFacultyAdmissions(@PathVariable("idGraduate") String idGraduate,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyAdmissions = "";
		GradSchoolCrawler crawler = new GradSchoolCrawler();
		facultyAdmissions = crawler.getFacultyAdmissions(idGraduate, idFaculty);

		return facultyAdmissions;
	}

	/**
	 * Grad School Faculty Info Page
	 * 
	 * showGradSchoolFacultyInfo retrieve the faculty info page of the grad
	 * school faculty related to the given grad school and faculty id
	 * 
	 * @param idGraduate
	 *            -> id of the gradSchool which information is going to be
	 *            provided
	 * @param idFaculty
	 *            -> id of the faculty related to the gradSchool which faculty
	 *            info page is going to be retrieved
	 * @return The faculty main information related to the faculty which matched
	 *         with the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/posgrado/id/{idGraduate}/{idFaculty}/info", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showGradSchoolFacultyInfo(@PathVariable("idGraduate") String idGraduate,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyInfo = "";
		GradSchoolCrawler crawler = new GradSchoolCrawler();
		facultyInfo = crawler.getFacultyInfo(idGraduate, idFaculty);

		return facultyInfo;
	}

	/**
	 * Grad School Faculty Support Page
	 * 
	 * showGradSchoolFacultySupport retrieve the faculty support page of the
	 * grad school faculty related to the given grad school and faculty id
	 * 
	 * @param idGraduate
	 *            -> id of the gradSchool which information is going to be
	 *            provided
	 * @param idFaculty
	 *            -> id of the faculty related to the gradSchool which faculty
	 *            support page is going to be retrieved
	 * @return The faculty support information related to the faculty which
	 *         matched with the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/posgrado/id/{idGraduate}/{idFaculty}/support", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showGradSchoolFacultySupport(@PathVariable("idGraduate") String idGraduate,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultySupport = "";
		GradSchoolCrawler crawler = new GradSchoolCrawler();
		facultySupport = crawler.getFacultySupport(idGraduate, idFaculty);

		return facultySupport;
	}

	/**
	 * Grad School Faculty Facilities Page
	 * 
	 * showGradSchoolFacultyFacilities retrieve the faculty facilities page of
	 * the grad school faculty related to the given grad school and faculty id
	 * 
	 * @param idGraduate
	 *            -> id of the gradSchool which information is going to be
	 *            provided
	 * @param idFaculty
	 *            -> id of the faculty related to the gradSchool which faculty
	 *            facilities page is going to be retrieved
	 * @return The faculty facilities information related to the faculty which
	 *         matched with the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/posgrado/id/{idGraduate}/{idFaculty}/facilities", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showGradSchoolFacultyFacilities(@PathVariable("idGraduate") String idGraduate,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyFacilities = "";
		GradSchoolCrawler crawler = new GradSchoolCrawler();
		facultyFacilities = crawler.getFacultyFacilities(idGraduate, idFaculty);

		return facultyFacilities;
	}

	/**
	 * Grad School Faculty Access Page
	 * 
	 * showGradSchoolFacultyAccess retrieve the faculty access page of the grad
	 * school faculty related to the given grad school and faculty id
	 * 
	 * @param idGraduate
	 *            -> id of the gradSchool which information is going to be
	 *            provided
	 * @param idFaculty
	 *            -> id of the faculty related to the gradSchool which faculty
	 *            access page is going to be retrieved
	 * @return The faculty access information related to the faculty which
	 *         matched with the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/posgrado/id/{idGraduate}/{idFaculty}/access", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showGradSchoolFacultyAccess(@PathVariable("idGraduate") String idGraduate,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyAccess = "";
		GradSchoolCrawler crawler = new GradSchoolCrawler();
		facultyAccess = crawler.getFacultyAccess(idGraduate, idFaculty);

		return facultyAccess;
	}

	/**
	 * Tech School Faculty Essential Information Page
	 * 
	 * showTechSchoolFacultyEssentialInfo retrieve the faculty essential info
	 * page of the tech school faculty related to the given tech school and
	 * faculty id
	 * 
	 * @param idTechSchool
	 *            -> id of the techSchool which information is going to be
	 *            provided
	 * @return The faculty essential information related to the faculty which
	 *         matched with the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/fp/id/{idTechSchool}/admissions", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showTechSchoolFacultyEssentialInfo(@PathVariable("idTechSchool") String idTechSchool)
			throws Exception {
		String TechSchoolEssentialInfo = "";
		TechSchoolCrawler crawler = new TechSchoolCrawler();
		TechSchoolEssentialInfo = crawler.getFacultyAdmissions(idTechSchool, "no value");

		return TechSchoolEssentialInfo;
	}

	/**
	 * Tech School Faculty Information Page
	 * 
	 * showTechSchoolFacultyInfo retrieve the faculty info page of the tech
	 * school faculty related to the given tech school and faculty id
	 * 
	 * @param idTechSchool
	 *            -> id of the techSchool which information is going to be
	 *            provided
	 * @return The faculty information related to the faculty which matched with
	 *         the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/fp/id/{idTechSchool}/info", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showTechSchoolFacultyInfo(@PathVariable("idTechSchool") String idTechSchool)
			throws Exception {
		String TechSchoolInfo = "";
		TechSchoolCrawler crawler = new TechSchoolCrawler();
		TechSchoolInfo = crawler.getFacultyInfo(idTechSchool, "no value");

		return TechSchoolInfo;
	}

	/**
	 * Tech School Faculty Support Page
	 * 
	 * showTechSchoolFacultySupport retrieve the faculty support page of the
	 * tech school faculty related to the given tech school and faculty id
	 * 
	 * @param idTechSchool
	 *            -> id of the techSchool which information is going to be
	 *            provided
	 * @return The faculty support information related to the faculty which
	 *         matched with the id in a JSON format
	 * @throws Exception
	 */
	@RequestMapping(value = "/fp/id/{idTechSchool}/support", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showTechSchoolFacultySupport(@PathVariable("idTechSchool") String idTechSchool)
			throws Exception {
		String TechSchoolSupport = "";
		TechSchoolCrawler crawler = new TechSchoolCrawler();
		TechSchoolSupport = crawler.getFacultySupport(idTechSchool, "no value");

		return TechSchoolSupport;
	}

	// TODO index them into elasticSearch
	/**
	 * Update articles from the blog and index them into elastic search
	 * 
	 * updateArticles update the blogs articles by applying the kettle
	 * transformation which generate as a output the sort.json file which
	 * include the latest articles sorted by date and a .json file related to
	 * each blog
	 * 
	 * @return a message when the transformation has finished
	 * @throws Exception
	 */
	@RequestMapping(value = "/articulos/actualizarRepositorio", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String updateArticles() throws Exception {
		KettleTransformation.start();
		//Call to the elastic search index process
		ElasticSearch.filesIndexation();
		return "Artículos actualizados";
	}

	/**
	 * Return sorted articles
	 * 
	 * getLatesArticles read the file sort.json where the articles are stored
	 * sorted by date and return it as a json file.
	 * 
	 * @return json file with the articles sorted by date. Error if the articles
	 *         are empty
	 * @throws Exception
	 */
	@RequestMapping(value = "/articulos", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String getLatestArticles(HttpServletResponse  response) throws Exception {

		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String obtainedJson = new String(Files.readAllBytes(Paths.get("src/main/resources/sort.json")));
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(obtainedJson).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = gson.toJson(json);

		return !prettyJson.equals("") ? prettyJson : "error";
	}

	/**
	 * Search on the articles
	 * 
	 * searchArticleByTitle allow the user to search a given string into the
	 * stored articles titles
	 * 
	 * @param titulo
	 *            -> String to search into the articles titles
	 * @return a json file with the hits
	 * @throws Exception
	 */
	@RequestMapping(value = "/articulos/buscar", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String searchArticleByTitle(@RequestParam(value = "titulo") String titulo) throws Exception {

		return SearchArticles.searchByTitle(titulo);
	}

	/**
	 * Language School List page
	 * 
	 * showLanguageSchoolList retrieve the user the Japanese language schools
	 * from a prefecture in Japan
	 * 
	 * @param area
	 *            -> prefecture where to search the Japanese language schools
	 * @return a json file with the matches. All the japanese language schools
	 *         from the given prefecture
	 * @throws Exception
	 */
	@RequestMapping(value = "/escuelasIdiomas/{area}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showLanguageSchoolList(@PathVariable("area") String area) throws Exception {
		String languageSchoolList = "";
		SchoolCrawler crawler = new SchoolCrawler();
		languageSchoolList = crawler.getSchoolList(area);

		return languageSchoolList;
	}

	/**
	 * Language School Info page
	 * 
	 * showLanguageSchoolInformation retrieve all the information from a
	 * language school by a given id
	 * 
	 * @param idSchool
	 *            -> id of the Japanese language school which to get the
	 *            information
	 * @return a json file with the information of the language school
	 * @throws Exception
	 */
	@RequestMapping(value = "/escuelasIdiomas/id/{idSchool}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showLanguageSchoolInformation(@PathVariable("idSchool") String idSchool)
			throws Exception {
		String languageSchoolInfo = "";
		SchoolCrawler crawler = new SchoolCrawler();
		languageSchoolInfo = crawler.getSchoolInfo(idSchool);

		return languageSchoolInfo;
	}

	/**
	 * Jobs provided by ApplyQ API
	 * 
	 * getApplyqJobs perform a simple API call to the ApplyQ API retrieving his
	 * information.
	 * 
	 * @param prefecture
	 *            -> prefecture where to search the jobs
	 * @param page
	 *            -> page related to the AppliQ API
	 * @return json file from ApplyQ API
	 * @throws Exception
	 */
	@RequestMapping(value = "/trabajo/applyq/{prefecture}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String getApplyqJobs(@PathVariable("prefecture") String prefecture,
			@RequestParam(value = "page") int page) throws Exception {
		String jobList = "";
		JobsCrawler crawler = new JobsCrawler();
		// UserAgent userAgent =
		// UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		// jobList = crawler.getJobsApplyq(prefecture, userAgent);
		jobList = crawler.getJobsApplyq(prefecture, page);

		return jobList;
	}

}
