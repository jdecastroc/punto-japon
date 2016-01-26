

/**
 * RESTFul service
 * 
 * @author: Jorge de Castro
 * @version: 24/01/2016/A
 * @see <a href = "https://github.com/jdecastroc/Reddit-music-surfer" /> Github
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

	final String[] prefecturesList = {"Hokkaido", "Aomori", "Miyagi", "Yamagata", "Iwate", "Fukushima", "Ibaraki", "Gunma", "Chiba",
			"Kanagawa", "Nagano", "Toichigi", "Saitama", "Tokyo", "Yamanashi", "Niigata", "Gifu", "Aichi",
			"Toyama", "Fukui", "Shizuoka", "Mie", "Ishikawa", "Shiga", "Kyoto", "Osaka", "Hyogo", "Nara",
			"Wakayama", "Tokushima", "Kagawa", "Ehime", "Kochi", "Tottori", "Shimane", "Okayama", "Hiroshima",
			"Yamaguchi", "Fukuoka", "Saga", "Nagasaki", "Kumamoto", "Oita", "Miyazaki", "Kagoshima", "Okinawa"};
	
	
	@RequestMapping(path = "/buscador/universidades/{prefecture}", method = RequestMethod.GET, produces="application/json")
	@ResponseStatus(HttpStatus.OK)
    public @ResponseBody String searchUni(@PathVariable("prefecture") String prefecture) throws Exception {
		
		String universityList = "";
		String url = "";
		
		if (Arrays.asList(prefecturesList).contains(prefecture)){
			url = prefecture;
		} else {
			url = "http://www.jpss.jp/en/search/?tb=1&search_x=1";
		}
		
		String typeStudies = "";
		String typeUni = "";
		CollegeList universitiesList = new CollegeList(prefecture + typeStudies + typeUni);
		String returnJson = "";
		
		universityList = UniversityCrawler.crawlUniversities(url, prefecture, typeStudies, typeUni, universitiesList, returnJson);
		
        return universityList;
    }
	
//	@RequestMapping(path = "/buscador/universidades/{prefectures}", method = RequestMethod.GET, produces="application/json")
//	@ResponseStatus(HttpStatus.OK)
//    public @ResponseBody String searchUni(@PathVariable String[] prefectures) throws Exception {
//		
//		String universityList = "";
//		String url = "";
//		String prefecture = Arrays.toString(prefectures);//TODO Comprobar que funciona esto y comprobar prefecturas
//		
//		url = "http://www.jpss.jp/en/search/?tb=1&search_x=1";
//		
//		String typeStudies = "";
//		String typeUni = "";
//		CollegeList universitiesList = new CollegeList(prefecture + typeStudies + typeUni);
//		String returnJson = "";
//		
//		universityList = UniversityCrawler.crawlUniversities(url, prefecture, typeStudies, typeUni, universitiesList, returnJson);
//		
//        return universityList;
//    }	
	
}
