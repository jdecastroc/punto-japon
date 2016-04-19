package com.puntojapon.languageSchools;

import java.util.Vector;

public class SchoolList {
	
	private boolean search = false;
	private String schoolArea = "";
	private int schoolCounter = 0;
	private Vector<School> schoolList = new Vector<School>();
	
	public SchoolList(String area) {
		setSchoolArea(area);
	}
	
	public void setSearch() {
		this.search = true;
	}
	
	public void setSchoolArea(String area) {
		this.schoolArea = area;
	}
	
	public void increaseSchoolCounter() {
		this.schoolCounter++;
	}
	
	public void addSchool (String id, String name, String address) {
		if (!isSearch()){
			setSearch();
		}
		getSchoolList().addElement(new School(id, name, address));
		increaseSchoolCounter();
	}
	
	public boolean isSearch() {
		return search;
	}
	
	public int schoolCounter() {
		return schoolCounter;
	}
	
	public Vector<School> getSchoolList() {
		return schoolList;
	}
	
	private class School {
		private String id;
		private String name;
		private String address;
		
		private School(String id, String name, String address) {
			
			setId(id);
			setName(name);
			setAddress(address);
		}
		
		private void setId(String id) {
			this.id = id;
		}
		
		private void setName(String name) {
			this.name = name;
		}
		
		private void setAddress(String address) {
			this.address = address;
		}
		
		
	}
	
}
