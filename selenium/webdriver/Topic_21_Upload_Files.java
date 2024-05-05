package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_21_Upload_Files {
    WebDriver driver;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Single_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadBy = By.cssSelector("input[name ='files[]']");

        driver.findElement(uploadBy).sendKeys(HaeFilePath);
        sleepInSeconds(2);

        driver.findElement(uploadBy).sendKeys(HyukFilePath);
        sleepInSeconds(2);

        driver.findElement(uploadBy).sendKeys(WonFilePath);
        sleepInSeconds(2);

        //Verify file LOAD thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + nameHae + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + nameHyuk + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + nameWon + "']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement button : startButtons){
            button.click();
            sleepInSeconds(2);
        }

        //Verify file UPLOAD thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + nameHae + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + nameHyuk + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + nameWon + "']")).isDisplayed());

    }

    @Test
    public void TC_02_Multiple_Files() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadBy = By.cssSelector("input[name ='files[]']");

        driver.findElement(uploadBy).sendKeys(HaeFilePath + "\n" + HyukFilePath + "\n" + WonFilePath);
        sleepInSeconds(2);

        //Verify file LOAD thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + nameHae + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + nameHyuk + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + nameWon + "']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement button : startButtons){
            button.click();
            sleepInSeconds(2);
        }

        //Verify file UPLOAD thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + nameHae + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + nameHyuk + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + nameWon + "']")).isDisplayed());


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