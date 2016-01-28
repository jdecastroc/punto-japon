
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

	//University subjects
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
	
	//Type of university
	//http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=|&u%5But%5D%5B%5D=1|&u%5Bac%5D=&a%5Bpf%5D=13&search.x=98&search.y=14&search=search
	final String nationalUni = "&u%5But%5D%5B%5D=1";
	final String publicUni = "&u%5But%5D%5B%5D=2";
	final String privateUni = "&u%5But%5D%5B%5D=3";
	final String jsfuUni = "&u%5But%5D%5B%5D=4";
	
	//Month of admission
	//http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=|&u%5Bsm%5D%5B%5D=1|&u%5Bac%5D=&a%5Bpf%5D=13&search.x=75&search.y=9&search=search
	final String januaryAdm = "&u%5Bsm%5D%5B%5D=1";
	final String marchAdm = "&u%5Bsm%5D%5B%5D=3";
	final String aprilAdm = "&u%5Bsm%5D%5B%5D=4";
	final String mayAdm = "&u%5Bsm%5D%5B%5D=5";
	final String septemberAdm = "&u%5Bsm%5D%5B%5D=9";
	final String octoberAdm = "&u%5Bsm%5D%5B%5D=10";
	
	
	//Application deadline
	//http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=|&u%5Bem%5D%5B%5D=1|&u%5Bac%5D=&a%5Bpf%5D=13&search.x=79&search.y=11&search=search
	final String januaryApp = "&u%5Bem%5D%5B%5D=1";
	final String februaryApp = "&u%5Bem%5D%5B%5D=2";
	final String marchApp = "&u%5Bem%5D%5B%5D=3";
	final String aprilApp = "&u%5Bem%5D%5B%5D=4";
	final String mayApp = "&u%5Bem%5D%5B%5D=5";
	final String juneApp = "&u%5Bem%5D%5B%5D=6";
	final String julyApp = "&u%5Bem%5D%5B%5D=7";
	final String augustApp = "&u%5Bem%5D%5B%5D=8";
	final String septemberApp = "&u%5Bem%5D%5B%5D=9";
	final String octoberApp = "&u%5Bem%5D%5B%5D=10";
	final String novemberApp = "&u%5Bem%5D%5B%5D=11";
	final String decemberApp = "&u%5Bem%5D%5B%5D=12";
	
	//EJU Examen
	//http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=|&u%5Bej%5D%5B%5D=1|&u%5Bac%5D=&a%5Bpf%5D=&search.x=103&search.y=15&search=search

	final String ejuYes = "&u%5Bej%5D%5B%5D=1";
	final String ejuNo = "&u%5Bej%5D%5B%5D=2";
	
	//English examination
	//http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=|&u%5Ben%5D%5B%5D=1|&u%5Bac%5D=&a%5Bpf%5D=&search.x=135&search.y=16&search=search
	final String engNotNecessary = "&u%5Ben%5D%5B%5D=1";
	final String engNecessary = "&u%5Ben%5D%5B%5D=2";
	final String engToeflUsed = "&u%5Ben%5D%5B%5D=3";
	final String engOthers = "&u%5Ben%5D%5B%5D=4";
	final String engToeflEtcUsed = "&u%5Ben%5D%5B%5D=5";
	
	//Admission to the university before entering Japan
	final String appOutsideJap = "&u%5Baf%5D%5B%5D=1";
	final String appInsideJap = "&u%5Baf%5D%5B%5D=2";
	
	//Final url example
	//"http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
	//+ typeUniUrl + admisionMonthUrl + deadLineUrl + ejuUrl + engExamUrl + admisionUniUrl +
	//"&u%5Bac%5D=" + urlStudies + "&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search";
	
	
	
	//Busqueda con todo y con nada
	//http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=
	//|&u%5But%5D%5B%5D=1&u%5Bsm%5D%5B%5D=1&u%5Bem%5D%5B%5D=1&u%5Bej%5D%5B%5D=1&u%5Ben%5D%5B%5D=1&u%5Baf%5D%5B%5D=1|
	//&u%5Bac%5D=102&a%5Bpf%5D=13&search.x=110&search.y=7&search=search
	//http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=
	//&u%5Bac%5D=&a%5Bpf%5D=&search.x=77&search.y=9&search=search
		
	@RequestMapping(value = "/universidades/buscador/{prefecture}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String searchUni(@PathVariable("prefecture") String[] prefecture,
											@RequestParam(value="typeUni") String[] typeUni,
											@RequestParam(value="admisionMonth") String[] admisionMonth,
											@RequestParam(value="deadLine") String[] deadLine,
											@RequestParam(value="eju") String[] eju,
											@RequestParam(value="engExam") String[] engExam,
											@RequestParam(value="admisionUni") String[] admisionUni) throws Exception {
		//Armar URL's
		String typeUniUrl = "";
		String admisionMonthUrl = "";
		String deadLineUrl = "";
		String ejuUrl = "";
		String engExamUrl = "";
		String admisionUniUrl = "";
		
		//Type uni URL
		if (typeUni.length > 0) //If many type uni
			typeUniUrl = buildTypeUniUrl(typeUni);
		if (admisionMonth.length > 0)
			admisionMonthUrl = buildAdmisionMonthUrl(admisionMonth);
		if (deadLine.length > 0)
			deadLineUrl = buildDeadLineUrl(deadLine);
		if (eju.length > 0)
			ejuUrl = buildEjuExamUrl(eju);
		if (engExam.length > 0)
			engExamUrl = buildEngExamUrl(engExam);
		if (admisionUni.length > 0)
			admisionUniUrl = buildAdmisionUniUrl(engExam);

		
		String universityList = "";
		String url = "";
		String typeStudies = "";
		//String typeUni = "";
		CollegeList universitiesList = new CollegeList(String.join(" ", prefecture) + typeStudies + typeUni);
		String returnJson = "";
		int counter = 0;
		
		//Final url example
		//"http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
		//+ typeUniUrl + admisionMonthUrl + deadLineUrl + ejuUrl + engExamUrl + admisionUniUrl +
		//"&u%5Bac%5D=" + urlStudies + "&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search";
		
		

		// Prefecture GET url
		if (Arrays.asList(prefecturesList).contains(prefecture[0]) && prefecture.length == 1) { // One
																								// prefect
			String urlPrefecture = buildPrefectureUrl(prefecturesList, prefecture);
			//url = "http://www.jpss.jp/en/search/Prefecture/" + prefecture[0] + "/";
			url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
					+ typeUniUrl + admisionMonthUrl + deadLineUrl + ejuUrl + engExamUrl + admisionUniUrl +
					"&u%5Bac%5D=&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search";
			//"http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
			//+ typeUniUrl + admisionMonthUrl + deadLineUrl + ejuUrl + engExamUrl + admisionUniUrl +
			//"&u%5Bac%5D=" + urlStudies + "&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search";
		} else if (prefecture.length > 1) { // If many prefectures choosen
			String urlPrefecture = buildPrefectureUrl(prefecturesList, prefecture);
//			url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D=&a%5Bpf%5D="
//					+ urlPrefecture + "&search.x=177&search.y=11&search=search";
			url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
					+ typeUniUrl + admisionMonthUrl + deadLineUrl + ejuUrl + engExamUrl + admisionUniUrl +
					"&u%5Bac%5D=&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search";
			
			System.out.println("urlPrefecture -> " + urlPrefecture);
		}
		if (prefecture[0].equals("all")) { // If no prefecture choosen
			//url = "http://www.jpss.jp/en/search/?tb=1&search_x=1";
			url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
					+ typeUniUrl + admisionMonthUrl + deadLineUrl + ejuUrl + engExamUrl + admisionUniUrl +
					"&u%5Bac%5D=&a%5Bpf%5D=&search.x=177&search.y=11&search=search";
			prefecture = prefecturesList;
		}

		universityList = UniversityCrawler.crawlUniversities(url, prefecture, typeStudies, universitiesList,
				returnJson, counter);

		return universityList;
	}

	private String buildAdmisionUniUrl(String[] admisionUni) {
		String admisionUniUrl = "";
		String text = "";
		for (String i : admisionUni) {
			if (i.equals("appOutsideJap"))
				text = appOutsideJap;
			if (i.equals("appInsideJap"))
				text = appInsideJap;
			if (i.equals("engToeflUsed"))
				text = engToeflUsed;
			if (i.equals("engOthers"))
				text = engOthers;
			if (i.equals("engToeflEtcUsed"))
				text = engToeflEtcUsed;
			admisionUniUrl = admisionUniUrl + text;
		}
		return admisionUniUrl;
	}

	private String buildEngExamUrl(String[] engExam) {
		String engExamUrl = "";
		String text = "";
		for (String i : engExam) {
			if (i.equals("engNotNecessary"))
				text = engNotNecessary;
			if (i.equals("engNecessary"))
				text = engNecessary;
			if (i.equals("engToeflUsed"))
				text = engToeflUsed;
			if (i.equals("engOthers"))
				text = engOthers;
			if (i.equals("engToeflEtcUsed"))
				text = engToeflEtcUsed;
			engExamUrl = engExamUrl + text;
		}
		return engExamUrl;
	}

	private String buildEjuExamUrl(String[] eju) {
		String ejuUrl = "";
		String text = "";
		for (String i : eju) {
			if (i.equals("ejuYes"))
				text = ejuYes;
			if (i.equals("ejuNo"))
				text = ejuNo;
			ejuUrl = ejuUrl + text;
		}
		return ejuUrl;
	}

	private String buildDeadLineUrl(String[] deadLine) {
		String deadLineUrl = "";
		String text = "";
		for (String i : deadLine) {
			if (i.equals("januaryApp"))
				text = januaryApp;
			if (i.equals("februaryApp"))
				text = februaryApp;
			if (i.equals("marchApp"))
				text = marchApp;
			if (i.equals("aprilApp"))
				text = aprilApp;
			if (i.equals("mayApp"))
				text = mayApp;
			if (i.equals("juneApp"))
				text = juneApp;
			if (i.equals("julyApp"))
				text = julyApp;
			if (i.equals("augustApp"))
				text = augustApp;
			if (i.equals("septemberApp"))
				text = septemberApp;
			if (i.equals("octoberApp"))
				text = octoberApp;
			if (i.equals("novemberApp"))
				text = novemberApp;
			if (i.equals("decemberApp"))
				text = decemberApp;
			deadLineUrl = deadLineUrl + text;
		}
		return deadLineUrl;
	}

	private String buildAdmisionMonthUrl(String[] admisionMonth) {
		String admisionMonthUrl = "";
		String text = "";
		for (String i : admisionMonth) {
			if (i.equals("januaryAdm"))
				text = januaryAdm;
			if (i.equals("marchAdm"))
				text = marchAdm;
			if (i.equals("aprilAdm"))
				text = aprilAdm;
			if (i.equals("mayAdm"))
				text = mayAdm;
			if (i.equals("septemberAdm"))
				text = septemberAdm;
			if (i.equals("octoberAdm"))
				text = octoberAdm;
			admisionMonthUrl = admisionMonthUrl + text;
		}
		return admisionMonthUrl;
	}
	
	private String buildTypeUniUrl(String[] typeUni) {
		String typeUniUrl = "";
		String text = "";
		for (String i : typeUni) {
			if (i.equals("nationalUni"))
				text = nationalUni;
			if (i.equals("publicUni"))
				text = publicUni;
			if (i.equals("privateUni"))
				text = privateUni;
			if (i.equals("jsfuUni"))
				text = jsfuUni;
			typeUniUrl = typeUniUrl + text;
		}
		return typeUniUrl;
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

		
		
		//Armar URL's
		String typeUniUrl = "";
		String admisionMonthUrl = "";
		String deadLineUrl = "";
		String ejuUrl = "";
		String engExamUrl = "";
		String admisionUniUrl = "";
		
		//Type uni URL
		if (typeUni.length > 0) //If many type uni
		typeUniUrl = buildTypeUniUrl(typeUni);
		if (admisionMonth.length > 0)
		admisionMonthUrl = buildAdmisionMonthUrl(admisionMonth);
		if (deadLine.length > 0)
		deadLineUrl = buildDeadLineUrl(deadLine);
		if (eju.length > 0)
		ejuUrl = buildEjuExamUrl(eju);
		if (engExam.length > 0)
		engExamUrl = buildEngExamUrl(engExam);
		if (admisionUni.length > 0)
		admisionUniUrl = buildAdmisionUniUrl(engExam);
		
		String universityList = "";
		String url = "";
		String typeStudies = "";
		//String typeUni = "";
		CollegeList universitiesList = new CollegeList(
				String.join(" ", prefecture) + " " + String.join(" ", typeStudiesList) + typeUni);
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
			if (prefecture.length > 0 && !prefecture[0].equals("all")) { // If many prefectures choosen
			String urlPrefecture = buildPrefectureUrl(prefecturesList, prefecture);
			//url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D=&a%5Bpf%5D="
			//		+ urlPrefecture + "&search.x=177&search.y=11&search=search";
			url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
					+ typeUniUrl + admisionMonthUrl + deadLineUrl + ejuUrl + engExamUrl + admisionUniUrl +
					"&u%5Bac%5D=&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search";
			
			System.out.println("urlPrefecture -> " + urlPrefecture);
			
			if (typeStudiesList.length > 0) { // If many prefectures choosen and many studies choosen
				String urlStudies = buildStudiesUrl(typeStudiesList);
//				url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D="
//						+ urlStudies + "&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search";
				url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
						+ typeUniUrl + admisionMonthUrl + deadLineUrl + ejuUrl + engExamUrl + admisionUniUrl 
						+ urlStudies + "&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search";
				System.out.println("urlStudies -> " + urlStudies);
			}
		}
		//http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5But%5D%5B%5D=2101-102-103-104-105-106-107-111&a%5Bpf%5D=13&search.x=177&search.y=11&search=search
		//http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5But%5D%5B%5D=2&u%5Bac%5D=101-102-103-104-105-106-107-111&a%5Bpf%5D=13&search.x=94&search.y=22&search=search
			
		if (prefecture[0].equals("all")) { // If no prefecture choosen
			//url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D=&a%5Bpf%5D=&search.x=177&search.y=11&search=search";
			String urlPrefecture = buildPrefectureUrl(prefecturesList, prefecture);
			url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
					+ typeUniUrl + admisionMonthUrl + deadLineUrl + ejuUrl + engExamUrl + admisionUniUrl +
					"&u%5Bac%5D=&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search";
			prefecture = prefecturesList;
			if (typeStudiesList.length > 0) { // If many studies choosen
				String urlStudies = buildStudiesUrl(typeStudiesList);
				url = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D="
						+ typeUniUrl + admisionMonthUrl + deadLineUrl + ejuUrl + engExamUrl + admisionUniUrl 
						+ urlStudies + "&a%5Bpf%5D=" + urlPrefecture + "&search.x=177&search.y=11&search=search";
				
				System.out.println("urlStudies -> " + urlStudies);
			}
		}

		// Studies GET
		// final String lawUrl =
		// "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D=201-202&a%5Bpf%5D=&search.x=115&search.y=19&search=search";
		// Tokyo =
		// http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5Bac%5D=201-202&a%5Bpf%5D=13&search.x=118&search.y=18&search=search

		universityList = UniversityCrawler.crawlUniversities(url, prefecture, typeStudies, universitiesList,
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
		return "&u%5Bac%5D="+urlStudies;
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
