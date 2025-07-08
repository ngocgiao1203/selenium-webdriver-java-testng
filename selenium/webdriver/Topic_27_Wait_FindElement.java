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
import java.util.List;

public class Topic_27_Wait_FindElement {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        //set totalTime = 13s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2Fregister");

    }

    @Test
    public void TC_01_FindElement() {
        //Nếu tìm thấy duy nhất 1 element
            //trả về đúng element đó
            //không cần chờ hết time của implicit
//        driver.findElement(By.cssSelector("input[name='FirstName']"));
//
//        //Nếu tìm thấy nhiều hơn 1 element
//            //chỉ thao tác với element đầu tiên
//            //Lưu ý là khi lấy locator nên check trưước
//        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Testing");

        //Nếu không tìm thấy element nào
            //Mới đầu vào find element nhưng ko thấy:
                //Tìm lại mà thấy element thì ko cần chờ hết tổng thời gian còn lại
                //Tìm lại và ko thấy hết tổng time 13s thì đánh fail test case
                //Show lỗi: NoSuchElementException
        WebElement rememberMeCheckbox = driver.findElement(By.cssSelector("input#RememberMe"));
    }

    @Test
    public void TC_02_FindElements() {
        List<WebElement> elements = null;
        //Nếu tìm thấy duy nhất 1 element
            //trả về đúng 1 cái
        elements = driver.findElements(By.cssSelector("input[name='FirstName']"));
        System.out.println(elements.size());
        //Nếu tìm thấy nhiều hơn 1 element
            //trả về hết toàn bộ các elements matching
        elements = driver.findElements(By.cssSelector("input[type='text']"));
        System.out.println(elements.size());

        //Nếu không tìm thấy element nào
            // Mới đầu vào find element nhưng ko thấy:
                //Tìm lại mà thấy element thì ko cần chờ hết tổng thời gian còn lại
                //Tìm lại và ko thấy hết tổng time 13s thì:
                    //trả về list elements = 0;
                    //KHÔNG ĐÁNH FAIL TEST CASES

        elements = driver.findElements(By.cssSelector("input#RememberMe"));
        System.out.println(elements.size());
        Assert.assertEquals(elements.size(),0);

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
