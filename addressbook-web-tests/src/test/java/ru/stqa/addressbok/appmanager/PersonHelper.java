package ru.stqa.addressbok.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.addressbok.model.PersonData;

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

    public void selectPerson() {
        click(By.xpath("//input[@name='selected[]' and @type='checkbox']"));
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

    public boolean isThereAPerson() {
        return isElementPresent(By.xpath("//input[@name='selected[]' and @type='checkbox']"));
    }

    public String getPersonStringId() {
        return wd.findElement(By.xpath("//input[@name='selected[]' and @type='checkbox']")).getAttribute("id");
    }
}
