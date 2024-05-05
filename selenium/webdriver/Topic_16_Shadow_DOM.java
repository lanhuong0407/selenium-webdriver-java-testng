package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Shadow_DOM() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        sleepInSeconds(3);

        //Di theo dung cau truc cua HTML/ DOM

        // driver = dai dien cho Real DOM (DOM ben ngoai)
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));

        // shadowRootContext = dai dien cho shadow DOM ben trong
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

        String someText = shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);

        WebElement nestedShadowHostElement = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRootContext = nestedShadowHostElement.getShadowRoot();

        String nestedText = nestedShadowRootContext.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        Assert.assertEquals(nestedText,"nested text");

    }

    @Test
    public void TC_02_() {

    }

    @Test
    public void TC_03_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond *1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}