package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.NoteRow;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteTab;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class NoteTabTest extends CloudStorageApplicationTests {

    @Test
    @Order(1)
    public void home() {
        homePage = navigateToHomePageWithValidUser();
        Assertions.assertEquals("Home", driver.getTitle());
    }

    @Test
    @Order(2)
    public void create() {
        NoteTab tab = homePage.selectNoteTab();
        Assertions.assertEquals(0, tab.getRows().size());
        tab.addNewRow("note1", "testing 1");

        // confirm new row creation
        tab = homePage.selectNoteTab();
        Assertions.assertEquals(1, tab.getRows().size());
        NoteRow noteRow = tab.getLastRow();
        Assertions.assertEquals("note1", noteRow.getTitle());
        Assertions.assertEquals("testing 1", noteRow.getDescription());
    }

    @Test
    @Order(3)
    public void update() {
        NoteTab tab = homePage.selectNoteTab();
        tab.addNewRow("note2", "testing 2");

        // edit
        tab = homePage.selectNoteTab();
        NoteRow noteRow = tab.getLastRow();
        noteRow.edit("new note title", "new note description");

        // confirm updates
        tab = homePage.selectNoteTab();
        NoteRow editedRow = tab.getLastRow();
        Assertions.assertEquals("new note title", editedRow.getTitle());
        Assertions.assertEquals("new note description", editedRow.getDescription());
    }

    @Test
    @Order(4)
    public void delete() {
        // create
        NoteTab tab = homePage.selectNoteTab();
        int noOfNotes = tab.getRows().size();
        tab.addNewRow("note3", "testing 3");

        // delete
        tab = homePage.selectNoteTab();
        Assertions.assertEquals(noOfNotes + 1, tab.getRows().size());
        tab.getLastRow().delete();

        // confirm deletion
        NoteTab modifiedTab = homePage.selectNoteTab();
        Assertions.assertEquals(noOfNotes, modifiedTab.getRows().size());
        Assertions.assertFalse(
                modifiedTab.getRows().stream().anyMatch((noteRow -> {
                    return noteRow.getTitle().equals("note3") && noteRow.getDescription().equals("testing 3");
                }))
        );
    }
}
