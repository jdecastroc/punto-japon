package com.puntojapon.articles;

import java.io.File;
import java.io.IOException;

public class ElasticSearch {
	
	public static void filesIndexation() {
		ProcessBuilder pb = new ProcessBuilder("src/main/resources/indexBlogs.sh");
		pb.inheritIO();
		pb.directory(new File("src/main/resources"));
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
