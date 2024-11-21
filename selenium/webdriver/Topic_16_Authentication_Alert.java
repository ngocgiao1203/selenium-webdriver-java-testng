package webdriver;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.network.model.Headers;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Topic_16_Authentication_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    String username = "admin";
    String password = "admin";

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Authentication_URL()  {
        //Cách 1: Truyền thẳng user, pass vào URL
        driver.get("http://" + username +":" +password +"@" + "the-internet.herokuapp.com/basic_auth");
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");
    }
    @Test
    public void TC_02_Authentication_Navigate()  {
        //Cách 2: Từ page A thao tác lên 1 element nó sẽ qua page B (cần phải thao tác vs Authen Alert trước)
        driver.get("http://the-internet.herokuapp.com/");
        String authenLinkUrl= driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        String[] authenArray= authenLinkUrl.split("//");
        System.out.println(authenArray[0]);
        System.out.println(authenArray[1]);
        driver.get(authenArray[0] +"//"+ username +":" + password +"@" + authenArray[1]);
        Assert.assertTrue(driver.findElement(By.xpath(
                "//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }
    @Test
    public void TC_03_Authentication_CDP(){
        //Cách 3:
        // Thư viện Alert ko sử dụng cho Authentication Alert được
        //Chrome DevTool Protocol (CDP): giả lập lại những gì mình test trên dev tool - Chỉ giả lập dc trên Chrome/ Edge (Chromium)
        //Cốc Cốc/ Opera (Chromium) - Work Around

//Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        //Start new session
        devTools.createSession();

        //Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        //Encode username/ password
        Map<String,Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s","admin","admin").getBytes()));
        headers.put("Authorization",basicAuthen);

        //Set to Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String getAuthenAlertByUrl(String url, String username, String password){
        String[] authenArray = url.split("//");
        return authenArray[0]+"//"+ username +":" + password +"@" + authenArray[1];
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
