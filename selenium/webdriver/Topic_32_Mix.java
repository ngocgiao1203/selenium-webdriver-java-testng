package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_32_Mix {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Element_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Wait vs Explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

        //Wait vs Implicit
        driver.findElement(By.cssSelector("input#email"));
    }

    @Test
    public void TC_02_Element_Not_Found_Only_Implicit() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Lấy timeout của implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("TC_02_Element_Not_Found_Only_Implicit" + getDateTimeNow() +"------");
        try{
            WebElement emailTextBox =  driver.findElement(By.cssSelector("input#automation"));
            Assert.assertTrue(emailTextBox.isDisplayed());
            System.out.println("Switch to try");
        }catch (Exception ex){
            System.out.println("TC_02_Element_Not_Found_Only_Implicit" + ex.getMessage());
            System.out.println("--------------------Exception của implicit-----------------");
        }
        System.out.println("TC_02_Element_Not_Found_Only_Implicit" + getDateTimeNow() +"--------------");

        //Wait vs Implicit

        //3. Nếu không tìm thấy element nào
        //Mới đầu vào find element nhưng ko thấy:
        //Tìm lại mà thấy element thì ko cần chờ hết tổng thời gian còn lại
        //Tìm lại và ko thấy hết tổng time 10s thì đánh fail test case
        //Show lỗi: NoSuchElementException
    }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit_By(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        By emailTextboxBy = By.cssSelector("input#email");

        //Implicit = 0
        //Explicit = 3
        System.out.println("Start = " + getDateTimeNow());
        try{
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(emailTextboxBy)));
        } catch (Exception e){
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TC_03_Element_Not_Found_Only_Explicit_WebElement(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Implicit = 0
        //Explicit = 3
        System.out.println("Start = " + getDateTimeNow());


        //Wait vs Explicit
        //explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
        WebElement emailTextbox = driver.findElement(By.cssSelector("input#automationfc")); //Element ở bên ngoài = được truyền như là 1 tham số trong hàm wait của explicit => Nên dùng wait nào của explicit là phù hợp

        try{
            explicitWait.until(ExpectedConditions.visibilityOf(emailTextbox));
        } catch (Exception e){
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_04_Element_Not_Found_Only_Explicit() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(8));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        System.out.println("Start" + getDateTimeNow());
        try{
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automationfc")));
        }catch(Exception e){
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }

    }

    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
