package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_05_Assert {
    WebDriver driver;
    @Test
    public void verifyTestNG(){
        //thư viện TestNG
        //Trong Java có nhiều thư viện để verìy dữ liệu
        //Testing Framework (Unit/ Intergration/ UI Automation Test)
        //JUnit 4/ TestNG/ JUnit 5/ Hamcrest/ AssertJ/..

        //Trên UI sẽ có 3 hàm: AssertTrue, AssertFalse, AssertEqual
        //kiểu dữ liệu nhận vào là boolean (true/false)
        //khi mong muốn điều kiện trả về là đúng thì dùng assertTrue để verìy
        Assert.assertTrue(driver.getCurrentUrl().contains("The Apple and Google Play logos are trademarks of their respective owners."));
        //mong muốn điều kiện trả về là sai thì dùng assertFalse
        Assert.assertFalse(driver.getCurrentUrl().contains("The Apple and Google Play logos are trademarks of their respective owners."));

        //Các hàm trả về KDL là boolean
        //Quy tắc: bắt đầu với tiền tố là isXXX
        driver.findElement(By.id("")).isDisplayed();
        driver.findElement(By.id("")).isEnabled();
        driver.findElement(By.id("")).isSelected();
        new Select(driver.findElement(By.id(""))).isMultiple();

        //AssertEquals: Mong đợi 1 điều kiện nó giống như thực tế (tuyệt đối)
        //Actual = Expected
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");
        Assert.assertEquals(driver.findElement(By.id("")).getText(),"Create a new account");





        //Unit Test
        Object name = null;
        Assert.assertNull(name);

        name = "Testing";
        Assert.assertNotNull(name);
    }
}
