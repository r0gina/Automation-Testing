import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

public class AmazonCheckout {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(AmazonCheckout.class);

    public AmazonCheckout(WebDriver driver) {
        this.driver = driver;
    }

    public void checkout() {
        try {
            WebElement checkoutButton = driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']"));
            checkoutButton.click();

            // Wait for the checkout page
            Thread.sleep(3000);
            logger.info("✅ Proceeding to checkout.");
        } catch (Exception e) {
            logger.error("Error during checkout", e);
        }
    }

    public void validateTotalAmount() {
        try {
            WebElement totalAmount = driver.findElement(By.cssSelector(".a-price .a-offscreen"));
            logger.info("Total cart value: " + totalAmount.getText());
        } catch (Exception e) {
            logger.error("Error while validating total amount", e);
        }
    }
}
