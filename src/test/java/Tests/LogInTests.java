package Tests;

import com.testframework.baseBrowser;
import org.SwagLabsPages.LogInPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class LogInTests extends baseBrowser {

    @Test
    void loginAccount_successful(){

        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LogInPage logInPage = new LogInPage(driver);
        logInPage.userLogInSuccessful();

        Assertions.assertTrue(logInPage.isLoggedIn());
    }

    @Test
    void loginAccount_unsuccessful(){
        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LogInPage logInPage = new LogInPage(driver);
        logInPage.userLogInUnsuccessful();

        String expectedMsg = "Epic sadface: Sorry, this user has been locked out.";
        WebElement errorMessageElement = driver.findElement(By.className("error-message-container"));
        String actualMsg = errorMessageElement.getText();
        Assertions.assertEquals(expectedMsg, actualMsg);
    }

    @Test
    void loginAccount_unsuccessful_emptyField(){
        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LogInPage logInPage = new LogInPage(driver);
        logInPage.userLogInUnsuccessfulEmptyFields();

        String expectedMsg = "Epic sadface: Username is required";
        WebElement errorMessageElement = driver.findElement(By.xpath("//h3[@data-test='error']"));
        String actualMsg = errorMessageElement.getText();
        Assertions.assertEquals(expectedMsg, actualMsg);

    }

    @Test
    void logOut_successful(){
        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LogInPage logInPage = new LogInPage(driver);
        logInPage.userLogInSuccessful();
        logInPage.userLogOut();

        Assertions.assertTrue(logInPage.isLoggedOut());
    }

}
