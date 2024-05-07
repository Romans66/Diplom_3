package logtest;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.*;
import pageobject.elements.HeaderMenuElement;
import testsettings.TestSettings;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static testsettings.utills.CreateNewUsersUtil.createNewUser;
import static testsettings.utills.DeleteTestsUsersUtil.deleteUser;

public class LogTest extends TestSettings {
    
    private RegPage regPage;
    private LogPage logPage;
    private BurgerBuilderPage burgerBuilderPage;
    private ForgotPasswordPage forgotPasswordPage;
    private HeaderMenuElement headerMenuElement;
    private String name;
    private String email;
    private String password;
    
    public LogTest() {
        super("chrome");
    }
    
    @Before
    public void startSetUpTest() {
        regPage = new RegPage(driver);
        logPage = new LogPage(driver);
        burgerBuilderPage = new BurgerBuilderPage(driver);
        headerMenuElement = new HeaderMenuElement(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        
        // Генерируем тестовые данные
        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        
        createNewUser(email, password, name);
    }
    
    @Test
    @Step("Проверка успешного входа личный кабинет со страницы регистрации по ссылке 'Войти'")
    public void logFromRegPageTest() {
        regPage
                .openRegPage()
                .logInLinkClick()
                .waitOfUrlElement(logPage.getLogPageURL());
        logPage
                .logUser(email, password)
                .waitOfUrlElement(burgerBuilderPage.getBurgerBuilderPageURL());
        
        assertThat(driver.getCurrentUrl(), equalTo(burgerBuilderPage.getBurgerBuilderPageURL()));
    }
    
    @Test
    @Step("Проверка успешного входа личный кабинет со страницы конструктора по кнопке 'Войти в личный кабинет'")
    public void logFromBurgerBuilderButtonTest() {
        burgerBuilderPage
                .openBurgerBuilderPage()
                .logInButtonClick()
                .waitOfUrlElement(logPage.getLogPageURL());
        
        logPage
                .logUser(email, password)
                .waitOfUrlElement(burgerBuilderPage.getBurgerBuilderPageURL());
        
        assertThat(driver.getCurrentUrl(), equalTo(burgerBuilderPage.getCurrentURL()));
    }
    
    @Test
    @Step("Проверка успешного входа личный кабинет со страницы конструктора по ссылке 'Личный кабинет'")
    public void logFromHeaderPersonalCabLinkTest() {
        burgerBuilderPage.openBurgerBuilderPage();
        
        headerMenuElement
                .personalCabLinkClick()
                .waitOfUrlElement(logPage.getLogPageURL());
        
        logPage
                .logUser(email, password)
                .waitOfUrlElement(burgerBuilderPage.getBurgerBuilderPageURL());
        
        assertThat(driver.getCurrentUrl(), equalTo(burgerBuilderPage.getCurrentURL()));
    }
    
    @Test
    @Step("Проверка успешного входа личный кабинет со страницы восстановления пароля по ссылке 'Войти'")
    public void logFromForgotPasswordPageTest()  {
        burgerBuilderPage.openBurgerBuilderPage();
        
        forgotPasswordPage
                .openForgotPasswordPage()
                .logInLinkClick()
                .waitOfUrlElement(logPage.getLogPageURL());
        
        logPage
                .logUser(email, password)
                .waitOfUrlElement(burgerBuilderPage.getBurgerBuilderPageURL());
        
        assertThat(driver.getCurrentUrl(), equalTo(burgerBuilderPage.getCurrentURL()));
    }
    
    @After
    public void tearDown() {
        deleteUser(email, password, name);
        driver.quit();
    }
}
