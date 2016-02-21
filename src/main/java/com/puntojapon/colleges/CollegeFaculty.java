/**
 * College Faculty
 * @author jdecastroc
 * @version 2.0, 21 Feb 2016
 *
 */
package com.puntojapon.colleges;

/**
 * CollegeFaculty mainly contains data structure and the functions used when
 * crawling for faculties
 * 
 * @author jdecastroc
 */
public class CollegeFaculty {

	private String facultyName;
	private String facultyUrl;

	/**
	 * Mainly class constructor which sets the facultyName and the faculty URL
	 * 
	 * @param facultyName
	 *            -> name of the faculty
	 * @param url
	 *            -> url of the faculty
	 */
	public CollegeFaculty(String facultyName, String url) {
		setFacultyName(facultyName);
		setFacultyUrl(url);
	}

	/**
	 * @param facultyName
	 *            -> set the name of the faculty
	 */
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	/**
	 * @param url
	 *            -> set the url of the faculty
	 */
	public void setFacultyUrl(String url) {
		this.facultyUrl = url;
	}

	/**
	 * @return the faculty name
	 */
	public String getFacultyName() {
		return this.facultyName;
	}

	/**
	 * @return the faculty URL
	 */
	public String getFacultyUrl() {
		return this.facultyUrl;
	}

}
