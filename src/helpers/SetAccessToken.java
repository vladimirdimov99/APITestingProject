package helpers;

public class SetAccessToken {

    public static String CheckAuthCodeAndSetAccessToken(String response){
            JsonParser json = new JsonParser();
            String authCode = json.getResponseCode(response);
            String accessToken = "";
            if (authCode.equals("0")) {
                 accessToken = json.getAccessToken(response);
            }
            return accessToken;
    }

}
