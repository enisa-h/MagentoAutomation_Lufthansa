package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WishlistPage;
import pages.LoginPage;
import utils.BaseTest;

import java.time.Duration;

public class WishlistTest extends BaseTest {

    @Test
    public void testWishlistFunctionality() {
        driver.get("https://magento.softwaretestingboard.com/");

        driver.findElement(By.linkText("Sign In")).click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("cate.b@gmail.com", "Root4321");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".customer-welcome")));

        driver.get("https://magento.softwaretestingboard.com/");

        WishlistPage wishlistPage = new WishlistPage(driver);
        wishlistPage.addFirstProductToWishlist();

        Assert.assertTrue(wishlistPage.isSuccessMessageDisplayed(), "Produkti nuk u shtua në Wish List!");

        driver.findElement(By.cssSelector(".customer-welcome")).click();
        driver.findElement(By.linkText("My Wish List")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-item")));
        int wishListCount = wishlistPage.getWishlistCount();
        Assert.assertTrue(wishListCount > 0, "Wish List është bosh!");
    }
}