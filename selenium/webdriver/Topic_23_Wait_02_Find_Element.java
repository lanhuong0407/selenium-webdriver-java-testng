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

public class Topic_23_Wait_02_Find_Element {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @Test
    public void TC_01_Find_Element() {
        driver.get("https://www.facebook.com/");

        //Case 1: Tim thay va tra ve 1 element
        driver.findElement(By.cssSelector("input#email"));

        //Case 2: Tim thay va tra ve nhieu hon 1 element
        // Se thao tac voi element dau tien duoc tim thay
        driver.findElement(By.xpath("//input[@id='email' or @id='pass']")).sendKeys("huong@gmail.com");

        //Case 3: Element khong duoc tim thay
        // Fail
        driver.findElement(By.cssSelector("input#emailpass"));


    }

    @Test
    public void TC_02_Find_Elements() {
        driver.get("https://www.facebook.com/");

        List<WebElement> elements;

        // Case 1: Tim ve dung 1 element
        elements = driver.findElements(By.cssSelector("input#email"));
        System.out.println("Số lượng element: " + elements.size());

        // Case 2: Tim ve nhieu hon 1 element
        elements = driver.findElements(By.xpath("//input[@id='email' or @id='pass']"));
        System.out.println("Số lượng element: " + elements.size());

        // Case 3: Khong tim thay element nao
        // Tra ve list element rong
        // Pass

        elements = driver.findElements(By.cssSelector("input#emailpass"));
        System.out.println("Số lượng element: " + elements.size());


    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}