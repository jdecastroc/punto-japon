

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

	//Las prefecturas están en orden numerico con respecto a la web del crawler para armar la URL.
	final String[] prefecturesList = {"Hokkaido", "Aomori", "Iwate", "Miyagi", "Akita", "Yamagata", "Fukushima", 
			"Ibaraki", "Tochigi", "Gunma", "Saitama", "Chiba", "Tokyo", "Kanagawa", "Niigata", "Toyama", "Ishikawa",
			"Fukui", "Yamanashi", "Nagano", "Gifu", "Shizuoka", "Aichi", "Mie", "Shiga", "Kyoto", "Osaka", "Hyogo", 
			"Nara", "Wakayama", "Tottori", "Shimane", "Okayama", "Hiroshima", "Yamaguchi", "Tokushima", "Kagawa", 
			"Ehime", "Kochi",  "Fukuoka", "Saga", "Nagasaki", "Kumamoto", "Oita", "Miyazaki", "Kagoshima", "Okinawa"};
	
	
	@RequestMapping(value = "/universidades/{prefecture}", method = RequestMethod.GET, produces="application/json")
	@ResponseStatus(HttpStatus.OK)
    public @ResponseBody String searchUni(@PathVariable("prefecture") String[] prefecture) throws Exception {
		
		String universityList = "";
		String url = "";
		
		if (Arrays.asList(prefecturesList).contains(prefecture[0]) && prefecture.length == 1){
			url = "http://www.jpss.jp/en/search/Prefecture/" + prefecture[0] + "/";
		} else if (prefecture.length > 1){
			String urlPrefecture = buildPrefectureUrl(prefecturesList,prefecture);
			url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D=&a%5Bpf%5D="+urlPrefecture+"&search.x=177&search.y=11&search=search";
			System.out.println("urlPrefecture -> " + urlPrefecture);
		}
		if (prefecture[0].equals("all")){
			url = "http://www.jpss.jp/en/search/?tb=1&search_x=1";
			prefecture = prefecturesList;
		}

		String typeStudies = "";
		String typeUni = "";
		CollegeList universitiesList = new CollegeList(String.join(" ", prefecture) + typeStudies + typeUni);
		String returnJson = "";
		int counter = 0;
		
		universityList = UniversityCrawler.crawlUniversities(url, prefecture, typeStudies, typeUni, universitiesList, returnJson, counter);
		
        return universityList;
    }


	private String buildPrefectureUrl(String[] prefecturesList, String[] prefecture) {
		String urlPrefectures = "";
		for (int i = 0; i < prefecturesList.length; i++){
			for (int k = 0; k < prefecture.length; k++){
				if (prefecture[k].contains(prefecturesList[i])){
					int m = i+1;
					urlPrefectures = urlPrefectures + m + "-";
				}
			}
		}
		urlPrefectures = urlPrefectures.substring(0,urlPrefectures.length()-1);
		return urlPrefectures;
	}
	
//	@RequestMapping(value = "/buscador/universidades/{prefectures}", method = RequestMethod.GET, produces="application/json")
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
