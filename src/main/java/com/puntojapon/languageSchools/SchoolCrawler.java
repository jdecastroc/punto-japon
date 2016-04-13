/**
 * Language School Crawler
 * @author jdecastroc
 * @version 1.0, 13 Apr 2016
 *
 */

package com.puntojapon.languageSchools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.*;
import com.puntojapon.colleges.CollegeList;

public class SchoolCrawler {

	public String getSchoolInfo(String idSchool) throws Exception {

		// Create the School json structure
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		SchoolInfo languageSchool = new SchoolInfo();
		
		String url = "http://www.nisshinkyo.org/search/college.php?lng=2&id=" + idSchool;
		System.out.println("Voy a -> " + url);

		// Crawler
		// TODO Change userAgent when application finished
		Document document = Jsoup.connect(url).userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0)
				.get();

		Elements text = document.select("div#mainPad");
		
		for(Element element : text) {
			// English title
			if (element.select("h2.collegeTitle") != null) {
				languageSchool.setName(element.select("h2.collegeTitle").first().text().trim());
			}
			
			//Japanese title
			if (element.select("p.bsp10") != null) {
				languageSchool.setJapaneseName(element.select("p.bsp10").first().text().trim());
			}
			
			//TODO Don't know why is the number (ID)
			if (element.select("div.floatBox > div.floL > p.bsp10") != null) {
				languageSchool.setId(element.select("div.floatBox > div.floL > p.bsp10").first().text().trim());
			}
	
		}
		
		return gson.toJson(languageSchool);
	}

}
