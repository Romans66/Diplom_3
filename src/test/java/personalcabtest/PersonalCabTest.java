package personalcabtest;

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

public class PersonalCabTest extends TestSettings {
    private LogPage logPage;
    private BurgerBuilderPage burgerBuilderPage;
    private HeaderMenuElement headerMenuElement;
    private PersonalCabPage personalCabPage;
    private String name;
    private String email;
    private String password;
    
    public PersonalCabTest() {
        super("яндекс.браузер");
    }
    
    @Before
    public void startSetUpTest() {
        logPage = new LogPage(driver);
        burgerBuilderPage = new BurgerBuilderPage(driver);
        headerMenuElement = new HeaderMenuElement(driver);
        personalCabPage = new PersonalCabPage(driver);
        
        // Генерируем тестовые данные
        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        
        createNewUser(email, password, name);
    }
    
    @Test
    @Step("Проверка успешного перехода на страницу личного каабинета по ссылке в хедере")
    public void goToThePersonalCabTest() {
        logPage
                .openLogPage()
                .logUser(email, password)
                .waitOfUrlElement(burgerBuilderPage.getBurgerBuilderPageURL());
        
        headerMenuElement
                .personalCabLinkClick()
                .waitOfUrlElement(personalCabPage.getPersonalCabPageURL());
        
        assertThat(driver.getCurrentUrl(), equalTo(personalCabPage.getPersonalCabPageURL()));
    }
    
    @Test
    @Step("Проверка успешного разлогина из личного кабинета")
    public void LogOutFromThePersonalCabTest() {
        logPage
                .openLogPage()
                .logUser(email, password)
                .waitOfUrlElement(burgerBuilderPage.getBurgerBuilderPageURL());
        
        headerMenuElement
                .personalCabLinkClick()
                .waitOfUrlElement(personalCabPage.getPersonalCabPageURL());
        
        personalCabPage
                .logOutButtonClick()
                .waitOfUrlElement(logPage.getLogPageURL());
        
        assertThat(driver.getCurrentUrl(), equalTo(logPage.getLogPageURL()));
    }
    
    @After
    public void tearDown() {
        deleteUser(email, password, name);
        driver.quit();
    }
}
