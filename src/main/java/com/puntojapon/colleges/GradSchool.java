/**
 * Faculty data structure
 * @author jdecastroc
 * @version 2.0, 21 Feb 2016
 *
 */
package com.puntojapon.colleges;

/**
 * Provides the gradSchool data structure used in the gradSchool crawler
 * 
 * @author jdecastroc
 * @see com.puntojapon.colleges.CollegeBasicInfo
 */
public class GradSchool extends CollegeBasicInfo {

	public GradSchool(String id, String japaneseName, String name, String prefecture, String type, String guideUrl,
			String imageUrl, String title, String description, CollegeFacultyList faculties, String officialUrl) {

		this.setId(id);
		this.setJapaneseName(japaneseName);
		this.setName(name);
		this.setPrefecture(prefecture);
		this.setType(type);
		this.setGuideUrl(guideUrl);
		this.setImageUrl(imageUrl);
		this.setTitle(title);
		this.setDescription(description);
		this.setFaculties(faculties);
		this.setOfficialUrl(officialUrl);

	}

	/**
	 * Main class constructor which sets all the specified variables
	 */
	public GradSchool() {
		this.setId("");
		this.setJapaneseName("");
		this.setName("");
		this.setPrefecture("");
		this.setType("");
		this.setGuideUrl("");
		this.setImageUrl("");
		this.setTitle("");
		this.setDescription("");
		this.setFaculties(null);
		this.setOfficialUrl("");
	}
}