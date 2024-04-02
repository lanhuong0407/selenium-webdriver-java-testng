package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Textbox_TextArea {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void Login_01_Empty_Email_And_Password() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("[id='send2']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("[id='advice-required-entry-email']")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("[id='advice-required-entry-pass']")).getText(),"This is a required field.");


    }
    @Test
    public void Login_02_Invalid_Email() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("[id='email']")).sendKeys("123@123456");
        driver.findElement(By.cssSelector("[id='pass']")).sendKeys("123456");

        driver.findElement(By.cssSelector("[id='send2']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("[id='advice-validate-email-email']")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");

    }

    @Test
    public void Login_03_Invalid_Password() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("[id='email']")).sendKeys("lanhuong@gmail.com");
        driver.findElement(By.cssSelector("[id='pass']")).sendKeys("12345");

        driver.findElement(By.cssSelector("[id='send2']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("[id='advice-validate-password-pass']")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");

    }

    @Test
    public void Login_04_Incorrect_Email_Or_Password() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("[id='email']")).sendKeys("lanhuong@gmail.com");
        driver.findElement(By.cssSelector("[id='pass']")).sendKeys("acb456");

        driver.findElement(By.cssSelector("[id='send2']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");

    }

    @Test
    public void Login_05_Success() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        //1 - Dang ky truoc bang tay (manual) 1 tai khoan email, roi dung no de login
        //Rui ro: khi he thong reset du lieu, phai dang ky lai
        //Qua 1 moi truong moi, dang ky 1 tai khoan moi

        //2 - Dung tinh nang register truoc - dung 1 email co dinh, khong thay doi
        //Chuc nang dang ky se lam Auto luon
        //Email fix cung (1 lan)

        //Dung tinh nang register truoc - dung 1 email khong co dinh (random)
        //Chay luon luon dung cho tat ca cac truong hop



        //Dang ky 1 tai khoan truoc

        String firstName = "Lan Huong", lastName = "CSW", emailAdsress = getEmailAddress(), password = "123456789";
        String fullName = firstName + " " + lastName;

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAdsress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(),"Hello, " + fullName + "!");

        String contactInfor = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfor.contains(fullName));
        Assert.assertTrue(contactInfor.contains(emailAdsress));

        //Logout
        driver.findElement(By.cssSelector("a.skip-account")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();
        sleepInSeconds(5);

        //Login
        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("[id='email']")).sendKeys(emailAdsress);
        driver.findElement(By.cssSelector("[id='pass']")).sendKeys(password);

        driver.findElement(By.cssSelector("[id='send2']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(),"Hello, " + fullName + "!");

        contactInfor = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfor.contains(fullName));
        Assert.assertTrue(contactInfor.contains(emailAdsress));

        //Verify Acc
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSeconds(2);

        //driver.findElement(By.cssSelector("input#firstname")).getAttribute("value");
        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"),emailAdsress);
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

    public String getEmailAddress(){
        Random rand = new Random();
        return "huong" + rand.nextInt(9999) + "@gmail.com";

    }
}