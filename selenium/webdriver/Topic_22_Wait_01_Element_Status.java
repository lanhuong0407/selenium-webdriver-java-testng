package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Wait_01_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;
    By reconfirmEmailTextbox = By.cssSelector("input[name='reg_email_confirmation__']");


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.facebook.com/");
        sleepInSeconds(3);

    }

    @Test
    public void TC_01_Visible() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("huong@gmail.com");
        sleepInSeconds(3);

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextbox));

        Assert.assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());

    }

    @Test
    public void TC_02_Invisible_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();

        //Dieu kien 2: khong co tren UI, co tren HTML

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

        Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());

    }

    @Test
    public void TC_02_Invisible_Not_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);

        //Dieu kien 3: khong co tren UI, khong co tren HTML

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

        //Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());

    }

    @Test
    public void TC_03_Presence() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("huong@gmail.com");
        sleepInSeconds(3);

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

        //Dieu kien 2: khong co tren UI, co tren HTML

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

    }

    @Test
    public void TC_04_Staleness() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        //Tai thoi diem nay. xuat hien Element
        WebElement reconfirmEmail = driver.findElement(reconfirmEmailTextbox);

        //Click close, dong popup
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);

        //Dieu kien 3: khong co tren UI va HTML
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));

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