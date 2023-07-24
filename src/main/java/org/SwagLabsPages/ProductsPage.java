package org.SwagLabsPages;

import com.testframework.BaseBrowserClass;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage extends BaseBrowserClass {

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }


    private WebElement SauceLabs_Backpack_item() {return driver.findElement(By.id("item_4_title_link"));}

    private WebElement Backpack_AddToCartBTN() {return driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));}

    private WebElement SauceLabs_BikeLight_item() {return driver.findElement(By.id("item_0_title_link"));}

    private WebElement Bikelight_AddToCartBTN() {return driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));}

    //the rest of products
    // private WebElement (){return driver.findElement(By.id(""));}
    private WebElement Backpack_RemoveBTN() {return driver.findElement(By.id("remove-sauce-labs-backpack"));}

    private WebElement Shopping_Cart() {return driver.findElement(By.id("shopping_cart_container"));}
    private WebElement ContinueBTN() {return driver.findElement(By.id("continue"));}

    private WebElement CheckoutBTN() {return driver.findElement(By.id("checkout"));}

    private WebElement getFirstName() {return driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/form/div[1]/div[1]/input"));}

    private WebElement getLastName() {return driver.findElement(By.id("last-name"));}

    private WebElement getZIPCode() {return driver.findElement(By.id("postal-code"));}

    private WebElement CancelBTN() {return driver.findElement(By.id("cancel"));}

    private WebElement FinishBTN() {return driver.findElement(By.id("finish"));}

    private WebElement ContinueShoppingBTN() {return driver.findElement(By.id("continue-shopping"));}


    public void Product_Accessibility() {

        SauceLabs_Backpack_item().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_item_container")));

    }


    public void Add_Item_To_Cart() {

        Backpack_AddToCartBTN().click();

        Shopping_Cart().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_contents_container")));

    }

    public void Remove_Item() {

        Backpack_RemoveBTN().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void Purchase_Item(){

        CheckoutBTN().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_container\"]/div[2]")));

        getFirstName().sendKeys("Krista");
        getLastName().sendKeys("Krista");
        getZIPCode().sendKeys("1010");

        ContinueBTN().click();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_summary_container")));

        FinishBTN().click();

    }













}




