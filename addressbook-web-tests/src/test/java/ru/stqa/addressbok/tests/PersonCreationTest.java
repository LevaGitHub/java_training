package ru.stqa.addressbok.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.*;
import ru.stqa.addressbok.model.PersonData;
import ru.stqa.addressbok.model.Persons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class PersonCreationTest extends TestBase{

    @DataProvider
    public Iterator<Object[]> validPersonsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/persons.xml")))){
            String xml = "";
            String line = reader.readLine();
            while (line != null){
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.allowTypes(new Class[] {PersonData.class});
            xstream.processAnnotations(PersonData.class);
            List<PersonData> persons = (List<PersonData>) xstream.fromXML(xml);
            return persons.stream().map((p) -> new Object[] {p}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validPersonsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/persons.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null){
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<PersonData> persons = gson.fromJson(json, new TypeToken<List<PersonData>>(){}.getType());   //List<PersonData>.class
            return persons.stream().map((p) -> new Object[] {p}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validPersonsFromJson")
    public void testPersonCreation(PersonData person) throws Exception {
        app.goTo().homePage();
        Persons before = app.db().persons();
        app.person().createPerson(person);
        app.goTo().homePage();
        assertThat(app.person().count(), equalTo(before.size() + 1));
        Persons after = app.db().persons();
        assertThat(after, equalTo(
                before.withAdded(person.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
        verifyPersonListInUI();
    }

}
