package com.udacity.jwdnd.course1.cloudstorage.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CredentialTab extends Tab<CredentialRow>{
    @FindBy(id = "new-credential-btn")
    private WebElement newCredentialBtn;

    @FindBy(xpath = "//table[@id='credential-table']//tbody//tr")
    private List<WebElement> rowElements;

    public CredentialTab(WebDriver driver) {
        super(driver, "new-credential-btn");
    }

    @Override
    public List<CredentialRow> createRows() {
        return rowElements.stream()
                .map(row -> new CredentialRow(row, driver))
                .collect(Collectors.toList());
    }

    public void addNewRow(String url, String username, String password) {
        this.newCredentialBtn.click();
        CredentialModal modal = new CredentialModal(driver);
        modal.submit(url, username, password);
    }
}
