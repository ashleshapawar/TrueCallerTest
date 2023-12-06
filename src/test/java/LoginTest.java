import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LoginTest {

    AndroidDriver driver;

    @BeforeTest
    public void initializeApp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("One Plus")
                .setPlatformVersion("10.0")
                .setUdid("239B6772627123").setAppActivity("com.truecaller.ui.TruecallerInit").setAppPackage("com.truecaller");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
        @Test
        public void loginApp(){
            driver.findElement(AppiumBy.id("nextButton")).click();
            driver.findElement(AppiumBy.id("countrySpinner")).click();
            driver.findElement(AppiumBy.id("searchEditText")).sendKeys("India");
            driver.findElements(AppiumBy.id("title")).get(0).click();
            driver.findElement(AppiumBy.id("numberField")).sendKeys("9876543210");
            driver.findElement(AppiumBy.id("nextButton")).click();

            String getAlertMessage=  driver.switchTo().alert().getText();
            Assert.assertEquals(getAlertMessage, "Confirm Your Number\n" +
                    "Is this your correct phone number? +91 9876543210");

            driver.switchTo().alert().accept();
            driver.findElement(AppiumBy.id("nextButton")).click();

            driver.findElement(AppiumBy.id("manualInputButton")).click();
            driver.findElement(AppiumBy.id("firstName")).clear();
            driver.findElement(AppiumBy.id("firstName")).sendKeys("FirstName");
            driver.findElement(AppiumBy.id("lastName")).clear();
            driver.findElement(AppiumBy.id("lastName")).sendKeys("LastName");
            driver.findElement(AppiumBy.id("nextButton")).click();

            Assert.assertTrue(driver.findElement(AppiumBy.id("button_backup")).isDisplayed());
            driver.findElement(AppiumBy.id("button_skip")).click();
        }


        @AfterTest
        public void destroyApp(){
        driver.quit();
        }


    }

