package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialModal {
    @FindBy(id = "credentialModal")
    private WebElement modalDiv;

    @FindBy(id = "credential-id")
    private WebElement idInput;

    @FindBy(id = "credential-url")
    private WebElement urlInput;

    @FindBy(id = "credential-username")
    private WebElement usernameInput;

    @FindBy(id = "credential-password")
    private WebElement passwordInput;

    @FindBy(id = "save-credential-changes")
    private WebElement saveChangesBtn;

    public CredentialModal(WebDriver driver) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialModal")));
        PageFactory.initElements(driver, this);
    }

    public void clear() {
        this.urlInput.clear();
        this.usernameInput.clear();
        this.passwordInput.clear();
    }

    public void submit(String url, String username, String password) {
        this.urlInput.sendKeys(url);
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.submit();
    }

    public void submit() {
        saveChangesBtn.click();
    }
}
