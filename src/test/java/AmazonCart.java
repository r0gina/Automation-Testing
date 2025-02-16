import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class AmazonCart {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(AmazonCart.class);

    public AmazonCart(WebDriver driver) {
        this.driver = driver;
    }

    public void addAllEligibleProductsToCart() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean productAdded = false;

            List<WebElement> products = driver.findElements(By.cssSelector(".s-main-slot .s-result-item"));
            logger.info("🔍 Found " + products.size() + " products on this page.");

            for (WebElement product : products) {
                try {
                    List<WebElement> priceElements = product.findElements(By.cssSelector(".a-price-whole"));
                    if (priceElements.isEmpty()) continue;

                    String priceText = priceElements.get(0).getText().replace(",", "").trim();
                    double price = Double.parseDouble(priceText);

                    if (price < 15000) {
                        logger.info("🛍️ Found product under 15K: " + price + " EGP");

                        List<WebElement> addToCartButtons = product.findElements(By.xpath("//button[@name='submit.addToCart']"));
                        if (addToCartButtons.isEmpty()) {
                            logger.warn("⚠️ No 'Add to Cart' button found for product at " + price);
                            continue;
                        }

                        for (WebElement addToCartButton : addToCartButtons) {
                            addToCartButton.click();
                        }

                        Thread.sleep(2000);

                        if (driver.getPageSource().contains("Added to Cart")) {
                            logger.info("✅ Successfully added product to cart | Price: " + price);
                            productAdded = true;
                        } else {
                            logger.warn("⚠️ No confirmation for adding to cart.");
                        }
                    }
                } catch (Exception e) {
                    logger.warn("⚠️ Error processing product: " + e.getMessage());
                }
            }

            if (productAdded) {
                logger.info("✅ Stopping since products were added.");
            }
        } catch (Exception e) {
            logger.error("Error adding products to cart", e);
        }
    }

    public void displayCartContents() {
        try {
            driver.get("https://www.amazon.eg/gp/cart/view.html");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".sc-list-item-content")));

            List<WebElement> cartItems = driver.findElements(By.cssSelector(".sc-list-item-content"));

            if (cartItems.isEmpty()) {
                logger.info("🛒 Your cart is empty.");
            } else {
                logger.info("🛒 Cart contains the following items:");
                for (WebElement item : cartItems) {
                    try {
                        String productName = item.findElement(By.cssSelector(".sc-product-title")).getText();
                        String productPrice = item.findElement(By.cssSelector(".sc-price")).getText();
                        logger.info("📦 " + productName + " | 💰 Price: " + productPrice);
                    } catch (NoSuchElementException e) {
                        logger.warn("⚠️ Could not retrieve product details for an item.");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error while displaying cart contents: ", e);
        }
    }
}
