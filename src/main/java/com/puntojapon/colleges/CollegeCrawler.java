package com.puntojapon.colleges;

public abstract class CollegeCrawler {
	private int collegeCounter;

	public CollegeCrawler() {
		setCollegeCounter(0);
	};

	public int getCollegeCounter() {
		return collegeCounter;
	}

	public void setCollegeCounter(int collegeCounter) {
		this.collegeCounter = collegeCounter;
	}

	public abstract String getCollegeList(String url, String[] prefectureSearchName, CollegeList universitiesList,
			String jsonUniversitiesList, int counter) throws Exception;

	public abstract String getCollege(String id) throws Exception;

	public abstract String getFacultyAdmissions(String parent, String id) throws Exception;
	
	public abstract String getFacultyInfo(String parent, String id) throws Exception;

}
