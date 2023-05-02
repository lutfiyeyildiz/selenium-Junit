package day07_assertions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.signature.qual.DotSeparatedIdentifiersOrPrimitiveType;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C01_Assertions {
    /*
    amazon ana sayfaya gidin
    3 farkli test methodu'u olusturarak asagidaki gorevleri yapin

    1-Url'nn amazon icerdigini test edin
    2-title'n facabook icermedigini test edin
    3-sol ust kosede amazon logosunun gorundugunu test edin
     */
    static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.amazon.com");
    }
   @AfterClass
    public static void tearDown(){

   }
   @Test
    public void test01(){

       //1-Url'nn amazon icerdigini test edin
        String arananKelime="amazon";
        String actualUrl=driver.getCurrentUrl();
       Assert.assertTrue(actualUrl.contains(arananKelime));
   }
   @Test
    public void test02(){
        // 2-title'n facabook icermedigini test edin
       String istenmeyenKelime="facebook";
       String actualTitle=driver.getTitle();
       Assert.assertFalse(actualTitle.contains(istenmeyenKelime));

   }
   @Test
    public void tet03(){
       //3-sol ust kosede amazon logosunun gorundugunu test edin
       WebElement logoElementi=driver.findElement(By.id("nav-logo-sprites"));
       Assert.assertTrue(logoElementi.isDisplayed());

   }
}
