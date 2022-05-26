package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbok.model.PersonData;

import java.util.Comparator;
import java.util.List;

public class PersonModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (! app.person().isThereAPerson()){
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
        List<PersonData> before = app.person().list();
        int index = before.size()-1;

        app.person().select(index);
        String modificationPersonId = String.valueOf(before.get(index).getId());
        PersonData person = new PersonData()
                .withId(Integer.parseInt(modificationPersonId))
                .withFirstName("FirstNameM")
                .withMiddleName("MiddleNameM")
                .withLastName("LastNameM")
                .withAddress("AddressM")
                .withPhone("123457890")
                .withMail("test@test.testM");
        app.person().modify(modificationPersonId, person);
        app.goTo().homePage();
        List<PersonData> after = app.person().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(person);
        Comparator<? super PersonData> byId = (p1, p2) -> Integer.compare(p1.getId(), p2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
