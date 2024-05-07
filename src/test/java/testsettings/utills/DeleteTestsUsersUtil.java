package testsettings.utills;

import testsettings.utills.obj.UserObj;

import static io.restassured.RestAssured.given;
import static testsettings.utills.ReceiveAccessTokenTestsUsersUtil.getUsersAccessToken;

public class DeleteTestsUsersUtil {
    public static void deleteUser(String email, String password, String name) {
        if (email != null && password != null) {
            UserObj userObj = new UserObj(email, password, name);
            String authorizationHeader = "Authorization";
            String deleteUserEndpoint = "https://stellarburgers.nomoreparties.site/api/auth/user";
            try {
                given()
                        .header("Content-Type", "application/json")
                        .header(authorizationHeader, getUsersAccessToken(email, password, name))
                        .body(userObj)
                        .delete(deleteUserEndpoint)
                        .then().statusCode(202);
                System.out.println("Пользователь успешно удален");
            } catch (IllegalArgumentException e) {
                System.err.println("accessToken был передан пустым, удаление пользователя не произошло");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Удаление пользователя не требуется");
        }
    }
}
