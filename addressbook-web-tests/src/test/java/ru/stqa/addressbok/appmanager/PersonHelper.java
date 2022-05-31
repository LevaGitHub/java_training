package ru.stqa.addressbok.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbok.model.PersonData;
import ru.stqa.addressbok.model.Persons;

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
        type(By.name("home"), personData.getHomePhone());
        type(By.name("email"), personData.getEmail());
        attach(By.name("photo"), personData.getPhoto());
    }

    public void initPersonCreation() {
        click(By.linkText("add new"));
    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
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
        personCache = null;
    }

    public void modify(PersonData person) {
        initPersonModification(String.valueOf(person.getId()));
        fillPersonData(person);
        submitPersonModification();
        personCache = null;

    }

    public void delete(PersonData person) {
        selectById(person.getId());
        deleteSelectedPerson();
        confirmDeletePerson();
        personCache = null;
    }

    public boolean isThereAPerson() {
        return isElementPresent(By.xpath("//input[@name='selected[]' and @type='checkbox']"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public String getPersonStringId() {
        return wd.findElement(By.xpath("//input[@name='selected[]' and @type='checkbox']")).getAttribute("id");
    }

    private Persons personCache = null;

    public Persons all() {
        if (personCache != null){
            return new Persons(personCache);
        }
        personCache = new Persons();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));

        for (WebElement element: elements){
            List <WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones =  cells.get(5).getText();
            PersonData person = new PersonData()
                    .withId(id)
                    .withLastName(lastName)
                    .withFirstName(firstName)
                    .withAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones);
            personCache.add(person);
        }
        return new Persons(personCache);
    }

    public PersonData infoFromEditForm(PersonData person) {
        initPersonModificationById(person.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String middlename = wd.findElement(By.name("middlename")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new PersonData()
                .withId(person.getId())
                .withFirstName(firstname)
                .withMiddleName(middlename)
                .withLastName(lastname)
                .withAddress(address)
                .withPhone(home)
                .withMobilePhone(mobile)
                .withWork(work)
                .withPhone2(phone2)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3);
    }

    private void initPersonModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

//        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
//        wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
//        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
}
