package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;

    String projectPath = System.getProperty("user.dir");

    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass(){
        if(osName.contains("Windows")){
            System.setProperty("webdriver.gecko.driver",projectPath + "\\browserDrivers\\geckodriver.exe");
        }else{
            System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
        }
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/register");

        driver.manage().window().maximize();

    }
    //HTML Element: <tagname attribute_name_1 = 'attribute_value' attribute_name_2 = 'attribute_value'...>
    /* <input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName"> */


    @Test
    public void TC_01_ID(){
        //Cách tìm 1 Element bằng ID <1:00:00>
        //by: selenium locator by
        //By: class of selenium (8 locator types)
        driver.findElement(By.id("FirstName")).sendKeys("Keane");
    }
    @Test
    public void TC_02_Class(){
        //nếu Element nào có class là duy nhất -> dùng được
        //nếu Element nào ko có class -> ko dùng dc
        //nếu Element có nhiều class -> ko dùng được
        driver.findElement(By.className("header-logo"));
    }
    @Test
    public void TC_03_Name(){
        driver.findElement(By.name("LastName"));
        driver.findElement(By.name("DateOfBirthDay"));
    }
    @Test
    public void TC_04_Tagname(){
        driver.findElements(By.tagName("input"));
    }
    @Test
    public void TC_05_LinkText(){
        //độ chính xác cao = tuyệt đối = toàn bộ
        driver.findElement(By.linkText("Shipping & returns"));
    }
    @Test
    public void TC_06_Partial_LinkText(){ //độ chính xác ko cao = tương đối ( 1 phần đầu/giữa/ cuối)
        driver.findElement(By.partialLinkText("for vendor")); //Apply for vendor account
        driver.findElement(By.partialLinkText("vendor account")); //Apply for vendor account
        driver.findElement(By.partialLinkText("Apply for vendor")); //Apply for vendor account
    }
    @Test
    public void TC_07_Css(){
        //css: có thể cover 6 TCs trên (id, class, name....)
        //Css vs ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));//combine tagname & id
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));

        //Css vs Class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));

        //Css vs Name (Name chỉ support khi viết đầy đủ)
        driver.findElement(By.cssSelector("input[name='FirstName']")); //combine tagname & attribute (name)
        driver.findElement(By.cssSelector("[name='FirstName']")); //using the "name" attribute directly


        //Css vs tagname
        driver.findElement(By.cssSelector("input"));

        //Css vs link
        //Css ko work với text -> buộc phải làm việc với Attribute
        driver.findElement(By.cssSelector("a[href='/customer/addresses']"));

        //Css vs partial link
        driver.findElement(By.cssSelector("a[href*='addresses']")); //lấy giữa
        //driver.findElement(By.cssSelector("a[href^='addresses']")); //lấy đầu
        driver.findElement(By.cssSelector("a[href$='addresses']")); //lấy đuôi
    }
    @Test
    public void TC_08_XPath(){
        //Xpath ko cho viết tắt như Css
        //VD:
        //Css có thể viết ("input#FirstName") / ("#FirstName")
        //Xpath vs ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        //Xpath vs Class
        driver.findElement(By.xpath("//div[@class='page-title']"));

        //Xpath vs Name (Name chỉ support khi viết đầy đủ)
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        //Xpath vs tagname
        driver.findElement(By.xpath("//input"));

        //Xpath vs link
        //Xpath ko work với text -> buộc phải làm việc với Attribute
        driver.findElement(By.xpath("//a[@href='/customer/addresses']"));
        driver.findElement(By.xpath("//a[text()='Addresses']"));

        //Xpath vs partial link
        driver.findElement(By.xpath("//a[contains(@href,'addresses')]")); //lấy giữa
        driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));
    }



    @AfterClass
    public void afterClass(){
        // driver.quit();
    }
}

