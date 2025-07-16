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
import java.util.List;
import java.util.regex.Pattern;

public class Topic_29_Explicit_Function {
    WebDriver driver;
    WebDriverWait explicitWait;


    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_() throws InterruptedException {
        //Wait cho element ko hiển thị ko còn trong HTML (trước đó là có tồn tại)
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        //Wait cho element ko hiển thị (còn/ ko còn trong HTML)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        //Wait cho element được phép click (button/ link/ radio/ checkbox/...)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        //Wait cho URL của page tuyệt đối
        explicitWait.until(ExpectedConditions.urlToBe(""));

        //Wait cho URL của page tương đối
        explicitWait.until(ExpectedConditions.urlContains("dynamic-loading"));
        //=================================================================================================

        //Wait cho URL của page thoả mãn biểu thức (Regex)
        explicitWait.until(ExpectedConditions.urlMatches("*$^...."));

        //Wait cho 1 đoạn JS trả về kiểu dữ liệu String
        explicitWait.until(ExpectedConditions.jsReturnsValue("return arguments[0].validationMessage;"));

        //Wait cho Alert có xuất hiện trong HTML
        explicitWait.until(ExpectedConditions.titleIs("Dynamic loading to demo wait in Selenium"));
        //Wait cho title của page tuyệt đối
        explicitWait.until(ExpectedConditions.titleIs("Dynamic loading to demo wait in Selenium"));

        //Wait cho title của page tương đối
        explicitWait.until(ExpectedConditions.titleContains("Dynamic loading"));

        //Wait tho mãn cả 2 điều kiện (AND)
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.urlContains(""),
                ExpectedConditions.titleIs("")
        ));

        //Wait thoã mãn 1 trong 2 điều kiện (OR)
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains(""),
                ExpectedConditions.titleIs("")
        ));
        //=================================================================================================

        //Wait cho 1 element có xuất hiện trong HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        //Wait cho 1 element có thuộc tính chứa 1 giá trị nào đó
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""),"class","email"));

        //Wait cho 1 element có thuộc tính bằng 1 giá trị nào đó
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""),"class","email"));

        //Wait cho 1 element có thuộc tính ko được rỗng/ null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("")),"class"));

        //Wait cho 1 element có thuộc tính ở trong DOM bằng giá trị nào đó
        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")),
                "baseURI", "https://automationfc.github.io/dynamic-loading/"));
        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")),
                "innerText", "Start"));

        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")),
                "innerText","Start"));
        //=================================================================================================

        //Wait cho element đã được chọn thành công (checkbox/ Radio/ Dropdown Item)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        //Wait cho element đã được chọn thành công
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), true));


        //Wait cho element chưa được chọn thành công
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), false));

        //Wait cho frame/ iframe xuất hiện và switch vào
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));

        //Wait cho 1 đoạn lệnh JS được thực thi ko trả về bất kì Exception nào
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("return arguments[0].validationMessage;"));


        //Phủ định lại điều kiện wait
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.javaScriptThrowsNoExceptions("return arguments[0].validationMessage;")));

        //Wait cho 1 list element bằng bao nhiêu item
        List<WebElement> allNumberSelected = explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("ol#selectable>li.ui-selected"),6));
        Assert.assertEquals(allNumberSelected.size(),6);

        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("ol#selectable>li.ui-selected"),7));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("ol#selectable>li.ui-selected"),5));

        //Wait cho số lượng Window/ Tab bằng bao nhiêu
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));

        //Wait cho 1 đoạn text bằng tuyệt đối
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""),""));
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), Pattern.compile("a*b")));

        //Wait cho 1 element hay bị change/ refresh lại/ update lại
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

    }



    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
