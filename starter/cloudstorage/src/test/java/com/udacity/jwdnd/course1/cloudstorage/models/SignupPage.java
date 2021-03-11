package com.udacity.jwdnd.course1.cloudstorage.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(xpath = "//body//div[@class='form-group']//label//a")
    private WebElement backToLoginLink;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signupButton;

    private final WebDriver driver;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void submit() {
        this.signupButton.click();
    }

    public void signup(String firstName, String lastName, String username, String password) {
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.submit();
    }

    public String getErrorMsg() {
        return this.driver.findElement(By.className("alert-danger")).getText();
    }

    public String getSuccessMsg() {
        return this.driver.findElement(By.className("alert-dark")).getText();
    }
}
