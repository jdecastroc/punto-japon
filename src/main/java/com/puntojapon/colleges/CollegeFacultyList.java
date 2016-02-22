/**
 * College Faculty List
 * @author jdecastroc
 * @version 2.0, 21 Feb 2016
 *
 */
package com.puntojapon.colleges;

import java.util.Vector;

/**
 * CollegeFacultyList structure list various collegeFaculties in order to
 * retrieve the data
 * 
 * @author jdecastroc
 */
public class CollegeFacultyList {
	private String collegeName;
	private Vector<CollegeFaculty> collegeFacultyList;

	/**
	 * Mainly class constructor which sets the collegeName and initialize the
	 * collegeFacultyList in order to save the college faculties
	 * 
	 * @param collegeName
	 *            -> name of the college
	 */
	public CollegeFacultyList(String collegeName) {
		setListCollegeName(collegeName);
		this.collegeFacultyList = new Vector<CollegeFaculty>();
	}

	/**
	 * @param collegeName
	 *            -> set the list college name
	 */
	public void setListCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	/**
	 * @return the lists college name
	 */
	public String getListCollegeName() {
		return this.collegeName;
	}

	/**
	 * @return the list with the college faculties in order to be manipulated
	 */
	public Vector<CollegeFaculty> getCollegeFaculties() {
		return this.collegeFacultyList;
	}

	/**
	 * @return the last element from the college faculty list
	 */
	public CollegeFaculty getCollegeFacultyLast() {
		return this.collegeFacultyList.lastElement();
	}

	/**
	 * @return the first element from the college faculty list
	 */
	public CollegeFaculty getCollegeFacultyFirst() {
		return this.collegeFacultyList.firstElement();
	}

	/**
	 * @return the size of the college faculty list
	 */
	public int getCollegeFacultySize() {
		return this.collegeFacultyList.size();
	}

	/**
	 * @param index
	 *            -> position of the value which content is going to be returned
	 * @return the element contained in the index param
	 */
	public CollegeFaculty getCollegeFacultyAt(int index) {
		return this.collegeFacultyList.elementAt(index);
	}

	/**
	 * @param faculty
	 *            -> faculty object
	 * @return whether or not adding the faculty object to the college faculty
	 *         list was succesful or not
	 */
	public boolean addCollegeFaculty(CollegeFaculty faculty) {
		this.collegeFacultyList.addElement(faculty);
		return true;
	}

	/**
	 * Translate all the faculties name based on the translator class
	 * 
	 * @see com.puntojapon.colleges.Translator#translate(String)
	 * @deprecated Not used from the version 2.0. Maybe useful in the future.
	 */
	public void translateCollegeFacultyList() {
		for (CollegeFaculty i : this.collegeFacultyList) {
			i.setFacultyName(Translator.translate(i.getFacultyName()));
		}
	}
}
