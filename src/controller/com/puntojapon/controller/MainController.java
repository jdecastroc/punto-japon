/**
 * RESTFul service
 * 
 * @author: Jorge de Castro
 * @version: 27/01/2016/A
 * @see <a href = "https://bitbucket.org/jdecastroc/punto-japon" /> Bitbucket
 *      repository </a>
 */
package com.puntojapon.controller;

import com.puntojapon.main.CollegeList;
import com.puntojapon.main.GradSchoolCrawler;
import com.puntojapon.main.PageCrawler;
import com.puntojapon.main.TechSchoolCrawler;
import com.puntojapon.main.UniversityCrawler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
		universityList = crawler.crawlUniversities(url, prefecture, universitiesList, returnJson, counter);

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
		String typeStudies = "";
		CollegeList gradSchoolsList = new CollegeList(
				String.join(" ", prefecture) + typeStudies + String.join(" ", typeGrad));
		String returnJson = "";
		int counter = 0;

		GradSchoolUrlBuilder search = new GradSchoolUrlBuilder(nameGrad, prefecture, typeGrad, typeCourse,
				englishCourse);
		System.out.println("Url final BUSQUEDA = " + search.getSearchUrl(prefecture));
		url = search.getSearchUrl(prefecture);

		GradSchoolCrawler crawler = new GradSchoolCrawler();
		gradSchoolList = crawler.crawlGradSchools(url, prefecture, typeStudies, gradSchoolsList, returnJson, counter);

		return gradSchoolList;
	}

	@RequestMapping(value = "/fp/{prefecture}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String searchUni(@PathVariable("prefecture") String[] prefecture,
			@RequestParam(value = "nameTech") String nameTech) throws Exception {

		String techSchoolList = "";
		String url = "";
		String typeStudies = "";
		CollegeList techSchoolsList = new CollegeList(String.join(" ", prefecture));
		String returnJson = "";
		int counter = 0;

		TechSchoolUrlBuilder search = new TechSchoolUrlBuilder(nameTech, prefecture);
		System.out.println("Url final BUSQUEDA = " + search.getSearchUrl(prefecture));
		url = search.getSearchUrl(prefecture);
		TechSchoolCrawler crawler = new TechSchoolCrawler();
		techSchoolList = crawler.crawlTechSchools(url, prefecture, typeStudies, techSchoolsList, returnJson, counter);

		return techSchoolList;
	}

	// University Page
	@RequestMapping(value = "/universidades/id/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showUniversity(@PathVariable("id") String id) throws Exception {
		String universityInfo = "";
		String crawlerType = "university";
		universityInfo = PageCrawler.crawlPage(id, crawlerType);

		return universityInfo;
	}

	// Grad Page
	@RequestMapping(value = "/posgrado/id/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showGradSchool(@PathVariable("id") String id) throws Exception {
		String gradSchoolInfo = "";
		String crawlerType = "grad";
		gradSchoolInfo = PageCrawler.crawlPage(id, crawlerType);

		return gradSchoolInfo;
	}

	// FP Page
	@RequestMapping(value = "/fp/id/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showTechSchool(@PathVariable("id") String id) throws Exception {
		String techSchoolInfo = "";
		String crawlerType = "fp";
		techSchoolInfo = PageCrawler.crawlPage(id, crawlerType);

		return techSchoolInfo;
	}

}
