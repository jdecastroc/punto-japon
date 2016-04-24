/**
 * Language School Crawler
 * @author jdecastroc
 * @version 1.0, 24 Apr 2016
 *
 */
package com.puntojapon.languageSchools;

/**
 * Course contains the mainly data structure related to the language school
 * courses which information is going to be crawled.
 * 
 * @author jdecastroc
 *
 */
public class CourseInfo {

	private String course;
	private String purpose;
	private String length;
	private String classHours;
	private String weeks;
	private String monthAdmission;
	private String selectionFee;
	private String admissionFee;
	private String tuitionFee;
	private String othersFee;
	private String totalFee;

	/**
	 * Main builder which package the object info
	 * 
	 * @param courseInput
	 *            -> Name of the course
	 * @param purposeInput
	 *            -> Description of the purpose of the course
	 * @param lengthInput
	 *            -> length of the course
	 * @param classHoursInput
	 *            -> Total class hours of the course
	 * @param weeksInput
	 *            -> Total weeks
	 * @param monthAdmissionInput
	 *            -> Months of admission related to the course
	 * @param selectionFeeInput
	 *            -> Fee related to the selection
	 * @param admissionFeeInput
	 *            -> Fee related to the admission
	 * @param tuitionFeeInput
	 *            -> Fee related to the tuition
	 * @param othersFeeInput
	 *            -> Others fee
	 * @param totalFeeInput
	 *            -> Total fee of the course
	 */
	public CourseInfo(String courseInput, String purposeInput, String lengthInput, String classHoursInput,
			String weeksInput, String monthAdmissionInput, String selectionFeeInput, String admissionFeeInput,
			String tuitionFeeInput, String othersFeeInput, String totalFeeInput) {

		setCourse(courseInput);
		setPurpose(purposeInput);
		setLength(lengthInput);
		setClassHours(classHoursInput);
		setWeeks(weeksInput);
		setMonthAdmission(monthAdmissionInput);
		setSelectionFee(selectionFeeInput);
		setAdmissionFee(admissionFeeInput);
		setTuitionFee(tuitionFeeInput);
		setOthersFee(othersFeeInput);
		setTotalFee(totalFeeInput);

	}

	/**
	 * Main constructor of the class
	 */
	public CourseInfo() {

	}

	/**
	 * 
	 * @return
	 */
	public String getCourse() {
		return course;
	}

	public String getPurpose() {
		return purpose;
	}

	public String getLength() {
		return length;
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

	public String getSelectionFee() {
		return selectionFee;
	}

	public String getAdmissionFee() {
		return admissionFee;
	}

	public String getTuitionFee() {
		return tuitionFee;
	}

	public String getOthersFee() {
		return othersFee;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public void setLength(String length) {
		this.length = length;
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

	public void setSelectionFee(String selectionFee) {
		this.selectionFee = selectionFee;
	}

	public void setAdmissionFee(String admissionFee) {
		this.admissionFee = admissionFee;
	}

	public void setTuitionFee(String tuitionFee) {
		this.tuitionFee = tuitionFee;
	}

	public void setOthersFee(String othersFee) {
		this.othersFee = othersFee;
	}

	public void setTotalFee(String totalfee) {
		this.totalFee = totalfee;
	}

}
