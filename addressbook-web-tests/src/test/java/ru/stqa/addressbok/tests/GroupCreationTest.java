package ru.stqa.addressbok.tests;

import org.testng.annotations.*;
import ru.stqa.addressbok.model.GroupData;
import ru.stqa.addressbok.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
      app.goTo().groupPage();
      Groups before = app.group().all();
      GroupData group = new GroupData().withName("Name");
      app.group().create(group);
      Groups after = app.group().all();
      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(
              before.withAdded(
                      group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

}
