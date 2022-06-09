package ru.stqa.addressbok.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.openqa.selenium.json.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqa.addressbok.model.GroupData;
import ru.stqa.addressbok.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase{
    Logger logger = LoggerFactory.getLogger(GroupCreationTest.class);


  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))){
          String xml = "";
          String line = reader.readLine();
          while (line != null){
              xml += line;
              line = reader.readLine();
          }
          XStream xstream = new XStream();
          xstream.allowTypes(new Class[] {GroupData.class});
          xstream.processAnnotations(GroupData.class);
          List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
          return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
      }
  }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {

          String json = "";
          String line = reader.readLine();
          while (line != null){
              json += line;
              line = reader.readLine();
          }
          Gson gson = new Gson();
          List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());   //List<GroupData>.class
          return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
      }
    }

  @Test(dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData group) throws Exception {
      app.goTo().groupPage();
      Groups before = app.db().groups();
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Groups after = app.db().groups();
      assertThat(after, equalTo(
              before.withAdded(
                      group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

    @Test(enabled = false)
    public void testBadGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        GroupData group = new GroupData().withName("Name'");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before));
    }

}
