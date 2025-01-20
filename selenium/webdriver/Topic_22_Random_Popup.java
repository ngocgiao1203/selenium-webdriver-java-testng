package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Random_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));

    }

    @Test
    public void TC_01_JavaCodeGeeks() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");
        By newletterPopupBy = By.xpath("//div[@data-title='Newsletter Free eBooks' and not(contains(@style,'display:none'))]");

        //Hiển thị thì close đi rồi action tiếp
        if (driver.findElements(newletterPopupBy).size()>0 && driver.findElements(newletterPopupBy).get(0).isDisplayed()){
            System.out.println("----------------------GO TO IF----------------------");
            driver.findElement(By.xpath("//div[@data-title='Newsletter Free eBooks' and not(contains(@style,'display:none'))]//a[contains(@onclick,'return lepopup_close();')]")).click();
            Thread.sleep(2000);
        }
        else{
            System.out.println("----------------------IGNORE IF----------------------");
        }
        //Ko hiển thị thì action tiếp
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile");
        driver.findElement(By.cssSelector("button#search-submit")).click();

        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("header>h1.page-title")).isDisplayed());
    }

    @Test
    public void TC_02_VNK() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");
        By marketingPopup = By.cssSelector("div.pum-container");
        if(driver.findElements(marketingPopup).size()>0 && driver.findElements(marketingPopup).get(0).isDisplayed()){
            System.out.println("---------------GO TO IF---------------");
            driver.findElement(By.cssSelector("div.pum-container>button.pum-close")).click();
            Thread.sleep(2000);
        }
        else{
            System.out.println("---------------IGNORE IF---------------");
        }
        driver.findElement(By.xpath("//div[@class='navbar-collapse collapse']//a[text()='Liên hệ']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content>h1")).isDisplayed());
    }

    @Test
    public void TC_03_DeHieu() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        By marketingPopup = By.cssSelector("div.modal-content");
        if(driver.findElements(marketingPopup).size()>0 && driver.findElements(marketingPopup).get(0).isDisplayed()){
            System.out.println("---------------GO TO IF---------------");
            driver.findElement(By.cssSelector("div.modal-content button.close")).click();
            Thread.sleep(2000);
        }
        else{
            System.out.println("---------------IGNORE IF---------------");
        }

        driver.findElement(By.cssSelector("input.search-form")).sendKeys("Khóa học Lập dự toán M&E");
        driver.findElement(By.cssSelector("div.navbar-collapse button[type='submit']")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.course-item-detail a")).getAttribute("title"),"Khóa học Lập dự toán M&E");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
