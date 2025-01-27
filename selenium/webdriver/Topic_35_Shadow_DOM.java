package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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

        //Element cha chứa Shadow Host thứ 1
        WebElement shadowHostParent = driver.findElement(By.cssSelector("div#shadow_host"));
        //Lấy ra element Shadow root
        SearchContext firstShadow = shadowHostParent.getShadowRoot();

        Assert.assertTrue(firstShadow.findElement(By.cssSelector("input[type='file']")).isDisplayed());

        Assert.assertEquals(firstShadow.findElement(By.cssSelector("span.info")).getText(),"some text");


        //Element cha chứa shadow host thứ 2
        WebElement firstShadowHostParent = firstShadow.findElement(By.cssSelector("div#nested_shadow_host"));
        //Lấy ra element shadow root
        SearchContext secondShadow = firstShadowHostParent.getShadowRoot();
        Assert.assertEquals(secondShadow.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(),"nested text");

        Assert.assertTrue(firstShadow.findElement(By.cssSelector("a[href='scroll.html']")).isDisplayed());
        driver.findElement(By.xpath("//a[text()='scroll.html']"));
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
