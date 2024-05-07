package testsettings.utills;

import testsettings.utills.obj.UserObj;

import static io.restassured.RestAssured.given;

public class ReceiveAccessTokenTestsUsersUtil {
    public static String getUsersAccessToken(String name, String email, String password) {
        
        UserObj userObj = new UserObj(name, email, password);
        String loginUserEndpoint = "https://stellarburgers.nomoreparties.site/api/auth/login";
        
        String accessToken = given()
                .header("Content-Type", "application/json")
                .body(userObj)
                .post(loginUserEndpoint)
                .then()
                .extract().body().jsonPath().getString("accessToken");
        return accessToken;
    }
}
