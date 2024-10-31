package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_WebElement_Commands_1 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        //01
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Element() {
        //1. Dùng để xoá dữ liệu trong 1 field cho phép nhập (editable)
        //Textbox/ TextArea/ Dropdown (Editable)
        //Thường được sử dụng trước hàm sendKeys
        driver.findElement(By.id("")).clear(); //**

        //2. dùng để nhập liệu vào các field bên trên
        driver.findElement(By.id("")).sendKeys(""); //**

        //3. Dùng để click lên element
        driver.findElement(By.id("")).click(); //**

        //4. Tìm từ node cha vào node con
        driver.findElement(By.id("form-validate")).findElement(By.id("firstname"));
        driver.findElement(By.id("form-validate")).findElements(By.cssSelector("input"));

        driver.findElement(By.cssSelector("form#form-validate input#firstname"));

        //5. Trả về 1 element khớp với điều kiện
        WebElement fullNameTextbox = driver.findElement(By.id(""));

        //6. Trả về nhiều elements khớp với điều kiện
        List<WebElement> textboxes =  driver.findElements(By.cssSelector(""));

        //7. Dùng để verify 1 checkbox/ radio/ dropdown đã được chọn hay chưa
        Assert.assertTrue(driver.findElement(By.id("")).isSelected()); //*
        Assert.assertFalse(driver.findElement(By.id("")).isSelected());

        //8. Dropdown (default/ custom)
        Select select = new Select(driver.findElement(By.id("")));


        //9. Dùng để verify 1 element bất kì có hiển thị hay ko
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed()); //** //mong muốn hiển thị
        Assert.assertFalse(driver.findElement(By.id("")).isDisplayed()); //mong muốn ko hiển thị

        //10. Dùng để verìy 1 element có được thao tác lên hay ko (ko phải read-only) 1:06:30
        Assert.assertTrue(driver.findElement(By.id("")).isEnabled()); //*
        Assert.assertFalse(driver.findElement(By.id("")).isEnabled());

        //11. HTML Element 1:07:10
        //<input type = "text" id="firstname" ngetTame="firstname" value=""
        //title="First Name" maxLength="255" class="input-text required-entry">
        driver.findElement(By.id("")).getAttribute("title"); //*
        driver.findElement(By.id("")).getAttribute("type");
        driver.findElement(By.id("")).getAttribute("id");


        //12. Tab Accessibility/ Properties trong Element
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute("checked");
        driver.findElement(By.id("")).getDomProperty("baseURI");
        driver.findElement(By.id("")).getDomProperty("outerHTML");

        //13. Tab Styles trong Elements <1:14:35>
        //Font/ Size/ Color/ Background
        driver.findElement(By.id("")).getCssValue("background-color"); //* //rgb(46, 138, 184)
        driver.findElement(By.id("")).getCssValue("font-size");
        driver.findElement(By.id("")).getCssValue("text-transform");

        //14. Vị trí của element so với độ phân giải màn hình
        Point nameTextboxLocation = driver.findElement(By.id("")).getLocation();
        nameTextboxLocation.getX();
        nameTextboxLocation.getY();

        //15. Chiều cao + rộng
        Dimension addressSize = driver.findElement(By.id("")).getSize();

        //16. Location + Size (1:20:10) (Mục 16 = mục 14 + 15)
        Rectangle nameTextboxRect = driver.findElement(By.id("")).getRect();

        //16.1 Location (Toạ độ trục tung + trục hoành)
        Point namePoint = nameTextboxRect.getPoint();

        //16.2 Size (VD textbox đó D + R bao nhiêu)
        Dimension nameSize = nameTextboxRect.getDimension();
        nameSize.getHeight();
        nameSize.getWidth();

        //17. Shadow Element (JavaScriptExecutor) (1:27:00)
        driver.findElement(By.id("")).getShadowRoot();

        //18. Từ id/ class/ name/ css/ xpath có thể truy ra ngược lại tagname HTML
        driver.findElement(By.cssSelector("#firstname")).getTagName(); //input
        driver.findElement(By.id("firstname")).getTagName(); //input
        driver.findElement(By.className("form-instructions")).getTagName(); //p

        //Element A -> đầu ra của nó là tagnam của element A
        String formListTag = driver.findElement(By.xpath("//*[@class='form-list']")).getTagName();

        //Đầu vào của Element B sẽ nhận tagname của element A là tham số
        driver.findElement(By.xpath("//div[@clas='remember-me-popup']/preceding-sibling::" + formListTag));

        //19. getText
        driver.findElement(By.cssSelector("address.copyright")).getText(); //**

        //20. Chụp hình bị lỗi và lưu dưới dạng nào
        //BYTE
        //FILE (Lưu thành 1 hình có kích thước ở trong ổ cứng: .png/ .jpg/..)
        //Base 64 (hình dạng text)
        driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.FILE);
        driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.BASE64);//*
        driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.BYTES);

        //21. Form (element nà là thẻ form hoặc nằm trong thẻ form)
        //hành vi giống phím Enter trên bàn phím
        //Register/ login/ Search/..
        driver.findElement(By.id("")).submit();

    }

    @Test
    public void TC_02() {

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}