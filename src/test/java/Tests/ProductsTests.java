package Tests;

import com.testframework.BaseBrowserClass;
import org.SwagLabsPages.LogInPage;
import org.SwagLabsPages.ProductsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class ProductsTests extends BaseBrowserClass {


    @Test
    void Accessing_Products_Content(){
        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS);

        LogInPage logInPage = new LogInPage(driver);
        logInPage.UserLogIn_Successful();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.Product_Accessibility();

        String expectedTitle1 = "Sauce Labs Backpack";
        WebElement productTitle1 = driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']"));
        String actualTitle1 = productTitle1.getText();
        Assertions.assertEquals(expectedTitle1, actualTitle1);
    }

    @Test
    void Products_Accessibility(){

        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        LogInPage logInPage = new LogInPage(driver);
        logInPage.UserLogIn_Successful();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.Access_All_Products();

        //assert here

    }

    @Test
    void Adding_Product_To_Cart(){

        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS);

        LogInPage logInPage = new LogInPage(driver);
        logInPage.UserLogIn_Successful();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.Add_Item_To_Cart();

        String expectedProduct = "Sauce Labs Backpack";
        WebElement productTitle = driver.findElement(By.xpath("//div[@class=\"inventory_item_name\"]"));
        String actualProduct = productTitle.getText();
        Assertions.assertEquals(expectedProduct, actualProduct);

     }

    @Test
    void Removing_Product_From_Cart(){

        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS);

        LogInPage logInPage = new LogInPage(driver);
        logInPage.UserLogIn_Successful();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.Add_Item_To_Cart();

        productsPage.Remove_Item();

        WebElement itemInBasket = null;
        try {
            itemInBasket = driver.findElement(By.xpath("//div[@class=\"inventory_item_name\"]"));
            }
        catch (org.openqa.selenium.NoSuchElementException e) {
            }
        Assertions.assertNull(itemInBasket, "Item is still in the basket.");


    }

     @Test
    void Purchase_Product(){
         driver.get(Baseurl);
         driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS);

         LogInPage logInPage = new LogInPage(driver);
         logInPage.UserLogIn_Successful();

         ProductsPage productsPage = new ProductsPage(driver);
         productsPage.Add_Item_To_Cart();

         productsPage.Purchase_Item();

         String expectedMsg = "Thank you for your order!";
         WebElement errorMessageElement = driver.findElement(By.xpath("//h2[@class=\"complete-header\"]"));
         String actualMsg = errorMessageElement.getText();
         Assertions.assertEquals(expectedMsg, actualMsg);
     }



}