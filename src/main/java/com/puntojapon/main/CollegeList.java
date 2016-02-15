/**
 * 
 */
package com.puntojapon.main;
import java.util.Vector;

/**
 * @author Jorge de Castro
 *
 */
public class CollegeList {
	private String searchType; //tipo de busqueda (prefecture, typeStudies, typeUni)
	private int searchFound; //Numero de universidades encontradas
	private boolean searchState; //Facilita una busqueda correcta o incorrecta
	private Vector<Object> collegeList;
	
	public CollegeList(String searchType){ //type = prefectura,tipo_estudios,tipo_universidad(priv,pub)
		this.setCollegeListType(searchType);
		this.collegeList = new Vector<Object>();
	}
	
	public void setCollegeListType(String type) {
		this.searchType = type;
	}
	
	public void setSearchFound(int number){
		this.searchFound = number;
	}
	
	public void setSearchState(boolean state) {
		this.searchState = state;
	}
	
	public int getSearchFound() {
		return this.searchFound;
	}
	
	public boolean getSearchState() {
		return this.searchState;
	}
	
	public String getCollegeListType() {
		return this.searchType;
	}
	
	
	public Vector<Object> getCollegeList() {
		return this.collegeList;
	}
	
	public boolean addCollege(Object college) {
		getCollegeList().addElement(college);
		return true;
	}

}