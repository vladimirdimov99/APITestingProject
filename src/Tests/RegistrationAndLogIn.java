package Tests;

import api.PostRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationAndLogIn {

    private static String name;
    private static String email;
    private static String password;

    @BeforeTest
    public static void credentials() {
        name = "Vladimir";
        email = "vladi_dimov99@gmail.com";
        password = "123456";
    }

    @Test
    public static void testSuccessfulLogin() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login(email, password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getAuthMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }

    @Test
    public static void testWrongPassword() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login(email, password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getAuthMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }

    @Test
    public static void testWrongUsername() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login("test@test.com", password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getAuthMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }

    @Test
    public static void testRegister() throws IOException{
        PostRequests postRequests = new PostRequests();
        postRequests.register(name, email, password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getAuthMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }
}
