package day09_handleWindows_testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class C03_windowHandles {

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


    @Test
    public void test01() {

//● Tests package’inda yeni bir class olusturun: WindowHandle2
//● https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");
//● Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        WebElement sayfadakiYaziElementi = driver.findElement(By.xpath("//h3"));
        String expectedYazi = "Opening a new window";
        String actualYazi = sayfadakiYaziElementi.getText();
        Assert.assertEquals(expectedYazi, actualYazi);
//● Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        String expectedtitle = "The Internet";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedtitle, actualTitle);

        /*
        eger kontrolsuz acilan bir tab veya window varsa
        o zaman sayfalarin window handle degerlerini elde etmemgerekir
        oncelikle 2.sayfaacilmadan once
        ilk sayfanin window handle degerini bir stringe atayalim
         */
        String ilkSayfaWindowhandleDegeri = driver.getWindowHandle();

//● Click Here butonuna basın.
        driver.findElement(By.linkText("Click Here")).click();

        String expectedIkinciTitle = "New Window";
        String actualIkinciTitle = driver.getTitle();
        Assert.assertEquals(expectedIkinciTitle,actualIkinciTitle);

        /*swichTo().newWindow() demeden linki tiklayarak yeni tab veya window olustugunda
         biz driver'a yeni sayfaya gec demedikce,driver eski sayfada kalir
         ve yeni sayfa ile ilgili hicbir islem yapamaz
         yeni sayfada driver'i calistirmak isterseniz
         once driver'i yeni sayfaya yollamalisiniz


         */

        /*
        yeni sayfaya gecebilmek icin oncelikleikinciSayfaWindowHandledegeri'ni bulmamiz gerekiyor
        driver.getwindowHandles(0 method'unu kullanarak acik olan tum sayfalarin window handle degerlerini alip bir set e assing ederiz
         */

        Set<String> windowHandleseti = driver.getWindowHandles();
        System.out.println(windowHandleseti);
        String ikinciSayfaWindowHandleDeger = "";
        for (String each : windowHandleseti
        ) {
            if (!each.equals(ilkSayfaWindowhandleDegeri)) {

                ikinciSayfaWindowHandleDeger = each;
            }
            //● Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
        }

//● Sayfadaki textin “New Window” olduğunu doğrulayın.
        WebElement ikinciYaziElementi=driver.findElement(By.xpath("//h3"));
        String expectedIkinciYazi="New Window";
        String actualikinciYazi=ikinciYaziElementi.getText();
        Assert.assertEquals(expectedIkinciYazi,actualikinciYazi);
//● Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu doğrulayın.
        driver.switchTo().window(ikinciSayfaWindowHandleDeger);
       expectedIkinciTitle = "The Internet";
       actualIkinciTitle = driver.getTitle();
       Assert.assertEquals(expectedtitle,actualTitle);


    }
}