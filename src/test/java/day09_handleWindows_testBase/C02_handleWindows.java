package day09_handleWindows_testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C02_handleWindows {

    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    //1-amazon ana sayfaya gidip
    //2 nutella icin arama yaptirin
    @Test
    public void test01() {

        //1=amazon ana sayfaya gidin
        driver.get("https://www.amazon.com");
        String  ilkSayfaWindowHandleDegeri=driver.getWindowHandle();
        //2-Url'nn amazon icerdigini test edelim
        String istenenKelime="amazon";
        String actualUrl=driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(istenenKelime));

        //3-yeni pencere acip bestbuy ana sayfaya gidelim
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.bestbuy.com");
        String ikiciSayfaWindowHandledegeri=driver.getWindowHandle();

        //4- title'in Best buy icerdigini test edelim
        String actualTitle=driver.getTitle();
        String arananKelime="Best Buy";
        Assert.assertTrue(actualTitle.contains(arananKelime));
        //5-ilk sayfaya donup sayfada Java aratalim
        driver.switchTo().window(ilkSayfaWindowHandleDegeri);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java" + Keys.ENTER);

        //6-arama sonuclarinin Java icerdigini test edelim
        WebElement sonucYazielementi=driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        String sonucYazisiStr=sonucYazielementi.getText();
        String aradigimizKelime="Java";
        Assert.assertTrue(sonucYazisiStr.contains(aradigimizKelime));
        //7-yeniden bestbuy'in acik oldugu sayfaya gidelim
        driver.switchTo().window(ikiciSayfaWindowHandledegeri);
        //8-logonun gorundugunu test edelim
        WebElement logoElementi=driver.findElement(By.xpath("//img[@class='logo']"));
        Assert.assertTrue(logoElementi.isDisplayed());

    }
}