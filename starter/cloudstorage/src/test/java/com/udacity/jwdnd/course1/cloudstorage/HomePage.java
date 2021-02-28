package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy(id = "logoutBtn")
    private WebElement logoutButton;

    @FindBy(id = "nav-files-tab")
    private WebElement filesTabNav;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTabNav;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTabNav;

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public NoteTab selectNoteTab() {
        this.notesTabNav.click();
        new WebDriverWait(driver, 3)
                .until(d -> {
                    this.notesTabNav.click();
                    return d.findElement(By.id("nav-notes-tab")).getAttribute("class").contains("active");
                });
        return new NoteTab(driver);
    }
}
