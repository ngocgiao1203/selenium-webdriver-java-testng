package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;

public class Topic_19_Action_III {
    WebDriver driver;
    //khai báo: Tạo một tham chiếu mà bạn có thể sử dụng sau này.
    Actions action; //Actions ở đây là tên lớp (class) trong Java. Nó định nghĩa kiểu của biến action.
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");

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
                jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Drag_Drop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));
        action.dragAndDrop(sourceCircle,targetCircle).perform();
        Assert.assertEquals(targetCircle.getText(),"You did great!");
        Assert.assertEquals(Color.fromString(targetCircle.getCssValue("background-color")).asHex(),"#03a9f4");
    }

    @Test
    public void TC_02_Drag_Drop_HTML5_JQuery() throws InterruptedException, IOException{
        driver.get("https://automationfc.github.io/drag-drop-html5/");

//        //Site ko suppport JQuery
//        String jqueryLibraries = getContentFile(projectPath +"dragDrop/jQueryLib.js");
//        jsExecutor.executeScript(jqueryLibraries);

        //Site support JQuery
        //Nếu chạy trên MacOS
        String jqueryDrapDropContent = getContentFile(projectPath + "/dragDrop/dragAnđrop.js");
        //Nếu chạy trên Windows
        //String jqueryDrapDropContent = getContentFile(projectPath + "\\dragDrop\\dragAnđrop.js");


        //Drag A to B
        jsExecutor.executeScript(jqueryDrapDropContent);
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        //Drag B to A
        jsExecutor.executeScript(jqueryDrapDropContent);
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
