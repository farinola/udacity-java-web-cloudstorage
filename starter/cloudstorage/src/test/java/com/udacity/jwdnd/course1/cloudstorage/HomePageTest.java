package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomePageTest extends PageTest {

    static HomePage homePage;

    public void navigateToHome() {
        SignupPage signupPage = SignupPageTest.signupCorrectly(driver, port, validUser);
        LoginPage loginPage = LoginPageTest.navigateToLoginPage(driver, port);
        loginPage.login(validUser.getUsername(), validUser.getPassword());
        homePage = new HomePage(driver);
    }
}
