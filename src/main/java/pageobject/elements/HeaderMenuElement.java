package pageobject.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pagesettings.PageSettings;

public class HeaderMenuElement extends PageSettings {
    
    public HeaderMenuElement(WebDriver driver) {
        super(driver);
    }
    
    private By personalCabHeaderLink = By.xpath("//p[text()='Личный Кабинет']");
    private By constructorHeaderLink = By.xpath("//p[text()='Конструктор']");
    private By listOfOrdersHeaderLink = By.xpath("//p[text()='Лента Заказов']");
    private By logoHeaderLing = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    
    public HeaderMenuElement personalCabLinkClick() {
        elementClick(personalCabHeaderLink);
        return this;
    }
    
    public HeaderMenuElement stellarBurgersLogoClick() {
        elementClick(logoHeaderLing);
        return this;
    }
}
