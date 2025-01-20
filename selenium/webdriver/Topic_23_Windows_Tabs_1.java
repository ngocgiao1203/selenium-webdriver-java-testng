package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_23_Windows_Tabs_1 {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String gitHubWindowID = driver.getWindowHandle();
        //============================GOOGLE============================
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        switchToWindowByID(gitHubWindowID);
        Thread.sleep(2000);
        String googleWindowID = driver.getWindowHandle();
        driver.findElement(By.xpath("//textarea[@title='Tìm kiếm']")).sendKeys("Selenium Webdriver");
        Thread.sleep(2000);

        //============================FACEBOOK============================
        switchToWindowByID(googleWindowID);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(2000);
        switchToWindowByTitle("Facebook – log in or sign up");

        //============================TIKI============================
        switchToWindowByTitle("Selenium WebDriver");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        //CLOSE CÁC TAB TRỪ Selenium WebDriver
        closeAllWindowsWithoutParent("Selenium WebDriver");

    }

    private void closeAllWindowsWithoutParent(String githubWindowID) throws InterruptedException {
        Set<String> allWindowsIDs = driver.getWindowHandles();
        for (String id : allWindowsIDs) {
            driver.switchTo().window(id);
            String pageTitle = driver.getTitle();
            if(!pageTitle.equals(githubWindowID)){
                driver.close();
            }
            Thread.sleep(2000);
        }
    }
////a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']
    private void switchToWindowByTitle(String expectedPageTitle) {
        Set<String> allWindowsIDs = driver.getWindowHandles();
        for (String id : allWindowsIDs) {
            driver.switchTo().window(id);
            if (driver.getTitle().equals(expectedPageTitle)) {
                break;
            }
        }
    }

    private void switchToWindowByID(String windowID) {
        Set<String> allWindowsIDs = driver.getWindowHandles();
        for(String id : allWindowsIDs){
            if(!id.equals(windowID)){
                driver.switchTo().window(id);
            }
        }
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
