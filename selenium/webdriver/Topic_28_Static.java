package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Static {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Less_Than() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }
    @Test
    public void TC_02_Equal() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }
    @Test
    public void TC_03_Greater_Than() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        Thread.sleep(10000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
