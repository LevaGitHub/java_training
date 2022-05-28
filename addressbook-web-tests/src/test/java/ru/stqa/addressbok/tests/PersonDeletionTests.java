package ru.stqa.addressbok.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.*;
import ru.stqa.addressbok.model.PersonData;
import ru.stqa.addressbok.model.Persons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class PersonDeletionTests extends TestBase{

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
    public void testPersonDeletion(){
        Persons before = app.person().all();
        PersonData deletedPerson = before.iterator().next();
        app.person().delete(deletedPerson);
        app.goTo().homePage();
        assertThat(app.person().count(), equalTo(before.size() - 1));
        Persons after = app.person().all();
        assertThat(after, CoreMatchers.equalTo(before.withOut(deletedPerson)));
    }

}
