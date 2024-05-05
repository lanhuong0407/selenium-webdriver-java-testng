package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_11_Button {
    WebDriver driver;


    WebDriverWait explicitWait;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");

        //Verify button disable khi chua click vao checkbox
        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));
        Assert.assertFalse(registerButton.isEnabled());


        //Verify button enable khi da duoc click vao checkbox
        driver.findElement(By.cssSelector("input#chinhSach")).click();
        sleepInSeconds(2);
        Assert.assertTrue(registerButton.isEnabled());

        //Lay ra ma mau nen cua button
        String registerBackgoundRGB = registerButton.getCssValue("background-color");
        System.out.println("Background color = " +registerBackgoundRGB);

        //Convert tu kieu String (ma RGB) sang ma Hexa
        Color registerBackgoundColor = Color.fromString(registerBackgoundRGB);

        //Convert qua kieu Hexa
        String registerBackgroundHexa = registerBackgoundColor.asHex();
        System.out.println(("Background color Hexa = " + registerBackgroundHexa));

        Assert.assertEquals(registerBackgroundHexa.toLowerCase(), "#ef5a00");

    }

    @Test
    public void TC_02_Fahasha_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-item.popup-login-tab-login")).click();

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        Assert.assertFalse(loginButton.isEnabled());

        //Verify background color
        System.out.println(loginButton.getCssValue("background-color"));

       Assert.assertEquals((Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase()),"#000000");

       driver.findElement(By.cssSelector("input#login_username")).sendKeys("huong@gmail.com");
       driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");

        Assert.assertTrue(loginButton.isEnabled());
        Assert.assertEquals((Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase()),"#c92127");

    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond *1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }



}