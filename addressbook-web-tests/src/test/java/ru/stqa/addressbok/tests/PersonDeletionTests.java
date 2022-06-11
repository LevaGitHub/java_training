package ru.stqa.addressbok.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.*;
import ru.stqa.addressbok.model.PersonData;
import ru.stqa.addressbok.model.Persons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class PersonDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().persons().size()==0) {
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
    public void testPersonDeletion(){
        Persons before = app.db().persons();
        PersonData deletedPerson = before.iterator().next();
        app.person().delete(deletedPerson);
        app.goTo().homePage();
        assertThat(app.person().count(), equalTo(before.size() - 1));
        Persons after = app.db().persons();
        assertThat(after, CoreMatchers.equalTo(before.withOut(deletedPerson)));
        verifyPersonListInUI();
    }

}
