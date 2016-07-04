package com.puntojapon.articles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.puntojapon.common.appLogger;

public class SearchArticles {
	
	public static String searchByTitle (String title, String remoteIp) throws IOException, InterruptedException {
		
		StringBuilder processOutput = new StringBuilder();

		// Query para busqueda en elasticsearch
		String query = "{\"query\": {\"nested\": {\"path\": \"data\",\"query\": {\"match\": {\"data.Title\": \"" + title + "\"}},\"inner_hits\": {}}}}";

		if (!title.equals("")) {
			appLogger.logInfo(" (" + remoteIp + ") [ARTICLES_SEARCH] - " + title);

			// Se arma el proceso que se va a ejecutar en el servidor
			ProcessBuilder processBuilder = new ProcessBuilder("curl", "-s", "-XPOST",
					"http://91.134.143.80:9200/blogs/articulos/_search", "-d", query);

			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();

			// Buffer para leer el output del proceso
			try (BufferedReader processOutputReader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));) {
				String readLine;

				while ((readLine = processOutputReader.readLine()) != null) {
					processOutput.append(readLine + System.lineSeparator());
				}
				process.waitFor();
			}

		}
		
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject) parser.parse(processOutput.toString().trim());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = gson.toJson(json);
		
		return !prettyJson.equals("") ? prettyJson : "error";
	}

}
