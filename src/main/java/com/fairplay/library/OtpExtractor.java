package com.fairplay.library;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class OtpExtractor {

	static String otp;

	@SuppressWarnings("finally")
	public static String sendPostRequest(String apiUrl, String accessToken, String requestBody, String desiredKey,
			String desiredKey1) throws Exception {

		String otp = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(apiUrl);

		// Set the header
		httpPost.addHeader("accessToken", accessToken);

		// Set the request body
		StringEntity requestEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
		httpPost.setEntity(requestEntity);

		// Send the request
		CloseableHttpResponse response = httpClient.execute(httpPost);

		try {
			// Process the response
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");

			// Print the response
			// System.out.println("Response: " + responseString);
			JSONObject jsonResponse = new JSONObject(responseString);

			// Extract the desired key value
			Object desiredValue = jsonResponse.get(desiredKey);

			// Print the desired key value
			//System.out.println(desiredKey + ": " + desiredValue.toString());

			JSONObject jsonResponse1 = new JSONObject(desiredValue.toString());

			Object desiredValue1 = jsonResponse1.get(desiredKey1);

			OtpExtractor.otp = desiredValue1.toString();
			otp = OtpExtractor.otp;
			System.out.println(desiredKey1 + ": " + otp);

		} finally {
			response.close();
			httpClient.close();
			return otp;
		}
	}

	public static void main(String[] args) throws Exception {

		String apiUrl = GetGsonFairplayTestData.getConfigData().getAdminAPIUrl();

		String requestBody = "{\"email\":\"tejal.gavade@wohlig.com\"}";

		String desiredKey = GetGsonFairplayTestData.getConfigData().getData();

		String desiredKey1 = GetGsonFairplayTestData.getConfigData().getAdminLoginOTP();

		OtpExtractor.sendPostRequest(apiUrl, null, requestBody, desiredKey, desiredKey1);

	}

}
