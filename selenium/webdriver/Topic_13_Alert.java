package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Alert {
    WebDriver driver;

    WebDriverWait explicitWait;

    By resultText = By.cssSelector("p#result");
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //explicitWait.until(ExpectedConditions.alertIsPresent());

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        //sleepInSeconds(3);

        //Cho alert present
        // Neu trong tgian cho ma chua xuar hien -> tuwj switch vao
        // Neu het tgian cho ma chua xuat hien => fail
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        // Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        //Khi accept hoac cancel=> alert se mat luon
        alert.accept();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked an alert successfully");

        //Cancel alert
        // void dismiss();

        //Accept alert
        // void accept();

        //Get text trong alert ra
        //String getText();

        //Nhap text vao alert
        //void sendKeys();

    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");

        alert.dismiss();
        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked: Cancel");

    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSeconds(3);

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        String text = "Selenium ABC";
        alert.sendKeys(text);
        sleepInSeconds(3);

        alert.accept();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(resultText).getText(),"You entered: " + text);
    }

    @Test
    public void TC_04_Authentication_Pass_To_URL() {
        //Thu vien Alert khong su dung cho Authetication Alert duoc
        //Chrome DevTool - Chrome/Edge
        String username = "admin";
        String password = "admin";

        //Cach 1: Truyen thang user/ pass vao url
        //driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        //Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());


        //Cach 2: Tu page A thao tac len 1 element no se qua page B (can phai thao tac voi Authen Alert truoc)
        driver.get("https://the-internet.herokuapp.com/");

        String authenLinkUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
         String [] authenArray = authenLinkUrl.split("//");
         driver.get(getAuthenAlertbyUrl(authenLinkUrl, username, password));
        Assert.assertTrue(driver.findElement(By.xpath(
                "//p[contains (text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());

    }
    @Test
    public void TC_05_Authentication_AutoIT() {
        // Cach 2: Chay tren Windown, khong chay tren Mac duoc
    }

    @Test
    public void TC_06_Authentication_Selenium_4x() {
        // Cach 3
        // Thu vien Alert khhong su dung cho Authentication Alert duọc
        // Chrome Devtool Potocol (CDP) - Chrome/ Edge (Chromium)
        // Coccoc/ Opera - Work Around

        DevTools devTools = ((HasDevTools) driver).getDevTools();

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }


    public String getAuthenAlertbyUrl (String url, String username, String password){
        String [] authenArray = url.split("//");
        return authenArray[0] + "//" + username + ":" + password + "@" + authenArray[1];
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond *1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}