package Tests;

import GetRequests.GetUserByID;
import PostRequests.LogInToTheAccount;
import PostRequests.Registration;
import helpers.CredentialsAndAllURL;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class RegisterLogInAndGetUserByID {

    private static String name;
    private static String email;
    private static String password;
    private static String accessToken;
    private static String userID;

    @BeforeTest
    public static void allCredentials() throws IOException, ParseException {
        CredentialsAndAllURL credentialsAndURL = new CredentialsAndAllURL();
        credentialsAndURL.CredentialsAndURLS();

        name = credentialsAndURL.getName();
        email = credentialsAndURL.getEmail();
        password = credentialsAndURL.getPassword();
    }

    @Test(priority = 1)
    public static void RegisterSuccessfully() throws IOException, ParseException {
        Registration registration = new Registration();
        registration.register(name, email, password);
        String responseCode = registration.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = registration.getAuthMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }

    @Test(priority = 2)
    public static void LogInSuccessfully() throws IOException, ParseException {
        LogInToTheAccount logInToTheAccount = new LogInToTheAccount();
        logInToTheAccount.login(email, password);
        String responseCode = logInToTheAccount.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);

        accessToken = logInToTheAccount.getAccessToken();
        userID = logInToTheAccount.getUserID();
        String authMessage = logInToTheAccount.getAuthMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }

    @Test(priority = 3)
    public static void GetUserByID() throws IOException, ParseException {
        LogInToTheAccount logInToTheAccount = new LogInToTheAccount();
        GetUserByID getUserByID = new GetUserByID();
        getUserByID.GetUserByID(accessToken, userID);
        Assert.assertEquals(name, logInToTheAccount.getName());
        Assert.assertEquals(accessToken, logInToTheAccount.getAccessToken());
    }
}
