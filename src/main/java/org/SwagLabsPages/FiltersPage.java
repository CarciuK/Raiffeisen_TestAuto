package org.SwagLabsPages;

import com.testframework.BaseBrowserClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FiltersPage extends BaseBrowserClass {

    public FiltersPage (WebDriver driver){
        this.driver = driver;
    }


    private WebElement FilterMenu (){return driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));}
    private WebElement Filter_ZtoA (){return driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[2]"));}

    public void Filter_Access (){

        FilterMenu().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        Filter_ZtoA().click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));



    }








}
