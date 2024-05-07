package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pagesettings.PageSettings;

public class PersonalCabPage extends PageSettings {
    private final String URL_PERSONAL_CAB_PAGE = "https://stellarburgers.nomoreparties.site/account/profile";
    
    private By logOutButton = By.xpath("//button[text()='Выход']");
    
    public PersonalCabPage(WebDriver driver) {
        super(driver);
    }
    
    public PersonalCabPage openPersonalCabPage() {
        driver.get(URL_PERSONAL_CAB_PAGE);
        return this;
    }
    
    public String getPersonalCabPageURL() {
        return URL_PERSONAL_CAB_PAGE;
    }
    
    public PersonalCabPage logOutButtonClick() {
        elementClick(logOutButton);
        return this;
    }
}
