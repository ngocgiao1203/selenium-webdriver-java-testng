package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_23_Windows_Tabs {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Lấy ra ID của tab/ window mà driver đang active tại page đó
        String githubWindowID = driver.getWindowHandle();

        //============================GOOGLE============================
        //Click vào Google Link -> nó sẽ bật lên 1 tab mới và tự nhảy qua
        //Nhưng về code Selenium là driver ko tự nhảy qua - nó vẫn ở tab cũ
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(2000);

        //Switch qua tab Google
        switchToWindowByID(githubWindowID);
        Thread.sleep(2000);

        String googleWindowID = driver.getWindowHandle();
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium WebDriver");
        Thread.sleep(2000);

        //============================FACEBOOK============================
        //Switch qua tab Github
        switchToWindowByID(googleWindowID);
        Thread.sleep(2000);
        //Click vào Facebook link -> nó sẽ bật lên 1 tab mới và tự nhảy qua
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(2000);
        switchToWindowByTitle("Facebook – log in or sign up");

        //============================TIKI============================
        switchToWindowByTitle("Selenium WebDriver");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        Thread.sleep(2000);
        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        closeAllWindowsWithoutParent(githubWindowID);


        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }

    @Test
    public void TC_02_TechPanda() throws InterruptedException {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        //Click button 'Add to Compare' of Sony Xperia
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2" +
                "/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),
                "The product Sony Xperia has been added to comparison list.");
        Thread.sleep(2000);
        //Click button 'Add to Compare' of Samsung Galaxy
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2" +
                "/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),
                "The product Samsung Galaxy has been added to comparison list.");
        Thread.sleep(2000);
        //click Compare button
        driver.findElement(By.xpath("//span[text()='Compare']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/catalog/product_compare/index/");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("button[title='Close Window']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Mobile");
        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/mobile.html");

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.switchTo().alert().getText(),
                "Are you sure you would like to remove all products from your comparison?");
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The comparison list was cleared.");
        Thread.sleep(2000);


    }
    @Test
    public void TC_03_Cambridge() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");
        driver.findElement(By.cssSelector("span.cdo-login-button")).click();
        switchToWindowByTitle("Login");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='username']~span.gigya-error-msg-active")).getText(),"This field is required");
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='password']~span.gigya-error-msg-active")).getText(),"This field is required");
        driver.close();
        Thread.sleep(3000);
        switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
        driver.findElement(By.cssSelector("input#searchword")).sendKeys("code");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button.cdo-search-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#cald4-1~div.pos-header span.headword>span")).getText(),"code");
    }

    @Test
    public void TC_04_Selenium_4x() throws InterruptedException {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        Thread.sleep(2000);
        System.out.println("Driver ID = " + driver.toString());
        System.out.println("Window ID = " + driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.switchTo().newWindow(WindowType.TAB).get("http://live.techpanda.org/index.php/customer/account/");
        Thread.sleep(2000);

        System.out.println("Driver ID = " + driver.toString());
        System.out.println("Window ID = " + driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");

        switchToWindowByTitle("Mobile");
        Thread.sleep(2000);

        //Click button 'Add to Compare' of Sony Xperia
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2" +
                "/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),
                "The product Sony Xperia has been added to comparison list.");
        Thread.sleep(2000);
        //Click button 'Add to Compare' of Samsung Galaxy
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2" +
                "/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),
                "The product Samsung Galaxy has been added to comparison list.");
        Thread.sleep(2000);
        //click Compare button
        driver.findElement(By.xpath("//span[text()='Compare']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/catalog/product_compare/index/");
        Thread.sleep(2000);

    }

    private void closeAllWindowsWithoutParent(String githubWindowID) throws InterruptedException {
        //============================CLOSE TOÀN BỘ WINDOWS TRỪ GITHUB============================
        //Lấy ra hết tất cả các ID của window/tab hiện tại
        Set<String> allWindowIDs = driver.getWindowHandles();
        for(String id : allWindowIDs){
            if (!id.equals(githubWindowID)){
                driver.switchTo().window(id);
                Thread.sleep(2000);
                driver.close();
            }
        }
        driver.switchTo().window(githubWindowID);
    }

    private void switchToWindowByTitle(String expectedPageTitle) throws InterruptedException {
        //Lấy hết toàn bộ các ID của các window/tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        //Dùng vòng lặp duyệt qua từng ID
        for(String id : allWindowIDs){
            //Mỗi lần duyệt sẽ cho nó switch vào trước
            driver.switchTo().window(id);
            Thread.sleep(2000);

            //Get ra title của window/ tab hiện tại
            String pageTitle = driver.getTitle();

            //Kiểm tra title
            if (pageTitle.equals(expectedPageTitle)){
                break;
            }
        }
    }

    //Chỉ đúng vs 2 Window/ Tab
    private void switchToWindowByID(String windowID) {
        //Lấy ra hết tất cả các ID của window/tab hiện tại
        Set<String> allWindowIDs = driver.getWindowHandles();

        //Dùng vòng lặp để duyệt qua từng ID một
        for (String id : allWindowIDs) {
            //Kiểm tra điều kiện: nếu ID nào mà khác với ID mong đợi thì switch qua
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
            }
        }
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
