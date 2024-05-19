package testng;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_02_Annotations {
    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }

    @BeforeGroups
    public void beforeGroups() {
        System.out.println("Before Groups");
    }
     @AfterGroups
     public void afterGroups() {
         System.out.println("After Groups");
     }

     @BeforeMethod
     public void beforeMethod() {
         System.out.println("Before Method");
     }
     @AfterMethod
     public void afterMethod() {
         System.out.println("After Method");
     }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test");
    }
    @AfterTest
    public void afterTest() {
        System.out.println("After Test");
    }

     @Test
    public void TC_01(){
        System.out.println("TC 01");;
    }
    @Test
    public void TC_02(){
        System.out.println("TC 02");;
    }
    @Test
    public void TC_03(){
        System.out.println("TC 03");;
    }

}
