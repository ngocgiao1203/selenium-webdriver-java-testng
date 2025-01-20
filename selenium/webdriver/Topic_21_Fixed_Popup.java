package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_21_Fixed_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_NgoaiNgu24h() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        WebElement loginPopup = driver.findElement(By.cssSelector("div.MuiPaper-root"));
        Assert.assertTrue(loginPopup.isDisplayed());
        driver.findElement(By.xpath("//input[@autocomplete='username']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//input[@autocomplete='new-password']")).sendKeys("automationfc");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("div.auth-form button[type='submit']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Bạn đã nhập sai tài khoản hoặc mật khẩu!']")).isDisplayed());
    }

    @Test
    public void
    TC_02_Kyna() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login-mb>div.modal-dialog")).isDisplayed());
        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC_03_Tiki() throws InterruptedException {
        driver.get("https://tiki.vn/");
        WebElement popupTiki = driver.findElement(By.cssSelector("div#VIP_BUNDLE"));
        if(popupTiki !=null){
            driver.findElement(By.cssSelector("div.home-page main div#VIP_BUNDLE img[alt='close-icon']")).click();
        }
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        By registerPopup = By.cssSelector("div.ReactModal__Content");
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElements(registerPopup).size(),1);

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).isDisplayed());
        driver.findElement(By.cssSelector("button.btn-close")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElements(registerPopup).size(),0);
    }

    @Test
    public void TC_04_Facebook(){
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
