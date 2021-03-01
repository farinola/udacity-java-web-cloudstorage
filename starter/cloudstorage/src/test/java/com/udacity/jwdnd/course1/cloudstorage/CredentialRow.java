package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CredentialRow extends Row {
    private final WebElement urlTh;
    private final WebElement usernameTd;
    private final WebElement passwordTd;

    public CredentialRow(WebElement row, WebDriver driver) {
        super(row, driver);
        this.urlTh = row.findElement(By.tagName("th"));
        this.usernameTd = row.findElements(By.tagName("td")).get(1);
        this.passwordTd = row.findElements(By.tagName("td")).get(2);
    }

    public String getUrl() {
        return this.urlTh.getText();
    }

    public String getUsername() {
        return this.usernameTd.getText();
    }

    public String getPassword() {
        return this.passwordTd.getText();
    }

    public void edit(String url, String username, String password) {
        this.editBtn.click();
        CredentialModal modal = new CredentialModal(this.driver);
        modal.clear();
        modal.submit(url, username, password);
    }
}
