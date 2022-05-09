package ru.stqa.addressbok.tests;

import org.testng.annotations.*;

public class PersonDeletionTests extends TestBase{

    @Test
    public void testPersonDeletion(){
        app.getNavigationHelper().goToPersonHomePage();
        app.getPersonHelper().selectPerson();
        app.getPersonHelper().deleteSelectedPerson();
        app.getPersonHelper().confirmDeletePerson();
    }
}
