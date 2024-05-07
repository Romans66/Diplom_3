package burgerbuildertest;

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

public class BurgerBuilderTest extends TestSettings {
    private LogPage logPage;
    private RegPage regPage;
    private BurgerBuilderPage burgerBuilderPage;
    private HeaderMenuElement headerMenuElement;
    private PersonalCabPage personalCabPage;
    private ForgotPasswordPage forgotPasswordPage;
    private String name;
    private String email;
    private String password;
    
    public BurgerBuilderTest() {
        super("яндекс.браузер");
    }
    
    @Before
    public void startSetUpTest() {
        logPage = new LogPage(driver);
        burgerBuilderPage = new BurgerBuilderPage(driver);
        headerMenuElement = new HeaderMenuElement(driver);
        regPage = new RegPage(driver);
        personalCabPage = new PersonalCabPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        
        // Генерируем тестовые данные
        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        
        createNewUser(email, password, name);
    }
    
    @Test
    @Step("Проверка успешного перехода на страницу конструктора по лого из страницы регистрации")
    public void goToTheStellarBurgersLogoFromRegPageTest() {
        regPage.openRegPage();
    
        headerMenuElement
                .stellarBurgersLogoClick()
                .waitOfUrlElement(burgerBuilderPage.getBurgerBuilderPageURL());
        
        assertThat(driver.getCurrentUrl(), equalTo(burgerBuilderPage.getBurgerBuilderPageURL()));
    }
    
    @Test
    @Step("Проверка успешного перехода на страницу конструктора по лого из страницы входа")
    public void goToTheStellarBurgersLogoFromLogPageTest() {
        logPage.openLogPage();
        
        headerMenuElement
                .stellarBurgersLogoClick()
                .waitOfUrlElement(burgerBuilderPage.getBurgerBuilderPageURL());
        
        assertThat(driver.getCurrentUrl(), equalTo(burgerBuilderPage.getBurgerBuilderPageURL()));
    }
    
    @Test
    @Step("Проверка успешного перехода на страницу конструктора по лого из страницы восстановления пароля")
    public void goToTheStellarBurgersLogoFromForgotPasswordPageTest() {
        forgotPasswordPage.openForgotPasswordPage();
        
        headerMenuElement
                .stellarBurgersLogoClick()
                .waitOfUrlElement(burgerBuilderPage.getBurgerBuilderPageURL());
        
        assertThat(driver.getCurrentUrl(), equalTo(burgerBuilderPage.getBurgerBuilderPageURL()));
    }
    
    @Test
    @Step("Проверка успешного перехода на страницу конструктора по лого из страницы личного кабинета")
    public void goToTheStellarBurgersLogoFromPersonalCabPageTest() {
        logPage
                .openLogPage()
                .logUser(email, password)
                .waitOfUrlElement(burgerBuilderPage.getBurgerBuilderPageURL());
        
        headerMenuElement
                .personalCabLinkClick()
                .waitOfUrlElement(personalCabPage.getPersonalCabPageURL());
        
        headerMenuElement
                .stellarBurgersLogoClick()
                .waitOfUrlElement(burgerBuilderPage.getBurgerBuilderPageURL());
        
        assertThat(driver.getCurrentUrl(), equalTo(burgerBuilderPage.getBurgerBuilderPageURL()));
    }
    
    @Test
    @Step("Проверка успешного перехода по разделам 'Булки', 'Соусы', 'Начинки'")
    public void goToTheStellarBurgersTabsTest() {
        logPage
                .openLogPage()
                .logUser(email, password)
                .waitOfUrlElement(burgerBuilderPage.getBurgerBuilderPageURL());
        
        burgerBuilderPage.bunsTubClickAndCheck().isDisplayed();
        burgerBuilderPage.sauceTubClickAndCheck().isDisplayed();
        burgerBuilderPage.fillingTubClickAndCheck().isDisplayed();
    
    }
    
    @After
    public void tearDown() {
        deleteUser(email, password, name);
        driver.quit();
    }
}
