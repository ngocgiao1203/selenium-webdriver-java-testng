package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Scope {
    //Topic_04_Scope: class -> ko có dấu ()
    //các biến được khai báo ở bên ngoài hàm -> phạm vi là Class
    //Biến Global (toàn cục)
    //có thể dùng cho tất cả các hàm ở trong 1 class đó
    WebDriver driver;
    String homePageUrl; //Khai báo: Declare
    String fullName = "Automation FC"; //Khai báo + khởi tạo (Initial)

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01() {
        //Các biến được khai báo ở trong 1 hàm/ 1 block code -> phạm vi cục bộ (local)
        //beforeClass(),TC_01() là hàm
        //dùng trong hàm nó được khai báo/ block code được sinh ra
        String homePageUrl = "https://www.facebook.com/";

        //Trong 1 hàm nếu như có 2 biến cùng tên (Global/ Local) thì nó sẽ ưu tiên lấy biến local dùng
        // 1 biến local nếu như gọi tới dùng mà chưa được khởi tạo thì sẽ bị lỗi
        //Biến Local chưa khởi tạo mà gọi ra dùng nó sẽ báo lỗi ngay (compile code)
        driver.get(homePageUrl);

        //Nếu trong 1 hàm có 2 biến cùng tên (Global/ Local) thì mình mun lấy biến Global ra để dùng
        //từ khoá this
        //Biến Global chưa khởi tạo mà gọi ra dùng thì ko báo lỗi ở level (compile code) //trong quá trình mình chạy, gọi là 'compile'/ 'runtime'
        //level runtime sẽ lỗi
        driver.get(this.homePageUrl);

    }

    @Test
    public void TC_02() {

    }
    @Test
    public void TC_03() {

    }
    @Test
    public void TC_04() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
