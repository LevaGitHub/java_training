package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.addressbok.model.PersonData;

import java.util.List;

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
        List<PersonData> before = app.getPersonHelper().getPersonList();
        app.getPersonHelper().selectPerson(before.size()-1);
        app.getPersonHelper().deleteSelectedPerson();
        app.getPersonHelper().confirmDeletePerson();
        app.getNavigationHelper().goToPersonHomePage();
        List<PersonData> after = app.getPersonHelper().getPersonList();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
