package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_18_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Basic_Form() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Lay ID cua tab hien tai
        String parentID = driver.getWindowHandle();
        System.out.println("Parent Tab ID = " + parentID);


        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSeconds(3);


        switchToWindowByID(parentID);

        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());


        String googleTabID = driver.getWindowHandle();

        switchToWindowByID(googleTabID);
        
    }

    @Test
    public void TC_02_Basic_Form_GetTitle() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSeconds(3);

        //Switch qua trang Google
        switchToWindowByTitle("Google");

        //Switch quay lai trang Basic form
        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Facebook – log in or sign up");

    }

    @Test
    public void TC_03_Live_tech_Panda() {
        driver.get("http://live.techpanda.org/");
        sleepInSeconds(3);

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2/following-sibling::div//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg"))
                .getText(),"The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[text()='IPhone']//parent::h2/following-sibling::div//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg"))
                .getText(),"The product IPhone has been added to comparison list.");

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2/following-sibling::div//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg"))
                .getText(),"The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        //Verify Header
        Assert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(),"COMPARE PRODUCTS");

        driver.close();
        switchToWindowByTitle("Mobile");

        driver.findElement(By.xpath("//div[@class='actions']//a[text()='Clear All']")).click();
        driver.switchTo().alert().accept();
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

    public void switchToWindowByID (String expected){
        //Lay ra het tat ca tab/ window ID
        Set<String> allIDs = driver.getWindowHandles();

        //Dung vong lap duyet qua tung ID trong Set o tren
        for (String id : allIDs){
            // Neu 1 ID nao khac voi parentID thi switch vao
            if (!id.equals(expected)){
                driver.switchTo().window(id);
                //Thoat khoi vong lap, khhong can ktra cac gia tri con lai (neu co)
                break;
            }

        }
    }

    public  void switchToWindowByTitle (String expectedTitle){
        //Lay het tat ca cac ID cua cac window/ tab
        Set<String> allIDs = driver.getWindowHandles();

        //Dung vong lap duyet qua tung ID trong Set o tren
        for (String id : allIDs){
           //Cho switch vao tung ID truoc
            driver.switchTo().window(id);

            //Lay ra title cua tab/ window hien tai
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)){
                break;
            }
        }

    }

}