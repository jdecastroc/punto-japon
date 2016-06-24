package com.puntojapon.live;

import java.util.Vector;

public class HouseList {
	
	private String searchType; // Search type
	private int searchFound = 0; // Number of jobs listed
	private int page;
	private boolean hasNextPage; // Set whether there is a next page or not to display
	private boolean searchState; // Set whether the search was or not succesful
	private Vector<Object> houseList = new Vector<Object>(); // Store the jobs
	
	public boolean itHasNextPage() {
		return hasNextPage;
	}
	
	public void setHasNextPage(boolean nextPage) {
		this.hasNextPage = nextPage;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public void setSearchFound(int searchFound) {
		this.searchFound = searchFound;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setSearchState(boolean searchState) {
		this.searchState = searchState;
	}

	public void setHouseList(Vector<Object> houseList) {
		this.houseList = houseList;
	}

	public Vector<Object> getHouseList() {
		return houseList;
	}
	
	public boolean addHouse(Object house) {
		getHouseList().addElement(house);
		return true;
	}

	public int getSearchFound() {
		return this.searchFound;
	}

}
