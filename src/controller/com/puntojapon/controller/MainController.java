/**
 * RESTFul service
 * 
 * @author: Jorge de Castro
 * @version: 27/01/2016/A
 * @see <a href = "https://bitbucket.org/jdecastroc/punto-japon" /> Bitbucket
 *      repository </a>
 */
package com.puntojapon.controller;

import java.nio.file.Files;
import java.nio.file.Paths;

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
import com.puntojapon.articles.KettleTransformation;
import com.puntojapon.colleges.CollegeList;
import com.puntojapon.colleges.GradSchoolCrawler;
import com.puntojapon.colleges.GradSchoolUrlBuilder;
import com.puntojapon.colleges.TechSchoolCrawler;
import com.puntojapon.colleges.TechSchoolUrlBuilder;
import com.puntojapon.colleges.UniversityCrawler;
import com.puntojapon.colleges.UniversityUrlBuilder;
import com.puntojapon.languageSchools.SchoolCrawler;

@RestController
public class MainController {

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

	// Grad school
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

	@RequestMapping(value = "/fp/{prefecture}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String searchUni(@PathVariable("prefecture") String[] prefecture,
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

	// University Page
	@RequestMapping(value = "/universidades/id/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showUniversity(@PathVariable("id") String id) throws Exception {
		String universityInfo = "";
		UniversityCrawler crawler = new UniversityCrawler();
		universityInfo = crawler.getCollege(id);

		return universityInfo;
	}

	// Grad Page
	@RequestMapping(value = "/posgrado/id/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showGradSchool(@PathVariable("id") String id) throws Exception {
		String gradSchoolInfo = "";
		GradSchoolCrawler crawler = new GradSchoolCrawler();
		gradSchoolInfo = crawler.getCollege(id);

		return gradSchoolInfo;
	}

	// FP Page
	@RequestMapping(value = "/fp/id/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showTechSchool(@PathVariable("id") String id) throws Exception {
		String techSchoolInfo = "";
		TechSchoolCrawler crawler = new TechSchoolCrawler();
		techSchoolInfo = crawler.getCollege(id);

		return techSchoolInfo;
	}

	// University Faculty Admissions Page
	@RequestMapping(value = "/universidades/id/{idUniversity}/{idFaculty}/admissions", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showUniversityFacultyAdmissions(@PathVariable("idUniversity") String idUniversity,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyAdmissions = "";
		UniversityCrawler crawler = new UniversityCrawler();
		facultyAdmissions = crawler.getFacultyAdmissions(idUniversity, idFaculty);

		return facultyAdmissions;
	}

	// University Faculty Info Page
	@RequestMapping(value = "/universidades/id/{idUniversity}/{idFaculty}/info", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showUniversityFacultyInfo(@PathVariable("idUniversity") String idUniversity,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyInfo = "";
		UniversityCrawler crawler = new UniversityCrawler();
		facultyInfo = crawler.getFacultyInfo(idUniversity, idFaculty);

		return facultyInfo;
	}

	// University Faculty Student Support Page
	@RequestMapping(value = "/universidades/id/{idUniversity}/{idFaculty}/support", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showUniversityFacultySupport(@PathVariable("idUniversity") String idUniversity,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultySupport = "";
		UniversityCrawler crawler = new UniversityCrawler();
		facultySupport = crawler.getFacultySupport(idUniversity, idFaculty);

		return facultySupport;
	}

	// University Faculty Facilities Page
	@RequestMapping(value = "/universidades/id/{idUniversity}/{idFaculty}/facilities", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showUniversityFacultyFacilities(@PathVariable("idUniversity") String idUniversity,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyFacilities = "";
		UniversityCrawler crawler = new UniversityCrawler();
		facultyFacilities = crawler.getFacultyFacilities(idUniversity, idFaculty);

		return facultyFacilities;
	}

	// University Faculty Access Page
	@RequestMapping(value = "/universidades/id/{idUniversity}/{idFaculty}/access", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showUniversityFacultyAccess(@PathVariable("idUniversity") String idUniversity,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyAccess = "";
		UniversityCrawler crawler = new UniversityCrawler();
		facultyAccess = crawler.getFacultyAccess(idUniversity, idFaculty);

		return facultyAccess;
	}

	// Grad Schools Faculty

	// Grad School Faculty Admissions Page
	@RequestMapping(value = "/posgrado/id/{idGraduate}/{idFaculty}/admissions", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showGradSchoolFacultyAdmissions(@PathVariable("idGraduate") String idGraduate,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyAdmissions = "";
		GradSchoolCrawler crawler = new GradSchoolCrawler();
		facultyAdmissions = crawler.getFacultyAdmissions(idGraduate, idFaculty);

		return facultyAdmissions;
	}

	// Grad School Faculty Info Page
	@RequestMapping(value = "/posgrado/id/{idGraduate}/{idFaculty}/info", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showGradSchoolFacultyInfo(@PathVariable("idGraduate") String idGraduate,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyInfo = "";
		GradSchoolCrawler crawler = new GradSchoolCrawler();
		facultyInfo = crawler.getFacultyInfo(idGraduate, idFaculty);

		return facultyInfo;
	}

	// Grad School Faculty Support Page
	@RequestMapping(value = "/posgrado/id/{idGraduate}/{idFaculty}/support", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showGradSchoolFacultySupport(@PathVariable("idGraduate") String idGraduate,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultySupport = "";
		GradSchoolCrawler crawler = new GradSchoolCrawler();
		facultySupport = crawler.getFacultySupport(idGraduate, idFaculty);

		return facultySupport;
	}

	// Grad School Faculty Facilities Page
	@RequestMapping(value = "/posgrado/id/{idGraduate}/{idFaculty}/facilities", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showGradSchoolFacultyFacilities(@PathVariable("idGraduate") String idGraduate,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyFacilities = "";
		GradSchoolCrawler crawler = new GradSchoolCrawler();
		facultyFacilities = crawler.getFacultyFacilities(idGraduate, idFaculty);

		return facultyFacilities;
	}

	// Grad School Faculty Access Page
	@RequestMapping(value = "/posgrado/id/{idGraduate}/{idFaculty}/access", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showGradSchoolFacultyAccess(@PathVariable("idGraduate") String idGraduate,
			@PathVariable("idFaculty") String idFaculty) throws Exception {
		String facultyAccess = "";
		GradSchoolCrawler crawler = new GradSchoolCrawler();
		facultyAccess = crawler.getFacultyAccess(idGraduate, idFaculty);

		return facultyAccess;
	}

	// Tech School Faculty Essential Information Page
	@RequestMapping(value = "/fp/id/{idTechSchool}/admissions", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showTechSchoolFacultyEssentialInfo(@PathVariable("idTechSchool") String idTechSchool)
			throws Exception {
		String TechSchoolEssentialInfo = "";
		TechSchoolCrawler crawler = new TechSchoolCrawler();
		TechSchoolEssentialInfo = crawler.getFacultyAdmissions(idTechSchool, "no value");

		return TechSchoolEssentialInfo;
	}

	// Tech School Faculty Information Page
	@RequestMapping(value = "/fp/id/{idTechSchool}/info", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showTechSchoolFacultyInfo(@PathVariable("idTechSchool") String idTechSchool)
			throws Exception {
		String TechSchoolInfo = "";
		TechSchoolCrawler crawler = new TechSchoolCrawler();
		TechSchoolInfo = crawler.getFacultyInfo(idTechSchool, "no value");

		return TechSchoolInfo;
	}

	// Tech School Faculty Information Page
	// localhost:80/fp/id/234/support
	// localhost:80/buscadorCurriculums/nombre/
	
	@RequestMapping(value = "/fp/id/{idTechSchool}/support", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showTechSchoolFacultySupport(@PathVariable("idTechSchool") String idTechSchool)
			throws Exception {
		String TechSchoolSupport = "";
		TechSchoolCrawler crawler = new TechSchoolCrawler();
		TechSchoolSupport = crawler.getFacultySupport(idTechSchool, "no value");
		
		return TechSchoolSupport;
	}

	// Update articles from the blog and index them in elastic search
	@RequestMapping(value = "/articulos/actualizarRepositorio", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String updateArticles() throws Exception {
		KettleTransformation.start();
		return "Artículos actualizados";
	}

	// Return sorted articles
	@RequestMapping(value = "/articulos", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String getLatestArticles() throws Exception {
	     
		String obtainedJson = new String(Files.readAllBytes(Paths.get("src/main/resources/sort.json")));
		JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(obtainedJson).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);
		
		return !prettyJson.equals("")? prettyJson : "error";
	}
	
	//TODO Search on the articles
	
	// Language School Info page
	@RequestMapping(value = "/escuelasIdiomas/id/{idSchool}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showLanguageSchoolInformation(@PathVariable("idSchool") String idSchool)
			throws Exception {
		String languageSchoolInfo = "";
		SchoolCrawler crawler = new SchoolCrawler();
		languageSchoolInfo = crawler.getSchoolInfo(idSchool);
		
		return languageSchoolInfo;
	}

}
