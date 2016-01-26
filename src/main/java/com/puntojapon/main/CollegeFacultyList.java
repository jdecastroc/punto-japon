/**
 * 
 */
package com.puntojapon.main;

import java.util.Vector;


/**
 * @author Jorge de Castro
 *
 */
public class CollegeFacultyList {
	private String collegeName;
	private Vector<CollegeFaculty> collegeFacultyList;
	
	public CollegeFacultyList(String collegeName){
		setListCollegeName(collegeName);
		this.collegeFacultyList = new Vector<CollegeFaculty>();
	}
	
	public String getListCollegeName() {
		return this.collegeName;
	}
	
	public Vector<CollegeFaculty> getCollegeFaculties() {
		return this.collegeFacultyList;
	}
	
	public void setListCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	
	public CollegeFaculty getCollegeFacultyLast(){
		return this.collegeFacultyList.lastElement();
	}
	
	public CollegeFaculty getCollegeFacultyFirst(){
		return this.collegeFacultyList.firstElement();
	} 
	
	public int getCollegeFacultySize(){
		return this.collegeFacultyList.size();
	}
	
	public CollegeFaculty getCollegeFacultyAt(int index){
		return this.collegeFacultyList.elementAt(index);
	}
	
	public boolean addCollegeFaculty(CollegeFaculty faculty) {
		this.collegeFacultyList.addElement(faculty);
		return true;
	}
	
	public void translateCollegeFacultyList(){
		for (CollegeFaculty i : this.collegeFacultyList) {
			i.setFacultyName(Translator.translate(i.getFacultyName()));
		}
	}
}
