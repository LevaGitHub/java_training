package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbok.model.PersonData;
import ru.stqa.addressbok.model.Persons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Persons before = app.person().all();
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
        Persons after = app.person().all();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifiedPerson).withAdded(person)));
    }

}
