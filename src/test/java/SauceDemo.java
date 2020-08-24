import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

import java.util.List;

public class SauceDemo {
    WebDriver driver;
    private static final Logger logger =  Logger.getLogger(SauceDemo.class);
    String productName, productPrice;

    @Test
    public void SauceDemo() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/index.html");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Get parent element
        WebElement parentElement = driver.findElement(By.className("inventory_list"));
        // Get child elements of parent
        List<WebElement> childrenElements = parentElement.findElements(By.className("inventory_item"));

        for(int i = 0; i < childrenElements.size(); i++) {
            productName = childrenElements.get(i).findElement(By.className("inventory_item_label")).
                    findElement(By.tagName("a")).findElement(By.className("inventory_item_name")).getText();
            productPrice = childrenElements.get(i).findElement(By.className("pricebar")).
                    findElement(By.className("inventory_item_price")).getText();

            logger.info(productName + " : " + productPrice);
        }
    }
}
