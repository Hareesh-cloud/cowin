package com.hcl.cowin.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;


@Service
public class ManageCowinApiService {

	private static void postData(String json, String url) throws Exception {

		StringEntity postingString = new StringEntity(json);
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet post = new HttpGet(url);
		HttpResponse response;
		try {
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
