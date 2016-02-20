/**
 * RESTFul service
 * 
 * @author: Jorge de Castro
 * @version: 27/01/2016/A
 * @see <a href = "https://bitbucket.org/jdecastroc/punto-japon" /> Bitbucket
 *      repository </a>
 */
package com.puntojapon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.puntojapon.colleges.CollegeList;
import com.puntojapon.colleges.GradSchoolCrawler;
import com.puntojapon.colleges.GradSchoolUrlBuilder;
import com.puntojapon.colleges.TechSchoolCrawler;
import com.puntojapon.colleges.TechSchoolUrlBuilder;
import com.puntojapon.colleges.UniversityCrawler;
import com.puntojapon.colleges.UniversityUrlBuilder;

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
		CollegeList gradSchoolsList = new CollegeList(
				String.join(" ", prefecture) + String.join(" ", typeGrad));
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
	
	// University Faculty Page
		@RequestMapping(value = "/universidades/id/{idUniversity}/{idFaculty}/admissions", method = RequestMethod.GET, produces = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public @ResponseBody String showUniversityFacultyAdmissions(@PathVariable("idUniversity") String idUniversity,
				@PathVariable("idFaculty") String idFaculty) throws Exception {
			String facultyInfo = "";
			UniversityCrawler crawler = new UniversityCrawler();
			facultyInfo = crawler.getFacultyAdmissions(idUniversity, idFaculty);

			return facultyInfo;
		}
		
	// University Faculty Page
		@RequestMapping(value = "/universidades/id/{idUniversity}/{idFaculty}/info", method = RequestMethod.GET, produces = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public @ResponseBody String showUniversityFacultyInfo(@PathVariable("idUniversity") String idUniversity,
				@PathVariable("idFaculty") String idFaculty) throws Exception {
			String facultyInfo = "";
			UniversityCrawler crawler = new UniversityCrawler();
			facultyInfo = crawler.getFacultyInfo(idUniversity, idFaculty);

			return facultyInfo;
		}		
	

}
