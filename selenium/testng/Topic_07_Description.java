package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Description {
    @BeforeClass(alwaysRun = true)
    public void init(){
        System.out.println("Pre-Condition for bellow TCs");
    }
    @Test(description = "JIRA - Search for new user")
    public void testSearchWithDate(){

    }

    @Test
    public void testSearchWithBilling(){

    }

    @Test
    public void testSearchWithProduct(){

    }

    @AfterClass(alwaysRun = true)
    public void after(){
        System.out.println("Post-Condition for above TCs");
    }
}
