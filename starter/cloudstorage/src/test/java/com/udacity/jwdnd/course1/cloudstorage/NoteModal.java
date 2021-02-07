package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NoteModal {
    @FindBy(id = "noteModal")
    private WebElement modalDiv;

    @FindBy(id = "note-id")
    private WebElement idInput;

    @FindBy(id = "note-title")
    private WebElement titleInput;

    @FindBy(id = "note-description")
    private WebElement descriptionInput;

    @FindBy(id = "save-note-changes")
    private WebElement saveChangesBtn;

    public NoteModal(WebDriver driver) {
        new WebDriverWait(driver, 3)
                .until(d -> d.findElement(By.id("noteModal")).isDisplayed());
        PageFactory.initElements(driver, this);
    }

    public boolean isVisible() {
        return modalDiv.isDisplayed();
    }

    public void clear() {
        this.titleInput.clear();
        this.descriptionInput.clear();
    }

    public void submit(String title, String description) {
        this.titleInput.sendKeys(title);
        this.descriptionInput.sendKeys(description);
        this.submit();
    }

    public void submit() {
        saveChangesBtn.click();
    }
}
