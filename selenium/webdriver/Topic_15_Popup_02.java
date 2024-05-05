package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Popup_02 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Random_Not_InDOM() {

        driver.get("https://www.javacodegeeks.com/");
        sleepInSeconds(6);

        By newletterPopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");

        //Neu hien thi -> close
        if (driver.findElements(newletterPopup).size()>0 && driver.findElements(newletterPopup).get(0).isDisplayed()){
            //>0 nghia la duoc render ra nhung chua biet co hien thi hay khong
            driver.findElement(By.cssSelector("a[onclick='return lepopup_close();']")).click();
            sleepInSeconds(3);
        } else {
            System.out.println("Popup khong hien thi");
        }

        //Neu khong hien thi, qua step tiep theo (Search du lieu)
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");

        //Click Search
        driver.findElement(By.cssSelector("button#search-submit")).click();
        sleepInSeconds(3);

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());



    }

    @Test
    public void TC_02_Random_InDOM() {
        driver.get("https://vnk.edu.vn/");
        sleepInSeconds(3);

        By marketingPopup = By.cssSelector("div#tve-p-scroller article");

        if (driver.findElement(marketingPopup).isDisplayed()){
            driver.findElement(By.cssSelector("svg[data-name = 'closeclose']")).click();
            sleepInSeconds(3);
            System.out.println("Popup hien thi");
        } else{
            System.out.println("Popup khong hien thi");
        }

    }

    @Test
    public void TC_03_Random_Not_InDOM() {
        driver.get("https://dehieu.vn/");
        sleepInSeconds(3);

        By marketingPopup = By.cssSelector("div.modal-content");

        if (driver.findElements(marketingPopup).size()>0 && driver.findElements(marketingPopup).get(0).isDisplayed()){
            driver.findElement(By.cssSelector("button.close")).click();
        } else {
            System.out.println("Popup khong hien thi");
        }

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