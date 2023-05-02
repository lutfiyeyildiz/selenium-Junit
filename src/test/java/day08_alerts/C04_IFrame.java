package day08_alerts;

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

public class C04_IFrame {

  //  ● Bir class olusturun: IframeTest

    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }
    @After
    public void tearDown(){
        //driver.close();
    }
    @Test
    public void test01(){
        //● https://the-internet.herokuapp.com/iframe adresine gidin.
        driver.get("https://the-internet.herokuapp.com/iframe");
        //
        //● Bir metod olusturun: iframeTest
        //
        //            ○ “An IFrame containing....” textinin erisilebilir oldugunu test edin ve konsolda yazdirin.
        WebElement baslikElementi=driver.findElement(By.xpath("//h3"));
        Assert.assertTrue(baslikElementi.isEnabled());
        System.out.println(baslikElementi.getText());



        //
        //            ○ Text Box’a “Merhaba Dunya!” yazin.
        WebElement iFrameElementi=driver.findElement(By.id("mce_0_ifr"));

        driver.switchTo().frame(iFrameElementi);
        WebElement textKutusu=driver.findElement(By.xpath("//*[text()='Your content goes here.']"));
        textKutusu.clear();
        textKutusu.sendKeys("merhaba yazilim");
        //
        //            ○ TextBox’in altinda bulunan “Elemental Selenium” linkini textinin gorunur oldugunu
        //    dogrulayin ve konsolda yazdirin.
        driver.switchTo().defaultContent();
        WebElement linkYaziElementi=driver.findElement(By.linkText("Elemental Selenium"));
        Assert.assertTrue(linkYaziElementi.isDisplayed());
        System.out.println(linkYaziElementi.getText());


    }
}
