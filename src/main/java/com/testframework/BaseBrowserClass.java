package com.testframework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import static com.testframework.DriverFactory.getChromeDriver;

public class BaseBrowserClass {

    public static final String Baseurl = "https://www.saucedemo.com/"; //https://c24test.raiffeisen.al/Retail/home/login
    public static WebDriver driver;

    @BeforeEach
    void setup(){
        driver = getChromeDriver();

    }

    @AfterEach
    void cleanup(){driver.close();
    }
}
