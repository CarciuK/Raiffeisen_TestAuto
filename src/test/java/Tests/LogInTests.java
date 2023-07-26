package Tests;

import com.testframework.BaseBrowserClass;
import org.SwagLabsPages.LogInPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class LogInTests extends BaseBrowserClass {

    @Test
    void Login_Account_Successful(){

        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS);

        LogInPage logInPage = new LogInPage(driver);
        logInPage.UserLogIn_Successful();

        Assertions.assertTrue(logInPage.isLoggedIn());
    }

    @Test
    void Login_Account_Unsuccessful(){
        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS);

        LogInPage logInPage = new LogInPage(driver);
        logInPage.UserLogIn_Unsuccessful();

        String expectedMsg = "Epic sadface: Sorry, this user has been locked out.";
        WebElement errorMessageElement = driver.findElement(By.className("error-message-container"));
        String actualMsg = errorMessageElement.getText();
        Assertions.assertEquals(expectedMsg, actualMsg);
    }

    @Test
    void Login_Account_Unsuccessful_EmptyField(){
        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        LogInPage logInPage = new LogInPage(driver);
        logInPage.UserLogIn_Unsuccessful_EmptyFields();

        String expectedMsg = "Epic sadface: Username is required";
        WebElement errorMessageElement = driver.findElement(By.xpath("//h3[@data-test='error']"));
        String actualMsg = errorMessageElement.getText();
        Assertions.assertEquals(expectedMsg, actualMsg);

    }

    @Test
    void LogOut_Successful(){
        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS);

        LogInPage logInPage = new LogInPage(driver);
        logInPage.UserLogIn_Successful();
        logInPage.UserLogOut();

        Assertions.assertTrue(logInPage.isLoggedOut());
    }

}
