package testsettings;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public abstract class TestSettings {
    protected WebDriver driver;
    protected Faker faker;
    public TestSettings(String browserName) {
        faker = new Faker();
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().clearDriverCache().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                break;
            case "яндекс.браузер" :
                WebDriverManager.chromedriver().clearDriverCache().setup();
                // Версия 24.1.0-stable
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                break;
        }
        
    }
    
    @After
    public void tearDown() throws InterruptedException {
        driver.quit();
    }
}
