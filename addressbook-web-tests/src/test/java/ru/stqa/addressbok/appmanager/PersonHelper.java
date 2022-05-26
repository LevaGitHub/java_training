package ru.stqa.addressbok.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbok.model.PersonData;

import java.util.ArrayList;
import java.util.List;

public class PersonHelper extends HelperBase {

    public PersonHelper(WebDriver wd) {
        super(wd);
    }
    public void submitPersonCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillPersonData(PersonData personData) {
        type(By.name("firstname"), personData.getFirstName());
        type(By.name("middlename"), personData.getMiddleName());
        type(By.name("lastname"), personData.getLastName());
        type(By.name("address"), personData.getAddress());
        type(By.name("home"), personData.getPhone());
        type(By.name("email"), personData.getMail());
    }

    public void initPersonCreation() {
        click(By.linkText("add new"));
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedPerson() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmDeletePerson() {
        wd.switchTo().alert().accept();
    }

    public void initPersonModification(String personId) {
        click((By.xpath("//a[contains(@href,'edit.php?id=" + personId + "')]")));
    }

    public void submitPersonModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void createPerson(PersonData person) {
        initPersonCreation();
        fillPersonData(person);
        submitPersonCreation();
    }

    public void modify(String modification_person_id, PersonData person) {
        initPersonModification(modification_person_id);
        fillPersonData(person);
        submitPersonModification();

    }

    public void delete(int index) {
        select(index);
        deleteSelectedPerson();
        confirmDeletePerson();
    }

    public boolean isThereAPerson() {
        return isElementPresent(By.xpath("//input[@name='selected[]' and @type='checkbox']"));
    }

    public String getPersonStringId() {
        return wd.findElement(By.xpath("//input[@name='selected[]' and @type='checkbox']")).getAttribute("id");
    }

    public List<PersonData> list() {
        List<PersonData> persons = new ArrayList<PersonData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));

        for (WebElement element: elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElements(By.cssSelector("td")).get(1).getText();
            String firstName = element.findElements(By.cssSelector("td")).get(2).getText();
            PersonData person = new PersonData().withId(id).withLastName(lastName).withFirstName(firstName);
            persons.add(person);
        }
        return persons;
    }
}
