package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.network.model.DataReceived;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.Driver;
import java.time.Duration;

public class Topic_27_Wait_07_Explicit_03 {
    WebDriver driver;
    WebDriverWait explicitWait;

    String projectPath = System.getProperty("user.dir");
    String nameHae = "Hae.jpg";
    String nameHyuk = "Hyuk.jpg";
    String nameWon = "Won.jpg";

    String HaeFilePath = projectPath + File.separator + "uploadFiles" + File.separator + nameHae;
    String HyukFilePath = projectPath + File.separator + "uploadFiles" + File.separator + nameHyuk;
    String WonFilePath = projectPath + File.separator + "uploadFiles" + File.separator + nameWon;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_AjaxLoading() {


        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        driver.findElement(By.xpath("//a[text()='9']")).click();

        By selectedDateBy = By.cssSelector("span#ctl00_ContentPlaceholder1_Label1");

        Assert.assertEquals(driver.findElement(selectedDateBy).getText(),"No Selected Dates to display.");

        //Wait cho loading icon bien mat trong vong x giay
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));


        Assert.assertEquals(driver.findElement(selectedDateBy).getText(),"Thursday, May 9, 2024");

    }

    @Test
    public void TC_02_Upload_File() {

        driver.get("https://gofile.io/welcome");

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border")));


        //Wait + Click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink>button"))).click();


        //Wait + Verify icon loading bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(HaeFilePath + "\n" + HyukFilePath + "\n" + WonFilePath);

        //Wait + Verify icon loading bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        //Wait Progress Bar bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress")))));

        driver.findElement(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink")).click();

        //Wait + Verify icon loading bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        //Wait + Verify button Download tai cac hinh duoc upload

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//span[text()='" + nameHae + "']/ancestor::div/following-sibling::div[contains(@class,'text-md-end')]//span[text()='Download']"))).isDisplayed());

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//span[text()='" + nameHyuk + "']/ancestor::div/following-sibling::div[contains(@class,'text-md-end')]//span[text()='Download']"))).isDisplayed());

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//span[text()='" + nameWon + "']/ancestor::div/following-sibling::div[contains(@class,'text-md-end')]//span[text()='Download']"))).isDisplayed());


        //Wait + Verify button Play tai cac hinh duoc upload

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//span[text()='" + nameHae + "']/ancestor::div/following-sibling::div[contains(@class,'text-md-end')]//span[text()='Play']"))).isDisplayed());

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//span[text()='" + nameHyuk + "']/ancestor::div/following-sibling::div[contains(@class,'text-md-end')]//span[text()='Play']"))).isDisplayed());

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//span[text()='" + nameWon + "']/ancestor::div/following-sibling::div[contains(@class,'text-md-end')]//span[text()='Play']"))).isDisplayed());


    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}