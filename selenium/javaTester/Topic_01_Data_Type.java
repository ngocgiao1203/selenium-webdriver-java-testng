package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Topic_01_Data_Type {
    //KDL trong Java - 2 nhm

    //I - KDL nguyên thuỷ (Primitive Type)
    //Số nguyên: byte - short - int - long
    //ko có phần thập phân: nhân viên trong công ty/ học sinh trong 1 lớp/ trường/ ..

    byte bNumber = 40;
    short sNumber = 3000;
    int iNumber = 15635658;
    long lNumber = 234343400;

    //số thực: float - double
    //có phần thập phân: điểm số/ lương/..
    float fNumber = 9.99f;
    double dNumber = 35.800789d;

    //kí tự: char
    //đại diện cho 1 kí t
    char c = '$';
    char d = 'i';

    //Logic: boolean (luận lý)
    //kết quả bài test: pass/ fail (ko ngoại lệ)
    boolean status = false;



    //II. KDL tham chiếu (Reference Type)
    //Class
    FirefoxDriver firefoxDriver = new FirefoxDriver();
    Select select = new Select(firefoxDriver.findElement(By.className(" ")));
    Topic_01_Data_Type topic01 = new Topic_01_Data_Type();

    //Interface
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    //Object
    Object name = "Automation";

    //Array (kiểu nguyên thuỷ/ tham chiếu)
    int[] studentAge = {15,20, 8}; //Array có 3 elements (size)
    String[] studentName = {"Automation","Testing"}; //Array có 2 elements (size)

    //Collection: List/ Set/ Queue
    List<String> studentAddress = new ArrayList<String>(); //ArrayList: KDL động (dynamic data type): ko bắt buộc phải khai báo số lượng elements ngay từ đầu
                                                                                                    // (còn Array bắt buộc phải có)

    List<String> studentCity = new LinkedList<>();

    List<String> studentPhone = new Vector<>();

    //String
    //String name = "Minh";
}
