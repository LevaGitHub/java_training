package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbok.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("Name"));
        }
    }

    @Test
    public void testGroupModification(){
        Set<GroupData> before = app.group().all();
        GroupData modiiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modiiedGroup.getId())
                .withName("Name")
                .withHeader("Header")
                .withFooter("Footer");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(modiiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }



}
