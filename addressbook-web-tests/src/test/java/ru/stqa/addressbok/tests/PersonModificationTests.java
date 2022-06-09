package ru.stqa.addressbok.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbok.model.PersonData;
import ru.stqa.addressbok.model.Persons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().persons().size() == 0){
            app.goTo().homePage();
            app.person().createPerson(new PersonData()
                    .withFirstName("FirstName")
                    .withMiddleName("MiddleName")
                    .withLastName("LastName")
                    .withAddress("Address")
                    .withPhone("12345789")
                    .withEmail("test@test.test"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testPersonModification(){
        Persons before = app.db().persons();
        PersonData modifiedPerson = before.iterator().next();
        app.person().selectById(modifiedPerson.getId());
        PersonData person = new PersonData()
                .withId(modifiedPerson.getId())
                .withFirstName("FirstNameM")
                .withMiddleName("MiddleNameM")
                .withLastName("LastNameM")
                .withAddress("AddressM")
                .withPhone("123457890")
                .withEmail("test@test.testM");
        app.person().modify(person);
        app.goTo().homePage();
        assertThat(app.person().count(), equalTo(before.size()));
        Persons after = app.db().persons();
        assertThat(after, equalTo(before.withOut(modifiedPerson).withAdded(person)));
    }

}
