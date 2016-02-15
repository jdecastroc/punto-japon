/**
 * 
 */
package com.puntojapon.colleges;

/**
 * @author Jorge de Castro
 *
 */
public abstract class CollegeBasicInfo {
	private String id;
	private String japaneseName;
	private String name;
	private String prefecture;
	private String type;
	private String imageUrl;
	private String guideUrl;
	private String title = "";
	private String description;
	private String officialUrl;
	private CollegeFacultyList faculties;
	
	public String getOfficialUrl() {
		return officialUrl;
	}

	public void setOfficialUrl(String officialUrl) {
		this.officialUrl = officialUrl;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setJapaneseName(String name) {
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
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public void setGuideUrl(String guideUrl) {
		this.guideUrl = guideUrl;
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
	
	public String getImageUrl() {
		return this.imageUrl;
	}
	
	public String getGuideUrl() {
		return this.guideUrl;
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
