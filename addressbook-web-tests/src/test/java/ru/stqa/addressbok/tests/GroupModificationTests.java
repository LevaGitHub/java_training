package ru.stqa.addressbok.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbok.model.GroupData;
import ru.stqa.addressbok.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Name"));
        }
    }

    @Test
    public void testGroupModification(){
        Groups before = app.db().groups();
        GroupData modiiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modiiedGroup.getId())
                .withName("Name")
                .withHeader("Header")
                .withFooter("Footer");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withOut(modiiedGroup).withAdded(group)));
        verifyGroupListInUI();
    }



}
