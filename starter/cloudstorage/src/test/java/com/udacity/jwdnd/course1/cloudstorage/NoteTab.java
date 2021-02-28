package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class NoteTab {
    @FindBy(id = "new-note-btn")
    private WebElement newNoteBtn;

    @FindBy(xpath = "//table[@id='note-table']//tbody//tr")
    private List<WebElement> noteRowElements;

    private List<NoteRow> noteRows;

    private NoteModal noteModal;
    private final WebDriver driver;

    public NoteTab(WebDriver driver) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("new-note-btn")));
        PageFactory.initElements(driver, this);
        this.noteRows = noteRowElements.stream()
                .map(row -> new NoteRow(row, driver))
                .collect(Collectors.toList());
        this.driver = driver;
    }

    public List<NoteRow> getNoteRows() {
        return this.noteRows;
    }

    public void addNewNote(String title, String description) {
        this.newNoteBtn.click();
        this.noteModal = new NoteModal(driver);
        this.noteModal.submit(title, description);
    }
}
