package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_13_Alert {
    WebDriver driver;
    WebDriverWait expliciWait;
    By resultText = By.cssSelector("p#result");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        //sleepInSeconds(3);
        //Chờ cho alert present
        //Nếu trong thời gian chờ mà xuất hiện thì tự switch vào
        //Nếu hết thời gian chờ mà chưa xuất hiện mới fail
        Alert alert = expliciWait.until(ExpectedConditions.alertIsPresent());
        sleepInSeconds(3);
        //Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        //Khi mình accept/cancel rồi thì alert sẽ mất luôn
        alert.accept();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert = expliciWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.accept();
        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Ok");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");

    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert alert = expliciWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        String text ="Selenium WebDriver";
        alert.sendKeys(text);
        alert.accept();
        Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + text);
    }
    @Test
    public void TC_04_Authentication_Pass_To_URL()  {
        //Cách 1: Truyền thẳng user, pass vào URL
        //Trick - ByPass
        String username = "admin";
        String password = "admin";
        driver.get("http://" + username +":" + password +"@" +"the-internet.herokuapp.com/basic_auth");
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        //Cách 2: Từ page A thao tác lên 1 element nó sẽ qua page B (cần phải thao tác vs Authen Alert trước)
    }
    @Test
    public void TC_05_Authentication_AutoIT() throws IOException{
        //Cách 2: Chạy trên Window (AutoIT)
        //Mỗi 1 browser sẽ cần 1 script khác nhau
        //Thực thi đoạn code AutoIT để chờ Alert xuất hiện
//        Runtime.getRuntime().exec(new String[]{
//            projectLocation + "\\autoIT\\authen_firefox.exe", "admin", "admin"
//        });
//        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
//        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }
    @Test
    public void TC_06_Authentication_Selenium_4x(){
        //Cách 3:
        // Thư viện Alert ko sử dụng cho Authentication Alert được
        //Chrome DevTool Protocol (CDP): giả lập lại những gì mình test trên dev tool - Chỉ giả lập dc trên Chrome/ Edge (Chromium)
        driver.get("http://the-internet.herokuapp.com/");
        String authenLinkUrl= driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        String[] authenArray= authenLinkUrl.split("//");
        System.out.println(authenArray[0]);
        System.out.println(authenArray[1]);
        String username = "admin";
        String password = "admin";
        driver.get(authenArray[0] +"//"+ username +":" + password +"@" +"the-internet.herokuapp.com/basic_auth");


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
}
