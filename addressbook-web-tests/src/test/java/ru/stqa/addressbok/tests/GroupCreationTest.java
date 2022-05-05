package ru.stqa.addressbok.tests;

import org.testng.annotations.*;
import ru.stqa.addressbok.model.GroupData;

public class GroupCreationTest extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {

    app.goToGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("Name", "Header", "Footer"));
    app.submitGroupCreation();
    app.returnToGroupPage();

  }

}
