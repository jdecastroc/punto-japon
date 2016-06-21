package com.puntojapon.work;

import java.util.Vector;

public class JobsList {
	
	private String searchType; // Search type
	private int searchFound = 0; // Number of jobs listed
	private int page;
	private boolean hasNextPage; // Set whether there is a next page or not to display
	private boolean searchState; // Set whether the search was or not succesful
	private Vector<Object> jobsList = new Vector<Object>(); // Store the jobs
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public int getSearchFound() {
		return searchFound;
	}
	public void setSearchFound(int searchFound) {
		this.searchFound = searchFound;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public boolean isSearchState() {
		return searchState;
	}
	public void setSearchState(boolean searchState) {
		this.searchState = searchState;
	}
	public Vector<Object> getJobsList() {
		return jobsList;
	}
	public void setJobsList(Vector<Object> jobsList) {
		this.jobsList = jobsList;
	}
	public boolean addJob(Object job) {
		getJobsList().addElement(job);
		return true;
	}

}
