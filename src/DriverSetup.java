import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class DriverSetup {

    public static WebDriver getWebDriverChrome() {
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--incognito");
        ChromeOptions options = new ChromeOptions();

        //options.addArguments("--disable-notifications");
        //WebDriver driver = new ChromeDriver(options);
        WebDriver driver = new ChromeDriver();
        //driver.navigate().to("https://www.justdial.com");
        driver.get("https://www.justdial.com");
        driver.manage().window().maximize();
        return driver;
    }
    public static WebDriver getWebDriverFirefox() {
        //FirefoxOptions options = new FirefoxOptions();
        //options.addArguments("-private");
        WebDriver driver=new FirefoxDriver();
        driver.get("http://www.justdial.com");
        driver.manage().window().maximize();
        return driver;
    }
}
