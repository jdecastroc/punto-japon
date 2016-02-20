package com.puntojapon.colleges;

import java.util.Vector;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Faculty {

	private String id; // URL id
	private String universityParent; // URL university parent
	private String name = "";
	private String guideUrl = "";
	private String webAppUrl = "";
	private String news = "";
	private Vector<String> linkList = new Vector<String>();
	private FacultyAdmissions facultyAdmissions;
	private FacultyInfo facultyInfo;
	private FacultySupport facultySupport;
	private FacultyFacilities facultyFacilities;
	private FacultyAccess facultyAccess;

	public Faculty(String parent, String id, String tab) {
		
		setUniversityParent(parent);
		setId(id);
		
		switch (tab) {
		case "admissions": 
			this.facultyAdmissions = new Faculty.FacultyAdmissions();
			break;
		case "info":
			this.facultyInfo = new Faculty.FacultyInfo();
			break;
		case "support":
			this.facultySupport = new Faculty.FacultySupport();
			break;
		case "facilities":
			this.facultyFacilities = new Faculty.FacultyFacilities();
			break;
		case "access":
			this.facultyAccess = new Faculty.FacultyAccess();
			break;
		}
		
	}

	public String getId() {
		return id;
	}

	public Vector<String> getLinkList() {
		return linkList;
	}

	public FacultyAdmissions getFacultyAdmissions() {
		return facultyAdmissions;
	}
	
	public FacultyInfo getFacultyInfo() {
		return facultyInfo;
	}
	
	public FacultySupport getFacultySupport() {
		return facultySupport;
	}
	
	public FacultyFacilities getFacultyFacilities() {
		return facultyFacilities;
	}
	
	public FacultyAccess getFacultyAccess() {
		return facultyAccess;
	}

	public String getUniversityParent() {
		return this.universityParent;
	}

	public String getNews() {
		return this.news;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLinkList(Vector<String> linkList) {
		this.linkList = linkList;
	}

	public void setFacultyAdmissions(FacultyAdmissions facultyAdmissions) {
		this.facultyAdmissions = facultyAdmissions;
	}
	
	public void setFacultyInfo(FacultyInfo facultyInfo) {
		this.facultyInfo = facultyInfo;
	}
	
	public void setFacultySupport(FacultySupport facultySupport) {
		this.facultySupport = facultySupport;
	}
	
	public void setFacultyFacilities(FacultyFacilities facultyFacilities) {
		this.facultyFacilities = facultyFacilities;
	}
	
	public void setFacultyAccess(FacultyAccess facultyAccess) {
		this.facultyAccess = facultyAccess;
	}

	public void setUniversityParent(String parent) {
		this.universityParent = parent;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGuideUrl(String guideUrl) {
		this.guideUrl = guideUrl;
	}

	public void setWebAppUrl(String webAppUrl) {
		this.webAppUrl = webAppUrl;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public void addLink(String link) {
		this.linkList.addElement(link);
	}

	public void setHeaderContent(Document document, Faculty faculty, Elements text) {

		for (Element element : text) {
			// Header ------>
			// Faculty name
			if (element.select("div#DepHead > div.leftBlock > h3").first() != null) {
				Element getName = element.select("div#DepHead > div.leftBlock > h3").first();
				faculty.setName(getName.text().trim());
			}
			// Guide and web Application check and get TODO doesn't work well
			if (element.select("div#DepHead > div.rightBlock > div#DepDl > a").first() != null) {
				Element getGuide = element.select("div#DepHead > div.rightBlock > div#DepDl > a").first();
				faculty.setGuideUrl(getGuide.attr("href").trim());

				if (element.select("div#DepHead > div.rightBlock > div#DepDl > a").first()
						.nextElementSibling() != null) {
					Element getWebApp = element.select("div#DepHead > div.rightBlock > div#DepDl > a").first()
							.nextElementSibling();
					faculty.setWebAppUrl(getWebApp.attr("href").trim());
				}
			}

			// News
			if (element.select("div#DepHead > div#DepNews > p").first() != null) {
				Element getNews = element.select("div#DepHead > div#DepNews > p").first();
				faculty.setNews(getNews.text().trim());
			}

			// Links
			if (element.select("div#DepHead > div#DepMenu > ul.clearFix > li").first() != null) {
				Elements links = element.select("div#DepHead > div#DepMenu > ul.clearFix > li");
				for (Element link : links) {
					// array de links
					if (link.select("a").first() != null) {
						Element getLink = link.select("a").first();
						faculty.addLink(getLink.attr("href").trim());
					}
				}
			}
			// --------->END HEADER
		}
	}

	public class FacultyAdmissions {

		private String lastUpdate = "";
		private Vector<RowContent> rowTable = new Vector<RowContent>();
		private Vector<RowContent> webLinks = new Vector<RowContent>();

		public FacultyAdmissions() {

		}

		public String getName() {
			return name;
		}

		public String getGuideUrl() {
			return guideUrl;
		}

		public String getWebAppUrl() {
			return webAppUrl;
		}

		public String getLastUpdare() {
			return lastUpdate;
		}

		public Vector<RowContent> getRowTable() {
			return this.rowTable;
		}

		public Vector<RowContent> getWebLinks() {
			return this.webLinks;
		}

		public void setLastUpdate(String lastUpdate) {
			this.lastUpdate = lastUpdate;
		}

		public void addRowTableInfo(String register, String content) {
			getRowTable().addElement(new RowContent(register, content));
		}

		public void addWebLink(String register, String content) {
			getWebLinks().addElement(new RowContent(register, content));
		}

	}

	// INFO
	public class FacultyInfo {

		private String title = "";
		private Vector<RowContent> objectTable = new Vector<RowContent>();
		private Vector<String> imagesLinks = new Vector<String>();
		private String regStudentsTitle = "";
		private Vector<RowContent> regStudentsList = new Vector<RowContent>();
		private String subjectsTitle = "";
		private Vector<RowContent> departmentList = new Vector<RowContent>();
		
		public FacultyInfo(){
			
		}

		public String getTitle() {
			return title;
		}

		public Vector<RowContent> getObjectTable() {
			return objectTable;
		}

		public Vector<String> getImagesLinks() {
			return imagesLinks;
		}

		public String getRegStudentsTitle() {
			return regStudentsTitle;
		}

		public Vector<RowContent> getRegStudentsList() {
			return regStudentsList;
		}

		public String getSubjectsTitle() {
			return subjectsTitle;
		}

		public Vector<RowContent> getDepartmentList() {
			return departmentList;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public void setObjectTable(Vector<RowContent> objectTable) {
			this.objectTable = objectTable;
		}

		public void setImagesLinks(Vector<String> imagesLinks) {
			this.imagesLinks = imagesLinks;
		}

		public void setRegStudentsTitle(String regStudentsTitle) {
			this.regStudentsTitle = regStudentsTitle;
		}

		public void setRegStudentsList(Vector<RowContent> regStudentsList) {
			this.regStudentsList = regStudentsList;
		}

		public void setSubjectsTitle(String subjectsTitle) {
			this.subjectsTitle = subjectsTitle;
		}

		public void setDepartmentList(Vector<RowContent> departmentList) {
			this.departmentList = departmentList;
		}

		public void addObject(String register, String content) {
			getObjectTable().addElement(new RowContent(register, content));
		}

		public void addImage(String imageUrl) {
			getImagesLinks().addElement(imageUrl);
		}

		public void addStudentInfo(String register, String content) {
			getRegStudentsList().addElement(new RowContent(register, content));
		}

		public void addDepartmentInfo(String register, String content) {
			getDepartmentList().addElement(new RowContent(register, content));
		}

	}

	// SUPPORT
	public class FacultySupport {

	}

	// FACILITIES
	public class FacultyFacilities {

	}

	// ACCESS
	public class FacultyAccess {

	}

	// Common used classes
	public class RowContent {
		private String register;
		private String content;

		public RowContent(String register, String content) {
			setRegister(register);
			setContent(content);
		}

		public String getRegister() {
			return register;
		}

		public String getContent() {
			return content;
		}

		public void setRegister(String register) {
			this.register = register;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}

}
