package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Default_Dropdown {
    WebDriver driver;
    String firstName = "Giao", lastName = "Ngoc", emailAdress = getEmailAddress();
    String companyName = "Selenium WebDriver", password = "123456";
    String day ="11", month="November",year = "1950";


    @BeforeClass
    public void beforeClass() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    @Test
    public void TC_01_Register() {
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
        //cách 1. tạo name -> input (nếu name đó dùng cho nhiều lần)
//        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
//        day.selectByVisibleText("15");
        //cách 2. gọi thằng
        //new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText("15");
        //Day Dropdown
        Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
        //Chọn ngày
        dayDropdown.selectByVisibleText(day);

        //Verify dropdown này là Single (ko phải Multiple)
        Assert.assertFalse(dayDropdown.isMultiple());
        //Nếu nó là multiple: true
        //Nếu nó là Single: false

        //Verify số lượng items trong dropdown này là 32 items
            //Assert.assertEquals(day.getOptions().size(),32); -> Cách verify thứ 1
            //Cách verify thứ 2
        List<WebElement> dayOptions = dayDropdown.getOptions();
        Assert.assertEquals(dayOptions.size(),32);

        //MONTH
        Select month  = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByVisibleText("November");
        //Verify số lượng items trong dropdown này là 13 items
        List<WebElement> monthOptions = month.getOptions();
        Assert.assertEquals(monthOptions.size(), 13);
        //YEAR
        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByVisibleText("1950");
        //Verify số lượng items trong dropdown này là 112 items
        List<WebElement> yearOptions = year.getOptions();
        Assert.assertEquals(yearOptions.size(), 112);

        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAdress);
        driver.findElement(By.name("Company")).sendKeys(companyName);
        driver.findElement(By.name("Password")).sendKeys(password);
        driver.findElement(By.name("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.name("register-button")).click();
        sleepInSeconds(60);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");

    }

    @Test
    public void TC_02_Login() {
        //Login
        driver.findElement(By.xpath("//div[@class='header-logo']//img")).click();
        driver.findElement(By.xpath("//a[@class='ico-logout']")).click();
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("input.email")).sendKeys(emailAdress);
        driver.findElement(By.cssSelector("input.password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(2);
        //Verify
        driver.findElement(By.cssSelector("ico-account")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmailAddress() {
        Random rand = new Random();
        return "ngocgiao" + rand.nextInt(99999) + "@gmail.com";
    }
}