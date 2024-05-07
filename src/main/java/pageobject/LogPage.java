package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pagesettings.PageSettings;

public class LogPage extends PageSettings {
    private final String URL_LOG_PAGE = "https://stellarburgers.nomoreparties.site/login";
    
    public LogPage(WebDriver driver) {
        super(driver);
    }
    
    private By emailInput =
            By.xpath("//input[@type='text']");
    private By passwordInput =
            By.xpath("//input[@type='password']");
    private By logButton = By.xpath("//button[text()='Войти']");
    
    public LogPage openLogPage() {
        driver.get(URL_LOG_PAGE);
        return this;
    }
    
    public String getLogPageURL() {
        return URL_LOG_PAGE;
    }
    
    public LogPage fillEmail(String email) {
        elementSendKey(emailInput, email);
        return this;
    }
    
    public LogPage fillPassword(String password) {
        elementSendKey(passwordInput, password);
        return this;
    }
    
    public LogPage logButtonClick() {
        elementClick(logButton);
        return this;
    }
    
    public LogPage logUser(String email, String password) {
        fillEmail(email);
        fillPassword(password);
        logButtonClick();
        return this;
    }
}