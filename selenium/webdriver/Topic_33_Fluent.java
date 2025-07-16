package webdriver;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_33_Fluent {
    WebDriver driver;


    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Dynamic_Loading_01() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        findElement(By.cssSelector("div#start>button")).click();
        Assert.assertEquals(getElementText(By.cssSelector("div#finish>h4")),"Hello World!");
    }
    @Test
    public void TC_02_Dynamic_Loading_01() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        findElement(By.cssSelector("div#start>button")).click();
        Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='finish']/h4p[text()='Hello World!']")));
    }

    @Test
    public void TC_03_CountDown(){
        driver.get("https://automationfc.github.io/fluent-wait/");
        WebElement countDownTime = findElement(By.id("javascript_countdown_time"));
        Assert.assertTrue(isSecondMatching(countDownTime));

    }

    public WebElement findElement(By by){
        FluentWait <WebDriver> driverFluentWait;
        driverFluentWait = new FluentWait<>(driver);
        driverFluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
    }
    //kiểm tra 1 element hiển thị
    //isDisplayed = boolean
    public boolean isElementDisplayed (By by){
        FluentWait <WebDriver> driverFluentWait = new FluentWait<>(driver);

        driverFluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).isDisplayed();
            }
        });
    }
    //Lấy ra 1 text của element
    public String getElementText (By by){
        FluentWait <WebDriver> driverFluentWait = new FluentWait<>(driver);

        driverFluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver driver) {
                return driver.findElement(by).getText();
            }
        });
    }

    public boolean isSecondMatching (WebElement element){
        FluentWait <WebElement> driverFluentWait = new FluentWait<>(element);

        driverFluentWait.withTimeout(Duration.ofSeconds(13))
                .pollingEvery(Duration.ofMillis(50)); //1 giây kiểm tra được 5 lần

        return driverFluentWait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                String text = element.getText();
                System.out.println("Text = " + text);
                return element.getText().endsWith("00");
            }
        });
    }


    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
