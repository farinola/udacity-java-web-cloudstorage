package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.CredentialRow;
import com.udacity.jwdnd.course1.cloudstorage.models.CredentialTab;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class CredentialTabTest extends CloudStorageApplicationTests {

    @Test
    @Order(1)
    public void create() {
        homePage = navigateToHomePageWithValidUser();
        CredentialTab tab = homePage.selectCredentialTab();
        Assertions.assertEquals(0, tab.getRows().size());
        tab.addNewRow("gmail.com", "paul-attreides@gmail.com", "arrakisDuke98");

        // confirm row creation
        tab = homePage.selectCredentialTab();
        Assertions.assertEquals(1, tab.getRows().size());
    }

    @Test
    @Order(2)
    public void update() {
        CredentialTab tab = homePage.selectCredentialTab();
        tab.addNewRow("twitter.com", "frodo.baggins", "ringBearer43");

        // edit
        tab = homePage.selectCredentialTab();
        CredentialRow row = tab.getLastRow();
        row.edit("twitter.com", "f.baggins", "sammysFriend44");

        // confirm updates
        tab = homePage.selectCredentialTab();
        CredentialRow editedRow = tab.getLastRow();
        Assertions.assertEquals("f.baggins", editedRow.getUsername());
        Assertions.assertEquals("twitter.com", editedRow.getUrl());
    }

    @Test
    @Order(3)
    public void delete() {
        // create
        CredentialTab tab = homePage.selectCredentialTab();
        int noOfCredentials = tab.getRows().size();
        tab.addNewRow("instagram.com", "emmy.martinez", "martin4@ll");

        // delete
        tab = homePage.selectCredentialTab();
        Assertions.assertEquals(noOfCredentials + 1, tab.getRows().size());
        tab.getLastRow().delete();

        // confirm deletion
        CredentialTab modifiedTab = homePage.selectCredentialTab();
        Assertions.assertEquals(noOfCredentials, modifiedTab.getRows().size());
    }
}
