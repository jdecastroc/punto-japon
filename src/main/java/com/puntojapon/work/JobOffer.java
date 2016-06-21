package com.puntojapon.work;

import java.util.ArrayList;

public class JobOffer {

	private String name;
	private String publishDate;
	private String company;
	private String location;
	private ArrayList<String> tags = new ArrayList<String>();
	private String description;
	private String link;

	public JobOffer(String name, String publishDate, String company, String location, ArrayList<String> tags,
			String description, String link) {
		
		setName(name);
		setPublishDate(publishDate);
		setCompany(company);
		setLocation(location);
		setTags(tags);
		setDescription(description);
		setLink(link);

	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
