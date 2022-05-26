package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.addressbok.model.PersonData;

import java.util.Comparator;
import java.util.List;

public class PersonCreationTest extends TestBase{

    @Test
    public void testPersonCreation() throws Exception {
        app.goTo().homePage();
        List<PersonData> before = app.person().list();
        PersonData person = new PersonData()
                .withFirstName("FirstName")
                .withMiddleName("MiddleName")
                .withLastName("LastName")
                .withAddress("Address")
                .withPhone("12345789")
                .withMail("test@test.test");
        app.person().createPerson(person);
        app.goTo().homePage();
        List<PersonData> after = app.person().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(person);
        Comparator<? super PersonData> byId = (p1, p2) -> Integer.compare(p1.getId(), p2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
