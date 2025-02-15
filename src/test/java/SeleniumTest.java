import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumTest {
    public static ChromeOptions options;
    public static WebDriver driver;

    @BeforeTest
    void setup(){
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins**");

        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
        driver= new ChromeDriver(options);
        driver.get("https://anupdamoda.github.io/AceOnlineShoePortal/index.html");
    }


    @Test
    void teststeps() throws InterruptedException {
driver.findElement(By.xpath("//*[@id=\"menuToggle\"]/input")).click();

Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"menu\"]/a[2]/li")).click();
        driver.findElement(By.xpath("//*[@id=\"usr\"]")).sendKeys("sa");
        driver.findElement(By.xpath("//*[@id=\"pwd\"]")).sendKeys("sa");
        driver.findElement(By.xpath("//*[@id=\"second_form\"]/input")).click();

        WebElement webElement =driver.findElement(By.xpath("//*[@id=\"ShoeType\"]"));
        String ActualFirstCategory=webElement.getText();
  String expectedFirstCategory="Formal Shoes";
        Assert.assertEquals(expectedFirstCategory,ActualFirstCategory);
        driver.close();
    }
}
