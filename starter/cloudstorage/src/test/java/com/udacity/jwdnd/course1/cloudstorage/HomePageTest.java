package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HomePageTest extends PageTest {

    private HomePage homePage;

    public void navigateToHome() {
        SignupPage signupPage = SignupPageTest.signupCorrectly(driver, port, validUser);
        LoginPage loginPage = LoginPageTest.navigateToLoginPage(driver, port);
        loginPage.login(validUser.getUsername(), validUser.getPassword());
        homePage = new HomePage(driver);
    }

    @Test
    public void getLoginPage() {
        navigateToHome();
        Assertions.assertEquals("Home", driver.getTitle());
    }

    @Test
    public void addNewNote() {
        navigateToHome();
        NoteTab noteTab = homePage.selectNoteTab();
        Assertions.assertEquals(0, noteTab.getNoteRows().size());
        noteTab.addNewNote("note1", "testing 1");
        noteTab = homePage.selectNoteTab();
        Assertions.assertEquals(1, noteTab.getNoteRows().size());
    }

    @Test
    public void updateNote() {
        navigateToHome();
        NoteTab noteTab = homePage.selectNoteTab();
        noteTab.addNewNote("note1", "testing 1");
        noteTab = homePage.selectNoteTab();
        NoteRow noteRow = noteTab.getNoteRows().get(0);
        noteRow.edit("new note title", "new note description");
        noteTab = homePage.selectNoteTab();
        NoteRow editedNoteRow = noteTab.getNoteRows().get(0);
        Assertions.assertEquals("new note title", editedNoteRow.getTitle());
    }

    @Test
    public void deleteNote() {
        navigateToHome();
        NoteTab noteTab = homePage.selectNoteTab();
        Assertions.assertEquals(0, noteTab.getNoteRows().size());
        noteTab.addNewNote("note1", "testing 1");
        noteTab = homePage.selectNoteTab();
        Assertions.assertEquals(1, noteTab.getNoteRows().size());
        noteTab.getNoteRows().get(0).delete();
        NoteTab modifiedNoteTab = homePage.selectNoteTab();
        Assertions.assertEquals(0, modifiedNoteTab.getNoteRows().size());
    }
}
