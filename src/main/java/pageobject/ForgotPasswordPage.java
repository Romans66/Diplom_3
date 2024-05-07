package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pagesettings.PageSettings;

public class ForgotPasswordPage extends PageSettings {
    private final String URL_LOG_PAGE = "https://stellarburgers.nomoreparties.site/forgot-password";
    
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }
    
    private By logInLink = By.xpath("//a[text()='Войти']");
    
    public ForgotPasswordPage openForgotPasswordPage() {
        driver.get(URL_LOG_PAGE);
        return this;
    }
    
    public String getLogPageURL() {
        return URL_LOG_PAGE;
    }
    
    public ForgotPasswordPage logInLinkClick() {
        elementClick(logInLink);
        return this;
    }
}