package ru.stqa.addressbok.tests;

import org.testng.annotations.*;

public class PersonDeletionTests extends TestBase{

    @Test
    public void testPersonDeletion(){
        app.getNavigationHelper().goToPersonHomePage();
        app.getPersonHelper().selectPerson(2);
        app.getPersonHelper().deleteSelectedPerson();
        app.getPersonHelper().confirmDeletePerson();
    }
}
