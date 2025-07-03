package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_25_Upload {
    WebDriver driver;
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
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Single_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By inputBy = By.xpath("//input[@type='file']");

        //1. Load file lên - MỖI LẦN LOAD 1 FILE

        driver.findElement(inputBy).sendKeys(haLongPath);
        Thread.sleep(2000);

        driver.findElement(inputBy).sendKeys(ninhBinh1Path);
        Thread.sleep(2000);

        driver.findElement(inputBy).sendKeys(ninhBinh2Path);
        Thread.sleep(2000);

        driver.findElement(inputBy).sendKeys(saiGonPath);
        Thread.sleep(2000);

        //2. Verify file được load lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ haLong +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ ninhBinh1 +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ ninhBinh2 +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ saiGon +"']")).isDisplayed());

        //3. Click upload cho từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement startButton: startButtons){
            startButton.click();
            Thread.sleep(2000);
        }

        //4. Verify các file được upload thành công
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+haLong+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ninhBinh1+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ninhBinh2+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+saiGon+"']")).isDisplayed());


    }

    @Test
    public void TC_02_Multiple_Files() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By inputBy = By.xpath("//input[@type='file']");

        //1. Load file lên - MỖI LẦN LOAD NHIỀU FILES

        driver.findElement(inputBy).sendKeys(haLongPath + "\n" + ninhBinh1Path + "\n" + ninhBinh2Path + "\n" +saiGonPath );
        Thread.sleep(2000);



        //2. Verify file được load lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ haLong +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ ninhBinh1 +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ ninhBinh2 +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ saiGon +"']")).isDisplayed());

        //3. Click upload cho từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement startButton: startButtons){
            startButton.click();
            Thread.sleep(2000);
        }

        //4. Verify các file được upload thành công
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+haLong+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ninhBinh1+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ninhBinh2+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+saiGon+"']")).isDisplayed());

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
