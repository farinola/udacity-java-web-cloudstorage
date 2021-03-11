package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.models.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.models.SignupPage;
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
