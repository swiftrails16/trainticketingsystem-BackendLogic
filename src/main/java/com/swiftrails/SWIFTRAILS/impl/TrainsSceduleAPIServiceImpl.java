package com.swiftrails.SWIFTRAILS.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.swiftrails.SWIFTRAILS.services.TrainsSceduleAPIService;

@Service
public class TrainsSceduleAPIServiceImpl implements TrainsSceduleAPIService{

	@Override
	public String fetchTrainStationsCodes() {
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create("https://api.publicapis.org/entries"))
////				.header("X-RapidAPI-Key", "c2fe9ef819msh6986a34c65d1874p1d8758jsn3f0df5ea548a")
////				.header("X-RapidAPI-Host", "community-nyc-subway-data.p.rapidapi.com")
//				
//				.method("GET", HttpRequest.BodyPublishers.noBody())
//				.build();
//		HttpResponse<String> response = null;
//		try {
//			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return response.body();
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://trains.p.rapidapi.com/"))
				.header("content-type", "application/json")
				.header("X-RapidAPI-Key", "c2fe9ef819msh6986a34c65d1874p1d8758jsn3f0df5ea548a")
				.header("X-RapidAPI-Host", "trains.p.rapidapi.com")
				.method("POST", HttpRequest.BodyPublishers.ofString("{\r\n    \"search\": \"Rajdhani\"\r\n}"))
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.body();
	
}
	
}
