package wordpress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class Test3Wordpress {
    private WebDriver driver;
    private String baseUrl;
    private String userName;
    private String password;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://demosite.center/wordpress/wp-login.php";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        readFromFile();
    }

    @Test
    public void test3Wordpress() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("user_login")).clear();
        driver.findElement(By.id("user_login")).sendKeys(userName);
        driver.findElement(By.id("user_pass")).clear();
        driver.findElement(By.id("user_pass")).sendKeys(password);
        driver.findElement(By.id("wp-submit")).click();
        try {
            driver.findElement(By.cssSelector("#wp-admin-bar-my-account > a.ab-item"));
            verificationErrors.append("login error");
        } catch (Error ignored) {
        }
        driver.findElement(By.linkText("Posts")).click();
        driver.findElement(By.cssSelector("a.add-new-h2")).click();
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys("Selenium Demo Post");
        driver.findElement(By.id("publish")).click();
        try {
            assertTrue(Pattern.compile("Post published.*").matcher(driver.findElement(By.cssSelector("#message > p")).getText()).find());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private void readFromFile() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("userInfo.txt").getFile());
        Scanner scanner = new Scanner(file);
        userName = scanner.nextLine();
        password = scanner.nextLine();
        scanner.close();
    }
}
