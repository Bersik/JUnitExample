package wiki;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * Created on 20:07 26.04.2016
 *
 * @author Bersik (Serhii Kisilchuk)
 */
public class TestWikipedia2 {
    public static final String SCREEN_FILE_1 = "Test2_1.png";
    public static final String SCREEN_FILE_2 = "Test2_2.png";

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://uk.wikipedia.org/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testWiki() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.linkText("English")).click();
        screenShot(Constants.SCREENS_PATH + SCREEN_FILE_1);
        driver.findElement(By.linkText("Deutsch")).click();
        screenShot(Constants.SCREENS_PATH + SCREEN_FILE_2);
        driver.get(baseUrl);
    }

    public void screenShot(String fileName) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(fileName));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}

