/**
 * Tech School Url Builder
 * @author jdecastroc
 * @version 2.0, 21 Feb 2016
 *
 */
package com.puntojapon.colleges;

/**
 * Provides the url Builder for the techSchools. It builds the url which is
 * going to be crawled following the url specifications of the crawling page
 * 
 * @author jdecastroc
 */
public class TechSchoolUrlBuilder extends UrlBuilder {

	private String nameTech = "";

	/**
	 * Mainly class constructor. Build the search information provided by the
	 * client in the urlBuilder.
	 * 
	 * @param nameTech
	 *            -> name of the tech school provided by the client
	 * @param prefecture
	 *            -> list of prefectures where search the techSchools
	 */
	public TechSchoolUrlBuilder(String nameTech, String[] prefecture) {
		// Build URL's
		setPrefecture(prefecture);

		if (prefecture.length > 0 && !prefecture[0].equals("all"))
			setUrlPrefecture(buildPrefectureUrl(getPrefecturesList(), prefecture));
		if (!nameTech.isEmpty())
			setNameTech(nameTech);
	}

	/**
	 * Build the final url which is going to be used by the crawler
	 * 
	 * @param prefecture
	 *            -> prefecture list provided by the client which is going to be
	 *            compared with the prefectures real list in order to check
	 *            errors
	 * @return the url which is going the be used by the crawler as a string
	 */
	@Override
	public String getSearchUrl(String[] prefecture) {

		if (getPrefecture()[0].equals("all")) { // If no prefecture choosen
			setUrl("http://www.jpss.jp/en/search/?tb=3&a%5Bnm%5D=" + getNameTech()
					+ "&a%5Bfw%5D=&a%5Bpf%5D=&search.x=0&search.y=0&search=search");
		} else {
			setUrl("http://www.jpss.jp/en/search/?tb=3&a%5Bnm%5D=" + getNameTech() + "&a%5Bfw%5D=&a%5Bpf%5D="
					+ getUrlPrefecture() + "&search.x=0&search.y=0&search=search");
		}
		return getUrl();
	}

	/**
	 * @return the name of the techSchool provided by the client
	 */
	private String getNameTech() {
		return nameTech;
	}

	/**
	 * @param nameTech
	 *            -> set the techSchool name provided
	 */
	private void setNameTech(String nameTech) {
		this.nameTech = nameTech;
	}

}
