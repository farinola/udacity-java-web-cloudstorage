package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NoteRow {
    private WebElement editBtn;
    private WebElement deleteLink;
    private WebElement titleTh;
    private WebElement descriptionTd;
    private final WebDriver driver;

    public NoteRow(WebElement row, WebDriver driver) {
        this.editBtn = row.findElement(By.xpath("//td//button"));
        this.deleteLink = row.findElements(By.tagName("td")).get(0).findElement(By.tagName("a"));
        this.titleTh = row.findElement(By.tagName("th"));
        this.descriptionTd = row.findElements(By.tagName("td")).get(1);
        this.driver = driver;
    }

    public String getTitle() {
        return this.titleTh.getText();
    }

    public String getDescription() {
        return this.descriptionTd.getText();
    }

    public void edit(String title, String description) {
        this.editBtn.click();
        NoteModal noteModal = new NoteModal(this.driver);
        noteModal.clear();
        noteModal.submit(title, description);
    }

    public void delete() {
        this.deleteLink.click();
    }
}
