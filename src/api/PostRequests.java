package api;

import helpers.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;

public class PostRequests {
    private static String loginUrl = "http://restapi.adequateshop.com/api/authaccount/login";
    private static String responseCode;
    private static String responseBody;
    private static String accessToken;
    private static String authMessage;
    private static String userID;
    private static String name;
    private static String registrationUrl = "http://restapi.adequateshop.com/api/authaccount/registration";

    public static void main(String[] args) { }

    public void register(String name, String email, String password) throws IOException {
        // Build the post request
        String postBody = "{\"name\":\"" + name + "\",\"email\":\"" + email + "\", " + "\"password\":\"" + password + "\"}";
        HttpPost postRegistration = new HttpPost(registrationUrl);
        postRegistration.setEntity(new StringEntity(postBody));
        postRegistration.setHeader("Content-type", "application/json");
        HttpClient httpClient = HttpClientBuilder.create().build();
        // Execute the post request
        HttpResponse response = httpClient.execute(postRegistration);
        responseCode = response.getStatusLine().toString();
        // Fill in the response body
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // A Simple JSON Response Read
            InputStream instream = entity.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            instream.close();
        }
        // Extract and set the access token
        if (responseCode.contains("200") == true) {
            JsonParser json = new JsonParser();
            String authCode = json.getResponseCode(responseBody);
            authMessage = json.getAuthMessage(responseBody);
            if (authCode.equals("0")) {
                accessToken = json.getAccessToken(responseBody);
            }
        }
    }
    public static void login(String email, String password) throws IOException {
        // Build the post request
        String postBody = "{\"email\":\"" + email + "\", " + "\"password\":\"" + password + "\"}";
        HttpPost postLogin = new HttpPost(loginUrl);
        postLogin.setEntity(new StringEntity(postBody));
        postLogin.setHeader("Content-type", "application/json");
        HttpClient httpClient = HttpClientBuilder.create().build();
        // Execute the post request
        HttpResponse response = httpClient.execute(postLogin);
        responseCode = response.getStatusLine().toString();
        // Fill in the response body
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // A Simple JSON Response Read
            InputStream instream = entity.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            instream.close();
        }
        // Extract and set the access token
        JsonParser json = new JsonParser();
        if (responseCode.contains("200") == true) {
            String authCode = json.getResponseCode(responseBody);
            authMessage = json.getAuthMessage(responseBody);
            if (authCode.equals("0")) {
                accessToken = json.getAccessToken(responseBody);
            }
        }
        userID = json.getUserID(responseBody);
        name = json.getName(responseBody);
    }

    public static String getAccessToken() {
        return accessToken;
    }
    public static String getName(){return name;}
    public static String getUserID() {return userID;}
    public static String getResponseCode() {return responseCode;}

    public static String getResponseBody() {
        return responseCode;
    }

    public static String getAuthMessage() {
        return authMessage;
    }
}
