package com.udacity.jwdnd.course1.cloudstorage.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Row {
    protected final WebElement editBtn;
    protected final WebElement deleteLink;
    protected final WebDriver driver;

    public Row(WebElement row, WebDriver driver) {
        this.editBtn = row.findElement(By.tagName("button"));
        this.deleteLink = row.findElements(By.tagName("td")).get(0).findElement(By.tagName("a"));
        this.driver = driver;
    }

    public void delete() {
        this.deleteLink.click();
    }
}
