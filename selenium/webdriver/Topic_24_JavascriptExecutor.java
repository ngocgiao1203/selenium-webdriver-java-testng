package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class Topic_24_JavascriptExecutor {
    WebDriver driver;
    //1. Muốn sử dụng thư viện của JavaScriptExecutor -> cần khai báo nó
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    Random random;
    String email;

    @BeforeClass
    public void beforeClass() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.locale.matchOS",false);
        profile.setPreference("intl.accept_languages", "en-US");
        profile.setPreference("intl.locale.requested","en-US");
        profile.setPreference("general.useragent.locale","en-US");
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        driver = new FirefoxDriver(options);

        //2. Khởi tạo ép kiểu
        jsExecutor = (JavascriptExecutor) driver; // Ép kiểu driver thành JavascriptExecutor vì các trình duyệt như ChromeDriver đã triển khai interface này.
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        email = "automation" + new Random().nextInt(9999) + "@gmail.com";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_Panda() throws InterruptedException {
        jsExecutor.executeScript("window.location = 'https://live.techpanda.org/'");

        Thread.sleep(2000);

        String techPandaDomain = (String) jsExecutor.executeScript("return document.domain;");
        Assert.assertEquals(techPandaDomain, "live.techpanda.org");
        Thread.sleep(2000);
        String homePageUrl = (String) jsExecutor.executeScript("return document.URL;");
        Assert.assertEquals(homePageUrl, "https://live.techpanda.org/");

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Mobile']")));
        Thread.sleep(2000);
        jsExecutor.executeScript("arguments[0].click();",
                driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button")));
        Thread.sleep(3000);

        String samsungText = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(samsungText.contains("Samsung Galaxy was added to your shopping cart."));

        Thread.sleep(2000);
        jsExecutor.executeScript("arguments[0].click();",
                driver.findElement(By.xpath("//a[text()='Customer Service']")));

        jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector("input#newsletter")));
        Thread.sleep(2000);
        //Cach 1:
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + email + "')", driver.findElement(By.cssSelector("input#newsletter")));
        //Cach 2: jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1])",driver.findElement(By.cssSelector("input#newsletter")),email);
        Thread.sleep(1000);
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button[title='Subscribe']")));
        Thread.sleep(3000);

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        Thread.sleep(3000);
        String subscription = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(subscription.contains("Thank you for your subscription."));

        Thread.sleep(2000);
        jsExecutor.executeScript("window.location = 'https://www.facebook.com/'");
        Thread.sleep(2000);
        String fbDomain = (String) jsExecutor.executeScript("return document.domain;");
        Assert.assertEquals(fbDomain, "www.facebook.com");

    }

    @Test
    public void TC_02_TechPanda() throws InterruptedException {
        navigateToUrlByJS("https://live.techpanda.org/");

        Assert.assertEquals(getDomain(), "live.techpanda.org");
        Assert.assertEquals(getPageURL(), "https://live.techpanda.org/");
        clickToElementByJS("//a[text()='Mobile']");
        clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
        clickToElementByJS("//a[text()='Customer Service']");
        scrollToElementOnTop("//input[@id='newsletter']");
        setAttributeInDOM("//input[@id='newsletter']", "value", email);
        clickToElementByJS("//button[@title='Subscribe']");
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        Thread.sleep(2000);
        Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
        navigateToUrlByJS("https://www.facebook.com/");
        Assert.assertEquals(getDomain(), "www.facebook.com");

    }

    @Test
    public void TC_03_Rode() throws InterruptedException {
        driver.get("https://warranty.rode.com/");
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        //empty
        loginButton.click();

        String emptyEmailMessage = getElementValidationMessage("//input[@id='email']");
        if (driver.toString().contains("Chrome")) {
            Assert.assertEquals(emptyEmailMessage, "Please fill out this field.");
        } else {
            Assert.assertEquals(emptyEmailMessage, "Vui lòng điền vào trường này.");
            //Assert.assertEquals(emptyEmailMessage, "Please fill out this field.");
        }

        //Assert.assertEquals(emptyEmailMessage, "Vui lòng điền vào trường này.");
        Thread.sleep(3000);

        //Email invalid
        String invalidEmailData = "aaa";
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginButton.click();
        Thread.sleep(2000);

        String invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        if (driver.toString().contains("Chrome")) {
            Assert.assertEquals(invalidEmailMessage, "Please include an '@' in the email address. '" + invalidEmailData + "' is missing an '@'.");
        } else {
            Assert.assertEquals(invalidEmailMessage, "Vui lòng điền một địa chỉ email.");
        }

        invalidEmailData = "aaa@";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginButton.click();
        Thread.sleep(2000);
        invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        if (driver.toString().contains("Chrome")) {
            Assert.assertEquals(invalidEmailMessage, "Please enter a part following '@'. '" + invalidEmailData + "' is incomplete.");
        } else {
            Assert.assertEquals(invalidEmailMessage, "Vui lòng điền một địa chỉ email.");
        }

        invalidEmailData = "aaa@aaa.";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginButton.click();
        Thread.sleep(2000);
        invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        if (driver.toString().contains("Chrome")) {
            Assert.assertEquals(invalidEmailMessage, "'.' is used at a wrong position in '"+invalidEmailData.split("@")[1]+"'.");
        } else {
            Assert.assertEquals(invalidEmailMessage, "Vui lòng điền một địa chỉ email.");
        }

        //Email valid
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        loginButton.click();
        Thread.sleep(2000);

        String emptyPasswordMessage = getElementValidationMessage("//input[@id='password']");
        if (driver.toString().contains("Chrome")) {
            Assert.assertEquals(emptyPasswordMessage, "Please fill out this field.");
        } else {
            Assert.assertEquals(emptyPasswordMessage, "Vui lòng điền vào trường này.");
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getPageURL() {
        return (String) jsExecutor.executeScript("return document.URL;");
    }

    public String getDomain() {
        return (String) jsExecutor.executeScript("return document.domain;");
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }


}
