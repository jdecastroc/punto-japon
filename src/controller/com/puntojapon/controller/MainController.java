
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
		
	@RequestMapping(value = "/universidades/buscador/{prefecture}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String searchUni(@PathVariable("prefecture") String[] prefecture,
											@RequestParam(value="typeUni") String[] typeUni,
											@RequestParam(value="admisionMonth") String[] admisionMonth,
											@RequestParam(value="deadLine") String[] deadLine,
											@RequestParam(value="eju") String[] eju,
											@RequestParam(value="engExam") String[] engExam,
											@RequestParam(value="admisionUni") String[] admisionUni) throws Exception {

		String[] typeStudiesList={};
		String universityList = "";
		String url = "";
		String typeStudies = "";
		CollegeList universitiesList = new CollegeList(String.join(" ", prefecture) + typeStudies + typeUni);
		String returnJson = "";
		int counter = 0;
		
		UniversityUrlBuilder search = new UniversityUrlBuilder(prefecture, typeStudiesList, typeUni, admisionUni, deadLine, eju, engExam, admisionUni);
		System.out.println("Url final BUSQUEDA = " + search.getSearchUrl(prefecture));
		url = search.getSearchUrl(prefecture);

		universityList = UniversityCrawler.crawlUniversities(url, prefecture, typeStudies, universitiesList,
				returnJson, counter);

		return universityList;
	}

	@RequestMapping(value = "/universidades/buscador/{prefecture}/{typeStudies}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String searchUni(@PathVariable("prefecture") String[] prefecture,
			@PathVariable("typeStudies") String[] typeStudiesList,
			@RequestParam(value="typeUni") String[] typeUni,
			@RequestParam(value="admisionMonth") String[] admisionMonth,
			@RequestParam(value="deadLine") String[] deadLine,
			@RequestParam(value="eju") String[] eju,
			@RequestParam(value="engExam") String[] engExam,
			@RequestParam(value="admisionUni") String[] admisionUni) throws Exception {

		
		String universityList = "";
		String url = "";
		String typeStudies = ""; //Sobra?
		CollegeList universitiesList = new CollegeList(
				String.join(" ", prefecture) + " " + String.join(" ", typeStudiesList) + typeUni);
		String returnJson = "";
		int counter = 0;
		
		UniversityUrlBuilder search = new UniversityUrlBuilder(prefecture, typeStudiesList, typeUni, admisionUni, deadLine, eju, engExam, admisionUni);
		System.out.println("Url final BUSQUEDA = " + search.getSearchUrl(prefecture));
		url = search.getSearchUrl(prefecture);
		
		universityList = UniversityCrawler.crawlUniversities(url, prefecture, typeStudies, universitiesList,
				returnJson, counter);

		return universityList;
	}

}
