package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.addressbok.model.PersonData;

import java.util.List;

public class PersonDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.person().list().size() == 0){
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
        List<PersonData> before = app.person().list();
        int index = before.size()-1;
        app.person().delete(index);
        app.goTo().homePage();
        List<PersonData> after = app.person().list();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
