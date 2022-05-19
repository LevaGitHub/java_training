package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.addressbok.model.PersonData;

import java.util.Comparator;
import java.util.List;

public class PersonCreationTest extends TestBase{

    @Test
    public void testPersonCreation() throws Exception {
        app.getNavigationHelper().goToPersonHomePage();
        List<PersonData> before = app.getPersonHelper().getPersonList();
        PersonData person = new PersonData(
                "FirstName",
                "MiddleName",
                "LastName",
                "Address",
                "12345789",
                "test@test.test");
        app.getPersonHelper().createPerson(person);
        app.getNavigationHelper().goToPersonHomePage();
        List<PersonData> after = app.getPersonHelper().getPersonList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(person);
        Comparator<? super PersonData> byId = (p1, p2) -> Integer.compare(p1.getId(), p2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
