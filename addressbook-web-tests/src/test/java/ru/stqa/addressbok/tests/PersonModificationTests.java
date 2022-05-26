package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbok.model.PersonData;

import java.util.Set;

public class PersonModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.person().all().size() == 0){
            app.person().createPerson(new PersonData()
                    .withFirstName("FirstName")
                    .withMiddleName("MiddleName")
                    .withLastName("LastName")
                    .withAddress("Address")
                    .withPhone("12345789")
                    .withMail("test@test.test"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testPersonModification(){
        Set<PersonData> before = app.person().all();
        PersonData modifiedPerson = before.iterator().next();
        app.person().selectById(modifiedPerson.getId());
        PersonData person = new PersonData()
                .withId(modifiedPerson.getId())
                .withFirstName("FirstNameM")
                .withMiddleName("MiddleNameM")
                .withLastName("LastNameM")
                .withAddress("AddressM")
                .withPhone("123457890")
                .withMail("test@test.testM");
        app.person().modify(person);
        app.goTo().homePage();
        Set<PersonData> after = app.person().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(modifiedPerson);
        before.add(person);
        Assert.assertEquals(before, after);
    }

}
