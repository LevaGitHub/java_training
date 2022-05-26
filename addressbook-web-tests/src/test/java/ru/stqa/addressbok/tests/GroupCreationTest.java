package ru.stqa.addressbok.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.addressbok.model.GroupData;

import java.util.Set;

public class GroupCreationTest extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
      app.goTo().groupPage();
      Set<GroupData> before = app.group().all();
      GroupData group = new GroupData().withName("Name");
      app.group().create(group);
      Set<GroupData> after = app.group().all();
      Assert.assertEquals(after.size(), before.size() + 1);

      group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
      before.add(group);
      Assert.assertEquals(before, after);
  }

}
