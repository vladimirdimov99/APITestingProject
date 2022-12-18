package PostRequests;

import api.ResponseReader;
import helpers.CredentialsAndAllURL;
import helpers.JsonParser;
import helpers.SetAccessToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.io.InputStream;

public class LogInToTheAccount {

    private static String responseCode;
    private static String authMessage;
    private static String responseBody;
    private static String accessToken;
    private static String userID;
    private static String name;

    public static void login(String email, String password) throws IOException, ParseException {
        // Build the post request
        String postBody = "{\"email\":\"" + email + "\", " + "\"password\":\"" + password + "\"}";
        CredentialsAndAllURL credentialsAndURL = new CredentialsAndAllURL();
        credentialsAndURL.CredentialsAndURLS();
        HttpPost postLogin = new HttpPost(credentialsAndURL.getBaseURL() + credentialsAndURL.getLoginURL());
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
            SetAccessToken setAccessToken = new SetAccessToken();
            accessToken = setAccessToken.CheckAuthCodeAndSetAccessToken(responseBody);
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
