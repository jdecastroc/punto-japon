package com.puntojapon.languageSchools;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class SchoolInfo {

	private boolean search = false;
	private String id;
	private String name;
	private String japaneseName;
	private String address;
	private String phone;
	private String fax;
	private String officialUrl;
	private String email;
	private String howToArrive;
	private String establishmentType;
	private String representativeName;
	private String principalName;
	private String startingDate;
	private String validityTerm;
	private String teachersNumber;
	private String capacity;
	private String accommodations;
	private String schoolStatus;
	private String admissionRequirements;
	private String selectionProcess;
	private String otherCourses;
	private String mapUrl;
	
	// country - number of people
	private Map<String, Integer> registeredStudents = new HashMap<String, Integer>();
	// Course's list
	private Vector<CourseInfo> authorizedCourseList = new Vector<CourseInfo>();
	// examinees - certified for N1
	private Map<Integer, Integer> n1Stadistics = new HashMap<Integer, Integer>();
	// examinees - certified for N2
	private Map<Integer, Integer> n2Stadistics = new HashMap<Integer, Integer>();
	// examinees - certified for N3
	private Map<Integer, Integer> n3Stadistics = new HashMap<Integer, Integer>();
	// examinees - certified for N4
	private Map<Integer, Integer> n4Stadistics = new HashMap<Integer, Integer>();
	// examinees - certified for N5
	private Map<Integer, Integer> n5Stadistics = new HashMap<Integer, Integer>();
	// type of School - number of people
	private Map<String, Integer> studentsDestination = new HashMap<String, Integer>();
	// feature number - Description
	private Map<Integer, String> schoolFeatures = new HashMap<Integer, String>();
	
	public SchoolInfo(){
		setSearch(false);
	}

	public boolean isSearch() {
		return search;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getJapaneseName() {
		return japaneseName;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getFax() {
		return fax;
	}

	public String getOfficialUrl() {
		return officialUrl;
	}

	public String getEmail() {
		return email;
	}

	public String getHowToArrive() {
		return howToArrive;
	}

	public String getEstablishmentType() {
		return establishmentType;
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public String getStartingDate() {
		return startingDate;
	}

	public String getValidityTerm() {
		return validityTerm;
	}

	public String getTeachersNumber() {
		return teachersNumber;
	}

	public String getCapacity() {
		return capacity;
	}

	public String getAccommodations() {
		return accommodations;
	}

	public String getSchoolStatus() {
		return schoolStatus;
	}

	public String getAdmissionRequirements() {
		return admissionRequirements;
	}

	public String getSelectionProcess() {
		return selectionProcess;
	}
	
	public String getOtherCourses() {
		return otherCourses;
	}

	public String getMapUrl() {
		return mapUrl;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		if (name != null) {
			setSearch(true);
			this.name = name;
		}
	}

	public void setJapaneseName(String japaneseName) {
		this.japaneseName = japaneseName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setOfficialUrl(String officialUrl) {
		this.officialUrl = officialUrl;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setHowToArrive(String howToArrive) {
		this.howToArrive = howToArrive;
	}

	public void setEstablishmentType(String establishmentType) {
		this.establishmentType = establishmentType;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	public void setValidityTerm(String validityTerm) {
		this.validityTerm = validityTerm;
	}

	public void setTeachersNumber(String teachersNumber) {
		this.teachersNumber = teachersNumber;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public void setAccommodations(String accommodations) {
		this.accommodations = accommodations;
	}

	public void setSchoolStatus(String schoolStatus) {
		this.schoolStatus = schoolStatus;
	}

	public void setAdmissionRequirements(String admissionRequirements) {
		this.admissionRequirements = admissionRequirements;
	}

	public void setSelectionProcess(String selectionProcess) {
		this.selectionProcess = selectionProcess;
	}
	
	public void setOtherCourses(String otherCourses) {
		this.otherCourses = otherCourses;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	public Map<String, Integer> getRegisteredStudents() {
		return registeredStudents;
	}

	public Vector<CourseInfo> getAuthorizedCourseList() {
		return authorizedCourseList;
	}
	
	public void addCourse(CourseInfo course) {
		getAuthorizedCourseList().addElement(course);
	}

	public Map<Integer, Integer> getN1Stadistics() {
		return n1Stadistics;
	}

	public Map<Integer, Integer> getN2Stadistics() {
		return n2Stadistics;
	}

	public Map<Integer, Integer> getN3Stadistics() {
		return n3Stadistics;
	}

	public Map<Integer, Integer> getN4Stadistics() {
		return n4Stadistics;
	}

	public Map<Integer, Integer> getN5Stadistics() {
		return n5Stadistics;
	}

	public Map<String, Integer> getStudentsDestination() {
		return studentsDestination;
	}

	public Map<Integer, String> getSchoolFeatures() {
		return schoolFeatures;
	}

}
