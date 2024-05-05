package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Radio_Checkbox {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Default_Telerik_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By airBagCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input");

        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        //Click vao checkbox
        //Case 1: Neu app mo ra ma checkbox da duoc chon

        if(!driver.findElement(airBagCheckbox).isSelected()){//true -> moi vao than ham if
            driver.findElement(airBagCheckbox).click();
            sleepInSeconds(2);
        }


        //Case 2: Neu app mo ra ma checkbox chua duoc chon

        if(!driver.findElement(dualZoneCheckbox).isSelected()){
            driver.findElement(dualZoneCheckbox).click();
            sleepInSeconds(2);
        }



        //Verify checkbox da duoc chon thanh cong
        Assert.assertTrue(driver.findElement(airBagCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        //Bo chon click
        if(driver.findElement(airBagCheckbox).isSelected()){//true -> moi vao than ham if
            driver.findElement(airBagCheckbox).click();
            sleepInSeconds(2);
        }

    }

    @Test
    public void TC_02_Default_Telerik_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        By twoDieselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::span/input");

        //Click chon 1 trong 2
        checkToElement(twoPetrolRadio);

        Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());

        checkToElement(twoDieselRadio);

        Assert.assertFalse(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());

    }

    @Test
    public void TC_03_Select_All_Checkboxes() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        //Chon het tat ca checkbox trong man hinh

        for(WebElement checkbox: allCheckboxes){
            if(!checkbox.isSelected()){
                checkbox.click();
                //sleepInSeconds(1);
            }
        }

        //Veify het tat ca checkbox
        for(WebElement checkbox: allCheckboxes){
            Assert.assertTrue(checkbox.isSelected());
        }

        //Chon 1 checkbox/ radio nao do trong tat ca radio/ checkbox

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        for(WebElement checkbox: allCheckboxes){
            if(checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()){
                checkbox.click();
                sleepInSeconds(1);
            }

        }

        //Veify het tat ca checkbox
        for(WebElement checkbox: allCheckboxes) {
            if (checkbox.getAttribute("value").equals("Heart Attack")) {
                Assert.assertTrue(checkbox.isSelected());
            } else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }
    }

    @Test
    public void TC_04_Custom_Radio() {
        

    }

    @Test
    public void TC_05_GoogleDocs() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
        By quangNamCheckbox = By.xpath("//div[@aria-label='Quảng Nam']");
        //Verify Radio is not selected
        //Cach 1
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"false");
        //Cach 2
        Assert.assertTrue((driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed()));

        driver.findElement(canThoRadio).click();
        sleepInSeconds(2);

        //Verify Radio is selected
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"true");


        driver.findElement(quangNamCheckbox).click();
        Assert.assertEquals(driver.findElement(quangNamCheckbox).getAttribute("aria-checked"),"true");

    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond *1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkToElement(By byxpath){
        //Neu element chua duoc chon-> click

        if(!driver.findElement(byxpath).isSelected()){
            driver.findElement(byxpath).click();
            sleepInSeconds(2);
        }
    }

    public void uncheckToElement(By byxpath){

        //Neu element da duoc chon-> vao click lan nua, thanh bo chon
        if(driver.findElement(byxpath).isSelected()){
            driver.findElement(byxpath).click();
            sleepInSeconds(2);
        }
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}