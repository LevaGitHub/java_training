package ru.stqa.addressbok.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.addressbok.appmanager.ApplicationManager;
import ru.stqa.addressbok.model.PersonData;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();
    private WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

}
