package ru.stqa.addressbok.appmanager;

import com.sun.org.apache.xpath.internal.objects.XObject;
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

    public void selectPerson(int personId) {
        // TODO: Переделать на рандом и поиск по родительской таблице "entry"
        //Object rows = wd.findElement(By.name("entry"));
        click(By.id(Integer.toString(personId)));
    }

    public void deleteSelectedPerson() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmDeletePerson() {
        wd.switchTo().alert().accept();
    }

    public void initPersonModification(int personId) {
        click(By.xpath("//table[@id='maintable']/tbody/tr["+ Integer.toString(personId) +"]/td[8]/a/img"
        ));
    }

    public void submitPersonModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }
}
