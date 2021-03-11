package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.models.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.models.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

    @LocalServerPort
    protected int port;

    static HomePage homePage;
    static WebDriver driver;
    static User validUser = new User(
            null,
            "jdoe",
            null,
            "jdoe123",
            "John",
            "Doe"
    );


    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    static void afterAll() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void navigateToHome() {
        SignupPage signupPage = SignupPageTest.signupCorrectly(driver, port, validUser);
        LoginPage loginPage = LoginPageTest.navigateToLoginPage(driver, port);
        loginPage.login(validUser.getUsername(), validUser.getPassword());
        homePage = new HomePage(driver);
    }
}
