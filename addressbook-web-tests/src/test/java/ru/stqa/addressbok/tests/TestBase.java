package ru.stqa.addressbok.tests;

import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.addressbok.appmanager.ApplicationManager;
import ru.stqa.addressbok.model.GroupData;
import ru.stqa.addressbok.model.Groups;
import ru.stqa.addressbok.model.PersonData;
import ru.stqa.addressbok.model.Persons;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    private WebDriver wd;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + "with parameters" + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m){
        logger.info("Stop test " + m.getName());
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g)-> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyPersonListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Persons dbPersons = app.db().persons();
            Persons uiPersons = app.person().all();
            assertThat(uiPersons, Matchers.equalTo(dbPersons.stream()
                    .map((p)-> new PersonData()
                            .withId(p.getId())
                            .withFirstName(p.getFirstName())
                            .withLastName(p.getLastName())
                            .withAddress(p.getAddress())
                    )
                    .collect(Collectors.toSet())));
        }
    }
}
