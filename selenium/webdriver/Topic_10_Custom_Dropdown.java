package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;


    //Tuong minh: trang thai cu the cho element
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Ngam dinh: khong ro rang cho 1 trang thai cu the nao do cua element
        //Cho viiẹc tim Element - findelements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    //@Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        //1- Click vao 1 the de xo het cac item trong dropdown ra
        driver.findElement(By.cssSelector("span#number-button")).click();
        sleepInSeconds(3);

        //Cho xo ra het cac item trong Dropdown
        //Xuat hien day du trongHTML
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));

        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
        //all Items dang luu tru 19 items ben trong

        for (WebElement item: allItems){

            //Neu element duoc click xong roi, cac item con lai se khong co trong HTML -> Ham getText se bi fail
            String textItem = item.getText();
            System.out.println("Text item = " +textItem);


            //3 - Kiem tra text cua tung item, thoa man dieu kien thi click vao

            if (textItem.equals("8")){
                item.click();
                break; // 9 -19: khong duoc kiem tra
                //thoat vong lap (for/while/do-while/swich-case)
            }
        }

        selectItemInDropdown("span#number-button", "ul#number-menu div", "15");
    }

    //@Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("i.dropdown.icon", "div.item>span.text", "Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Christian");
        sleepInSeconds(3);

        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("i.dropdown.icon", "div.item>span.text", "Jenny Hess");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Jenny Hess");
        
    }

    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");
        sleepInSeconds(3);

    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectItemInEditableDropdown("input.search", "div.item span", "Bahamas");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Bahamas");
        sleepInSeconds(1);


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

    //Nhung du lieu dung de truyen vao - duoc xem la tham so
    public void selectItemInDropdown(String parentCss, String childItemCss, String itemTextExpected){
        driver.findElement(By.cssSelector(parentCss)).click(); //"span#number-button"
        sleepInSeconds(1);
        //Vua wait, vua tim element
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss))); //"ul#number-menu div"

        List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss)); //"ul#number-menu div"
        for (WebElement item: allItems) {

            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }

    public void selectItemInEditableDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);
        sleepInSeconds(1);

        List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss)); //"ul#number-menu div"
        for (WebElement item: allItems) {

            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }

    }

}