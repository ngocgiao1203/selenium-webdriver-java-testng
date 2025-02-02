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


    @Test
    public void TC_02_() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");

        Thread.sleep(5000);
        WebElement firstShadowHostElement = driver.findElement(By.xpath("//book-app"));
        SearchContext firstShadowHost = firstShadowHostElement.getShadowRoot();

        WebElement secondShadowHostElement = firstShadowHost.findElement(By.cssSelector("book-input-decorator"));
        SearchContext secondShadowHost = secondShadowHostElement.getShadowRoot();
        Thread.sleep(2000);
        firstShadowHost.findElement(By.cssSelector("input#input")).sendKeys("Harry Potter and the Sorcerer's Stone");
        secondShadowHost.findElement(By.cssSelector("div.icon")).click();
        Thread.sleep(2000);

        WebElement thirdShadowHostElement = firstShadowHost.findElement(By.cssSelector("book-explore"));
        SearchContext thirdShadowHost = thirdShadowHostElement.getShadowRoot();
        Thread.sleep(2000);
        WebElement forthShadowHostElement = thirdShadowHost.findElement(By.cssSelector("ul>li:nth-of-type(1)>book-item"));
        SearchContext forthShadowHost = forthShadowHostElement.getShadowRoot();
        Thread.sleep(2000);
        Assert.assertEquals(forthShadowHost.findElement(By.cssSelector("h2.title")).getText(),"Harry Potter and the Sorcerer's Stone");
    }

    @Test
    public void TC_03_Shopee() throws InterruptedException {
        driver.get("https://shopee.vn/");
        Thread.sleep(5000);
        WebElement firstShadowHostElement = driver.findElement(By.xpath("//div.home-page"));
        SearchContext firstShadowHost = firstShadowHostElement.getShadowRoot();
        Thread.sleep(2000);
        WebElement secondShadowHostElement = firstShadowHost.findElement(By.cssSelector("div.home-popup__content"));
        SearchContext secondShadowHost = secondShadowHostElement.getShadowRoot();
        Thread.sleep(2000);
        firstShadowHost.findElement(By.cssSelector("div.home-popup__close-area")).click();
        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("iPhone 16 Pro Max");


    }
        @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
