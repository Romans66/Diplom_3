package pagesettings;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageSettings {
    protected WebDriver driver;
    protected JavascriptExecutor jsExecutor;
    
    public PageSettings(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
    
    public void elementClick(By locator) {
        driver.findElement(locator).click();
    }
    
    public void elementSendKey(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }
    
    public void waitOfUrlElement(String url) {
        int timeToWait = 3;
        new WebDriverWait(driver, Duration.ofSeconds(timeToWait))
                .until(ExpectedConditions.urlToBe(url));
    }
    
    public boolean isElementDisplayed(By element) {
        return driver.findElement(element).isDisplayed();
    }
    
    // Метод для скролла к элементу
    public void scrollToElement(By element) {
        // Создаем экземпляр JavascriptExecutor
        jsExecutor = (JavascriptExecutor) driver;
        // Выполняем скрипт для скролла к элементу
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
    }
}
