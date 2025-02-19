package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

public class EmptyCartTest extends BaseTest {

    @Test
    public void testEmptyCart() {
        driver.get("https://magento.softwaretestingboard.com/");

        driver.findElement(By.cssSelector(".action.showcart")).click();

        while (driver.findElements(By.cssSelector(".cart-table .item")).size() > 0) {
            driver.findElement(By.cssSelector(".action.delete")).click();
        }

        Assert.assertTrue(driver.getPageSource().contains("You have no items in your shopping cart."));
    }
}
