package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FiltersPage;
import pages.LoginPage;
import utils.BaseTest;
import java.time.Duration;
import java.util.List;

public class FiltersTest extends BaseTest {

    @Test
    public void testFiltersAndAddToCart() {
        driver.get("https://magento.softwaretestingboard.com/");

        driver.findElement(By.linkText("Sign In")).click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("cate.b@gmail.com", "Root4321");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Women"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tops"))).click();

        FiltersPage filtersPage = new FiltersPage(driver);

        WebElement colorCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-role='title' and text()='Color']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", colorCategory);

        WebElement blackColor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[aria-label='Black']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", blackColor);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-item")));

        WebElement priceCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-role='title' and text()='Price']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", priceCategory);

        WebElement priceFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href*='price=50-60']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", priceFilter);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-item")));

        List<WebElement> filteredProducts = filtersPage.getFilteredProducts();
        Assert.assertFalse(filteredProducts.isEmpty(), "Nuk u gjetën produkte pas filtrimit!");



    }
}
