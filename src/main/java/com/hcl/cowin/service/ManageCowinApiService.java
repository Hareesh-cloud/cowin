package com.hcl.cowin.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.hcl.dto.Centers;


@Service
public class ManageCowinApiService {

	public Centers processCowinApi(String districtId, String date)  {

		String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=670&date=14/5/2021";
		String responseString = "";
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0");   
		HttpResponse response;
		try {
			response = httpClient.execute(request);
			 System.out.println("Response Code : "
	                    + response.getStatusLine().getStatusCode());
			HttpEntity entity = response.getEntity();
			 responseString = EntityUtils.toString(entity, "UTF-8");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		Centers cowinObjClass = gson.fromJson(responseString,Centers.class);
		
		return cowinObjClass;
	}
}
