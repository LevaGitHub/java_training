package ru.stqa.addressbok.tests;

import org.testng.annotations.*;
import ru.stqa.addressbok.model.GroupData;

public class GroupCreationTest extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("Name", null, null));
  }

}
