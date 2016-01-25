/**
 * 
 */
package com.puntojapon.main;

/**
 * @author Jorge de Castro
 *
 */
public class CollegeFaculty {
	
	private String facultyName;
	private String facultyUrl;
	
	public CollegeFaculty (String facultyName, String url){
		setFacultyName(facultyName);
		setFacultyUrl(url);
	}
	
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	
	public void setFacultyUrl (String url) {
		this.facultyUrl = url;
	}
	
	public String getFacultyName() {
		return this.facultyName;
	}
	
	public String getFacultyUrl() {
		return this.facultyUrl;
	}

}
