package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Popup_01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Fixed_Popup_InDOM_1() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_")).click();
        sleepInSeconds(2);

        By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div");

        //Kiem tra login popup dang hien thi
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div input#account-input")).sendKeys("321huong123");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div input#password-input")).sendKeys("321huong123");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).getText(),"Tài khoản không tồn tại!");

        //Close popup
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        sleepInSeconds(2);

        //Kiem tra login popup khong hien thi
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
    }

    @Test
    public void TC_02_Fixed_Popup_InDOM_2() {
        driver.get("https://skills.kynaenglish.vn/");

    }

    @Test
    public void TC_03_Fixed_Popup_Not_InDOM_1() {

        driver.get("https://tiki.vn/");
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        sleepInSeconds(3);

        //Kiem tra popup hien thi
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]"))
                .getText(),"Email không được để trống");

        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span"))
                .getText(),"Mật khẩu không được để trống");

        //Close popup
        driver.findElement(By.cssSelector("button.btn-close")).click();
        sleepInSeconds(3);


        //Khi popup dong lai, HTML khong con trong DOM
        //findElement should not be used to look for non-present elements
        //Assert.assertFalse(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        //use findElements(By) and assert zero length response instead.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(),0);


    }


    @Test
    public void TC_04_Fixed_Popup_Not_InDOM_2() {
        driver.get("https://www.facebook.com/");

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());

        driver.findElement(By.cssSelector("img[class='_8idr img']")).click();
        sleepInSeconds(2);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.cssSelector("div._8ien")).size(),0);


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