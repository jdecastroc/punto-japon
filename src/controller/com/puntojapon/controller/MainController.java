
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

	// Las prefecturas están en orden numerico con respecto a la web del crawler
	// para armar la URL.
	final String[] prefecturesList = { "Hokkaido", "Aomori", "Iwate", "Miyagi", "Akita", "Yamagata", "Fukushima",
			"Ibaraki", "Tochigi", "Gunma", "Saitama", "Chiba", "Tokyo", "Kanagawa", "Niigata", "Toyama", "Ishikawa",
			"Fukui", "Yamanashi", "Nagano", "Gifu", "Shizuoka", "Aichi", "Mie", "Shiga", "Kyoto", "Osaka", "Hyogo",
			"Nara", "Wakayama", "Tottori", "Shimane", "Okayama", "Hiroshima", "Yamaguchi", "Tokushima", "Kagawa",
			"Ehime", "Kochi", "Fukuoka", "Saga", "Nagasaki", "Kumamoto", "Oita", "Miyazaki", "Kagoshima", "Okinawa" };

	final String literatureUrl = "101-102-103-104-105-106-107-111";
	final String languageUrl = "151-160-166";
	final String lawUrl = "201-202";
	final String economManagCommercUrl = "221-222-223";
	final String sociologyUrl = "241-242-243-244";
	final String internacionalStudUrl = "261";
	final String nursingHealtUrl = "651-652-653-654";
	final String medicalDentalUrl = "601";
	final String pharmacyUrl = "621";
	final String physicalScienceUrl = "501-502-503-504-505-506-507";
	final String engineeringUrl = "551-552-553-554-555-556-557-558-559-560-561-563-564-566";
	final String agriFishUrl = "701-702-703-704-705-707-708-709";
	final String educationUrl = "301-315-317";
	final String lifeScienceUrl = "401-402-403-404-405";
	final String artsUrl = "451-452-453-458";
	final String integSciencUrl = "751-752-753";

	// Ponerlo todo en linea sin rest?

	// Tokyo =
	// http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D=201-202&a%5Bpf%5D=13&search.x=118&search.y=18&search=search

	@RequestMapping(value = "/universidades/{prefecture}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String searchUni(@PathVariable("prefecture") String[] prefecture) throws Exception {

		String universityList = "";
		String url = "";
		String typeStudies = "";
		String typeUni = "";
		CollegeList universitiesList = new CollegeList(String.join(" ", prefecture) + typeStudies + typeUni);
		String returnJson = "";
		int counter = 0;

		// Prefecture GET url
		if (Arrays.asList(prefecturesList).contains(prefecture[0]) && prefecture.length == 1) { // One
																								// prefect
			url = "http://www.jpss.jp/en/search/Prefecture/" + prefecture[0] + "/";
		} else if (prefecture.length > 1) { // If many prefectures choosen
			String urlPrefecture = buildPrefectureUrl(prefecturesList, prefecture);
			url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D=&a%5Bpf%5D="
					+ urlPrefecture + "&search.x=177&search.y=11&search=search";
			System.out.println("urlPrefecture -> " + urlPrefecture);
		}
		if (prefecture[0].equals("all")) { // If no prefecture choosen
			url = "http://www.jpss.jp/en/search/?tb=1&search_x=1";
			prefecture = prefecturesList;
		}

		universityList = UniversityCrawler.crawlUniversities(url, prefecture, typeStudies, typeUni, universitiesList,
				returnJson, counter);

		return universityList;
	}

	@RequestMapping(value = "/universidades/{prefecture}/{typeStudies}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String searchUni(@PathVariable("prefecture") String[] prefecture,
			@PathVariable("typeStudies") String[] typeStudiesList) throws Exception {

		String universityList = "";
		String url = "";
		String typeStudies = "";
		String typeUni = "";
		CollegeList universitiesList = new CollegeList(
				String.join(" ", prefecture) + String.join(" ", typeStudiesList) + typeUni);
		String returnJson = "";
		int counter = 0;

//		if (Arrays.asList(prefecturesList).contains(prefecture[0]) && prefecture.length == 1) { // One
//																								// prefect
//			url = "http://www.jpss.jp/en/search/Prefecture/" + prefecture[0] + "/";
//			if (typeStudiesList.length > 1) { // If many prefectures choosen and many studies choosen
//				String urlStudies = buildStudiesUrl(typeStudiesList);
//				url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D="
//						+ urlStudies + "&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search";
//				System.out.println("urlStudies -> " + urlStudies);
//			}
//		} else 
			if (prefecture.length > 0) { // If many prefectures choosen
			String urlPrefecture = buildPrefectureUrl(prefecturesList, prefecture);
			url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D=&a%5Bpf%5D="
					+ urlPrefecture + "&search.x=177&search.y=11&search=search";
			System.out.println("urlPrefecture -> " + urlPrefecture);
			
			if (typeStudiesList.length > 0) { // If many prefectures choosen and many studies choosen
				String urlStudies = buildStudiesUrl(typeStudiesList);
				url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D="
						+ urlStudies + "&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search";
				System.out.println("urlStudies -> " + urlStudies);
			}
		}
		if (prefecture[0].equals("all")) { // If no prefecture choosen
			url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D=&a%5Bpf%5D=&search.x=177&search.y=11&search=search";
			prefecture = prefecturesList;
			if (typeStudiesList.length > 0) { // If many studies choosen
				String urlStudies = buildStudiesUrl(typeStudiesList);
				url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D="
						+ urlStudies + "&a%5Bpf%5D=&search.x=177&search.y=11&search=search";
				System.out.println("urlStudies -> " + urlStudies);
			}
		}

		// Studies GET
		// final String lawUrl =
		// "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D=201-202&a%5Bpf%5D=&search.x=115&search.y=19&search=search";
		// Tokyo =
		// http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D=201-202&a%5Bpf%5D=13&search.x=118&search.y=18&search=search

		universityList = UniversityCrawler.crawlUniversities(url, prefecture, typeStudies, typeUni, universitiesList,
				returnJson, counter);

		return universityList;
	}

	private String buildStudiesUrl(String[] typeStudiesList) {
		String urlStudies = "";
		String text = "";
		for (String i : typeStudiesList) {
			if (i.contains("Literature"))
				text = literatureUrl;
			if (i.contains("Language"))
				text = languageUrl;
			if (i.contains("Law"))
				text = lawUrl;
			if (i.contains("Economics"))
				text = economManagCommercUrl;
			if (i.contains("Sociology"))
				text = sociologyUrl;
			if (i.contains("International"))
				text = internacionalStudUrl;
			if (i.contains("Health Sciences"))
				text = nursingHealtUrl;
			if (i.contains("Medical"))
				text = medicalDentalUrl;
			if (i.contains("Pharmacy"))
				text = medicalDentalUrl;
			if (i.contains("Physical Science"))
				text = physicalScienceUrl;
			if (i.contains("Engineering"))
				text = engineeringUrl;
			if (i.contains("Agricultur")) //TODO Take care
				text = agriFishUrl;
			if (i.contains("Education"))
				text = educationUrl;
			if (i.contains("Life"))
				text = lifeScienceUrl;
			if (i.contains("Arts"))
				text = artsUrl;
			if (i.contains("Integrated Science"))
				text = integSciencUrl;
			
			urlStudies = urlStudies + text + "-";
		}
		urlStudies = urlStudies.substring(0, urlStudies.length() - 1);
		return urlStudies;
	}

	private String buildPrefectureUrl(String[] prefecturesList, String[] prefecture) {
		String urlPrefectures = "";
		for (int i = 0; i < prefecturesList.length; i++) {
			for (int k = 0; k < prefecture.length; k++) {
				if (prefecture[k].contains(prefecturesList[i])) {
					int m = i + 1;
					urlPrefectures = urlPrefectures + m + "-";
				}
			}
		}
		urlPrefectures = urlPrefectures.substring(0, urlPrefectures.length() - 1);
		return urlPrefectures;
	}

}
