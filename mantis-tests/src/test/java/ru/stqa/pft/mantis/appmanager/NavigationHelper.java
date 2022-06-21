package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void manageMenuItem() {
        click(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i"));
    }

    public void manageUsersTab() {
        click(By.linkText("Manage Users"));
    }
}