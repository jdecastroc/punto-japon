package com.puntojapon.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.*;

import com.puntojapon.colleges.CollegeList;
import com.puntojapon.colleges.UniversityCrawler;
import com.puntojapon.colleges.UniversityUrlBuilder;

public class CrawlerTest {

	@Test
	public void universityCrawlerTest() throws Exception {
		System.out.println("Running crawler test...");

		int crawlerUniversities = 0;
		int realUniversitiesCount = 0;
		
		// Test battery
		String[] prefecture = {"all"};
		String[] typeStudiesList = {"Literature", "Language"};
		String nameUni = "";
		String[] typeUni = {"national"};
		String[] admisionMonth = {};
		String[] deadLine = {};
		String[] eju = {"ejuYes"};
		String[] engExam = {"engNecessary"};
		String[] admisionUni = {};
		
		String url = "";
		String correctUrl = "http://www.jpss.jp/en/search/?tb=1&a%5Bnm%5D=&a%5Bfw%5D=&u%5Bfc%5D=&u%5Bdp%5D=&u%5But%5D%5B%5D=1&u%5Bej%5D%5B%5D=1&u%5Ben%5D%5B%5D=2&u%5Bac%5D=101-102-103-104-105-106-107-111-151-160-166&a%5Bpf%5D=&search.x=123&search.y=9&search=search";
		
		CollegeList universitiesList = new CollegeList(String.join(" ", prefecture)+ typeUni);
		String returnJson = "";
		int counter = 0;

		UniversityUrlBuilder search = new UniversityUrlBuilder(nameUni, prefecture, typeStudiesList, typeUni,
				admisionMonth, deadLine, eju, engExam, admisionUni);
		url = search.getSearchUrl(prefecture);
		
		UniversityCrawler crawlerTest1 = new UniversityCrawler();
		crawlerTest1.getCollegeList(url, prefecture, universitiesList, returnJson,
				counter);
		
		crawlerUniversities = crawlerTest1.getCollegeCounter();
		realUniversitiesCount = Integer.parseInt(realCrawlerCounter(correctUrl));
		
		System.out.println("Got: " + crawlerUniversities + " throug " + url);
		System.out.println("Expected: " + realUniversitiesCount + " throug " + correctUrl);
		
		assertTrue(crawlerUniversities == realUniversitiesCount);
		
	}
	
	public static String realCrawlerCounter(String url) throws IOException{
		String uniCounter = "";
		Document document = Jsoup.connect(url).userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0).get();
		
		Elements text = document
				.select("div[id=SearchResult]");
		for (Element element : text) { // Take all the uni divs

			// Getting university ID
			Element getCounter = element.select("p[class=padB15 mText]").first();
			uniCounter = getCounter.text();
			uniCounter = uniCounter.split(" ")[0];
		}
		return uniCounter;
	}
	
}

