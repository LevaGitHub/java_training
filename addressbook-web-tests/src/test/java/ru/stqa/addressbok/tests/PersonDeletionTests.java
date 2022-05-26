package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.addressbok.model.PersonData;

import java.util.Set;

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
        Set<PersonData> before = app.person().all();
        PersonData deletedPerson = before.iterator().next();
        app.person().delete(deletedPerson);
        app.goTo().homePage();
        Set<PersonData> after = app.person().all();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(deletedPerson);
        Assert.assertEquals(before, after);
    }

}
