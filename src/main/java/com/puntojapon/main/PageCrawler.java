/**
 * 
 */
package com.puntojapon.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Jorge de Castro
 *
 */
public class PageCrawler {

	public static String crawlPage(String id, String crawlerType) throws Exception {

		//TODO Adaptar para que sirva para universidades, escuelas de posgrado y fp
		// Create the College List of Universities
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		String jsonUniversity = "";
		String url = "";
		
		if (crawlerType.equals("university")){
			System.out.println("Estoy -> http://www.jpss.jp/en/univ/" + id + "/");
			url = "http://www.jpss.jp/en/univ/" + id + "/";
		}
		if (crawlerType.equals("grad")){
			System.out.println("Estoy -> http://www.jpss.jp/en/grad/" + id + "/");
			url = "http://www.jpss.jp/en/grad/" + id + "/";	
		}
		if (crawlerType.equals("fp")){
			System.out.println("Estoy -> http://www.jpss.jp/en/tech/" + id + "/");
			url = "http://www.jpss.jp/en/tech/" + id + "/";	
		}

		// Main info of each uni
		// String id = "";
		String japaneseName = "";
		String name = "";
		String prefecture = "";
		String type = "";
		String collegeType = crawlerType;
		String imageUrl = "";
		String guideUrl = "";
		String title = "";
		String description = "";
		String officialUrl = "";

		// Crawler
		// TODO Change userAgent when application finished
		Document document = Jsoup.connect(url).userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0)
				.get();

		Elements text = document.select("div#schoolContainer");
		for (Element element : text) {
			// Getting prefecture and type for universities and GradSchools
			if(element.select("div.titleWrapper > p").first() != null){
				Element getPrefecture = element.select("div.titleWrapper > p").first();
				String prefectureDirty = getPrefecture.text();

				prefectureDirty = prefectureDirty.replaceAll("&" + "nbsp;", " ");
				prefectureDirty = prefectureDirty.replaceAll(String.valueOf((char) 160), "");
				prefecture = prefectureDirty.split("/")[1];
				type = prefectureDirty.split("/")[2];
				// type = translate(type);
			}
			// Getting prefecture and type for FP
			if(element.select("div#SchoolName > p").first() != null){
				
				Element getType = element.select("div#SchoolName > p > span").first();
				type = getType.text();
				Element getPrefecture = element.select("div#SchoolName > p").first();
				String prefectureDirty = getPrefecture.text();

				prefectureDirty = prefectureDirty.replaceAll("&" + "nbsp;", " ");
				prefectureDirty = prefectureDirty.replaceAll(String.valueOf((char) 160), "");
				prefecture = prefectureDirty.split("/")[1];
			}

			// Japanese name and english name for uni and grad schools
			if(element.select("div.titleWrapper > h2 > span").first() != null){
			Element getJapaneseName = element.select("div.titleWrapper > h2 > span").first();
			japaneseName = getJapaneseName.text().trim();

			Element getName = getJapaneseName.nextElementSibling();
			name = getName.text().trim();
			}
			// Japanese name and english name if FP
			if(element.select("div#SchoolName > h2 > span").first() != null){
				Element getJapaneseName = element.select("div#SchoolName > h2 > span").first();
				japaneseName = getJapaneseName.text().trim();

				Element getName = getJapaneseName.nextElementSibling();
				name = getName.text().trim();
			}
			
			// Getting guide link
			if (element.select("div.dlBtn > a").first() != null) {
				Element getGuideUrl = element.select("div.dlBtn > a").first();
				guideUrl = getGuideUrl.attr("href");
				// guideUrl = guideUrl.replace("/en", "");
			}

			// Getting image link
			if (element.select("div#SchoolHead > div.leftBlock > img").first() != null) {
				Element getImageUrl = element.select("div#SchoolHead > div.leftBlock > img").first();
				imageUrl = getImageUrl.attr("src");
			}

			// Getting title
			if (element.select("blockquote > h4.lText").first() != null) {
				Element getTitle = element.select("blockquote > h4.lText").first();
				title = getTitle.text();
			}

			// Getting description
			if (element.select("blockquote > p").first() != null) {
				Element getDescription = element.select("blockquote > p").first();
				description = getDescription.text();
			}

			// Official URL
			if (element.select("div.bottomOsLink > p > a").first() != null) {
				Element getOfficialUrl = element.select("div.bottomOsLink > p > a")
						.first();
				
				officialUrl = getOfficialUrl.text();
			}
			//For fp pages
			if (element.select("div#OsLink > p > a").first() != null) {
				Element getOfficialUrl = element.select("div#OsLink > p > a")
						.first();
				
				officialUrl = getOfficialUrl.text();
			}

			CollegeFacultyList facultyList = new CollegeFacultyList(name);

			Elements facultiesDiv = element.select("div#SchoolMenu > ul.clearFix.padT15 > li");
			for (Element facultyContent : facultiesDiv) {
				Element facultiesRaw = facultyContent.select("a").first();

				String facultyTitle = facultiesRaw.attr("title");
				String facultyHref = facultiesRaw.attr("href");

				facultyList.addCollegeFaculty(new CollegeFaculty(facultyTitle, facultyHref));
			}
			
			if (crawlerType.equals("university")){
				University college = new University(id, japaneseName, name, prefecture, type, collegeType, guideUrl, imageUrl,
					title, description, facultyList, officialUrl);
				jsonUniversity = gson.toJson(college);
			}
			if (crawlerType.equals("grad")){
				GradSchool college = new GradSchool(id, japaneseName, name, prefecture, type, collegeType, guideUrl, imageUrl,
						title, description, facultyList, officialUrl);
				jsonUniversity = gson.toJson(college);
			}
			if (crawlerType.equals("fp")){
				TechSchool college = new TechSchool(id, japaneseName, name, prefecture, type, collegeType, guideUrl, imageUrl,
						title, description, facultyList, officialUrl);
				jsonUniversity = gson.toJson(college);
			}
			
		}
		
		return jsonUniversity;
	}
}
