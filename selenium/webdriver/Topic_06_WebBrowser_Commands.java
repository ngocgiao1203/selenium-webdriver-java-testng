package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {
    //Các câu lệnh để thao tác với trình duyệt sẽ đứng sau câu lệnh ".driver"
    WebDriver driver;
    //Các câu lệnh để thao tác với elements sẽ đứng sau câu lệnh ".element"
    WebElement element;

    FirefoxDriver ffDriver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
