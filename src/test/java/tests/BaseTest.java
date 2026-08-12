package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.ConfigReader;
import utils.DriverManager;

/**
 * Parent class for all test classes: opens a fresh browser + home page
 * before each test method, and quits the browser afterwards so tests
 * remain independent of one another.
 */
public class BaseTest {

    protected HomePage homePage;

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver();
        DriverManager.getDriver().get(ConfigReader.getBaseUrl());
        homePage = new HomePage(DriverManager.getDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
