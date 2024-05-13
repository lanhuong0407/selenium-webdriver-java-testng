package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_30_Wait_09_Fluent {
    WebDriver driver;
    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> fluentElement;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        fluentDriver = new FluentWait<WebDriver>(driver);

    }

    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        // Cho cho HelloWorld text hien thi trong vong 10s
        //Setting
        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        //Condition
        fluentDriver.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed();
            }
        });
    }

    @Test
    public void TC_02_() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countDownTime = driver.findElement(By.cssSelector("div#javascript_countdown_time"));

        fluentElement = new FluentWait<WebElement>(countDownTime);

        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                return webElement.getText().endsWith("00");
            }
        });

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}