/**
 * College Crawler
 * @author jdecastroc
 * @version 2.0, 21 Feb 2016
 *
 */
package com.puntojapon.colleges;

/**
 * CollegeCrawler contains de main data structure and functions followed by the
 * crawlers. Each crawler use the functions in a different way depending of the
 * html crawled page.
 * 
 * @author jdecastroc
 */
public abstract class CollegeCrawler {
	private int collegeCounter;

	/**
	 * Mainly constructor of CollegeCrawler. It set's the college's counter to 0
	 * in order to start counting
	 */
	public CollegeCrawler() {
		setCollegeCounter(0);
	};

	/**
	 * @return the college's counter in order to know how many colleges have
	 *         been crawled
	 */
	public int getCollegeCounter() {
		return collegeCounter;
	}

	/**
	 * @param collegeCounter
	 *            set the collegeCounter. The mainly class constructor sets it
	 *            to 0
	 */
	public void setCollegeCounter(int collegeCounter) {
		this.collegeCounter = collegeCounter;
	}

	/**
	 * @param url
	 *            -> url to be crawled
	 * @param prefectureSearchName
	 *            -> prefecture list related with the colleges that are going to
	 *            be crawled
	 * @param universitiesList
	 *            -> list of crawled universities with the recursive function
	 * @param jsonUniversitiesList
	 *            -> Final string which is going to be converted to json format
	 *            to retrieve the crawled data
	 * @param counter
	 *            -> College counter for search details
	 * 
	 * @return json object with the search results
	 * @throws Exception
	 */
	public abstract String getCollegeList(String url, String[] prefectureSearchName, CollegeList universitiesList,
			String jsonUniversitiesList, int counter, String remoteIp) throws Exception;

	/**
	 * @param id
	 *            of the college which is going to be crawled
	 * @return json object with the college information
	 */
	public abstract String getCollege(String id);

	/**
	 * @param parent
	 *            -> id of the faculty's university
	 * @param id
	 *            -> id of the faculty
	 * @return json object with the faculty admissions information
	 */
	public abstract String getFacultyAdmissions(String parent, String id);

	/**
	 * @param parent
	 *            -> id of the faculty's university
	 * @param id
	 *            -> id of the faculty
	 * @return json object with the faculty basic information
	 */
	public abstract String getFacultyInfo(String parent, String id);

	/**
	 * @param parent
	 *            -> id of the faculty's university
	 * @param id
	 *            -> id of the faculty
	 * @return json object with the faculty support information
	 */
	public abstract String getFacultySupport(String parent, String id);

	/**
	 * @param parent
	 *            -> id of the faculty's university
	 * @param id
	 *            -> id of the faculty
	 * @return json object with the faculty facilities information
	 */
	public abstract String getFacultyFacilities(String parent, String id);

	/**
	 * @param parent
	 *            -> id of the faculty's university
	 * @param id
	 *            -> id of the faculty
	 * @return json object with the faculty access information
	 */
	public abstract String getFacultyAccess(String parent, String id);
	
	public static boolean matchStringOnArray(String[] arr, String targetValue) {
		for(String s: arr){
			if(s.equals(targetValue) || targetValue.equals("all"))
				return true;
		}
		return false;
	}

}
