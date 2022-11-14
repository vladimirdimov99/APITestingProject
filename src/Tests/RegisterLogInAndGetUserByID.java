package Tests;

import api.GetRequests;
import api.PostRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegisterLogInAndGetUserByID {

    private static String name;
    private static String email;
    private static String password;

    @BeforeTest
    public static void allCredentials() {
        name = "Vladimir";
        email = "vladi_dimov24@gmail.com";
        password = "123456";
    }

    @Test(priority = 1)
    public static void testRegister() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.register(name, email, password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getAuthMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }

    @Test(priority = 2)
    public static void testSuccessfulLogin() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login(email, password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getAuthMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }

    @Test(priority = 3)
    public static void testGetUserByID() throws  IOException{
        GetRequests getRequests = new GetRequests();
        PostRequests postRequests = new PostRequests();
        getRequests.GetUserByID();
        Assert.assertEquals(name, postRequests.getName());
    }
}
