package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Frame_Iframe {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_00_Ex() {
        //Trang A, domain: formsite.com
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        //Chua iframe, trang B,
        //Tu A vao B
        driver.switchTo().frame("frame-one85593366");
        driver.findElement(By.cssSelector(""));

        //Từ B vào C
        driver.switchTo().frame("");
        driver.findElement(By.cssSelector(""));

        //Tu C quay lai B
        driver.switchTo().parentFrame();

        //Dang o B
        //Tu B quay lai A
        driver.switchTo().defaultContent();


    }

    @Test
    public void TC_01_Form_Site() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSeconds(2);

        WebElement formIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        Assert.assertTrue(formIframe.isDisplayed());

        //Switch vao frame, iframe truoc khi thao tac voi cac element ben trong
        driver.switchTo().frame(formIframe);

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        sleepInSeconds(3);

        //Switch ra lai trang A
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        sleepInSeconds(3);

    }

    @Test
    public void TC_02_Frame_HDFC_Bank() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        sleepInSeconds(2);

        WebElement loginFrame = driver.findElement(By.cssSelector("html frame"));

        driver.switchTo().frame(loginFrame);

        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("siwon_0407");
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("a.login-btn")).click();
        



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