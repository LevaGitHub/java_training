package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbok.model.PersonData;

import java.util.Comparator;
import java.util.List;

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
        List<PersonData> before = app.getPersonHelper().getPersonList();
        app.getPersonHelper().selectPerson(before.size()-1);
        app.getPersonHelper().initPersonModification(String.valueOf(before.get(before.size() - 1).getId()));
        PersonData person = new PersonData(
            "FirstNameM",
            "MiddleNameM",
            "LastNameM",
            "AddressM",
            "123457890",
            "test@test.testM"
        );
        app.getPersonHelper().fillPersonData(person);
        app.getPersonHelper().submitPersonModification();
        app.getNavigationHelper().goToPersonHomePage();

        List<PersonData> after = app.getPersonHelper().getPersonList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size()-1);
        before.add(person);
        Comparator<? super PersonData> byId = (p1, p2) -> Integer.compare(p1.getId(), p2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
