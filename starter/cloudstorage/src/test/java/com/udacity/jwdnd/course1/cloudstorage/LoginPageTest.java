package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.springframework.lang.NonNull;

public class LoginPageTest extends PageTest {

    private LoginPage loginPage;

    public static LoginPage navigateToLoginPage(@NonNull WebDriver driver, int port) {
        String url = String.format("http://localhost:%s/login", port);
        driver.get(url);
        return new LoginPage(driver);
    }

    @Test
    public void getLoginPage() {
        this.loginPage = navigateToLoginPage(driver, this.port);
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void testInvalidLogin() {
        this.loginPage = navigateToLoginPage(driver, this.port);
        loginPage.login("funso", "password123");
        Assertions.assertEquals(loginPage.getErrorMsg(), "Invalid username or password");
    }

    @Test
    public void testValidLogin() {
        SignupPage signupPage = SignupPageTest.signupCorrectly(driver, port, validUser);
        Assertions.assertNotNull(signupPage.getSuccessMsg());
        Assertions.assertThrows(NoSuchElementException.class, signupPage::getErrorMsg);

        this.loginPage = navigateToLoginPage(driver, this.port);
        loginPage.login(validUser.getUsername(), validUser.getPassword());
        Assertions.assertThrows(NoSuchElementException.class, () -> loginPage.getErrorMsg());
    }
}
