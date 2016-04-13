package com.puntojapon.languageSchools;

public class CourseInfo {

	private String course;
	private String purpose;
	private String lenght;
	private String classHours;
	private String weeks;
	private String monthAdmission;
	private int selectionFee;
	private int admissionFee;
	private int tuitionFee;
	private int othersFee;
	private int totalFee;
	private String otherCourses;

	public CourseInfo(String courseInput, String purposeInput, String lenghtInput, String classHoursInput,
			String weeksInput, String monthAdmissionInput, int selectionFeeInput, int admissionFeeInput,
			int tuitionFeeInput, int othersFeeInput, int totalFeeInput, String otherCourses) {

		setCourse(courseInput);
		setPurpose(purposeInput);
		setLenght(lenghtInput);
		setClassHours(classHoursInput);
		setWeeks(weeksInput);
		setMonthAdmission(monthAdmissionInput);
		setSelectionFee(selectionFeeInput);
		setAdmissionFee(admissionFeeInput);
		setTuitionFee(tuitionFeeInput);
		setOthersFee(othersFeeInput);
		setTotalFee(totalFeeInput);
		setOtherCourses(otherCourses);

	}

	public String getCourse() {
		return course;
	}

	public String getPurpose() {
		return purpose;
	}

	public String getLenght() {
		return lenght;
	}

	public String getClassHours() {
		return classHours;
	}

	public String getWeeks() {
		return weeks;
	}

	public String getMonthAdmission() {
		return monthAdmission;
	}

	public int getSelectionFee() {
		return selectionFee;
	}

	public int getAdmissionFee() {
		return admissionFee;
	}

	public int getTuitionFee() {
		return tuitionFee;
	}

	public int getOthersFee() {
		return othersFee;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public String getOtherCourses() {
		return otherCourses;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public void setLenght(String lenght) {
		this.lenght = lenght;
	}

	public void setClassHours(String classHours) {
		this.classHours = classHours;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public void setMonthAdmission(String monthAdmission) {
		this.monthAdmission = monthAdmission;
	}

	public void setSelectionFee(int selectionFee) {
		this.selectionFee = selectionFee;
	}

	public void setAdmissionFee(int admissionFee) {
		this.admissionFee = admissionFee;
	}

	public void setTuitionFee(int tuitionFee) {
		this.tuitionFee = tuitionFee;
	}

	public void setOthersFee(int othersFee) {
		this.othersFee = othersFee;
	}

	public void setTotalFee(int totalfee) {
		this.totalFee = totalfee;
	}

	public void setOtherCourses(String otherCourses) {
		this.otherCourses = otherCourses;
	}

}
