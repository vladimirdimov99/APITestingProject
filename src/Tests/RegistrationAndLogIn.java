package Tests;

import PostRequests.LogInToTheAccount;
import PostRequests.Registration;
import helpers.CredentialsAndAllURL;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationAndLogIn {

    private static String name;
    private static String email;
    private static String password;

    @BeforeTest
    public static void Credentials() throws IOException, ParseException {
        CredentialsAndAllURL credentialsAndURL = new CredentialsAndAllURL();
        credentialsAndURL.CredentialsAndURLS();
        name = credentialsAndURL.getName();
        email = credentialsAndURL.getEmail();
        password = credentialsAndURL.getPassword();
    }

    @Test
    public static void LogIn() throws IOException, ParseException {
        LogInToTheAccount logInToTheAccount = new LogInToTheAccount();
        logInToTheAccount.login(email, password);
        String responseCode = logInToTheAccount.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = logInToTheAccount.getAuthMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }

    @Test
    public static void WrongPassword() throws IOException, ParseException {
        LogInToTheAccount logInToTheAccount = new LogInToTheAccount();
        logInToTheAccount.login(email, password);
        String responseCode = logInToTheAccount.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = logInToTheAccount.getAuthMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }

    @Test
    public static void WrongUsername() throws IOException, ParseException {
        LogInToTheAccount logInToTheAccount = new LogInToTheAccount();
        logInToTheAccount.login(email, password);
        String responseCode = logInToTheAccount.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = logInToTheAccount.getAuthMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }

    @Test
    public static void Register() throws IOException, ParseException {
        Registration registration = new Registration();
        registration.register(name, email, password);
        String responseCode = registration.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = registration.getAuthMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }
}
