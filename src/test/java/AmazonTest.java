import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonTest {
    private static final Logger logger = LogManager.getLogger(AmazonTest.class);
    private final WebDriver driver;
    private final AmazonLogin login;
    private final AmazonNavigation navigation;
    private final AmazonCart cart;
    private final AmazonCheckout checkout;

    public AmazonTest() {
        try {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
            this.driver = new ChromeDriver();
            this.login = new AmazonLogin(driver);
            this.navigation = new AmazonNavigation(driver);
            this.cart = new AmazonCart(driver);
            this.checkout = new AmazonCheckout(driver);
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver: ", e);
            throw new RuntimeException("WebDriver initialization failed");
        }
    }

    public static void main(String[] args) {
        AmazonTest test = new AmazonTest();
        test.login.loginToAmazon();
        test.navigation.navigateToVideoGames();
        test.navigation.applyFilters();
        test.navigation.sortByPrice();
        test.cart.addAllEligibleProductsToCart();
        test.cart.displayCartContents();
        test.checkout.checkout();
        test.checkout.validateTotalAmount();
    }
}
