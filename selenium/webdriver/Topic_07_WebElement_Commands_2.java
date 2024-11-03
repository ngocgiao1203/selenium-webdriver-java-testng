package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_WebElement_Commands_2 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        //01
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Displayed() {
    //Nếu như mong đợi 1 element hiển thị thì verifyTrue
        //Nếu như mong đợi 1 element ko hiển thị thì verifyFalse
        //isDisplayed() = true | false
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if(driver.findElement(By.cssSelector("input#mail")).isDisplayed()){
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Email is displayed");
        }else{
            System.out.println("Email is not displayed");
        }
        if(driver.findElement(By.cssSelector("input#under_18")).isDisplayed()){
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("under 18 is displayed");
        }else{
            System.out.println("under18 is not displayed");
        }
        if(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()){
            driver.findElement(By.cssSelector("textarea#edu")).click();
            System.out.println("education is displayed");
        }else{
            System.out.println("education is not displayed");
        }
        if(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()){
            driver.findElement(By.xpath("//h5[text()='Name: User5']")).click();
            System.out.println("User 5 is displayed");
        }else{
            System.out.println("User 5 is not displayed");
        }

    }

    @Test
    public void TC_02_Enabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if(driver.findElement(By.cssSelector("input#mail")).isEnabled()){
            System.out.println("Email is enabled");
        }else{
            System.out.println("Email is disabled");
        }

        if(driver.findElement(By.cssSelector("input#under_18")).isEnabled()){
            System.out.println("under 18 is enabled");
        }else{
            System.out.println("under18 is disabled");
        }

        if(driver.findElement(By.cssSelector("textarea#edu")).isEnabled()){
            System.out.println("education is enabled");
        }else{
            System.out.println("education is disabled");
        }
        //Job Role 01
        if(driver.findElement(By.cssSelector("select#job1")).isEnabled()){
            System.out.println("Job Role 01 is enabled");
        }else{
            System.out.println("Job Role 01 is disabled");
        }
        //Job Role 02
        if(driver.findElement(By.cssSelector("select#job2")).isEnabled()){
            System.out.println("Job Role 02 is enabled");
        }else{
            System.out.println("Job Role 02 is disabled");
        }
        //interests (development) Checkbox
        if(driver.findElement(By.cssSelector("input#development")).isEnabled()){
            System.out.println("interests (development) Checkbox  is enabled");
        }else{
            System.out.println("interests (development) Checkbox  is disabled");
        }

        //Slider 01
        if(driver.findElement(By.cssSelector("input#slider-1")).isEnabled()){
            System.out.println("Slider 01 is enabled");
        }else{
            System.out.println("Slider 01 is disabled");
        }

        //CHECK CÁC PHẦN TỬ DISABLE TRÊN TRANG
        //PASSWORD
        if(driver.findElement(By.cssSelector("input#password")).isEnabled()){
            System.out.println("password is enabled");
        }else{
            System.out.println("password is disabled");
        }
        //AGE (RADIOBUTTON IS DISABLED)
        if(driver.findElement(By.cssSelector("input#radio-disabled")).isEnabled()){
            System.out.println("AGE (RADIOBUTTON IS DISABLED) is enabled");
        }else{
            System.out.println("AGE (RADIOBUTTON IS DISABLED) is disabled");
        }
        //BIOGRAPHY
        if(driver.findElement(By.cssSelector("textarea#bio")).isEnabled()){
            System.out.println("BIOGRAPHY is enabled");
        }else{
            System.out.println("BIOGRAPHY is disabled");
        }
        //JOB ROLE 03
        if(driver.findElement(By.cssSelector("select#job3")).isEnabled()){
            System.out.println("JOB ROLE 03 is enabled");
        }else{
            System.out.println("JOB ROLE 03 is disabled");
        }
        //INTERESTS (CHECKBOX IS DISABLED)
        if(driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled()){
            System.out.println("INTERESTS (CHECKBOX IS DISABLED) is enabled");
        }else{
            System.out.println("INTERESTS (CHECKBOX IS DISABLED) is disabled");
        }
        //SLIDE 02 (DISABLED)
        if(driver.findElement(By.cssSelector("input#slider-2")).isEnabled()){
            System.out.println("Slider 02 is enabled");
        }else{
            System.out.println("Slider 02 is disabled");
        }
    }
    @Test
    public void TC_03_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //Age (under 18) Radio button
        driver.findElement(By.cssSelector("input#under_18")).click();
        if (driver.findElement(By.cssSelector("input#under_18")).isSelected()) {
            System.out.println("under 18 is selected");
        } else {
            System.out.println("under 18 is de-selected");
        }
        //"Languages: Java" checkbox
        driver.findElement(By.cssSelector("input#java")).click();
        if (driver.findElement(By.cssSelector("input#java")).isSelected()) {
            System.out.println("java is selected");
        } else {
            System.out.println("java is de-selected");
        }
        driver.findElement(By.cssSelector("input#java")).click();
        if (driver.findElement(By.cssSelector("input#java")).isSelected()) {
            System.out.println("java is selected");
        } else {
            System.out.println("java is de-selected");
        }
        Assert.assertFalse(driver.findElement(By.cssSelector("input#java")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());

    }
    @Test
    public void TC_04_MailChimp() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("ngocgiaok18ufm@gmail.com");
        //number
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("1234");
        sleepInSeconds (2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        //Assert.assertTrue(driver.findElement(By.cssSelector("//li[class='username-check completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //lower chars
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto");
        sleepInSeconds (5);
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        //Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());
        //upper chars
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("ABC");
        sleepInSeconds (2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='username-check completed']")).isDisplayed());
        //special chars
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("@#");
        sleepInSeconds (2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='username-check completed']")).isDisplayed());
        //more than 8 chars
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123423222");
        sleepInSeconds (2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='username-check completed']")).isDisplayed());
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSeconds (long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}