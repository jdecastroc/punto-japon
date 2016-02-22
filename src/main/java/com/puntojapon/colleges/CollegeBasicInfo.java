/**
 * College Basic Info
 * @author jdecastroc
 * @version 2.0, 21 Feb 2016
 *
 */
package com.puntojapon.colleges;

/**
 * CollegeBasicInfo contains the mainly data structure related to the
 * universities, gradSchools and techSchools which information is going to be
 * crawled.
 * 
 * @author jdecastroc
 *
 */
public abstract class CollegeBasicInfo {
	private boolean search = true;
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

	/**
	 * @param officialUrl
	 *            -> set officialUrl crawled
	 */
	public void setOfficialUrl(String officialUrl) {
		this.officialUrl = officialUrl;
	}

	/**
	 * @param search
	 *            -> set whether the search is successful
	 */
	public void setSearch(boolean search) {
		this.search = search;
	}

	/**
	 * @param id
	 *            -> set the faculty id crawled from the URL
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            -> set the college's japanese name crawled
	 */
	public void setJapaneseName(String name) {
		this.japaneseName = name;
	}

	/**
	 * @param name
	 *            -> set the college's english name crawled
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param prefecture
	 *            -> set the college's prefecture crawled
	 */
	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	/**
	 * @param type
	 *            -> set the college's environment type crawled
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param title
	 *            -> set the college's description title crawled
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param imageUrl
	 *            -> set the college's image url crawled
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @param guideUrl
	 *            -> set the college's guideUrl crawled if exists
	 */
	public void setGuideUrl(String guideUrl) {
		this.guideUrl = guideUrl;
	}

	/**
	 * @param description
	 *            -> set the college's description crawled
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param facultyList
	 */
	public void setFaculties(CollegeFacultyList facultyList) {
		this.faculties = facultyList;
	}

	/**
	 * @return web URL from the official university page
	 */
	public String getOfficialUrl() {
		return officialUrl;
	}

	/**
	 * @return whether or not the crawling search has been succesfull
	 */
	public boolean getSearch() {
		return this.search;
	}

	/**
	 * @return the college id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return the college's japanese name
	 */
	public String getJapaneseName() {
		return this.japaneseName;
	}

	/**
	 * @return the college's english name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the college's prefecture
	 */
	public String getPrefecture() {
		return this.prefecture;
	}

	/**
	 * @return the college's environment type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @return the college's image URL
	 */
	public String getImageUrl() {
		return this.imageUrl;
	}

	/**
	 * @return the college's guide URL
	 */
	public String getGuideUrl() {
		return this.guideUrl;
	}

	/**
	 * @return the college's description title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @return the college's description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the college's faculty list in order to be manipulated
	 */
	public CollegeFacultyList getFacultyList() {
		return this.faculties;
	}

}
