package day09_handleWindows_testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C01_handleWindows {




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
        //driver.close();
    }
    //1-amazon ana sayfaya gidip
    //2 nutella icin arama yaptirin
    @Test
    public void test01() {
        driver.get("https://www.amazon.com");
        String ilkSayfaHandledegeri=driver.getWindowHandle();

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nutella"+ Keys.ENTER);

        /*
        CDwindow-4BE8BC04947F879446F3C4C7D81C1341
        Bu kod acilan sayfanin unique hash kodudur
        Selenium sayfalar arasi geciste bu window handle degerini kullanir

        eger sayfalar arasi driver imizi gezdiriyorsak ve herhangi bir sayfadan
        suanda bulundugumuz sayfaya gecmek istiyorsak

                driver.switchTo().window("CDwindow-4BE8BC04947F879446F3C4C7D81C1341");
        bu sayfanin wndow handle degerini girerek bu sayfaya gecisi saglayabiliriz
         */
        //3-ilk urunun resmini tiklayarak farkli bir tab olarak acin

        WebElement ilkUrunResmi=driver.findElement(By.xpath("(//img[@class='s-image'])[1]"));
         driver.switchTo().newWindow(WindowType.TAB);
         /*bu komutu kullandigimizda driber otomatik olarak olusturulan
         new tab'a gecer
         yeni tab'da gorevi gerceklestirmek icin
         adimlari bastan almamiz gerekir
          */
        driver.get("https://www.amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nutella"+ Keys.ENTER);
        driver.findElement(By.xpath("(//img[@class='s-image'])[1]"));

        //4-yeni tab da acilan urunun fiyatini yazin
        WebElement urunTitleElementi=driver.findElement(By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']"));
        System.out.println(urunTitleElementi.getText());
        System.out.println(driver.getCurrentUrl());

        //ilk sayfaya gecipurl'yi yazdiralim
        driver.switchTo().window(ilkSayfaHandledegeri);
        System.out.println(driver.getCurrentUrl());
    }
}