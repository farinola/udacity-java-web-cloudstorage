package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.*;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomePageTest extends PageTest {

    static HomePage homePage;

    public void navigateToHome() {
        SignupPage signupPage = SignupPageTest.signupCorrectly(driver, port, validUser);
        LoginPage loginPage = LoginPageTest.navigateToLoginPage(driver, port);
        loginPage.login(validUser.getUsername(), validUser.getPassword());
        homePage = new HomePage(driver);
    }

    @Test
    @Order(1)
    public void getHomePage() {
        navigateToHome();
        Assertions.assertEquals("Home", driver.getTitle());
    }

    @Test
    @Order(2)
    public void addNewNote() {
        NoteTab noteTab = homePage.selectNoteTab();
        Assertions.assertEquals(0, noteTab.getNoteRows().size());
        noteTab.addNewNote("note1", "testing 1");
        noteTab = homePage.selectNoteTab();
        Assertions.assertEquals(1, noteTab.getNoteRows().size());
    }

    @Test
    @Order(3)
    public void updateNote() {
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
    @Order(4)
    public void deleteNote() {
        NoteTab noteTab = homePage.selectNoteTab();
        int noOfNotes = noteTab.getNoteRows().size();
        noteTab.addNewNote("note1", "testing 1");
        noteTab = homePage.selectNoteTab();
        Assertions.assertEquals(noOfNotes + 1, noteTab.getNoteRows().size());
        noteTab.getNoteRows().get(0).delete();
        NoteTab modifiedNoteTab = homePage.selectNoteTab();
        Assertions.assertEquals(noOfNotes, modifiedNoteTab.getNoteRows().size());
    }
}
