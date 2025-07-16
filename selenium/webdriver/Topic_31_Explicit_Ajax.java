package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_31_Explicit_Ajax {
    WebDriver driver;
    WebDriverWait explicitWait;
    String uploadFolderPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;
    String haLong = "Ha Long.jpg";
    String ninhBinh1 = "NinhBinh1.jpg";
    String ninhBinh2 = "NinhBinh2.jpg";
    String saiGon = "Sai Gon.jpg";

    String haLongPath = uploadFolderPath + haLong;
    String ninhBinh1Path = uploadFolderPath + ninhBinh1;
    String ninhBinh2Path = uploadFolderPath + ninhBinh2;
    String saiGonPath = uploadFolderPath + saiGon;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Calendar() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        //Wait & Verify Calendar element is displayed
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1"))).isDisplayed());

        //Wait & Verify text
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),"No Selected Dates to display.")));

        //Wait and click to element
        explicitWait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//td/a[text()='22']"))).click();


        //Wait cho ajax loading invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv"))));

        //Wait & Verify text
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),"Tuesday, July 22, 2025")));
    }

    @Test
    public void TC_02_Upload() throws InterruptedException {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://gofile.io/?t=uploadFiles");

        //Wait cho loading icon ở màn hình Upload ko còn hiển thị
//        Assert.assertTrue(explicitWait.until(ExpectedConditions
//                .invisibilityOfElementLocated(By.cssSelector("div#index_app div.animate-spin"))));

        //Wait và click cho "Drag & Drop or Click to Upload" button dc click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='home_uploadFile']/parent::div"))).click();

        By inputBy = By.xpath("//input[@type='file']");

        //1. Load file lên - MỖI LẦN LOAD NHIỀU FILES
        driver.findElement(inputBy).sendKeys(haLongPath + "\n" + ninhBinh1Path + "\n" + ninhBinh2Path + "\n" +saiGonPath );
        Thread. sleep(2000);

        //Wait cho loading icon của màn hình load file lên biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.cssSelector("div.animate-spin"))));

        //Wait cho các progress bar của các file biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-container"))));

        //Kiểm tra text hiển thị sau khi upload files thành công
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h2[text()='Upload Complete']"))).isDisplayed());

        //Wait và click vào link
        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("a.linkSuccessCard")))).click();

        //Wait cho loading icon ở màn hình successfully uploaded
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.cssSelector("div#filemanager_loading div.animate-spin"))));


    }




    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
