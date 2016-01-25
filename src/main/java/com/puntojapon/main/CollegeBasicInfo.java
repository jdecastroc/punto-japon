/**
 * 
 */
package com.puntojapon.main;

/**
 * @author Jorge de Castro
 *
 */
public class CollegeBasicInfo {
	private String id;
	private String japaneseName;
	private String name = "";
	private String prefecture = "";
	private String type = "";  //Public or private
	private String collegeType = "";
	private boolean guideLink = false;
	private boolean imageLink = false;
	private String title = "";
	private String description = "";
	private CollegeFacultyList faculties;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setJapeneseName(String name) {
		this.japaneseName = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setCollegeType(String type) {
		this.collegeType = type;
	}
	
	public void setGuideLink(boolean state) {
		this.guideLink = state;
	}
	
	public void setImageLink(boolean state) {
		this.imageLink = state;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription (String description) {
		this.description = description;
	}
	
	public void setFaculties (CollegeFacultyList facultyList) {
		this.faculties = facultyList;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getJapaneseName() {
		return this.japaneseName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPrefecture() {
		return this.prefecture;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getCollegeType() {
		return this.collegeType;
	}
	
	public String getGuideLink() {
		String address = "";
		String typeGuide = "";
		switch (getCollegeType()) {
		case "university": 
			typeGuide = "univ";
			break;
		case "graduate_school": 
			typeGuide = "grad";
			break;
		}
		if (this.guideLink) {
			return address = "http://www.jpss.jp/uploads/" + typeGuide + "/" + getId() + "/guide.zip";
		} else {
			return address;
		}
	}
	
	public String getImageLink() {
		String address = "";
		String typeImage = "";
		switch (getCollegeType()){
		case "university":
			typeImage = "univ";
			break;
		case "graduate_school":
			typeImage = "grad";
			break;
		}
		
		if (this.imageLink) {
			return address = "http://www.jpss.jp/uploads/" + typeImage + "/" + getId() + "/main.jpg";
		} else {
			return address;
		}
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public CollegeFacultyList getFacultyList() {
		return this.faculties;
	}
	
}
