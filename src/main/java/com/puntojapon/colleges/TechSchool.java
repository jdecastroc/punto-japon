package com.puntojapon.colleges;

public class TechSchool extends CollegeBasicInfo {

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
