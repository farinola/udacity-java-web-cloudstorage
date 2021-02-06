package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NoteRow {
    private WebElement editBtn;
    private WebElement deleteLink;
    private WebElement titleTh;
    private WebElement descriptionTd;

    public NoteRow(WebElement row) {
        this.editBtn = row.findElement(By.xpath("//td//button"));
        this.deleteLink = row.findElement(By.xpath("//td//a"));
        this.titleTh = row.findElement(By.tagName("th"));
        this.descriptionTd = row.findElements(By.tagName("td")).get(1);
    }

    public void edit() {
        this.editBtn.click();
    }

    public void delete() {
        this.deleteLink.click();
    }
}
