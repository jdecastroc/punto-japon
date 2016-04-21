package com.puntojapon.work;

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import eu.bitwalker.useragentutils.UserAgent;

public class JobsCrawler {

	private static final String[] PREFECTURES_LIST = { "hokkaido", "aomori", "iwate", "miyagi", "akita", "yamagata",
			"fukushima", "ibaraki", "tochigi", "gunma", "saitama", "chiba", "tokyo", "kanagawa", "niigata", "toyama",
			"ishikawa", "fukui", "yamanashi", "nagano", "gifu", "shizuoka", "aichi", "mie", "shiga", "kyoto", "osaka",
			"hyogo", "nara", "wakayama", "tottori", "shimane", "okayama", "hiroshima", "yamaguchi", "tokushima",
			"kagawa", "ehime", "kochi", "fukuoka", "saga", "nagasaki", "kumamoto", "oita", "miyazaki", "kagoshima",
			"okinawa" };

	// public String getJobsApplyq(String prefecture, UserAgent getUserAgent)
	// throws IOException {
	public String getJobsApplyq(String prefecture, int page) throws IOException {
		// TODO Comprobar que la prefectura se encuentra en la lista
		// TODO page?

		String jobsJson = "";
		
		if(Arrays.asList(PREFECTURES_LIST).contains(prefecture.trim().toLowerCase()) && page >= 0) {
			
		String url = "https://www.applyq.com/jobfeed_json?pro=all&geo=" + prefecture + "&page=" + page;

		// PRUEBA
		// UserAgent.parseUserAgentString(request.getHeader("User-Agent"))
		// TODO cambiar USER AGENT
		
		jobsJson = Jsoup.connect(url).userAgent("Puntojaponbot/0.1 (+jdecastrocabello@gmail.com").timeout(0)
				.ignoreContentType(true).execute().body();
		} else {
			jobsJson = "Invalid prefecture";
		}

		return jobsJson;
	}
}
