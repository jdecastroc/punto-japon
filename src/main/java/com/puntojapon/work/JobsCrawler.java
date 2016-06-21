/**
 * JobsCrawler
 * @author jdecastroc
 * @version 1.0, 24 Apr 2016
 *
 */
package com.puntojapon.work;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.puntojapon.common.RandomUserAgent;

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
	
	//TODO Final list of professions

	/**
	 * getJobsApplyq perform the call to the ApplyQ API and retrieve his json
	 * file after check that the chosen prefecture is correct
	 * 
	 * @param prefecture
	 *            -> prefecture where to search the jobs
	 * @param page
	 *            -> page related to the jobs in the ApplyQ API
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

			jobsJson = Jsoup.connect(url).userAgent(RandomUserAgent.getRandomUserAgent())
					.timeout((int) Math.random() * 5).ignoreContentType(true).execute().body();
		} else {
			jobsJson = "Invalid prefecture";
		}

		return jobsJson = "";
	}

	public String getJobs(String prefecture, String specialty, int page) throws IOException {

		String jsonJobs = "";

		// Jobs list
		JobsList jobsList = new JobsList();
		jobsList.setSearchType("Job offers search");
		jobsList.setPage(page);
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		if (Arrays.asList(PREFECTURES_LIST).contains(prefecture.trim().toLowerCase()) && page >= 0) {

			// Main info of each job
			String name = "";
			String publishDate = "";
			String company = "";
			String location = "";
			ArrayList<String> tags = new ArrayList<String>();
			String description = "";
			String link = "";

			Document document = Jsoup
					.connect("https://www.applyq.com/jobs/" + prefecture + "/" + specialty + "?p=" + page)
					.userAgent(RandomUserAgent.getRandomUserAgent()).timeout((int) Math.random() * 5).get();

			Elements jobsSource = document.select("div#jobpost_search_list");

			for (Element sourceElement : jobsSource) {

				// Check has next page
				if (sourceElement.select("div.row.pagination.nopagenum > a.cell").first() != null) {
					jobsList.setHasNextPage(true);
				} else {
					jobsList.setHasNextPage(false);
				}

				Elements jobOffers = sourceElement.select("div.row.item");

				for (Element offer : jobOffers) {

					// Name
					if (offer.select("a.w6").first() != null) {
						name = offer.select("a.w6").first().text().trim();
					}

					// Publish Date
					if (offer.select("div.w1").first() != null) {
						publishDate = offer.select("div.w1").first().text().trim();
					}

					// Company
					if (offer.select("div.w3").first() != null) {
						company = offer.select("div.w3").first().text().trim();
					}

					// Location
					if (offer.select("div.w2").first() != null) {
						location = offer.select("div.w2").first().text().trim();
					}

					// Tags
					if (offer.select("div.jobpost_profession > a").first() != null) {

						Elements tagArea = offer.select("div.jobpost_profession").first().select("a");
						for (Element tag : tagArea) {
							tags.add(tag.text().trim());
						}
					}

					// Description and link
					if (offer.select("a.jobpost_short").first() != null) {
						description = offer.select("a.jobpost_short").text().trim() + "...";
						link = offer.select("a.jobpost_short").attr("href").trim();
					}

					jobsList.addJob(new JobOffer(name, publishDate, company, location, tags, description, link));
					jobsList.setSearchFound(jobsList.getSearchFound() + 1);
					tags.clear();

				}

			}

		}
		if (jobsList.getSearchFound() > 0) {
			jobsList.setSearchState(true);
		} else {
			jobsList.setSearchState(false);
		}
		jsonJobs = gson.toJson(jobsList);
		return jsonJobs;
	}
}
