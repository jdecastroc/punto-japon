/**
 * 
 */
package jschools.crawler;

import org.jsoup.Jsoup;
//import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.regex.*;
import java.io.IOException;
import java.util.Vector;
/**
 * @author airwave
 * Web: Nisshynkyo
 *
 */

/**
 * 
 * Llamadas que quiero hacer
 * schoolList = Area(listaAreas.each).Prefecture(listaPrefectures.each).getSchools();
 * schoolList = Area(Area.getAreas.each).Prefecture.....
 * guardar datos del id del colegio, prefectura y area.
 * los colegios se trataran con sus ids a parte para guardar su informacion
 */

public class Area{
		
	private Vector<String> areaList = new Vector<String>();
	private String areaName;
			
	public class Prefecture{
			
		private Vector<String> prefecturesList = new Vector<String>();
		private Vector<String> schoolList = new Vector<String>();
		private String prefectureName;
		
		//Crawl each area website to take all the prefectures by his JAPANESE name (take care here)
		public Vector<String> getPrefectures(String web,Vector<String> areaList, Vector<String> prefectureList) throws IOException{
		
			return prefectureList;
		}
		
		//Crawl each prefecture website to gather the schools ID (on the http address)
		public Vector<String> getSchools(String web,Vector<String> schoolList) throws IOException{
			
			return schoolList;
		}
	}
	
	public Vector<String> getAreas(String web,Vector<String> areaList) throws IOException{
		
		return areaList;
	}
		
}
	
