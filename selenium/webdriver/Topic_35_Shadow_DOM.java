package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_35_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/shadow-dom/");

        //Thuộc DOM cha bên ngoài
        driver.findElement(By.xpath("//a[text()='scroll.html']"));

        //Element cha chứa shadow host
        WebElement shadowHostParent = driver.findElement(By.cssSelector("div#shadow_host"));

        //Lấy ra element shadow root
        SearchContext firstShadown= shadowHostParent.getShadowRoot();
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
