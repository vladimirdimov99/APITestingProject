package GetRequests;

import api.ResponseReader;
import helpers.CredentialsAndAllURL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;

public class GetUserByID {

    private static String responseCode;

    public static String GetUserByID(String accessToken, String userID) throws IOException {
        String responseBody = "";
        CredentialsAndAllURL credentialsAndURL = new CredentialsAndAllURL();

        HttpGet getUsers = new HttpGet(credentialsAndURL.getBaseURL() + userID);
        getUsers.setHeader("Content-type", "application/json");
        getUsers.setHeader("Authorization", accessToken);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(getUsers);
        responseCode = response.getStatusLine().toString();

        //Parse the response body
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // A Simple JSON Response Read
            InputStream instream = entity.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            instream.close();
        }
        return responseBody;
    }
}
