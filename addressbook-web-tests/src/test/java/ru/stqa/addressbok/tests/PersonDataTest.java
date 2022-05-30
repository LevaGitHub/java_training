package ru.stqa.addressbok.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbok.model.PersonData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonDataTest extends TestBase{

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
                    .withEmail("test@test.test"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testPersonData(){
        app.goTo().homePage();
        PersonData person = app.person().all().iterator().next();
        PersonData personInfoFromEditForm = app.person().infoFromEditForm(person);

        assertThat(person.getAllPhones(), equalTo(mergePhones(personInfoFromEditForm)));
        assertThat(person.getAddress(), equalTo(personInfoFromEditForm.getAddress()));
        assertThat(person.getAllEmails(), equalTo(mergeEmails(personInfoFromEditForm)));

    }

    private String mergePhones(PersonData person) {

        return Arrays.asList(
                        person.getHomePhone(),
                        person.getMobilePhone(),
                        person.getWorkPhone(),
                        person.getPhone2())
                .stream().filter((s) -> ! s.equals(""))
                .map(PersonDataTest::cleaned)
                .collect(Collectors.joining("\n"));

    }

    private String mergeEmails(PersonData person) {

        return Arrays.asList(
                        person.getEmail(),
                        person.getEmail2(),
                        person.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));

    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
