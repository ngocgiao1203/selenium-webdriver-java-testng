package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_18_Action_II {
    WebDriver driver;
    Actions action;
    String osName = System.getProperty("os.name");
    Keys keys;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        action = new Actions(driver);

        //Cách check hệ điều hành  - system operation
        //Cách 1:
//        if(osName.startsWith("Windows")){
//            keys = Keys.CONTROL;
//        }
//        else {
//            keys = Keys.COMMAND;
//        }
        //Cách 2: Toán tử 3 ngôi
        keys = osName.startsWith("Windows") ? Keys.CONTROL : Keys.COMMAND;

    }

    @Test
    public void TC_01_Click_And_Hold_Block() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        sleepInSeconds(2);
        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumber.size(),20);
        action.clickAndHold(allNumber.get(0)) //Click vào số 1 và giữ chuột
              .moveToElement(allNumber.get(3)) //Di chuột tới số 4
              .release()//Nhả chuột trái ra - kết thúc cho s kiện clickAndHold()
              .perform();//Thực thi các câu lệnh trên (nếu ko có thì ko thực thi)
        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        sleepInSeconds(2);
        Assert.assertEquals(allNumberSelected.size(),4);
    }

    @Test
    public void TC_02_Click_And_Hold_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        sleepInSeconds(2);
        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumber.size(),20);

        //Nhấn phím Ctrl xuống (chưa nhả ra)
        action.keyDown(keys).perform();
        action.click(allNumber.get(0))
              .click(allNumber.get(3))
              .click(allNumber.get(7))
              .click(allNumber.get(10))
              .click(allNumber.get(13))
              .click(allNumber.get(17))
              .pause(Duration.ofSeconds(3)) //sau khi click -> pause 3s -> click
              .perform();
        //Nhả phím Ctrl ra
        action.keyUp(keys).perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        sleepInSeconds(2);
        Assert.assertEquals(allNumberSelected.size(),6);
    }

    @Test
    public void TC_03_DoubleClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()",driver.findElement((By.xpath("//button[text()='Double click me']"))));
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
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
