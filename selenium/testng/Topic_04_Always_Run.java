package testng;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.SequenceOfUint8;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Always_Run {
    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        System.out.println("Run before class");
        Assert.assertTrue(false);
    }

    @Test
    public void TC_01_() {
        System.out.println("Run TC 01");

    }

    @Test
    public void TC_02_() {
        System.out.println("Run TC 02");
    }

    @Test
    public void TC_03_() {
        System.out.println("Run TC 03");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
        System.out.println("Run after class");
        Assert.assertTrue(false);
    }
}
