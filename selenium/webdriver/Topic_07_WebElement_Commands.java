package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_WebElement_Commands {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {}
    @Test
    public void TC_01_Element() {
        driver.getCurrentUrl();
        driver.findElements(By);
        driver

    }
    @Test
    public void TC_02() {

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}