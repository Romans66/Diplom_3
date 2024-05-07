package regtest;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.BurgerBuilderPage;
import pageobject.LogPage;
import pageobject.RegPage;
import testsettings.TestSettings;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static testsettings.utills.DeleteTestsUsersUtil.deleteUser;

public class RegTest extends TestSettings {
    
    private RegPage regPage;
    private LogPage logPage;
    private String name;
    private String email;
    private String password;
    
    public RegTest() {
        super("chrome");
    }
    
    @Before
    public void startSetUpTest() {
        regPage = new RegPage(driver);
        logPage = new LogPage(driver);
        
        // Генерируем тестовые данные
        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        
        // Открываем страницу регистрации
        regPage.openRegPage();
    }
    
    @Test
    @Step("Проверка успешной регистрации пользователя")
    public void regTest() {
        String expectedBuilderUrl = "https://stellarburgers.nomoreparties.site/";
        String expectedLogUrl = "https://stellarburgers.nomoreparties.site/login";
        
        regPage
                .regNewUser(name, email, password)
                .waitOfUrlElement(expectedLogUrl);
        
        logPage
                .logUser(email, password)
                .waitOfUrlElement(expectedBuilderUrl);
        
        assertThat(driver.getCurrentUrl(), equalTo(expectedBuilderUrl));
    }
    
    @Test
    @Step("Проверка ошибки при введении некорректного пароля")
    public void regWithIncorrectPasswordTest() {
        String expectedMessageForIncorrectPassword = "Некорректный пароль";
        String incorrectPassword = "12345";
        
        regPage
                .regNewUser(name, email, incorrectPassword)
                .getErrorPasswordFrameForField()
                .isDisplayed();
        
        regPage
                .getErrorMessageForIncorrectPassword()
                .isDisplayed();
        
        assertThat(regPage.getErrorMessageForIncorrectPassword().getText(), equalTo(expectedMessageForIncorrectPassword));
    }
    
    @After
    public void tearDown() {
        deleteUser(email, password, name);
        driver.quit();
    }
}
