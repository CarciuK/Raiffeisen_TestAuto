package Tests;

import com.testframework.baseBrowser;
import org.SwagLabsPages.LogInPage;
import org.SwagLabsPages.ProductsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ProductsTests extends baseBrowser {


    @Test
    void accessingProducts_content(){
        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LogInPage logInPage = new LogInPage(driver);
        logInPage.userLogInSuccessful();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.productAccessibility();

        String expectedTitle1 = "Sauce Labs Backpack";
        WebElement productTitle1 = driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']"));
        String actualTitle1 = productTitle1.getText();
        Assertions.assertEquals(expectedTitle1, actualTitle1);
    }

    @Test
    void productsAccessibility(){

        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LogInPage logInPage = new LogInPage(driver);
        logInPage.userLogInSuccessful();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.accessAllProducts();

    }

    @Test
    void addingProductToCart(){

        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LogInPage logInPage = new LogInPage(driver);
        logInPage.userLogInSuccessful();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemToCart();

        String expectedProduct = "Sauce Labs Backpack";
        WebElement productTitle = driver.findElement(By.xpath("//div[@class=\"inventory_item_name\"]"));
        String actualProduct = productTitle.getText();
        Assertions.assertEquals(expectedProduct, actualProduct);

     }

    @Test
    void removingProductFromCart(){

        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LogInPage logInPage = new LogInPage(driver);
        logInPage.userLogInSuccessful();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemToCart();

        productsPage.removeItem();

        WebElement itemInBasket = null;
        try {
            itemInBasket = driver.findElement(By.xpath("//div[@class=\"inventory_item_name\"]"));
            }
        catch (org.openqa.selenium.NoSuchElementException e) {
            }
        Assertions.assertNull(itemInBasket, "Item is still in the basket.");


    }

     @Test
    void purchaseProduct(){
         driver.get(Baseurl);
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

         LogInPage logInPage = new LogInPage(driver);
         logInPage.userLogInSuccessful();

         ProductsPage productsPage = new ProductsPage(driver);
         productsPage.addItemToCart();

         productsPage.purchaseItem();

         String expectedMsg = "Thank you for your order!";
         WebElement errorMessageElement = driver.findElement(By.xpath("//h2[@class=\"complete-header\"]"));
         String actualMsg = errorMessageElement.getText();
         Assertions.assertEquals(expectedMsg, actualMsg);
     }



}