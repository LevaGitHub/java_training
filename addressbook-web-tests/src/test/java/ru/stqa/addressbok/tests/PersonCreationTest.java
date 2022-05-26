package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.addressbok.model.PersonData;
import java.util.Set;

public class PersonCreationTest extends TestBase{

    @Test
    public void testPersonCreation() throws Exception {
        app.goTo().homePage();
        Set<PersonData> before = app.person().all();
        PersonData person = new PersonData()
                .withFirstName("FirstName")
                .withMiddleName("MiddleName")
                .withLastName("LastName")
                .withAddress("Address")
                .withPhone("12345789")
                .withMail("test@test.test");
        app.person().createPerson(person);
        app.goTo().homePage();
        Set<PersonData> after = app.person().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        person.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(person);
        Assert.assertEquals(before, after);
    }


}
