package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pagesettings.PageSettings;

public class RegPage extends PageSettings {
    private final String URL_REG_PAGE = "https://stellarburgers.nomoreparties.site/register";
    
    public RegPage(WebDriver driver) {
        super(driver);
    }
    
    private By nameInput =
            By.xpath("//form[@class='Auth_form__3qKeq mb-20']//label[text()='Имя']/following-sibling::input");
    private By emailInput =
            By.xpath("//form[@class='Auth_form__3qKeq mb-20']//label[text()='Email']/following-sibling::input");
    private By passwordInput =
            By.xpath("//form[@class='Auth_form__3qKeq mb-20']//label[text()='Пароль']/following-sibling::input");
    private By regButton = By.xpath("//button[text()='Зарегистрироваться']");
    private By errorMessageForIncorrectPassword = By.xpath("//p[text()='Некорректный пароль']");
    private By errorPasswordField =
            By.xpath("//div[@class='input pr-6 pl-6 input_type_password input_size_default input_status_error']");
    private By logInLink = By.xpath("//a[text()='Войти']");
    
    public RegPage openRegPage() {
        driver.get(URL_REG_PAGE);
        return this;
    }
    
    public String getRegPageURL() {
        return URL_REG_PAGE;
    }
    
    public RegPage fillName(String name) {
        elementSendKey(nameInput, name);
        return this;
    }
    
    public RegPage fillEmail(String email) {
        elementSendKey(emailInput, email);
        return this;
    }
    
    public RegPage fillPassword(String password) {
        elementSendKey(passwordInput, password);
        return this;
    }
    
    public RegPage regButtonClick() {
        elementClick(regButton);
        return this;
    }
    
    public RegPage regNewUser(String name, String email, String password) {
        fillName(name);
        fillEmail(email);
        fillPassword(password);
        regButtonClick();
        return this;
    }
    
    public WebElement getErrorMessageForIncorrectPassword() {
        return driver.findElement(errorMessageForIncorrectPassword);
    }
    
    public WebElement getErrorPasswordFrameForField() {
        return driver.findElement(errorPasswordField);
    }
    
    public RegPage logInLinkClick() {
        elementClick(logInLink);
        return this;
    }
}