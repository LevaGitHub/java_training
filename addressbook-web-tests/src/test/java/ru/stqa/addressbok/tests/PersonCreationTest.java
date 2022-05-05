package ru.stqa.addressbok.tests;

import org.testng.annotations.*;
import ru.stqa.addressbok.model.PersonData;

public class PersonCreationTest extends TestBase{

    @Test
    public void testPersonCreation() throws Exception {
        app.getPersonHelper().initPersonCreation();
        app.getPersonHelper().fillPersonData(new PersonData(
                "FirstName",
                "MiddleName",
                "LastName",
                "Address",
                "12345789",
                "test@test.test"));
        app.getPersonHelper().submitPersonCreation();
        app.getNavigationHelper().goToPersonHomePage();
    }


}
