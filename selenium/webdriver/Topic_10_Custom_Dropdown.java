package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
////        1. Click vào 1 thẻ (tag) để cho nó xổ hết các items bên trong dropdown ra
//        driver.findElement(By.cssSelector("span#number-button")).click();
//        sleepInSeconds(5);
////          Chờ cho nó xổ ra hết tất cả các item trong dropdown
//        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
//        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
//        //allItems đang lưu trữ 19 items bên trong
//        for (WebElement item : allItems) { //dùng biến item có KDL là WebElement để duyệt qua hết tất cả các phần tử trong list allItems
//            //Trước khi click cần kiểm tra nếu như text của item bằng với item cần chọn thì click vào
//            if (item.getText().equals("8")) {
//                item.click();
//                break;// Thoát vòng lặp (for/ while/ do-while/ switch-case)
//            }
//        }
        selectItemInDropdown("span#speed-button","ul#speed-menu div", "Medium");
        sleepInSeconds(3);
        //driver.navigate().refresh();
        selectItemInDropdown("span#files-button","ul#files-menu div", "Some unknown file");
        sleepInSeconds(3);
        selectItemInDropdown("span#number-button","ul#number-menu div", "19");
        sleepInSeconds(3);
        selectItemInDropdown("span#salutation-button","ul#salutation-menu div", "Dr.");
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Medium");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button span.ui-selectmenu-text")).getText(),"Some unknown file");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),"19");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),"Dr.");
    }

    @Test
    public void TC_02_ReactJS() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("i.dropdown.icon","div.item>span.text","Jenny Hess");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Jenny Hess");
        sleepInSeconds(5);
        selectItemInDropdown("i.dropdown.icon","div.item>span.text","Elliot Fu");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Elliot Fu");
        sleepInSeconds(5);
        selectItemInDropdown("i.dropdown.icon","div.item>span.text","Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Stevie Feliciano");
        sleepInSeconds(5);


    }

    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a","First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");
        sleepInSeconds(5);

    }

    @Test
    public void TC_04_Editable() {//Editable: dropdown vừa chọn vừa nhập
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInDropdown("input.search","div.item span", "Aland Islands");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Aland Islands");
        sleepInSeconds(5);
    }

    @Test
    public void TC_05_NopCommerce(){
        driver.get("https://demo.nopcommerce.com/register");
        selectItemInDropdown("select[name='DateOfBirthDay']","select[name='DateOfBirthDay'] option","12");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay'] option[value='12']")).isSelected());
        sleepInSeconds(1);
        //month
        selectItemInDropdown("select[name='DateOfBirthMonth']","select[name='DateOfBirthMonth'] option","March");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth'] option[value='3']")).isSelected());
        sleepInSeconds(1);

        //year
        selectItemInDropdown("select[name='DateOfBirthYear']","select[name='DateOfBirthYear'] option","2000");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthYear'] option[value='2000']")).isSelected());
        sleepInSeconds(3);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectItemInDropdown(String parentCss, String childCss, String itemTextExpected){
        driver.findElement(By.cssSelector(parentCss)).click(); //"span#number-button"
        sleepInSeconds(1);
//        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));//"ul#number-menu div"
//        List<WebElement> allItems = driver.findElements(By.cssSelector(childCss));//"ul#number-menu div"
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));
        for (WebElement item : allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }

    public void selectItemEditableDropdown(String parentCss, String childCss, String itemTextExpected) {

        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);
        sleepInSeconds(1);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));
        for (WebElement item : allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }
}