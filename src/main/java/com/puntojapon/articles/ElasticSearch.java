package com.puntojapon.articles;

import java.io.IOException;

public class ElasticSearch {
	
	public static void filesIndexation() {
//		ProcessBuilder pb = new ProcessBuilder("src/main/resources/indexBlogs.sh");
//		pb.inheritIO();
//		pb.directory(new File("src/main/resources"));
//		try {
//			pb.start();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try{
			// Run command and wait till it's done
	        Process p = Runtime.getRuntime().exec("src/main/resources/indexBlogs.sh");
	        p.waitFor();
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Error while trying to execute indexBlogs.sh");
		}
	}

}
