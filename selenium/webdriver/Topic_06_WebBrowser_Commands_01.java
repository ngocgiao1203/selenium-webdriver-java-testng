//**: thường xuyên sử dụng
//*: có khả năng sử dụng
package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands_01 {
    // Các câu lệnh để thao tác với Browser đều đứng đằng sau câu lệnh driver.
    // Các câu lệnh thao tác với Element đều đứng đằng sau câu lệnh element.
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        //Muốn dùng được thì phải khởi tạo
        //Nếu không khởi tạo sẽ gặp lỗi: NullPointerException
        driver = new FirefoxDriver(); //**
        /*driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver = new SafariDriver();*/

        //selenium ver 3/2/1 (deprecated)
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //selenium ver 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //**
    }

    @Test
    public void TC_01_Browser() throws MalformedURLException {
        //Cách 1 - set trực tiếp vào
        driver.get("https://www.facebook.com/"); //**
        //Cách 2 - khai báo bến rồi gán vào sau
        // Nếu như biến ny chỉ dùng duy nhất 1 lần thì ko nên tạo biến
        String homePageUrl = "https://www.facebook.com/";
        driver.get(homePageUrl);


        //Nếu như có 1 tab/ window thì tính năng tương tự quit
        driver.close();//*

        //đóng browser (ko care bao nhiêu tab/ window)
        driver.quit();//**

        //2 hàm này sẽ bị ảnh hưởng timeout của implicitWait
        //findElement sẽ trả về kiểu web element - nó sẽ đi tìm với loại By nào và trả về element nếu như được tìm thấy (WebElement)
        // ko dc tìm thấy -> fail tại step này - throw exception: NoSearchElementException
        driver.findElement(By.id("email")); //**
        //để lưu trữ lại
        WebElement emailAddressTextbox = driver.findElement(By.id("email")); //**

        //Nó sẽ đi tìm vs loại By nào và trả về 1 danh sách element nếu như được tìm thấy (WebElement)
        // ko dc tìm thấy -> ko bị fail - trả về 1 list rỗng (0 element)
        driver.findElements(By.xpath("//input[@type='checkbox']")); //lấy hết tất cả các checkboxes có trên màn hình này
        //lưu trữ list
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']")); //** //List được viết dưới dạng Java Generic
        //list checkboxes -> lấy ra 1 checkbox cụ thể -> index 1:54:15
        checkboxes.get(1).click(); //get -> truyền index -> lấy element trong list đó

        //Tại sao lại cần phải lấy dữ lệu ra để làm gì?
        //Dùng để lấy ra Url của màn hình/ page hiện tại đang đứng
        driver.getCurrentUrl(); //*
        //lấy ra page source HTML/ CSS/ JS của page hiện tại
        //Verìy 1 cách tương đối
        //ko thể dùng AssertEqual cho getPageSource đc, vì AssertEqual để check tương đối
        driver.getPageSource();
        //đúng:
        driver.getCurrentUrl().contains("The Apple and Google Play logos are trademarks of their respective owners.");
        Assert.assertTrue(driver.getCurrentUrl().contains("The Apple and Google Play logos are trademarks of their respective owners."));
        //sai
        //Assert.assertEquals(driver.getCurrentUrl().contains("The Apple and Google Play logos are trademarks of their respective owners.");


        //lấy ra title của page hiện tại
        driver.getTitle();

        //Lấy ra ID của cửa sổ
        //Handle Windơ/ Tab
        driver.get("https://www.facebook.com/");
        System.out.println("Window/Tab ID =" + driver.getWindowHandle()); //*
        driver.getWindowHandles();//*

        //Cookies - Framework
        driver.manage().getCookies();//*

        //Get ra những log ở dev tool - framework
        driver.manage().logs().get(LogType.DRIVER);//*

        //Apply cho việc tìm element (findElement/ findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));//**
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        //Chờ cho page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        //Set trước khi dùng vs thư viện JavaScriptExecutor
        //Inject 1 đoạn code JS vào trong Browser/ Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        //Selenium 4 mới có
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();

        //chạy full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize(); //**
        driver.manage().window().minimize();

        //Test GUI
        //Test Responsive (Resolution)
        driver.manage().window().setSize(new Dimension(1366,768));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().setSize(new Dimension(2560, 1440));
        driver.manage().window().getSize();

        //Set cho browser ở vị trí nào so với độ phân giải màn hình (run trên màn hình có kích thước bao nhiêu)
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();

        //Điều hướng trang web
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();

        //thao tác với history của web page (back/ forward)
        driver.navigate().to("https://www.facebook.com/");
        driver.navigate().to(new URL("https://www.facebook.com/"));


        //Alert/ Window (Tab)/ Freame (iFrame) //*
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Test");

        //Lấy ra ID của cửa sổ/ tab hiện tại //*
        //Handle Window/ Tab
        String homePageWindowID = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowID);

        //Switch/ handle frame (iframe) //*
        //Index/ ID (name)/ Element
        driver.switchTo().frame(0);
        driver.switchTo().frame("39493494");
        driver.switchTo().frame(driver.findElement(By.id("")));

        //Switch về HTML chứa frame trước đó
        driver.switchTo().defaultContent();

        //Từ frame trong đi ra frame ngoài chứa nó
        driver.switchTo().parentFrame();

    }

    @Test
    public void TC_02() {

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}