package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_WebElement_Commands_2 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        //01
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Displayed() {

    }

    @Test
    public void TC_02_Enabled() {

    }
    @Test
    public void TC_03_Selected() {

    }
    @Test
    public void TC_04_MailChimp() {

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}