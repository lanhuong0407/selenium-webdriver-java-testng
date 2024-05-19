package testng;

import org.bouncycastle.cms.PasswordRecipientId;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Assert {

    WebDriver driver;

    @Test
    public void TC_01(){
        String fullName = "Lan Huong";

        Assert.assertEquals(fullName,"Lann Huong");

        Assert.assertTrue(isElementDisplayed(By.cssSelector("")));
        Assert.assertTrue(isElementDisplayed(By.cssSelector("")),"Element is not displayed!");

        Assert.assertFalse(isElementDisplayed(By.cssSelector("")));

    }

    private boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }
}
