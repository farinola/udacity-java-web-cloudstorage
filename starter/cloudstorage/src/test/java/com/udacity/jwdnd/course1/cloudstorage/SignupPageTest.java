package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.springframework.lang.NonNull;

public class SignupPageTest extends CloudStorageApplicationTests {

    private SignupPage signupPage;

    public static SignupPage navigateToSignupPage(@NonNull WebDriver driver, int port) {
        String url = String.format("http://localhost:%s/signup", port);
        driver.get(url);
        return new SignupPage(driver);
    }

    public static SignupPage signupCorrectly(@NonNull WebDriver driver, int port, User user) {
        SignupPage signupPage = navigateToSignupPage(driver, port);
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String username = user.getUsername();
        String password = user.getPassword();
        signupPage.signup(firstName, lastName, username, password);
        return signupPage;
    }

    @Test
    public void getSignupPage() {
        this.signupPage = navigateToSignupPage(driver, this.port);
        Assertions.assertEquals("Sign Up", driver.getTitle());
    }

    @Test
    public void testValidSignup() {
        this.signupPage = SignupPageTest.signupCorrectly(driver, port, validUser);
        Assertions.assertNotNull(signupPage.getSuccessMsg());
        Assertions.assertThrows(NoSuchElementException.class, signupPage::getErrorMsg);
    }
}
