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
		
		// Construct the URL with query parameters
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://irctc1.p.rapidapi.com/api/v3/trainBetweenStations?fromStationCode=BVI&toStationCode=NDLS&dateOfJourney=2024-01-17"))
				.header("X-RapidAPI-Key", "aba3dfb600msh213cb11198aac29p1bd178jsnc7d2c0cb7589")
				.header("X-RapidAPI-Host", "irctc1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException: " + e);
		}
		return response.body();
	
}
	
}
