/**
 * JobsCrawler
 * @author jdecastroc
 * @version 1.0, 24 Apr 2016
 *
 */
package com.puntojapon.work;

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;

import com.puntojapon.common.RandomUserAgent;

//import eu.bitwalker.useragentutils.UserAgent;

/**
 * JobsCrawler performs a simple API call to the ApplyQ API. By selecting the
 * area to search the jobs, the call retrieves a json file sorted by date
 * 
 * @author jdecastroc
 *
 */
public class JobsCrawler {

	private static final String[] PREFECTURES_LIST = { "hokkaido", "aomori", "iwate", "miyagi", "akita", "yamagata",
			"fukushima", "ibaraki", "tochigi", "gunma", "saitama", "chiba", "tokyo", "kanagawa", "niigata", "toyama",
			"ishikawa", "fukui", "yamanashi", "nagano", "gifu", "shizuoka", "aichi", "mie", "shiga", "kyoto", "osaka",
			"hyogo", "nara", "wakayama", "tottori", "shimane", "okayama", "hiroshima", "yamaguchi", "tokushima",
			"kagawa", "ehime", "kochi", "fukuoka", "saga", "nagasaki", "kumamoto", "oita", "miyazaki", "kagoshima",
			"okinawa" };

	/**
	 * getJobsApplyq perform the call to the ApplyQ API and retrieve his json
	 * file after check that the chosen prefecture is correct
	 * 
	 * @param prefecture -> prefecture where to search the jobs
	 * @param page -> page related to the jobs in the ApplyQ API
	 * @return json file provided by the ApplyQ API
	 * @throws IOException
	 */
	// public String getJobsApplyq(String prefecture, UserAgent getUserAgent)
	// throws IOException {
	public String getJobsApplyq(String prefecture, int page) throws IOException {
		// TODO Comprobar que la prefectura se encuentra en la lista
		// TODO page?

		String jobsJson = "";

		if (Arrays.asList(PREFECTURES_LIST).contains(prefecture.trim().toLowerCase()) && page >= 0) {

			String url = "https://www.applyq.com/jobfeed_json?pro=all&geo=" + prefecture + "&page=" + page;

			// PRUEBA
			// UserAgent.parseUserAgentString(request.getHeader("User-Agent"))
			// TODO cambiar USER AGENT

			jobsJson = Jsoup.connect(url).userAgent(RandomUserAgent.getRandomUserAgent()).timeout((int)Math.random() * 5)
					.ignoreContentType(true).execute().body();
		} else {
			jobsJson = "Invalid prefecture";
		}

		return jobsJson;
	}
}
