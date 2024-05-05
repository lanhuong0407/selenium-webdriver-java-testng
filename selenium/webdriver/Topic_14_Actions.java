package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class Topic_14_Actions {
    WebDriver driver;
    Actions actions;

    JavascriptExecutor javascriptExecutor;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        //Dang gia lap hanh vi cua mouse, keyboard, pen ==> khi chay TC thi khong su dung cac thiet bi nay
        actions = new Actions(driver);

        javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


    }

    @Test
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));

        actions.moveToElement(ageTextbox).perform();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content"))
                .getText(),"We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_Menu_Login() {
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        sleepInSeconds(2);
        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Bách Hóa Online - Lưu Niệm']"))).perform();
        //sleepInSeconds(2);

        driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Thiết Bị Số - Phụ Kiện Số']")).click();
        //actions.click(driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Thiết Bị Số - Phụ Kiện Số']"))).perform();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(),"THIẾT BỊ SỐ - PHỤ KIỆN SỐ");

    }

    @Test
    public void TC_03_Click_and_Hold() {

        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        //20 o
        Assert.assertEquals(allNumbers.size(),20);

        // Chon theo block ngang or doc
        // Chon 1-> 15
        actions.clickAndHold(allNumbers.get(0)) // Click len so 1 va giu chuot
                .moveToElement(allNumbers.get(14)) // Di chuot den so 15
                .release() // Nha chuot trai ra
                .perform(); // Thuc thi (execute)
        sleepInSeconds(3);

        // Tong cac so da duoc chon
        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(),12);
    }

    @Test
    public void TC_04_Click_and_Hold() {

        driver.get("https://automationfc.github.io/jquery-selectable/");

        // Tong so chua chon
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        //20 o
        Assert.assertEquals(allNumbers.size(),20);

        // Chon 1-> 12 theo du hang/ cot
        actions.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(11)).release().perform();

        // Chon tu 13 ->15
        actions.keyDown(Keys.CONTROL).perform(); // Nhan phim Ctrl xuong (chua nha ra)
        actions.click(allNumbers.get(12))
                .click(allNumbers.get(13))
                .click(allNumbers.get(14))
                .keyUp(Keys.CONTROL).perform();

        sleepInSeconds(3);

        // Tong cac so da duoc chon
        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(),15);

    }

    @Test
    public void TC_05_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));

        // Can scroll toi element roi moi double Click - chi rieng Firefox moi phai scroll
        if (driver.toString().contains("firefox")) {
            //scrollIntoView (true): keo mep tren cua element len phia tren cung cua viewport
            //scrollIntoView (false): keo mep tren cua element len phia duoi cung cua viewport
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",doubleClickButton);
            sleepInSeconds(3);
        }

        actions.doubleClick(doubleClickButton).perform();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");



    }

    @Test
    public void TC_06_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        // Chua click chuot phai, cac element khkong duoc visible
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        sleepInSeconds(2);

        // Moi click chuot phai, cac element se duoc visible
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSeconds(2);

        //Duoc cap nhat lai class cua element - kiem tra su kien hover thanh cong
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());

        //Click len paste
        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSeconds(3);

        driver.switchTo().alert().accept();
        sleepInSeconds(3);

        //Kiem tra paste khong con hien thi
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

    }

    @Test
    public void TC_07_Drag_and_Drop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

        actions.dragAndDrop(smallCircle,bigCircle).perform();
        sleepInSeconds(3);

        Assert.assertEquals(bigCircle.getText(),"You did great!");

        Assert.assertEquals((Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase()),"#03a9f4 ");
    }

    @Test
    public void TC_08_Drag_and_Drop_HTML5_CSS() {
        driver.get("https://automationfc.github.io/drag-drop-html5/");



    }

    @Test
    public void TC_09_Drag_and_Drop_HTML5_XPath() {
        driver.get("https://automationfc.github.io/drag-drop-html5/");



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