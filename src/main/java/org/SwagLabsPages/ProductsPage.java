package org.SwagLabsPages;

import com.testframework.baseBrowser;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class ProductsPage extends baseBrowser {

    public ProductsPage(WebDriver driver) {

        this.driver = driver;
    }


    private WebElement saucelabsBackpackItem() {
        return driver.findElement(By.id("item_4_title_link"));
    }

    private WebElement backpackAddToCartBTN() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
    }

    private WebElement sauceLabsBikeLightItem() {
        return driver.findElement(By.id("item_0_title_link"));
    }

    private WebElement sauceLabsBoltTshirtItem() {
        return driver.findElement(By.id("item_1_title_link"));
    }

    private WebElement bikelightAddToCartBTN() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
    }

    //the rest of products
    private WebElement backpackRemoveBTN() {
        return driver.findElement(By.id("remove-sauce-labs-backpack"));
    }

    private WebElement shoppingCart() {
        return driver.findElement(By.id("shopping_cart_container"));
    }

    private WebElement continueBTN() {
        return driver.findElement(By.id("continue"));
    }

    private WebElement checkoutBTN() {
        return driver.findElement(By.id("checkout"));
    }

    private WebElement getFirstName() {
        return driver.findElement(By.xpath("//input[@id='first-name']"));
    }

    private WebElement getLastName() {
        return driver.findElement(By.id("last-name"));
    }

    private WebElement getZIPCode() {
        return driver.findElement(By.id("postal-code"));
    }

    private WebElement cancelBTN() {
        return driver.findElement(By.id("cancel"));
    }

    private WebElement finishBTN() {
        return driver.findElement(By.id("finish"));
    }

    private WebElement continueShoppingBTN() {
        return driver.findElement(By.id("continue-shopping"));
    }


    public void productAccessibility() {

        saucelabsBackpackItem().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_item_container")));

    }


    public void addItemToCart() {

        backpackAddToCartBTN().click();
        shoppingCart().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_contents_container")));

    }

    public void removeItem() {

        backpackRemoveBTN().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void purchaseItem() {

        checkoutBTN().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_container\"]/div[2]")));

        getFirstName().sendKeys("Krista");
        getLastName().sendKeys("Krista");
        getZIPCode().sendKeys("1010");

        continueBTN().click();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_summary_container")));

        finishBTN().click();

    }

    public void accessAllProducts_Displayed(){

        WebElement[] products = {saucelabsBackpackItem(),sauceLabsBikeLightItem()};

        for (WebElement product : products) {

            WebElement productElement = driver.findElement(By.className("inventory_item_name"));
            productElement.click();


            WebElement productDetails = driver.findElement(By.className("inventory_details_desc_container"));
            assert productDetails.isDisplayed();

            driver.navigate().back();
        }
    }


    /////////////////////new works


    public void accessAllProducts() {
        List<Product> expectedProducts = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/test/data/products.txt"));
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String description = parts[1].trim();
                    String price = parts[2].trim();
                    expectedProducts.add(new Product(name, description, price));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String[] productNames = {
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)"
        };

        String[] productDescriptions = {
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                "A red light isn't the desired state in testing but it sure is attractive",
                "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.",
                "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.",
                "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.",
                "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton."
        };

        String[] productPrices = {
                "$29.99",
                "$9.99",
                "$15.99",
                "$49.99",
                "$7.99",
                "15.99"
        };

        for (int i = 0; i < productNames.length; i++) {
            String expectedName = productNames[i];
            String expectedDescription = productDescriptions[i];
            String expectedPrice = productPrices[i];

            WebElement productElement = driver.findElement(By.xpath("//div[text()='" + expectedName + "']"));
            productElement.click();

            WebElement productNameElement = driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']"));
            String actualName = productNameElement.getText();
            WebElement productDescriptionElement = driver.findElement(By.xpath("//div[@class='inventory_details_desc large_size']"));
            String actualDescription = productDescriptionElement.getText();
            WebElement productPriceElement = driver.findElement(By.xpath("//div[@class='inventory_details_price']"));
            String actualPrice = productPriceElement.getText();

            Product actualProduct = new Product(actualName, actualDescription, actualPrice);

            if (expectedProducts.contains(actualProduct)) {
                System.out.println("Product content matches the text file");
            } else {
                System.out.println("Product content does not match the text file");
            }

            driver.navigate().back();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.className("inventory_item_name")));
        }
    }

////////

    class Product {
        private String name;
        private String description;
        private String price;

        public Product(String name, String description, String price) {
            this.name = name;
            this.description = description;
            this.price = price;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Product product = (Product) obj;
            return name.equals(product.name) && description.equals(product.description) && price.equals(product.price);
        }
    }


//        public void accessAllProducts() {
//
//        List<Product> expectedProducts = new ArrayList<>();
//        try {
//            List<String> lines = Files.readAllLines(Paths.get("src/test/data/products.txt"));
//            for (String line : lines) {
//                String[] parts = line.split(";");
//                if (parts.length == 3) {
//                    String name = parts[0].trim();
//                    String description = parts[1].trim();
//                    String price = parts[2].trim();
//                    expectedProducts.add(new Product(name, description, price));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//
//        WebElement[] products = {saucelabsBackpackItem(),sauceLabsBikeLightItem(), sauceLabsBoltTshirtItem()};
//
//        for (int i = 0; i < products.length; i++) {
//            WebElement productElement = products[i];
//            productElement.click();
//
//
//            WebElement productNameElement = driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']"));
//            String actualName = productNameElement.getText();
//            WebElement productDescriptionElement = driver.findElement(By.xpath("//div[@class='inventory_details_desc large_size']"));
//            String actualDescription = productDescriptionElement.getText();
//            WebElement productPriceElement = driver.findElement(By.xpath("//div[@class='inventory_details_price']"));
//            String actualPrice = productPriceElement.getText();
//
//
//            Product actualProduct = new Product(actualName, actualDescription, actualPrice);
//
//            if (actualProduct.equals(expectedProducts.get(i))) {
//                System.out.println("Product " + (i + 1) + " content matches the text file.");
//            } else {
//                System.out.println("Product " + (i + 1) + " content does not match the text file.");
//            }
//
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//            driver.navigate().back();
//
//        }
//    }
/////////////////////////////////////////////////////////////method 2 works

//    public void accessAllProducts() {
//        List<Product> expectedProducts = new ArrayList<>();
//        try {
//            List<String> lines = Files.readAllLines(Paths.get("src/test/data/products.txt"));
//            for (String line : lines) {
//                String[] parts = line.split(";");
//                if (parts.length == 3) {
//                    String name = parts[0].trim();
//                    String description = parts[1].trim();
//                    String price = parts[2].trim();
//                    expectedProducts.add(new Product(name, description, price));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        WebElement[] products = {saucelabsBackpackItem(), sauceLabsBikeLightItem(), sauceLabsBoltTshirtItem()};
//
//        for (WebElement productElement : products) {
//            productElement.click();
//
//            WebElement productNameElement = driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']"));
//            String actualName = productNameElement.getText();
//            WebElement productDescriptionElement = driver.findElement(By.xpath("//div[@class='inventory_details_desc large_size']"));
//            String actualDescription = productDescriptionElement.getText();
//            WebElement productPriceElement = driver.findElement(By.xpath("//div[@class='inventory_details_price']"));
//            String actualPrice = productPriceElement.getText();
//
//            Product actualProduct = new Product(actualName, actualDescription, actualPrice);
//            boolean productFound = false;
//
//            for (Product expectedProduct : expectedProducts) {
//                if (actualProduct.equals(expectedProduct)) {
//                    System.out.println("Product content matches the text file");
//                    productFound = true;
//                    break;
//                }
//            }
//
//            if (!productFound) {
//                System.out.println("Product content does not match the text file ");
//            }
//
//
//            driver.navigate().back();
//
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//            wait.until(ExpectedConditions.elementToBeClickable(By.className("inventory_item_name")));
//
//
//        }
//    }
//
//







}










