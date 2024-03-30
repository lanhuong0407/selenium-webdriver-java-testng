package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Topic_06_WebBrowser_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {


        driver = new FirefoxDriver();

        //Cách viết của Selenium ver 3
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Cách viết của Selenium ver 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() {
        //2 hàm này sẽ bị ảnh hưởng timeout của implicitWait
        //findElement/ findElements

        //Nó sẽ đi tìm với loại By nào và trả về element nếu như được tìm thấy (WebElement)
        //Không được tìm thấy -> fail tại step này - throw exception: NoSuchElementException
        //Trả về 1 element - nếu tìm thấy nhiều hơn 1 thì cũng chỉ lấy 1 (thao tác với cái đầu tiên)
        WebElement emailAddressTextbox = driver.findElement(By.id("email"));


        //Nó sẽ đi tìm với loại By nào và trả về 1 list element nếu như được tìm thấy (list WebElement)
        //Không được tìm thấy -> không bị fail tại step này -> trả về 1 list rỗng (0 element)
       List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

    }
    @Test
    public void TC_02() {
        //Tại sao cần lấy dữ liệu ra?
        // Dùng để lấy ra url của page hiện tại (đang đứng)
        driver.getCurrentUrl();

        //Lấy ra page source HTML/CSS/JS của page hiện tại
        //Verify 1 cách tương đối
        driver.getPageSource();



        //Nếu chỉ dùng 1 lần, thì không cần khai báo biến
       d


    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}