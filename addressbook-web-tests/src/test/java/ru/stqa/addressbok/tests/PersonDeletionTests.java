package ru.stqa.addressbok.tests;

import org.testng.annotations.*;
import ru.stqa.addressbok.model.GroupData;
import ru.stqa.addressbok.model.PersonData;

public class PersonDeletionTests extends TestBase{

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
        app.getPersonHelper().selectPerson();
        app.getPersonHelper().deleteSelectedPerson();
        app.getPersonHelper().confirmDeletePerson();
    }
}
