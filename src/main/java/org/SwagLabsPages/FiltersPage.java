package org.SwagLabsPages;

import com.testframework.baseBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.System.in;

public class FiltersPage extends baseBrowser {

    public FiltersPage (WebDriver driver){
        this.driver = driver;
    }


    private WebElement filterMenu(){return driver.findElement(By.className("product_sort_container"));}
    private WebElement filterZtoA(){return driver.findElement(By.xpath("//option[@value='za']"));}
    private WebElement filterLowToHigh(){return driver.findElement(By.xpath("//option[@value='lohi']"));}
    private WebElement filterHighToLow(){return driver.findElement(By.xpath("//option[@value='hilo']"));}

    public void filterAccess() {
        String[] dValues = {"az", "za", "lohi", "hilo"};

        for (var dvalue : dValues) {
            Select dpFilter = new Select(driver.findElement(By.className("product_sort_container")));
            dpFilter.selectByValue(dvalue);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_list")));

            List<Double> productPrices = getProductPrices(driver);

            boolean isSortedHighToLow = isListSortedHighToLow(productPrices);

            if (isSortedHighToLow) {
                System.out.println("Prices are sorted from high to low after selecting option: " + dvalue);
            } else {
                System.out.println("Prices are NOT sorted from high to low after selecting option: " + dvalue);
            }
        }


        List<WebElement> allOptions = new ArrayList<>();
        try {
            allOptions = new Select(driver.findElement(By.className("product_sort_container"))).getOptions();
        } catch (Exception e) {
            System.out.println("Dropdown element not found.");
        }

//        // Make sure you found the right number of elements
//        if (dValues.length != allOptions.size()) {
//            System.out.println("Fail, wrong number of elements found");
//        }


        for (int i = 0; i < dValues.length; i++) {
            String optionValue = allOptions.get(i).getAttribute("value");
            if (optionValue.equals(dValues[i])) {
                System.out.println("Passed on: " + optionValue);
            } else {
                System.out.println("Failed on: " + optionValue);
            }
        }
    }

    private static List<Double> getProductPrices(WebDriver driver) {
        List<WebElement> priceElements = driver.findElements(By.cssSelector(".product-price"));
        List<Double> prices = new ArrayList<>();

        for (WebElement priceElement : priceElements) {
            String priceStr = priceElement.getText().replaceAll("[^0-9.]", "");

            double price = Double.parseDouble(priceStr);
            prices.add(price);
        }

        return prices;
    }

    private static boolean isListSortedHighToLow(List<Double> list) {
        List<Double> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList, Collections.reverseOrder());
        return list.equals(sortedList);
    }





}
