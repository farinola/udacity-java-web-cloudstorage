package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NoteRow extends Row {
    private final WebElement titleTh;
    private final WebElement descriptionTd;

    public NoteRow(WebElement row, WebDriver driver) {
        super(row, driver);
        this.titleTh = row.findElement(By.tagName("th"));
        this.descriptionTd = row.findElements(By.tagName("td")).get(1);
    }

    public String getTitle() {
        return this.titleTh.getText();
    }

    public String getDescription() {
        return this.descriptionTd.getText();
    }

    public void edit(String title, String description) {
        this.editBtn.click();
        NoteModal modal = new NoteModal(this.driver);
        modal.clear();
        modal.submit(title, description);
    }
}
