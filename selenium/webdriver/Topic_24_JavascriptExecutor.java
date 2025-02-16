package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class Topic_24_JavascriptExecutor {
    WebDriver driver;
    //1. Muốn sử dụng thư viện của JavaScriptExecutor -> cần khai báo nó
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    Random random;
    String email;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        //2. Khởi tạo ép kiểu
        jsExecutor = (JavascriptExecutor) driver; // Ép kiểu driver thành JavascriptExecutor vì các trình duyệt như ChromeDriver đã triển khai interface này.
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        email = "automation" + new Random().nextInt(9999) + "@gmail.com";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_() throws InterruptedException {
        jsExecutor.executeScript("window.location = 'https://live.techpanda.org/'");

        Thread.sleep(2000);

        String techPandaDomain = (String) jsExecutor.executeScript("return document.domain;");
        Assert.assertEquals(techPandaDomain, "live.techpanda.org");
        Thread.sleep(2000);
        String homePageUrl = (String) jsExecutor.executeScript("return document.URL;");
        Assert.assertEquals(homePageUrl, "https://live.techpanda.org/");

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Mobile']")));
        Thread.sleep(2000);
        jsExecutor.executeScript("arguments[0].click();",
                driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button")));
        Thread.sleep(3000);

        String samsungText = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(samsungText.contains("Samsung Galaxy was added to your shopping cart."));

        Thread.sleep(2000);
        jsExecutor.executeScript("arguments[0].click();",
                driver.findElement(By.xpath("//a[text()='Customer Service']")));

        jsExecutor.executeScript("arguments[0].scrollIntoView(true)",driver.findElement(By.cssSelector("input#newsletter")));
        Thread.sleep(2000);
        //Cach 1:
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + email + "')",driver.findElement(By.cssSelector("input#newsletter")));
        //Cach 2: jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1])",driver.findElement(By.cssSelector("input#newsletter")),email);
        Thread.sleep(1000);
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("button[title='Subscribe']")));
        Thread.sleep(3000);

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        //Assert.assertEquals(alert.getText(), "I am a JS prompt");
        alert.accept();
        Thread.sleep(3000);
        String subscription = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(subscription.contains("Thank you for your subscription."));

        Thread.sleep(2000);
        jsExecutor.executeScript("window.location = 'https://www.facebook.com/'");
        Thread.sleep(2000);
        String fbDomain = (String) jsExecutor.executeScript("return document.domain;");
        Assert.assertEquals(fbDomain, "www.facebook.com");

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
