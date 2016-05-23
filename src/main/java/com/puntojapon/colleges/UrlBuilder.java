/**
 * URL Builder
 * @author jdecastroc
 * @version 2.0, 21 Feb 2016
 *
 */
package com.puntojapon.colleges;

/**
 * Provides the abstract class used by the college crawlers. Provides the data
 * structure and functions to build the url based on the crawled website
 * 
 * @author jdecastroc
 */
public abstract class UrlBuilder {

	// Prefectures in crawler numerical order
	protected static final String[] PREFECTURES_LIST = { "Hokkaido", "Aomori", "Iwate", "Miyagi", "Akita", "Yamagata",
			"Fukushima", "Ibaraki", "Tochigi", "Gunma", "Saitama", "Chiba", "Tokyo", "Kanagawa", "Niigata", "Toyama",
			"Ishikawa", "Fukui", "Yamanashi", "Nagano", "Gifu", "Shizuoka", "Aichi", "Mie", "Shiga", "Kyoto", "Osaka",
			"Hyogo", "Nara", "Wakayama", "Tottori", "Shimane", "Okayama", "Hiroshima", "Yamaguchi", "Tokushima",
			"Kagawa", "Ehime", "Kochi", "Fukuoka", "Saga", "Nagasaki", "Kumamoto", "Oita", "Miyazaki", "Kagoshima",
			"Okinawa"};

	protected String url = "";
	protected String[] prefecture;
	protected String urlPrefecture = "";

	/**
	 * @return the list with the prefectures of Japan.
	 */
	protected String[] getPrefecturesList() {
		return UrlBuilder.PREFECTURES_LIST;
	}

	/**
	 * @return the url builded in order to crawl it
	 */
	protected String getUrl() {
		return url;
	}

	/**
	 * @return the prefecture list which is going to be evaluated in the
	 *         crawling
	 */
	protected String[] getPrefecture() {
		return prefecture;
	}

	/**
	 * @return the prefecture url build for the final url
	 */
	protected String getUrlPrefecture() {
		return urlPrefecture;
	}

	/**
	 * @param urlPrefecture
	 *            -> set the prefecture url which is part of the final url
	 */
	protected void setUrlPrefecture(String urlPrefecture) {
		this.urlPrefecture = urlPrefecture;
	}

	/**
	 * @param url
	 *            -> set the final url
	 */
	protected void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param prefecture
	 *            -> set the prefectures list which is going to be evaluated in
	 *            the crawling
	 */
	protected void setPrefecture(String[] prefecture) {
		this.prefecture = prefecture;
	}

	/**
	 * Return the final url to be crawled. The final url is the search based url
	 * used in the website to crawl given the search parameters provided by the
	 * client
	 * 
	 * @param prefecture
	 *            -> list of prefectures where to search the colleges
	 * @return the final url used to be crawled
	 */
	public abstract String getSearchUrl(String[] prefecture);

	/**
	 * Build the url related to the prefectures. It's part of the final url. The
	 * function evaluate the given prefectures to check whether them match to
	 * the real prefectures or not in order to provide a correct url string
	 * 
	 * @param prefecturesList
	 *            -> list with all the prefectures of Japan
	 * @param prefecture
	 *            -> list of prefectures provided by the client
	 * @return the url related to the prefectures
	 */
	protected String buildPrefectureUrl(String[] prefecturesList, String[] prefecture) {
		String urlPrefectures = "";
		for (int i = 0; i < prefecturesList.length; i++) {
			for (int k = 0; k < prefecture.length; k++) {
				if (prefecture[k].contains(prefecturesList[i])) {
					int m = i + 1;
					urlPrefectures = urlPrefectures + m + "-";
				}
			}
		}
		if (!urlPrefectures.equals(""))
			urlPrefectures = urlPrefectures.substring(0, urlPrefectures.length() - 1);
		return urlPrefectures;
	}

}
