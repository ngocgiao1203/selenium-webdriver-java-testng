package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_17_Action_I {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        action = new Actions(driver);
        //set tọa độ cho con trỏ chuột
        action.moveByOffset(0,0).perform();
    }

    @Test
    public void TC_01_Hover()  {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
        action.moveToElement(ageTextbox).perform();
        //Thread.sleep(2000);
        sleepInSeconds(2);
        WebElement toolTip = driver.findElement(By.cssSelector("div.ui-tooltip-content"));
        Assert.assertEquals(toolTip.getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_Myntra(){
        driver.get("https://www.myntra.com/");
        action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
        sleepInSeconds(1);
        action.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']"))).perform();
        sleepInSeconds(5);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).getText(),"Kids Home Bath");
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title' and text()='Kids Home Bath']")).getText(),"Kids Home Bath");
    }

    @Test
    public void TC_03_Hover_Fahasa(){
        driver.get("https://www.fahasa.com/");
        action.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
        sleepInSeconds(2);
        action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
        sleepInSeconds(1);

        action.click(driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Kỹ Năng Sống']"))).perform();
        sleepInSeconds(5);
        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Kỹ năng sống']")).isDisplayed());
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}