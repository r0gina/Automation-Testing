import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AmazonNavigation {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(AmazonNavigation.class);

    public AmazonNavigation(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToVideoGames() {
        try {
            WebElement allMenu = driver.findElement(By.id("nav-hamburger-menu"));
            allMenu.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hmenu-content")));

            WebElement seeAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/div[2]/ul[1]/li[14]/a[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seeAllButton);
            seeAllButton.click();

            WebElement videoGamesMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/div[2]/ul[1]/ul/li[11]/a")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", videoGamesMenu);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", videoGamesMenu);

            WebElement allVideoGames = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'All Video Games')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allVideoGames);

            logger.info("Successfully navigated to 'All Video Games' section.");
        } catch (Exception e) {
            logger.error("Error navigating to 'Video Games' section", e);
        }
    }

    public void applyFilters() {
        try {
            WebElement freeShippingFilter = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/ul/li/span/a/div[1]/label/i"));
            freeShippingFilter.click();

            WebElement newConditionFilter = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div[3]/span/div/span/div/div[2]/div[7]/div[1]/ul/span/span[2]/li"));
            newConditionFilter.click();

            logger.info("Filters applied successfully: Free Shipping and New Condition.");
        } catch (Exception e) {
            logger.error("Error applying filters", e);
        }
    }

    public void sortByPrice() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Actions actions = new Actions(driver);

            // Scroll up to make sure the sort button is visible
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000); // Allow time for scrolling

            // Locate and click the sort dropdown menu
            WebElement sortMenu = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.a-dropdown-prompt")));
            js.executeScript("arguments[0].click();", sortMenu); // Click using JavaScript
            Thread.sleep(1000); // Small delay to ensure dropdown opens

            // Select "Price: High to Low" option
            WebElement highToLowOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Price: High to Low']")));
            js.executeScript("arguments[0].click();", highToLowOption); // Click using JavaScript

            logger.info("Successfully sorted by price: High to Low.");
        } catch (NoSuchElementException e) {
            logger.error("Error sorting by price: Element not found", e);
        } catch (TimeoutException e) {
            logger.error("Error sorting by price: Timeout while waiting for elements", e);
        } catch (Exception e) {
            logger.error("Unexpected error while sorting by price", e);
        }
    }


}
