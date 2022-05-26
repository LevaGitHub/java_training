package ru.stqa.addressbok.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        click(By.linkText("groups"));
    }

    public void homePage() {
        click(By.xpath("//div[@id='header']/a"));
    }
}
