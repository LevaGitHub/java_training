package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbok.model.GroupData;
import ru.stqa.addressbok.model.Groups;
import ru.stqa.addressbok.model.PersonData;
import ru.stqa.addressbok.model.Persons;

import java.util.Iterator;

public class RemovePersonFromGroupTests extends TestBase{
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
        // Если ни один пользователь не добавлен в группу
        Persons persons = app.db().persons();
        boolean needToAddPersonToGroup = true;
        Iterator<PersonData> iter = persons.iterator();
        for (int i=0; i < persons.size(); i++) {
            PersonData person = iter.next();
            if (person.getGroups().size() > 0) {
                needToAddPersonToGroup = false;
                break;
            }
        }
        if (needToAddPersonToGroup) {
            app.goTo().homePage();
            app.person().selectGroupFilter("[all]");
            Groups groups = app.db().groups();
            PersonData personToAddGroup = app.person().findPersonToAddGroup(persons, groups.size());
            GroupData groupToAdd = app.person().getAvailableGroup(personToAddGroup, groups);
            app.person().selectById(personToAddGroup.getId());
            app.person().addPersonToGroup(groupToAdd);
            app.goTo().homePage();
        }
    }

    @Test
    public void testRemovePersonFromGroup() throws Exception {
        app.goTo().homePage();
        app.person().selectGroupFilter("[all]");
        Persons before = app.db().persons();
        PersonData personToRemoveGroup = app.person().findPersonToRemoveGroup(before);
        GroupData groupToRemove = personToRemoveGroup.getGroups().iterator().next();
        app.person().removePersonFromGroup(personToRemoveGroup, groupToRemove);
        app.goTo().homePage();
        Persons after = app.db().persons();
        PersonData modifiedPersonAfterRemoveGroup = app.person().getPersonFromListById(after, personToRemoveGroup.getId());
        Assert.assertTrue(!(modifiedPersonAfterRemoveGroup.getGroups().contains(groupToRemove)));
        verifyPersonListInUI();
    }


}
