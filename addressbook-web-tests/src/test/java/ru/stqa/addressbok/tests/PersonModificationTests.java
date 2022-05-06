package ru.stqa.addressbok.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbok.model.PersonData;

public class PersonModificationTests extends TestBase {
    @Test
    public void testPersonDeletion(){
        app.getNavigationHelper().goToPersonHomePage();
        app.getPersonHelper().initPersonModification(4);
        app.getPersonHelper().fillPersonData(new PersonData(
                "FirstNameM",
                "MiddleNameM",
                "LastNameM",
                "AddressM",
                "123457890",
                "test@test.testM"));
        app.getPersonHelper().submitPersonModification();
        app.getNavigationHelper().goToPersonHomePage();
    }
}
