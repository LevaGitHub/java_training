package ru.stqa.addressbok.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.addressbok.model.PersonData;
import ru.stqa.addressbok.model.Persons;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

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
                .withMail("test@test.test");
        app.person().createPerson(person);
        app.goTo().homePage();
        Persons after = app.person().all();
        assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(
                before.withAdded(person.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

//        person.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
//        before.add(person);
//        assertEquals(before, after);
    }


}
