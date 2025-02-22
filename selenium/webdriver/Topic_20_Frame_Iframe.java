package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_Frame_Iframe {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Iframe_FormSite() throws InterruptedException {
        //Trang HTML A
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        WebElement cookies = driver.findElement(By.cssSelector("div.osano-cm-window__dialog.osano-cm-dialog.osano-cm-dialog--position_top.osano-cm-dialog--type_bar"));

        if (cookies != null){
            driver.findElement(By.cssSelector("button.osano-cm-dialog__close.osano-cm-close")).click();
        }

        driver.findElement(By.cssSelector("img[alt='Campus Safety Survey']")).click();
        Thread.sleep(3000);
        //Switch qua iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#frame-one85593366")));
        //Element thuộc trang HTML B
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("South Dorm");
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        Thread.sleep(3000);
        //Từ B quay lại A
        driver.switchTo().defaultContent();
        //Driver đã quay lại A rồi
        driver.findElement(By.cssSelector("a.menu-item-login.fs-btn--transparent-kashmir")).click();
        driver.findElement(By.cssSelector("button#login")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");
    }

    @Test
    public void TC_02_Iframe_ToiDiCodeDao(){
        driver.get("https://toidicodedao.com/");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@data-testid='fb:page Facebook Social Plugin']")));
        WebElement followerText = driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']//parent::div//following-sibling::div"));
        Assert.assertEquals(followerText.getText(),"405,276 followers");
    }

    @Test
    public void TC_03_Frame() throws InterruptedException {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame("login_page");
        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationfc");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("a.login-btn")).click();
        Thread.sleep(3000);
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456789");
        driver.findElement(By.cssSelector("a#loginBtn")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("p.error-msg")).getText(),"Customer ID/IPIN (Password) is invalid. Please try again.");

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
