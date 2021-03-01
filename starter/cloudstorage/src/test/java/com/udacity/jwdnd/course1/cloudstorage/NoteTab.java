package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class NoteTab extends Tab<NoteRow> {
    @FindBy(id = "new-note-btn")
    private WebElement newNoteBtn;

    @FindBy(xpath = "//table[@id='note-table']//tbody//tr")
    private List<WebElement> rowElements;

    public NoteTab(WebDriver driver) {
        super(driver, "new-note-btn");
    }

    @Override
    public List<NoteRow> createRows() {
        return rowElements.stream()
                .map(row -> new NoteRow(row, driver))
                .collect(Collectors.toList());
    }

    public void addNewRow(String title, String description) {
        this.newNoteBtn.click();
        NoteModal modal = new NoteModal(driver);
        modal.submit(title, description);
    }
}
