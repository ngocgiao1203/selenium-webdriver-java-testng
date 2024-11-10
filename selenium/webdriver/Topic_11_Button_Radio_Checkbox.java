package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Topic_11_Button_Radio_Checkbox {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Egov_button() {
        //driver.get("https://www.fahasa.com/customer/account/create");
        driver.get("https://egov.danang.gov.vn/reg");
        WebElement registerButton = driver.findElement(By.cssSelector("input#button2"));
        //Verify button bị disable khi chưa click vào checkbox
        Assert.assertFalse(registerButton.isEnabled());
        driver.findElement(By.cssSelector("input#chinhSach")).click();
        //Verify button bị enable khi click vào checkbox
        Assert.assertTrue(registerButton.isEnabled());

        //Lấy ra mã màu nền của button
        String registerBackgroundRGB = registerButton.getCssValue("background-color");
        System.out.println("Background color RGB = " + registerBackgroundRGB);
        //Convert từ kiểu String (mã RGB) qua kiểu Color
        Color registerBackgroundColour = Color.fromString(registerBackgroundRGB);
        System.out.println("Register Background Colour = " + registerBackgroundColour);

        //Convert qua kiểu Hexa
        String registerBackgroundHexa = registerBackgroundColour.asHex();
        System.out.println("Background color Hexa = " + registerBackgroundHexa);
        System.out.println("Background color Hexa = " + registerBackgroundHexa.toUpperCase());
        System.out.println("Background color Hexa = " + registerBackgroundHexa.toLowerCase());

        //Color registerBackgroundColorHexa = registerBackgroundColorRGB.asH
        Assert.assertEquals(registerBackgroundHexa.toLowerCase(),"#ef5a00");
    }

    @Test
    public void TC_02_Fahasha(){
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        //Button Login bị disabled
        Assert.assertFalse(loginButton.isEnabled());
        String loginButtonColorRGB = loginButton.getCssValue("color");
        Color loginButtonColor = Color.fromString(loginButtonColorRGB);
        String loginButtonColorHexa = loginButtonColor.asHex();
        Assert.assertEquals(loginButtonColorHexa,"#636363");
        // Input
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("ngocgiao@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        //Button Login được enabled
        Assert.assertTrue(loginButton.isEnabled());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
