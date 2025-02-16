import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AmazonLogin {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(AmazonLogin.class);

    public AmazonLogin(WebDriver driver) {
        this.driver = driver;
    }

    public void loginToAmazon() {
        try {
            driver.get("https://www.amazon.eg/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-accountList")));
            js.executeScript("arguments[0].click();", loginButton);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_email")));
            emailField.sendKeys("rogina.michelle1@gmail.com");

            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
            continueButton.click();

            Thread.sleep(2000);
            if (driver.getPageSource().contains("Enter the characters you see")) {
                logger.warn("CAPTCHA detected! Please complete manually.");
                return;
            }

            WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_password")));
            passwordField.sendKeys("roginatest2025");

            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("signInSubmit")));
            js.executeScript("arguments[0].click();", signInButton);

            wait.until(ExpectedConditions.urlContains("ref_=nav_signin"));
            logger.info("Successfully logged into Amazon.");
        } catch (Exception e) {
            logger.error("Login error: ", e);
        }
    }
}
