package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WishlistPage {
    WebDriver driver;
    WebDriverWait wait;


    private By wishlistIcons = By.cssSelector(".product-item .actions-secondary a.action.towishlist");
    private By successMessage = By.cssSelector(".message-success");
    private By wishlistLink = By.linkText("My Wish List");
    private By wishlistItems = By.cssSelector(".product-item");

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void addFirstProductToWishlist() {
        List<WebElement> icons = driver.findElements(wishlistIcons);
        if (!icons.isEmpty()) {
            WebElement firstProduct = icons.get(0);
            try {
                firstProduct.click();
            } catch (Exception e) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", firstProduct);
            }
        }
    }


    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }


    public void openWishlist() {
        driver.findElement(wishlistLink).click();
    }


    public int getWishlistCount() {
        return driver.findElements(wishlistItems).size();
    }
}