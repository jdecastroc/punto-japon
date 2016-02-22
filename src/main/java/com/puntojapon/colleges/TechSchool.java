/**
 * Tech School data structure
 * @author jdecastroc
 * @version 2.0, 21 Feb 2016
 *
 */
package com.puntojapon.colleges;

/**
 * Provides the techSchool data structure used in the techSchool crawler for
 * store the information
 * 
 * @author jdecastroc
 * @see com.puntojapon.colleges.CollegeBasicInfo
 */
public class TechSchool extends CollegeBasicInfo {

	/**
	 * Mainly class constructor which sets all the specified variables
	 * 
	 * @param id
	 *            -> id of the college
	 * @param japaneseName
	 *            -> japanese name of the college
	 * @param name
	 *            -> english name of the college
	 * @param prefecture
	 *            -> prefecture which the college belongs
	 * @param type
	 *            -> college's type
	 * @param guideUrl
	 *            -> college's guide url
	 * @param imageUrl
	 *            -> college's image url
	 * @param title
	 *            -> college's title
	 * @param description
	 *            -> college's description
	 * @param faculties
	 *            -> college's faculties
	 * @param officialUrl
	 *            -> college's official url to the main college website
	 */
	public TechSchool(String id, String japaneseName, String name, String prefecture, String type, String guideUrl,
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
	 * Class constructor which sets all the specified variables with an empty
	 * value
	 */
	public TechSchool() {
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
