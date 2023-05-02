package day10_actions;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

public class C02_Actions extends TestBase {

    @Test
    public void test01() throws InterruptedException {
        //amazon anasayfaya gidip
        //account menusunden create a list linkine tiklayalim
        driver.get("https://www.amazon.com");
        Actions actions=new Actions(driver);

        WebElement accountLinki=driver.findElement(By.id("nav-link-accountList-nav-line-1"));
        actions.moveToElement(accountLinki).perform();

        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[text()='Create a List']")).click();
    }


}
