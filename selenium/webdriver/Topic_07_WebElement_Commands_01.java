package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_07_WebElement_Commands_01 {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {}
    @Test
    public void TC_01_Element() {
        //HTML Element: textbox, dropdown,image,...
        //Tìm Element
        driver.findElement(By.id("")); //**

        //tìm và tương tác
        WebElement fullNameTextbox = driver.findElement(By.id(""));
        //dung de xoa du lieu trong field
        //thuong duoc su dung truoc ham senkey
        fullNameTextbox.clear(); //*

        //dung de nhap lieu vao cac feild
        fullNameTextbox.sendKeys("Automation"); //**

        //dung de click len element
        fullNameTextbox.click(); //**

        //tim tu node cha vao node con
        driver.findElement(By.id("form-validate")).findElement(By.id("first-name"));

        //tra về element khop voi dieu kien
        driver.findElement(By.cssSelector(""));

        //tra ve nhieu element khop voi dieu kien
        List<WebElement> textboxes = driver.findElements(By.cssSelector(""));

        //Dung de verify checkbox, radio, dropdown da duoc chon hay chua
        Assert.assertTrue(driver.findElement(By.id("")).isSelected()); //*
        Assert.assertFalse(driver.findElement(By.id("")).isSelected());

        //Dung de verify 1 element bat ky co hien thi hay khong
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed()); //**
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isDisplayed());

        //Dung de verify 1 element co duoc thao tac len hay kkhong
        Assert.assertTrue(driver.findElement(By.id("")).isEnabled()); //*
        Assert.assertFalse(driver.findElement(By.id("")).isEnabled());

        //Lay ra gia tri cua attribute ma minh truyen vao
        driver.findElement(By.id("")).getAttribute("title");

        //Tab style trong element
        //Font/Size/Color/Background/...
        driver.findElement(By.id("")).getCssValue("font-size");

        //Vi tri cua element so voi do phan giai man hinh
        Point nameTextboxLocation = driver.findElement(By.id("")).getLocation();

        //Location + Size
        driver.findElement(By.id("")).getRect();

        //Shadow element (Javascript Executor)
        driver.findElement(By.id("")).getShadowRoot();

        //Tu id/ class/ name/ css/ xpath cos the truy ra nguoc lai tag name cua HTML
        driver.findElement(By.cssSelector("#firstname")).getTagName();
        //Element A ->dau ra cua no la tag name cua element A
        //Dau vao cua Element B se nhan tagname cua element A la tham so

        //
        driver.findElement(By.id("")).getText(); //**

        //Chup hinh bi loi, luu duoi dang nao:
        //Byte
        // FILE (Luu thanh 1 hinh co kich thuoc trong o cung: .png, jpg,...)
        // BASE64 (Hinh dang text)
        //driver.findElement(By.id("")).getScreenshotAs("FILE");

        //Form (element nao la the form hoac nam trong the form)
        //Hành vi giong phim Enter
        driver.findElement(By.id("")).submit();

    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}