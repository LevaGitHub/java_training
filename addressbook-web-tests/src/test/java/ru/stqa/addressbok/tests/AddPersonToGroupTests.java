package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbok.model.GroupData;
import ru.stqa.addressbok.model.Groups;
import ru.stqa.addressbok.model.PersonData;
import ru.stqa.addressbok.model.Persons;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;

public class AddPersonToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        // Если нет групп
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Name"));
        }
        //Если нет пользователей
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
        // Если все пользователи уже добавлены во все группы
        int groupsCount = app.db().groups().size();
        Persons persons = app.db().persons();
        boolean needToAddGroup = true;
        Iterator<PersonData> iter = persons.iterator();
        for (int i=0; i < persons.size(); i++) {
            PersonData person = iter.next();
            if (person.getGroups().size() < groupsCount) {
                needToAddGroup = false;
                break;
            }
        }
        if (needToAddGroup) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Name"));
        }
    }

    @Test
    public void testAddPersonToGroup() throws Exception {
        Groups groups = app.db().groups();
        Persons before = app.db().persons();
        app.goTo().homePage();
        app.person().selectGroupFilter("[all]");
        PersonData personToAddGroup = app.person().findPersonToAddGroup(before, groups.size());
        GroupData groupToAdd = app.person().getAvailableGroup(personToAddGroup, groups);
        app.person().selectById(personToAddGroup.getId());
        app.person().addPersonToGroup(groupToAdd);
        app.goTo().homePage();
        Persons after = app.db().personsById(personToAddGroup.getId());
        Assert.assertTrue(after.iterator().next().getGroups().contains(groupToAdd));
        verifyPersonListInUI();
    }

}
