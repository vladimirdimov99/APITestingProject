package helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class CredentialsAndAllURL {

    private static String baseURL;
    private static String registerURL;
    private static String email;
    private static String password;
    private static String name;
    public static String loginURL;

    public static void CredentialsAndURLS() throws IOException, ParseException {
        JSONParser jsonparser = new JSONParser();
        FileReader reader = new FileReader("C:/Users/Vladimir/IdeaProjects/APITestingProject/src/config/config.json");
        Object object = jsonparser.parse(reader);
        JSONObject configJSONObj = (JSONObject) object;

        baseURL = configJSONObj.get("baseURL").toString();
        loginURL = configJSONObj.get("loginURL").toString();
        registerURL = configJSONObj.get("registerURL").toString();
        email = configJSONObj.get("email").toString();
        password = configJSONObj.get("password").toString();
        name = configJSONObj.get("name").toString();
    }

    public void setBaseURL (String baseURL){this.baseURL = baseURL;}

    public static String getBaseURL (){return baseURL;}

    public void setName (String name){this.name = name;}

    public static String getName() {return name;}

    public void setLoginURL(String loginURL){this.loginURL = loginURL;}

    public static String getLoginURL() {
       return loginURL;
    }

    public void setRegisterURL(String registerURL) {this.registerURL = registerURL;}

    public String getRegisterURL() {return registerURL;}

    public void setEmail(String email) {this.email = email;}

    public String getEmail() {return email;}

    public void setPassword (String password) {this.password = password;}

    public static String getPassword() {return password;}
}
