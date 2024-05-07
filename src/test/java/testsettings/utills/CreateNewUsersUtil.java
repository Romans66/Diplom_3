package testsettings.utills;

import testsettings.utills.obj.UserObj;

import static io.restassured.RestAssured.given;

public class CreateNewUsersUtil {
    public static void createNewUser(String email, String password, String name) {
        String createNewUserEndpoint = "https://stellarburgers.nomoreparties.site/api/auth/register";
        UserObj userObj = new UserObj(email, password, name);
        given()
                .header("Content-Type", "application/json")
                .body(userObj)
                .post(createNewUserEndpoint);
    }
}
