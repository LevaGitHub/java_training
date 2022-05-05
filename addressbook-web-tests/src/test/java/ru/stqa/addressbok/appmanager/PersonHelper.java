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
}
