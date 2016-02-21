/**
 * Faculty data structure
 * @author jdecastroc
 * @version 2.0, 21 Feb 2016
 *
 */
package com.puntojapon.colleges;

import java.util.Vector;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Provides all data structures, classes and functions related to the faculty
 * information crawling
 * 
 * @author jdecastroc
 */

public class Faculty {

	private boolean search = true;
	private String id; // URL id
	private String parent; // URL university parent
	private String name = "";
	private String news = "";
	private Vector<String> linkList = new Vector<String>();
	private FacultyAdmissions facultyAdmissions;
	private FacultyInfo facultyInfo;
	private FacultySupport facultySupport;
	private FacultyFacilities facultyFacilities;
	private FacultyAccess facultyAccess;
	private FacultyTechEssentialInfo facultyTechEssentialInfo;
	private FacultyTechInfo facultyTechInfo;
	private FacultyTechSupport facultyTechSupport;

	/**
	 * Mainly class constructor which sets the college parent id, the id and the
	 * data structure which is going to be used to store the data which is going
	 * to be retrieved to the client
	 * 
	 * @param parent
	 *            -> specifies the faculty college parent id
	 * @param id
	 *            -> specifies the faculty id
	 * @param tab
	 *            -> specifies which kind of page is going to be crawled in
	 *            order to use the proper data structure
	 */
	public Faculty(String parent, String id, String tab) {

		setParent(parent);
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
		case "essentialInfo":
			this.facultyTechEssentialInfo = new Faculty.FacultyTechEssentialInfo();
			break;
		case "techInfo":
			this.facultyTechInfo = new Faculty.FacultyTechInfo();
			break;
		case "techSupport":
			this.facultyTechSupport = new Faculty.FacultyTechSupport();
			break;
		}

	}

	/**
	 * @return the list of crawled links
	 */
	public Vector<String> getLinkList() {
		return linkList;
	}

	/**
	 * @return the faculty admissions object (admissions information stored in
	 *         the data structure)
	 */
	public FacultyAdmissions getFacultyAdmissions() {
		return facultyAdmissions;
	}

	/**
	 * @return the faculty info object (basic information stored in the data
	 *         structure)
	 */
	public FacultyInfo getFacultyInfo() {
		return facultyInfo;
	}

	/**
	 * @return the faculty support object (support information stored in the
	 *         data structure)
	 */
	public FacultySupport getFacultySupport() {
		return facultySupport;
	}

	/**
	 * @return the faculties facilities information object (facilities
	 *         information stored in the data structure)
	 */
	public FacultyFacilities getFacultyFacilities() {
		return facultyFacilities;
	}

	/**
	 * @return the faculties access information object (access information
	 *         stored in the data structure)
	 */
	public FacultyAccess getFacultyAccess() {
		return facultyAccess;
	}

	/**
	 * @return the faculties tech essential information used just in tech
	 *         colleges crawlers (essential information stored in the data
	 *         structure
	 */
	public FacultyTechEssentialInfo getFacultyTechEssentialInfo() {
		return facultyTechEssentialInfo;
	}

	/**
	 * @return the faculties tech basic information used just in tech colleges
	 *         crawlers (basic information stored in the data structure)
	 */
	public FacultyTechInfo getFacultyTechInfo() {
		return facultyTechInfo;
	}

	/**
	 * @return the faculties tech support information used just in tech colleges
	 *         crawlers (basic information stored in the data structure)
	 */
	public FacultyTechSupport getFacultyTechSupport() {
		return facultyTechSupport;
	}

	/**
	 * @return whether or not the search operation was succesful
	 */
	public boolean getSearch() {
		return search;
	}

	/**
	 * @return the faculty id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the faculty university
	 */
	public String getParent() {
		return this.parent;
	}

	/**
	 * @return the faculty news
	 */
	public String getNews() {
		return this.news;
	}

	/**
	 * @param search
	 *            -> set whether the search operation has been succesful
	 */
	public void setSearch(Boolean search) {
		this.search = search;
	}

	/**
	 * @param id
	 *            -> set the faculty id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param linkList
	 *            -> set the vector link list
	 */
	public void setLinkList(Vector<String> linkList) {
		this.linkList = linkList;
	}

	/**
	 * @param facultyAdmissions
	 *            -> set the faculty admissions info object
	 */
	public void setFacultyAdmissions(FacultyAdmissions facultyAdmissions) {
		this.facultyAdmissions = facultyAdmissions;
	}

	/**
	 * @param facultyInfo
	 *            -> set the faculty basic info object
	 */
	public void setFacultyInfo(FacultyInfo facultyInfo) {
		this.facultyInfo = facultyInfo;
	}

	/**
	 * @param facultySupport
	 *            -> set the faculty support info object
	 */
	public void setFacultySupport(FacultySupport facultySupport) {
		this.facultySupport = facultySupport;
	}

	/**
	 * @param facultyFacilities
	 *            -> set the faculty facilities info object
	 */
	public void setFacultyFacilities(FacultyFacilities facultyFacilities) {
		this.facultyFacilities = facultyFacilities;
	}

	/**
	 * @param facultyAccess
	 *            -> set the faculty access info object
	 */
	public void setFacultyAccess(FacultyAccess facultyAccess) {
		this.facultyAccess = facultyAccess;
	}

	/**
	 * @param parent
	 *            -> set the faculty university id
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}

	/**
	 * @param name
	 *            -> set the faculty name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param guideUrl
	 *            -> set the faculty guide URL
	 */
	public void setGuideUrl(String guideUrl) {
	}

	/**
	 * @param webAppUrl
	 *            -> set the faculty web application url
	 */
	public void setWebAppUrl(String webAppUrl) {
	}

	/**
	 * @param news
	 *            -> set the faculty news
	 */
	public void setNews(String news) {
		this.news = news;
	}

	/**
	 * @param link
	 *            -> set the faculty link
	 */
	public void addLink(String link) {
		this.linkList.addElement(link);
	}

	/**
	 * retrieve the header content of the faculty tabs information. It's shared
	 * by every tab information
	 * 
	 * @param document
	 *            -> document to be crawled (Added in case of adding more info
	 *            from the faculty)
	 * @param faculty
	 *            -> faculty data structure to be used
	 * @param text
	 *            -> Elements taken from the document
	 */
	public void setHeaderContent(Document document, Faculty faculty, Elements text) {

		for (Element element : text) {
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
		}
	}

	/**
	 * Faculty Admissions store the data retrieved by the crawl of a faculty
	 * admissions page. It also provides the proper functions in order to
	 * accomplish the objective
	 * 
	 * @author jdecastroc
	 *
	 */
	public class FacultyAdmissions {

		private String lastUpdate = "";
		private Vector<RowContent> rowTable = new Vector<RowContent>();
		private Vector<RowContent> webLinks = new Vector<RowContent>();

		/**
		 * Mainly class constructor
		 */
		public FacultyAdmissions() {

		}

		/**
		 * @return the faculty admissions title
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the faculty last update information
		 */
		public String getLastUpdare() {
			return lastUpdate;
		}

		/**
		 * @return the rowTable information list in order to be manipulated
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public Vector<RowContent> getRowTable() {
			return this.rowTable;
		}

		/**
		 * @return the webLinks information list in order to be manipulated
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public Vector<RowContent> getWebLinks() {
			return this.webLinks;
		}

		/**
		 * @param lastUpdate
		 *            -> sets the lastUpdate crawled string
		 */
		public void setLastUpdate(String lastUpdate) {
			this.lastUpdate = lastUpdate;
		}

		/**
		 * Add a RowContent object to the RowTable list information
		 * (value-value)
		 * 
		 * @param register
		 *            -> sets the register string
		 * @param content
		 *            -> sets the content string
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void addRowTableInfo(String register, String content) {
			getRowTable().addElement(new RowContent(register, content));
		}

		/**
		 * Add a RowContent object to the webLink list information (value-value)
		 * 
		 * @param register
		 *            -> sets the register string
		 * @param content
		 *            -> sets the content string
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void addWebLink(String register, String content) {
			getWebLinks().addElement(new RowContent(register, content));
		}

	}

	/**
	 * Faculty Info store the data retrieved by the crawl of a faculty info
	 * page. It also provides the proper functions in order to accomplish the
	 * objective
	 * 
	 * @author jdecastroc
	 *
	 */
	public class FacultyInfo {

		private String title = "";
		private Vector<RowContent> objectTable = new Vector<RowContent>();
		private Vector<String> imagesLinks = new Vector<String>();
		private String regStudentsTitle = "";
		private Vector<RowContent> regStudentsList = new Vector<RowContent>();
		private String subjectsTitle = "";
		private Vector<RowContent> departmentList = new Vector<RowContent>();

		/**
		 * Mainly class constructor. Doesn't have functionality.
		 */
		public FacultyInfo() {

		}

		/**
		 * @return the faculty info title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @return the object list which stores the object content
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public Vector<RowContent> getObjectTable() {
			return objectTable;
		}

		/**
		 * @return the imagesLinks list which stores the images urls in order to
		 *         be manipulated
		 */
		public Vector<String> getImagesLinks() {
			return imagesLinks;
		}

		/**
		 * @return the register students title
		 */
		public String getRegStudentsTitle() {
			return regStudentsTitle;
		}

		/**
		 * @return the register students object information list in order to be
		 *         manipulated
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public Vector<RowContent> getRegStudentsList() {
			return regStudentsList;
		}

		/**
		 * @return the subjects section title
		 */
		public String getSubjectsTitle() {
			return subjectsTitle;
		}

		/**
		 * @return the department list in order to be manipulated
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public Vector<RowContent> getDepartmentList() {
			return departmentList;
		}

		/**
		 * @param ->
		 *            sets the title of the faculty info
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * @param objectTable
		 *            -> sets the list which is going to store the objects from
		 *            the crawled table
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void setObjectTable(Vector<RowContent> objectTable) {
			this.objectTable = objectTable;
		}

		/**
		 * @param imagesLinks
		 *            -> sets the list which is going to store the url of the
		 *            crawled images
		 */
		public void setImagesLinks(Vector<String> imagesLinks) {
			this.imagesLinks = imagesLinks;
		}

		/**
		 * @param regStudentsTitle
		 *            -> sets the regStudents section crawled title
		 */
		public void setRegStudentsTitle(String regStudentsTitle) {
			this.regStudentsTitle = regStudentsTitle;
		}

		/**
		 * @param regStudentsList
		 *            -> sets the regStudents table crawled information
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void setRegStudentsList(Vector<RowContent> regStudentsList) {
			this.regStudentsList = regStudentsList;
		}

		/**
		 * @param subjectsTitle
		 *            -> sets the subjects section title
		 */
		public void setSubjectsTitle(String subjectsTitle) {
			this.subjectsTitle = subjectsTitle;
		}

		/**
		 * @param departmentList
		 *            -> sets the deparment list in order to be manipulated
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void setDepartmentList(Vector<RowContent> departmentList) {
			this.departmentList = departmentList;
		}

		/**
		 * @param register
		 *            -> sets the register string related with table object
		 *            information
		 * @param content
		 *            -> sets the content string with table object information
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void addObject(String register, String content) {
			getObjectTable().addElement(new RowContent(register, content));
		}

		/**
		 * @param imageUrl
		 *            -> imageUrl which is going to be added to the imagesLinks
		 *            list
		 */
		public void addImage(String imageUrl) {
			getImagesLinks().addElement(imageUrl);
		}

		/**
		 * @param register
		 *            -> sets the register string related with regStudents
		 *            information
		 * @param content
		 *            -> sets the content string related with regStudents
		 *            information
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void addStudentInfo(String register, String content) {
			getRegStudentsList().addElement(new RowContent(register, content));
		}

		/**
		 * @param register
		 *            -> sets the register string related with department
		 *            information
		 * @param content
		 *            -> sets the content string related with department
		 *            information
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void addDepartmentInfo(String register, String content) {
			getDepartmentList().addElement(new RowContent(register, content));
		}

	}

	/**
	 * Faculty Support store the data retrieved by the crawl of a faculty
	 * support page. It also provides the proper functions in order to
	 * accomplish the objective
	 * 
	 * @author jdecastroc
	 *
	 */
	public class FacultySupport {
		private String supportTitle = "";
		private Vector<RowContent> objectTable = new Vector<RowContent>();
		private Vector<String> imagesLinks = new Vector<String>();

		/**
		 * @return the support page title
		 */
		public String getSupportTitle() {
			return supportTitle;
		}

		/**
		 * @return the object table list in order to be manipulated
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public Vector<RowContent> getObjectTable() {
			return objectTable;
		}

		/**
		 * @return the images Links list in order to be manipulated
		 */
		public Vector<String> getImagesLinks() {
			return imagesLinks;
		}

		/**
		 * @param supportTitle
		 *            -> set the support page title
		 */
		public void setSupportTitle(String supportTitle) {
			this.supportTitle = supportTitle;
		}

		/**
		 * @param objectTable
		 *            -> set the object table list
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void setObjectTable(Vector<RowContent> objectTable) {
			this.objectTable = objectTable;
		}

		/**
		 * @param imagesLinks
		 *            -> set the imagesLinks list
		 */
		public void setImagesLinks(Vector<String> imagesLinks) {
			this.imagesLinks = imagesLinks;
		}

		/**
		 * Add a object table information to the object table list
		 * 
		 * @param register
		 *            -> sets the register string related with table object
		 *            information
		 * @param content
		 *            -> sets the content string related with table object
		 *            information information
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void addObject(String register, String content) {
			getObjectTable().addElement(new RowContent(register, content));
		}

		/**
		 * Add a imageUrl to the imagesLinks list
		 * 
		 * @param imageUrl
		 *            -> image URL which is going to be added to the list
		 */
		public void addImage(String imageUrl) {
			getImagesLinks().addElement(imageUrl);
		}

	}

	/**
	 * Faculty Facilities store the data retrieved by the crawl of a faculty
	 * facilities page. It also provides the proper functions in order to
	 * accomplish the objective
	 * 
	 * @author jdecastroc
	 *
	 */
	public class FacultyFacilities {
		private String facilitiesTitle = "";
		private Vector<RowContent> objectTable = new Vector<RowContent>();
		private Vector<String> imagesLinks = new Vector<String>();

		/**
		 * @return the facilities page title
		 */
		public String getFacilitiesTitle() {
			return facilitiesTitle;
		}

		/**
		 * @return the object table list in order to be manipulated
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public Vector<RowContent> getObjectTable() {
			return objectTable;
		}

		/**
		 * @return the imagesLinks list which store the crawled page url images
		 */
		public Vector<String> getImagesLinks() {
			return imagesLinks;
		}

		/**
		 * @param facilitiesTitle
		 *            -> set the facilities page title
		 */
		public void setFacilitiestTitle(String facilitiesTitle) {
			this.facilitiesTitle = facilitiesTitle;
		}

		/**
		 * @param objectTable
		 *            -> set the objectTable list
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void setObjectTable(Vector<RowContent> objectTable) {
			this.objectTable = objectTable;
		}

		/**
		 * @param imagesLinks
		 *            -> set the imagesLinks list
		 */
		public void setImagesLinks(Vector<String> imagesLinks) {
			this.imagesLinks = imagesLinks;
		}

		/**
		 * Add an information object to the ObjectTable list
		 * 
		 * @param register
		 *            -> sets the register string related with table object
		 *            information
		 * @param content
		 *            -> sets the content string related with table object
		 *            information information
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void addObject(String register, String content) {
			getObjectTable().addElement(new RowContent(register, content));
		}

		/**
		 * Add a imageUrl to the imagesLinks list
		 * 
		 * @param imageUrl
		 *            -> image URL which is going to be added to the list
		 */
		public void addImage(String imageUrl) {
			getImagesLinks().addElement(imageUrl);
		}

	}

	/**
	 * Faculty Access store the data retrieved by the crawl of a faculty access
	 * page. It also provides the proper functions in order to accomplish the
	 * objective
	 * 
	 * @author jdecastroc
	 *
	 */
	public class FacultyAccess {
		private String accessTitle = "";
		private Vector<MapData> maps = new Vector<MapData>();

		/**
		 * Mainly class constructor. Doesn't have functionality.
		 */
		public FacultyAccess() {

		}

		/**
		 * @return the access page title
		 */
		public String getAccessTitle() {
			return accessTitle;
		}

		/**
		 * @return the list of access maps in order to be manipulated
		 */
		public Vector<MapData> getMaps() {
			return maps;
		}

		/**
		 * 
		 * @param accessTitle
		 *            -> set the access page title
		 */
		public void setAccessTitle(String accessTitle) {
			this.accessTitle = accessTitle;
		}

		/**
		 * Add a map object to the access map list in order to be retrieved by
		 * the client
		 * 
		 * @param name
		 *            -> name of the map location
		 * @param lng
		 *            -> longitude used to determine the location position
		 * @param lat
		 *            -> latitude used to determine the location position
		 * @param address
		 *            -> address of the location
		 * @param nearbyPlaces
		 *            -> location nearby places
		 * @param otherInfo
		 *            -> other information related to the location
		 * @see com.puntojapon.colleges.Faculty.MapData
		 */
		public void addMap(String name, String lng, String lat, String address, String nearbyPlaces, String otherInfo) {
			this.maps.addElement(new MapData(name, lng, lat, address, nearbyPlaces, otherInfo));
		}

	}

	/**
	 * Faculty Tech Essential info store the data retrieved by the crawl of a
	 * tech Faculty essential information page. It also provides the proper
	 * functions in order to accomplish the objective
	 * 
	 * @author jdecastroc
	 *
	 */
	public class FacultyTechEssentialInfo {

		private String title = "";
		private RowContent addressInfo = new RowContent();
		private RowContentContainer registerStudents = new RowContentContainer();
		private RowContentContainer entranceInfo = new RowContentContainer();

		/**
		 * @return the title of the essential information page
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @return the essential info address information
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public RowContent getAddressInfo() {
			return addressInfo;
		}

		/**
		 * @return the register students object information in order to be
		 *         manipulated
		 * @see com.puntojapon.colleges.Faculty.RowContentContainer
		 */
		public RowContentContainer getRegisterStudents() {
			return registerStudents;
		}

		/**
		 * @return the entrance object information in order to be manipulated
		 * @see com.puntojapon.colleges.Faculty.RowContentContainer
		 */
		public RowContentContainer getEntranceInfo() {
			return entranceInfo;
		}

		/**
		 * @param title
		 *            -> set the essential information page title
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * @param addressInfo
		 *            -> set the address information object
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void setAddressInfo(RowContent addressInfo) {
			this.addressInfo = addressInfo;
		}

		/**
		 * @param registerStudents
		 *            -> set the register student container object information
		 * @see com.puntojapon.colleges.Faculty.RowContentContainer
		 */
		public void setRegisterStudents(RowContentContainer registerStudents) {
			this.registerStudents = registerStudents;
		}

		/**
		 * @param entranceInfo
		 *            -> set the entrance container object information
		 * @see com.puntojapon.colleges.Faculty.RowContentContainer
		 */
		public void setEntranceInfo(RowContentContainer entranceInfo) {
			this.entranceInfo = entranceInfo;
		}

	}

	/**
	 * Faculty Tech Info store the data retrieved by the crawl of a tech Faculty
	 * information page. It also provides the proper functions in order to
	 * accomplish the objective
	 * 
	 * @author jdecastroc
	 *
	 */
	public class FacultyTechInfo {
		private String title = "Faculty Information";
		private Vector<FacultyCourse> courses = new Vector<FacultyCourse>();

		/**
		 * @return the courses list in order to be manipulated
		 */
		public Vector<FacultyCourse> getCourses() {
			return this.courses;
		}

		/**
		 * Add a course to the courses list
		 * 
		 * @param course
		 *            -> course object with the course data
		 * @see com.puntojapon.colleges.Faculty.FacultyCourse
		 */
		public void addCourse(FacultyCourse course) {
			this.courses.addElement(course);
		}

		/**
		 * @return the title of the tech School info page
		 */
		public String getTitle() {
			return this.title;
		}
	}

	/**
	 * Faculty Tech Support store the data retrieved by the crawl of a tech
	 * Faculty support page. It also provides the proper functions in order to
	 * accomplish the objective
	 * 
	 * @author jdecastroc
	 *
	 */
	public class FacultyTechSupport {
		private String title = "International student support";
		private Vector<RowContent> objectInfo = new Vector<RowContent>();

		/**
		 * @return the title of the tech school support page
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @return the object information list which store table information
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public Vector<RowContent> getObjectInfo() {
			return objectInfo;
		}

		/**
		 * @param title
		 *            -> set the tech school support title
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * @param objectInfo
		 *            -> set the object info list
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void setObjectInfo(Vector<RowContent> objectInfo) {
			this.objectInfo = objectInfo;
		}

		/**
		 * Add objectInfo to the objects information lists
		 * 
		 * @param register
		 *            -> sets the register string related with table object
		 *            information
		 * @param content
		 *            -> sets the content string related with table object
		 *            information information
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void addObjectInfo(String register, String content) {
			this.objectInfo.addElement(new RowContent(register, content));
		}

	}

	// Common used classes
	/**
	 * Faculty Course provides a data structure and functions which allow the
	 * application to store courses based on the crawled page
	 * 
	 * @author jdecastroc
	 *
	 */
	public static class FacultyCourse {
		private String courseName = "";
		private String courseDescription = "";
		private String courseWorkPermitted = "";
		// private RowContent courseAdmissionInfo;
		private Vector<RowContent> courseAdmissionInfo = new Vector<RowContent>();

		/**
		 * Mainly class constructor. Doesn't have functionality
		 */
		public FacultyCourse() {
		}

		/**
		 * @return the course name
		 */
		public String getCourseName() {
			return courseName;
		}

		/**
		 * @return the course description
		 */
		public String getCourseDescription() {
			return courseDescription;
		}

		/**
		 * @return the string related with the course work permitted
		 */
		public String getCourseWorkPermitted() {
			return courseWorkPermitted;
		}

		/**
		 * @return the list with the admission information objects in order to
		 *         be manipulated
		 */
		public Vector<RowContent> getCourseAdmissionInfo() {
			return courseAdmissionInfo;
		}

		/**
		 * @param courseName
		 *            -> set the course name
		 */
		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}

		/**
		 * @param courseDescription
		 *            -> set the course description
		 */
		public void setCourseDescription(String courseDescription) {
			this.courseDescription = courseDescription;
		}

		/**
		 * @param courseWorkPermitted
		 *            -> set the course work permitted information
		 */
		public void setCourseWorkPermitted(String courseWorkPermitted) {
			this.courseWorkPermitted = courseWorkPermitted;
		}

		/**
		 * @param courseAdmissionInfo
		 *            -> set the course admission information list
		 */
		public void setCourseAdmissionInfo(Vector<RowContent> courseAdmissionInfo) {
			this.courseAdmissionInfo = courseAdmissionInfo;
		}

		/**
		 * Add objectAdmissionInfo to the objects admission information list
		 * 
		 * @param register
		 *            -> sets the register string related with admission object
		 *            information
		 * @param content
		 *            -> sets the content string related with admission object
		 *            information information
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void addCourseAdmissionInfo(String register, String content) {
			this.courseAdmissionInfo.addElement(new RowContent(register, content));
		}
	}

	/**
	 * Provides a own pair value data structure
	 * 
	 * @author jdecastro
	 *
	 */
	public static class RowContent {
		private String register;
		private String content;

		/**
		 * Constructor which takes the register and content in order to set them
		 * 
		 * @param register
		 *            -> sets the register string
		 * @param content
		 *            -> sets the content string
		 */
		public RowContent(String register, String content) {
			setRegister(register);
			setContent(content);
		}

		/**
		 * Constructor whitout reference variables. Sets empty strings
		 */
		public RowContent() {
			setRegister("");
			setContent("");
		}

		/**
		 * @return the register string
		 */
		public String getRegister() {
			return register;
		}

		/**
		 * @return the content string
		 */
		public String getContent() {
			return content;
		}

		/**
		 * @param register
		 *            -> set the register string
		 */
		public void setRegister(String register) {
			this.register = register;
		}

		/**
		 * @param content
		 *            -> set the content string
		 */
		public void setContent(String content) {
			this.content = content;
		}

	}

	/**
	 * Provides a container for the RowContent structure to set a title related
	 * to the content and embed both in an object in order to retrieve it in a
	 * proper way for the json structure data
	 * 
	 * @author jdecastroc
	 *
	 */
	public static class RowContentContainer {
		private String register;
		private Vector<RowContent> content = new Vector<RowContent>();

		/**
		 * Constructor which sets a title for the container given by reference
		 * 
		 * @param register
		 *            -> sets the title of the container
		 */
		public RowContentContainer(String register) {
			setRegister(register);
		}

		/**
		 * Constructor whichs sets an empty title
		 */
		public RowContentContainer() {
			setRegister("");
		}

		/**
		 * @return the title of the container
		 */
		public String getRegister() {
			return register;
		}

		/**
		 * @return the pair data content
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public Vector<RowContent> getContent() {
			return content;
		}

		/**
		 * @param register
		 *            -> sets the container title
		 */
		public void setRegister(String register) {
			this.register = register;
		}

		/**
		 * Given the pair values, adds a new RowContent object to the content
		 * list
		 * 
		 * @param register
		 *            -> register string
		 * @param content
		 *            -> content string
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void setContent(String register, String content) {
			this.content.addElement(new RowContent(register, content));
		}

		/**
		 * Given the rowContent object, adds it to the content list
		 * 
		 * @param rowContent
		 *            -> rowContent object
		 * @see com.puntojapon.colleges.Faculty.RowContent
		 */
		public void addContainerContent(RowContent rowContent) {
			this.content.addElement(rowContent);
		}

	}

	/**
	 * MapData provides a data structure for store map data. It's used in the
	 * application to store the access maps of the college's faculties
	 * 
	 * @author jdecastroc
	 *
	 */
	public class MapData {
		private String name = "";
		private String lng = "";
		private String lat = "";
		private String address = "";
		private String nearbyPlaces = "";
		private String otherInfo = "";

		/**
		 * Mainly class constructor create a mapData object by the given reference variables
		 * 
		 * @param name
		 *            -> name of the map location
		 * @param lng
		 *            -> longitude used to determine the location position
		 * @param lat
		 *            -> latitude used to determine the location position
		 * @param address
		 *            -> address of the location
		 * @param nearbyPlaces
		 *            -> location nearby places
		 * @param otherInfo
		 *            -> other information related to the location
		 * @see com.puntojapon.colleges.Faculty.MapData
		 */
		public MapData(String name, String lng, String lat, String address, String nearbyPlaces, String otherInfo) {
			setName(name);
			setLng(lng);
			setLat(lat);
			setAddress(address);
			setNearbyPlaces(nearbyPlaces);
			setOtherInfo(otherInfo);
		}

		/**
		 * @return the map location name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the longitude used to determine the location position
		 */
		public String getLng() {
			return lng;
		}

		/**
		 * @return the latitude used to determine the location position
		 */
		public String getLat() {
			return lat;
		}

		/**
		 * @return the map location address
		 */
		public String getAddress() {
			return address;
		}
		
		/**
		 * @return the map location nearby places information
		 */
		public String getNearbyPlaces() {
			return nearbyPlaces;
		}
		
		/**
		 * @return the map other map location information
		 */
		public String getOtherInfo() {
			return otherInfo;
		}

		/**
		 * @param name -> set the map location name
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @param lng -> set the map location longitude
		 */
		public void setLng(String lng) {
			this.lng = lng;
		}

		/**
		 * @param lat -> set the map location latitude
		 */
		public void setLat(String lat) {
			this.lat = lat;
		}

		/**
		 * @param address -> set the map location address
		 */
		public void setAddress(String address) {
			this.address = address;
		}

		/**
		 * @param nearbyPlaces -> set the map location nearby places information
		 */
		public void setNearbyPlaces(String nearbyPlaces) {
			this.nearbyPlaces = nearbyPlaces;
		}

		/**
		 * @param otherInfo -> set the other map location information
		 */
		public void setOtherInfo(String otherInfo) {
			this.otherInfo = otherInfo;
		}
	}
}
