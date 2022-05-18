import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.apache.poi.ss.usermodel.DataFormatter;
import java.sql.Driver;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.File;

import org.openqa.selenium.io.FileHandler;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.collections.Lists;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class justdial{
    public static WebDriver driver;


    public static WebDriver createdriver(int ch){
        if (ch==1)
            driver = DriverSetup.getWebDriverChrome();
        else if (ch==2)
            driver = DriverSetup.getWebDriverFirefox();

        return driver;
    }

    public String ReadCellData(int vRow, int vColumn)
    {
        String value=null;
        Workbook wb=null;
        try
        {
            //FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\userdetails.xlsx");
            FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\userdetails.xlsx");
            wb=new XSSFWorkbook(fis);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        Sheet sheet=wb.getSheetAt(0);
        Row row=sheet.getRow(vRow);
        Cell cell=row.getCell(vColumn);
        value=cell.getStringCellValue();
        return value;
    }
    public static void carwash(){

        justdial rc=new justdial();
        String city=rc.ReadCellData(1, 1);
        String area=rc.ReadCellData(1, 2);
        String service=rc.ReadCellData(1,3);
        String name=rc.ReadCellData(1,4);
        driver.findElement(By.id("city")).click();
        driver.manage().deleteAllCookies();
        driver.findElement(By.id("city")).sendKeys("");
        driver.findElement(By.id(city)).click();
        driver.findElement(By.id("srchbx")).click();
        driver.findElement(By.id("insrch")).sendKeys(area);
        driver.findElement(By.id("srchbx")).sendKeys(service);
        driver.findElement(By.id("search")).click();
        //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.findElement(By.id("rating")).click();
        List<WebElement> list=driver.findElements(By.xpath("//*[@class='green-box']"));
        //List<WebElement> list2=driver.findElements(By.xpath("//*[@class='rt_count lng_vote']"));
        int c=1;
        int count=0;
        int pos=0;
        for (WebElement i:list){
            String op=i.getText();
            Double x=Double.parseDouble(op);
            if (x>4) {
                String votes = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div[1]/div/section/div/ul/li[" + c + "]/section/div[1]/section/div[1]/p[1]/a/span[3]")).getText();
                String[] vote = votes.split(" ");
                int y = Integer.parseInt(vote[0]);
                if (y > 20 && count < 5) {
                    String store=driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div[1]/div/section/div/ul/li[" + c + "]/section/div[1]/section/div[1]/h2/span/a/span")).getText();
                    System.out.println(count+1+". Store name: "+store);
                    System.out.println("Rating: "+x);
                    System.out.println("Votes: "+y);
                    pos=c;
                    count++;
                }
            }
            c++;
        }
        //driver.manage().deleteAllCookies();
        driver.findElement(By.id("result_loader_"+String.valueOf(pos-1))).click();
        driver.findElement(By.id("bd_name")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id='best_deal_div']/section/section/section/form/aside/p[4]/button")).click();
        System.out.println("Error message: "+driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath("//*[@id='best_deal_div']/section/span")).click();
    }

    public static void fitness(){
        driver.navigate().to("https://www.justdial.com");
        //driver.manage().deleteAllCookies();
        driver.findElement(By.id("hotkeys_text_24")).click();
        driver.findElement(By.xpath("//*[@id='mnintrnlbnr']/ul/li[3]/a")).click();
        List<WebElement> list=driver.findElements(By.xpath("//*[@id='mnintrnlbnr']/ul[1]/li/a/span[2]"));
        int a=1;
        System.out.println("Gym Options: ");
        for(WebElement i:list){
            System.out.println(a+". "+i.getText());
            a++;
        }
    }

    public static void closedriver()
    {
        System.out.println("Closing browser");
        driver.close();
    }

    public static void main(String[] args){
        justdial obj=new justdial();
        String choice=obj.ReadCellData(1, 0);
        System.out.println("Select Browser: ");
        System.out.println("1. Chrome");
        System.out.println("2. Firefox"); // change 1,0 index of userdetails sheet to 1 or 2 to change browser
        obj.createdriver(Integer.parseInt(choice));
        obj.carwash();
        obj.fitness();
        obj.closedriver();
    }
}
