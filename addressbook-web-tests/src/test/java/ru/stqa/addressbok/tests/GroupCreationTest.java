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
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Groups after = app.group().all();
      assertThat(after, equalTo(
              before.withAdded(
                      group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

    @Test
    public void testBadGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("Name'");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }

}
