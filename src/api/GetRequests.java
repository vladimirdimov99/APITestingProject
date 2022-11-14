package api;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.io.InputStream;

public class GetRequests {
    private static String urlString = "http://restapi.adequateshop.com/api/users?page=1";
    private static String url = "http://restapi.adequateshop.com/api/users/";
    private static String accessToken;
    private static String responseCode;
    private static String responseBody;
    private static String userID = "";


    public static void main(String[] args){ }

    public static void GetUserByID() throws IOException {
        PostRequests postRequests = new PostRequests();
        accessToken = postRequests.getAccessToken();
        userID = postRequests.getUserID();
        HttpGet getUsers = new HttpGet(url + userID);
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
    }
}
