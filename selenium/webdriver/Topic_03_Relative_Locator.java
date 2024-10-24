package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_03_Relative_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Relative() {
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fregister");
        //Get Element of 'Remember me' base on the 'RM checkbox' & the 'Login button'
        //Login button
        //define element 'login button' bằng css (Topic 12 - 11ph40)
        //Cách 1
        By loginButtonBy = By.cssSelector("button.login-button");
        //Cách 2
        WebElement loginButtonElement = driver.findElement(By.cssSelector("button.login-button"));
        //Remember Me checkbox
        By rememberMeCheckBoxBy = By.id("RememberMe");

        //Forgot Password link
        WebElement forgotPasswordElement = driver.findElement(By.cssSelector("span.forgot-password"));

        //Password textbox
        WebElement passwordTextBoxElement = driver.findElement(By.cssSelector("input.password")); //class
        By passwordTextBoxBy = By.cssSelector("input#Password"); //id


        //Remember me label
        //tag label "Remember me?" nằm trên (above) Login button
        //findElement
        WebElement rememberMeTextElement = driver.findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginButtonBy) //    Cách 2: .above(loginButtonElement)
                .toRightOf(rememberMeCheckBoxBy)
                .toLeftOf(forgotPasswordElement)
                .below(passwordTextBoxElement)
                .near(forgotPasswordElement)); //Cách 2: .below(passwordTextBoxBy)
        System.out.println(rememberMeTextElement.getText());

        //findElements
        List<WebElement> allLinks = driver.findElements(RelativeLocator.with(By.tagName("a")));
        System.out.println(allLinks.size());


    }

    @Test
    public void TC_02() {

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
