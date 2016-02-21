/**
 * College List
 * @author jdecastroc
 * @version 2.0, 21 Feb 2016
 *
 */
package com.puntojapon.colleges;

import java.util.Vector;

/**
 * CollegeList mainly contains the search structure when crawling the colleges.
 * It saves the search status and the list of the crawled colleges
 * 
 * @author jdecastroc
 */
public class CollegeList {
	private String searchType; // Search type
	private int searchFound; // Number of found universities
	private boolean searchState; // Set whether the search was or not succesful
	private Vector<Object> collegeList; // Store the colleges

	/**
	 * Mainly class constructor which sets the search type string and initialize
	 * the college's list
	 * 
	 * @param searchType
	 *            -> Specifies the search type based on the search options
	 */
	public CollegeList(String searchType) {
		this.setCollegeListType(searchType);
		this.collegeList = new Vector<Object>();
	}

	/**
	 * @param type
	 *            -> sets the search type string
	 */
	public void setCollegeListType(String type) {
		this.searchType = type;
	}

	/**
	 * @param number
	 *            -> sets the number of found colleges
	 */
	public void setSearchFound(int number) {
		this.searchFound = number;
	}

	/**
	 * @param state
	 *            -> sets whether the search was or not succesful
	 */
	public void setSearchState(boolean state) {
		this.searchState = state;
	}

	/**
	 * @return the number of found colleges
	 */
	public int getSearchFound() {
		return this.searchFound;
	}

	/**
	 * @return whether the search was or not succesful
	 */
	public boolean getSearchState() {
		return this.searchState;
	}

	/**
	 * @return the search type string
	 */
	public String getCollegeListType() {
		return this.searchType;
	}

	/**
	 * @return the list of the colleges in order to be manipulated
	 */
	public Vector<Object> getCollegeList() {
		return this.collegeList;
	}

	/**
	 * @param college
	 *            -> college object (University, gradSchool or techSchool) which
	 *            is going to be added in the college list
	 * @return whether or not the adding operation was succesful
	 */
	public boolean addCollege(Object college) {
		getCollegeList().addElement(college);
		return true;
	}

}