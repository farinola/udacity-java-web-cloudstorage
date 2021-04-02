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
import org.springframework.lang.NonNull;

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

    public static SignupPage navigateToSignupPage(@NonNull WebDriver driver, int port) {
        String url = String.format("http://localhost:%s/signup", port);
        driver.get(url);
        return new SignupPage(driver);
    }

    public HomePage navigateToHomePageWithValidUser() {
        SignupPage signupPage = signupCorrectly(driver, port, validUser);
        LoginPage loginPage = AuthTest.navigateToLoginPage(driver, port);
        loginPage.login(validUser.getUsername(), validUser.getPassword());
        return new HomePage(driver);
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
}
