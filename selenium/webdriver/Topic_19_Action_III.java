package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class Topic_19_Action_III {
    WebDriver driver;
    //khai báo: Tạo một tham chiếu mà bạn có thể sử dụng sau này.
    Actions action; //Actions ở đây là tên lớp (class) trong Java. Nó định nghĩa kiểu của biến action.

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        //Khởi tạo: Tạo một đối tượng thực tế để tham chiếu hoạt động.
        //Cụ thể trong trường hợp của Actions, việc khởi tạo đòi hỏi một tham số là WebDriver driver.
        // Đối tượng Actions cần driver để biết nó sẽ thao tác trên trình duyệt nào.
        //new Actions(driver) tạo ra một đối tượng mới của lớp Actions
                action = new Actions(driver);
    }

    @Test
    public void TC_01_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
