package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_29_Wait_08_Mix_Implicit_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Only_Implicit_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.facebook.com/");

        // Khi vao tim element, thi tim thay ngay
        // Khong can cho het timeouts
        driver.findElement(By.cssSelector("input#email"));
    }

    @Test
    public void TC_02_Only_Implicit_Not_Found() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.facebook.com/");

        // Khi vao tim element, khong thay
        // Polling, moi 0.5s tim lai 1 lan
        // Khi het timeouts, danh fail TC
        // Throw exception: NoSuchElementException
        driver.findElement(By.cssSelector("input#automation"));

    }

    @Test 
    public void TC_03_Only_Explicit_Found() {
        driver.get("https://www.facebook.com/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

    }

    @Test
    public void TC_04_Only_Explicit_Not_Found() {

        driver.get("https://www.facebook.com/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Khi vao tim element, khong thay
        // Polling, moi 0.5s tim lai 1 lan
        // Khi het timeouts, danh fail TC
        // Throw exception: TimeoutException

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));

    }

    @Test
    public void TC_05_Mix_Implicit_Explicit_Not_Found_Param_By() {

        driver.get("https://www.facebook.com/");

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));

    }

    @Test
    public void TC_06_Mix_Implicit_Explicit_Not_Found_Param_WebElement() {

        driver.get("https://www.facebook.com/");

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#automation"))));

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}