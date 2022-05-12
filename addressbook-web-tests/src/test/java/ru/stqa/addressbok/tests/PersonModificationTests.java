package ru.stqa.addressbok.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbok.model.PersonData;

public class PersonModificationTests extends TestBase {
    @Test
    public void testPersonDeletion(){
        app.getNavigationHelper().goToPersonHomePage();
        if (! app.getPersonHelper().isThereAPerson()){
            app.getPersonHelper().createPerson(new PersonData(
                    "FirstName",
                    "MiddleName",
                    "LastName",
                    "Address",
                    "12345789",
                    "test@test.test"));
            app.getNavigationHelper().goToPersonHomePage();
        }
        String PersonStringId = app.getPersonHelper().getPersonStringId();
        app.getPersonHelper().initPersonModification(PersonStringId);
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
