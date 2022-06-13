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

public class addPersonToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        // Если нет групп
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Name"));
            return;
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
            return;
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
        app.goTo().homePage();
        app.person().selectGroupFilter("[all]");
        Persons before = app.db().persons();

        GroupData group_to_add = groups.iterator().next();  // Без значений по умолчанию не билдится
        int modifiedPersonId = 0;   // Без значений по умолчанию не билдится variable ... might not have been initialized
        // т.к. конечное определение переменных в условии ниже, которое по логике всегда должно выплоняться,
        // но java так не считает. Гарантия выполнения условия - выполнение предусловия на создание группы если все
        // контакты уже состоят в группах

        Iterator<PersonData> person_iter = before.iterator();
        PersonData modifiedPerson = person_iter.next();  //
        for (int i=0; i < before.size(); i++) {
            if (modifiedPerson.getGroups().size() < groups.size()) {
                group_to_add = app.person().get_available_group(modifiedPerson, groups);
                modifiedPersonId = modifiedPerson.getId();
                break;
            } else {
                modifiedPerson = person_iter.next();
            }
        }

        app.person().selectById(modifiedPerson.getId());
        app.person().addPersonToGroup(group_to_add);
        app.goTo().homePage();
        Persons after = app.db().persons();
        PersonData modifiedPersonAfterAddGroup = app.person().getPersonFromListById(after, modifiedPersonId);
        Assert.assertTrue(modifiedPersonAfterAddGroup.getGroups().contains(group_to_add));
        verifyPersonListInUI();
    }

}
