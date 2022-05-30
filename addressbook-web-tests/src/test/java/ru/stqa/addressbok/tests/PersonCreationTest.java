package ru.stqa.addressbok.tests;

import org.testng.annotations.*;
import ru.stqa.addressbok.model.PersonData;
import ru.stqa.addressbok.model.Persons;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class PersonCreationTest extends TestBase{

    @Test
    public void testPersonCreation() throws Exception {
        app.goTo().homePage();
        Persons before = app.person().all();
        PersonData person = new PersonData()
                .withFirstName("FirstName")
                .withMiddleName("MiddleName")
                .withLastName("LastName")
                .withAddress("Address")
                .withPhone("12345789")
                .withEmail("test@test.test");
        app.person().createPerson(person);
        app.goTo().homePage();
        assertThat(app.person().count(), equalTo(before.size() + 1));
        Persons after = app.person().all();
        assertThat(after, equalTo(
                before.withAdded(person.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }

}
