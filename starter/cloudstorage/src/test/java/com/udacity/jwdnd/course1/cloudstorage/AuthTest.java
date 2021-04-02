package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.models.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.models.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.springframework.lang.NonNull;

public class AuthTest extends CloudStorageApplicationTests {

    public static LoginPage navigateToLoginPage(@NonNull WebDriver driver, int port) {
        String url = String.format("http://localhost:%s/login", port);
        driver.get(url);
        return new LoginPage(driver);
    }

    @Test
    // Given an authorized user
    public void testUnauthorizedAccessRestriction() {
        // user can access the Signup page
        navigateToSignupPage(driver, this.port);
        Assertions.assertEquals("Sign Up", driver.getTitle());

        // user can access the Login page
        navigateToLoginPage(driver, this.port);
        Assertions.assertEquals("Login", driver.getTitle());

        assertUnauthorizedAccessRestriction();
    }

    private void assertUnauthorizedAccessRestriction() {
        // user cannot access the Home page; redirected to the Login page instead
        String url = String.format("http://localhost:%s/home", port);
        driver.get(url);
        Assertions.assertFalse(driver.getCurrentUrl().contains("/home"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test
    public void testValidSignup() {
        User user = new User(
                null,
                "janeD",
                null,
                "janeD@1",
                "Jane",
                "Doe"
        );
        SignupPage signupPage = signupCorrectly(driver, port, user);
        Assertions.assertNotNull(signupPage.getSuccessMsg());
        Assertions.assertThrows(NoSuchElementException.class, signupPage::getErrorMsg);
    }

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = navigateToLoginPage(driver, this.port);
        loginPage.login("funso", "password123");
        Assertions.assertEquals(loginPage.getErrorMsg(), "Invalid username or password");
    }

    @Test
    public void testValidLogin() {
        // a new user signs up
        SignupPage signupPage = signupCorrectly(driver, port, validUser);
        Assertions.assertNotNull(signupPage.getSuccessMsg());
        Assertions.assertThrows(NoSuchElementException.class, signupPage::getErrorMsg);

        // the user logs in
        LoginPage loginPage = navigateToLoginPage(driver, this.port);
        loginPage.login(validUser.getUsername(), validUser.getPassword());
        Assertions.assertThrows(NoSuchElementException.class, () -> loginPage.getErrorMsg());

        // the user visits the home page
        String url = String.format("http://localhost:%s/home", port);
        driver.get(url);
        Assertions.assertEquals("Home", driver.getTitle());
        HomePage homePage = new HomePage(driver);

        // the user logs out
        homePage.logout();

        assertUnauthorizedAccessRestriction();
    }
}
