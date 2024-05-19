package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Skip {
    @BeforeClass(alwaysRun = true)
    public void init(){
        System.out.println("Pre-Condition for bellow TCs");
    }
    @Test(priority = 1)
    public void testSearchWithDate(){

    }

    @Test(enabled = false)
    public void testSearchWithBilling(){

    }

    @Test(priority = 3)
    public void testSearchWithProduct(){

    }

    @AfterClass(alwaysRun = true)
    public void after(){
        System.out.println("Post-Condition for above TCs");
    }
}
