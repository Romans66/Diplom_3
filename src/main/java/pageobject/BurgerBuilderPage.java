package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pagesettings.PageSettings;

public class BurgerBuilderPage extends PageSettings {
    private final String URL_BURGER_BUILDER_PAGE = "https://stellarburgers.nomoreparties.site/";
    
    public BurgerBuilderPage(WebDriver driver) {
        super(driver);
    }
    
    private By bunsTub = By.xpath("//span[text()='Булки']");
    private By bunsSelectedTub = By.xpath("//span[text()='Булки']/parent::div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    private By bunHeader = By.xpath("//h2[text()='Булки']");
    private By sauceTub = By.xpath("//span[text()='Соусы']");
    private By sauceSelectedTub = By.xpath("//span[text()='Соусы']/parent::div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    private By sauceHeader = By.xpath("//h2[text()='Соусы']");
    private By fillingTub = By.xpath("//span[text()='Начинки']");
    private By fillingSelectedTub = By.xpath("//span[text()='Начинки']/parent::div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    private By fillingHeader = By.xpath("//h2[text()='Начинки']");
    private By logInAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    
    public BurgerBuilderPage openBurgerBuilderPage() {
        driver.get(URL_BURGER_BUILDER_PAGE);
        return this;
    }
    
    public String getBurgerBuilderPageURL() {
        return URL_BURGER_BUILDER_PAGE;
    }
    
    public BurgerBuilderPage logInButtonClick() {
        elementClick(logInAccountButton);
        return this;
    }
    
    public WebElement bunsTubClickAndCheck() {
        isElementDisplayed(bunsTub);
        scrollToElement(bunHeader);
        isElementDisplayed(bunsSelectedTub);
        return driver.findElement(bunHeader);
    }
    
    public WebElement sauceTubClickAndCheck() {
        isElementDisplayed(sauceTub);
        scrollToElement(sauceHeader);
        isElementDisplayed(sauceSelectedTub);
        return driver.findElement(sauceHeader);
    }
    
    public WebElement fillingTubClickAndCheck() {
        isElementDisplayed(fillingTub);
        scrollToElement(fillingHeader);
        isElementDisplayed(fillingSelectedTub);
        return driver.findElement(fillingHeader);
    }
}
