package org.SwagLabsPages;

import com.testframework.baseBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInPage extends baseBrowser {
    public LogInPage(WebDriver driver) {

        this.driver = driver;
    }


    private WebElement userNameField(){return driver.findElement(By.id("user-name"));}
    private WebElement passWordField(){return driver.findElement(By.id("password"));}
    private WebElement logInBTN(){return driver.findElement(By.id("login-button"));}
    private WebElement burgerMenu(){return driver.findElement(By.id("react-burger-menu-btn"));}
    private WebElement logOutItem(){return driver.findElement(By.id("logout_sidebar_link"));}


   /*
    private WebElement userName(){return driver.findElement(By.id(""));}*/

    public void userLogInSuccessful() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_button_container")));

        userNameField().sendKeys("standard_user");
        passWordField().sendKeys("secret_sauce");

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        logInBTN().click();

        try {
            Thread.sleep(1000);
            }
        catch (InterruptedException e) {
            e.printStackTrace();
            }
    }

    public boolean isLoggedIn() {
        try {

            WebElement homeElement = driver.findElement(By.id("inventory_container"));
            return homeElement.isDisplayed();
            }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
            }
    }

    public void userLogInUnsuccessful() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_button_container")));

        userNameField().sendKeys("locked_out_user");
        passWordField().sendKeys("secret_sauce");

        logInBTN().click();
    }

    public void userLogInUnsuccessfulEmptyFields() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_button_container")));

        logInBTN().click();
    }

    public void userLogOut() {
        burgerMenu().click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        logOutItem().click();
    }
    public boolean isLoggedOut() {
        try {
            WebElement logInElement = driver.findElement(By.id("login_button_container"));
            return logInElement.isDisplayed();
            }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
            }
    }

}
