package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class Topic_12_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Default_Telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By rearSideCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
        By dualZoneCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input");
        //Chọn 2 checkboxes
        //Case 1: Nếu như app này mở ra mà checkbox đã được chọn thì sao
        sleepInSeconds(8);
        checkToElement(rearSideCheckbox);
        //Case 2: Nếu như app này mở ra mà checkbox chưa được chọn
        checkToElement(dualZoneCheckbox);
        //Verìy checkbox đã được chọn thành công
        Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
        //Bỏ chọn 2 checkbox này
        uncheckToElement(rearSideCheckbox);
        uncheckToElement(dualZoneCheckbox);
        //Verìy checkbox đã uncheck thành công
        Assert.assertFalse(driver.findElement(rearSideCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
    }


    @Test
    public void TC_02_Default_Telerik_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        By twoDieselRadio = By.xpath("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::span/input");

        //Click chọn 1 trong 2
        checkToElement(twoPetrolRadio);
        //Verify
        Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());
        //Click chọn 1 trong 2
        checkToElement(twoDieselRadio);
        //Verify
        Assert.assertFalse(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());
    }

    @Test
    public void TC_03_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        //Chọn hết tất cả các checkbox trong màn hình đó
        for (WebElement checkbox : allCheckboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                //sleepInSeconds(1);
            }
        }
        //Verify hết tất cả các checkboxes
        for (WebElement checkbox : allCheckboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        //Chọn 1 checkbox/ radio nào đó trong tất cả các checkbox/ radio
        allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        for (WebElement checkbox : allCheckboxes) {
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()) {
                checkbox.click();
                sleepInSeconds(1);
            }
        }

        //Verify checkbox
        for (WebElement checkbox : allCheckboxes) {
            if (checkbox.getAttribute("value").equals("Heart Attack")) {
                Assert.assertTrue(checkbox.isSelected());
            } else {
                Assert.assertFalse(checkbox.isSelected());

            }
        }
    }
    @Test
    public void TC_04_Custom_Radio() {
        driver.get("https://login.ubuntu.com/");
        //Case 1:
        //Dùng thẻ input để click => Thẻ input bị che bởi 1 element khác => Fail
        //WebElement click(): The element must be visible, and it must have a height and width greater than 0
        //isSelected: only applies to input elements

        //Case2:
        //Dùng thẻ div bên ngoài để click => passed
        //Dùng thẻ div để verify => Failed
        // driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']")).click();
        //sleepInSeconds(3);
        //Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']")).isSelected();

        //Case 3:
        //Dùng thẻ div bên ngoài để click => passed
        //Dùng thẻ input để verìy => passed
        //1 element mà cần define tới 2 locator thì sau này => maintain mất nhiều thời gian hơn
        // driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']")).click();
        //sleepInSeconds(3);
        //Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());

        //Case 4:
        //Dùng thẻ input để click => JavaScriptExecutor (JS)
        //Dùng thẻ input để verify => isSelected: only applies to input elements
        //Chỉ cần 1 locator

        By noAccountRadio = By.xpath("//span[text()='I don’t have an Ubuntu One account']/parent::label/preceding-sibling::input");
        By readAcceptCheckbox = By.xpath("//label[contains(text(),'I have read and accept the')]/parent::div/input");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(noAccountRadio));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(readAcceptCheckbox));

        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(noAccountRadio).isSelected());
        Assert.assertTrue(driver.findElement(readAcceptCheckbox).isSelected());
    }

    @Test
    public void TC_05_Custom_Google_Docs(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
        By quangNamCheckbox = By.xpath("//div[@aria-label='Quảng Nam']");

        //Verify radio is not selected
        //RadioButton
        //Cách 1
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"false");
        //Cách 2
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());
        //Checkbox
        //Cách 1:
        Assert.assertEquals(driver.findElement(quangNamCheckbox).getAttribute("aria-checked"),"false");
        //Cách 2:
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='false']")).isDisplayed());

        //Verify radio/Checkbox is selected
        //RadioButton
        WebElement canthoRadioEl = driver.findElement(canThoRadio);
        WebElement grandFatherCanthoRadioEl = canthoRadioEl.findElement(By.xpath("./../.."));
        grandFatherCanthoRadioEl.click();

        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
        //Checkbox
        driver.findElement(quangNamCheckbox).click();
        sleepInSeconds(1);
        Assert.assertEquals(driver.findElement(quangNamCheckbox).getAttribute("aria-checked"),"true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='true']")).isDisplayed());


    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void checkToElement (By byXpath){
        //Nếu như element chưa dc chọn thì mới click
        if(!driver.findElement(byXpath).isSelected()){
            driver.findElement(byXpath).click();
            sleepInSeconds(2);
        }
    }

    public void uncheckToElement (By byXpath){
        if(driver.findElement(byXpath).isSelected()){
            //Nếu element được chọn rồi thì vào click lần nữa cho nó thành bỏ chọn
            driver.findElement(byXpath).click();
            sleepInSeconds(2);
        }
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
