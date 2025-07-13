package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_28_Implicit {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://automationfc.github.io/dynamic-loading/");
    }

    @Test
    public void TC_01_Dont_Set() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.cssSelector("div#start>button")).click();

    }
    @Test
    public void TC_02_LessThan() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_03_Equal() {

    }
    @Test
    public void TC_04_Greater_Than() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
