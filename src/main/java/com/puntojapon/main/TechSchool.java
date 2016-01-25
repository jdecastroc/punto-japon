package com.puntojapon.main;

public class TechSchool extends CollegeBasicInfo{
	
	public TechSchool(String id, String japaneseName, String name, String prefecture, String type,
			String collegeType, boolean guideLinkState, boolean imageLinkState,
			String title, String description, CollegeFacultyList faculties){
	this.setId(id);
	this.setJapeneseName(japaneseName);
	this.setName(name);
	this.setPrefecture(prefecture);
	this.setType(type);
	this.setCollegeType(collegeType);
	this.setGuideLink(guideLinkState);
	this.setImageLink(imageLinkState);
	this.setTitle(title);
	this.setDescription(description);
	this.setFaculties(faculties);

	}
}