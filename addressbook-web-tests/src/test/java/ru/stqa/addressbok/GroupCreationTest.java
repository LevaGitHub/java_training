package ru.stqa.addressbok;

import org.testng.annotations.*;

public class GroupCreationTest extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {

    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Name", "Header", "Footer"));
    submitGroupCreation();
    returnToGroupPage();

  }

}
