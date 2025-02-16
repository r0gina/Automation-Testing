Amazon Automation Test Task with Selenium

OVERVIEW

This project demonstrates an automation script to perform an end-to-end shopping flow on Amazon's website using Selenium WebDriver. The script logs in to Amazon, applies filters, sorts products by price, adds eligible products to the cart, proceeds to checkout, and validates the total amount.

The test script is modular, broken down into different methods to handle various steps of the shopping process.

REQUIRMENTS:

Java (version 8 or above)

Maven for dependency management

Selenium WebDriver for browser automation

Log4j for logging

ChromeDriver for browser interaction (make sure to have it in the appropriate directory)

DEPENDCIES:

To set up the project, include the following dependencies in your pom.xml file:

<dependencies>
  
    <dependency>
    
        <groupId>org.seleniumhq.selenium</groupId>
        
        <artifactId>selenium-java</artifactId>
        
        <version>4.0.0</version>
        
    </dependency>
    
    <dependency>
    
        <groupId>org.apache.logging.log4j</groupId>
        
        <artifactId>log4j-api</artifactId>
        
        <version>2.14.1</version>
        
    </dependency>
    
    <dependency>
    
        <groupId>org.apache.logging.log4j</groupId>
        
        <artifactId>log4j-core</artifactId>
        
        <version>2.14.1</version>
        
    </dependency>
    
</dependencies>

PROJECT STRUCTURE:

The script is structured into one main class AmazonTest where all the actions and interactions are defined.

KEY SECTIONS:

AmazonTest.java:

Contains the main functionality of the test: logging into Amazon, navigating through sections, applying filters, sorting products, adding products to cart, 

proceeding with checkout, and validating the total amount,called from other classes to make it a clean and organized code when running the task this is the file to click run in.

LOGGING:

Uses Log4j to log actions and errors at each step of the process. This helps in troubleshooting and tracking the script's progress.

WEBDRIVER SETUP:

-The script is configured to use ChromeDriver for browser automation. Make sure to set the chromedriver.exe in the correct path for your system.

How to Run:

-git clone https://github.com/r0gina/Automation-Testing.git

-cd C:\Users\lenovo\IdeaProjects\AZ_Task

-Set up WebDriver: Download ChromeDriver for your platform and place it in the src/test/resources folder (or the location specified in the code).

-Install Maven Dependencies: Run the following command in the root directory to install the necessary dependencies:

-mvn clean install

RUN THE TEST: 
You can run the test using your IDE or via Maven:

mvn test

-EXPECTED OUTPUT:

The script will log into Amazon, navigate to the "Video Games" section, apply filters for free shipping and new conditions, and sort by price. It will then add

products under 15,000 EGP to the cart, proceed to checkout, and validate the total amount.

Logs will be generated throughout the process, and you should see outputs like:

Successfully logged into Amazon.

Successfully navigated to 'All Video Games' section.

Filters applied successfully: Free Shipping and New Condition.

Successfully sorted by price: High to Low.

Finished adding products to cart.

Successfully proceeded to checkout and selected payment method.

Total amount validated successfully.

If there are issues (like missing elements, login failures, etc.), error messages will be logged.


-Key Methods:

 loginToAmazon()
 
Purpose: Logs into Amazon with a specified email and password.

STEPS:

Navigates to Amazon's homepage.

Enters login credentials (email and password).

Handles CAPTCHA if detected.

Logs in using JavaScript to avoid click failures.

 navigateToVideoGames()
 
Purpose: Navigates to the "Video Games" section in the Amazon menu.

STEPS:

Clicks on the hamburger menu.

Clicks on "All Video Games" to view the list of products.

 applyFilters()
 
Purpose: Applies filters to only show products with free shipping and in new condition.

STEPS:

Selects "Free Shipping" filter.

Selects "New Condition" filter.

 sortByPrice()
 
Purpose: Sorts the products by price from high to low.

STEPS:

Clicks the sorting dropdown menu.

Selects the "Price: High to Low" option.

 addAllEligibleProductsToCart()
 
Purpose: Adds all products under 15,000 EGP to the cart.

STEPS:

Iterates over each product on the page.

Checks the price of each product and adds it to the cart if eligible.

Continues to the next page if no products were added on the current page.


 displayCartContents()
 
Purpose: Displays the contents of the shopping cart.

STEPS:

Navigates to the cart page.

Logs the names and prices of items in the cart.

 checkout()
 
Purpose: Proceeds with the checkout process.

STEPS:

Clicks on the cart button.

Clicks on "Proceed to Checkout".

Selects the shipping address and payment method.

 validateTotalAmount()
 
Purpose: Validates that the total amount in the cart is correct.

STEPS:

Extracts the total amount and shipping fee.

Compares it against the expected total (currently a placeholder value of 1000).

Troubleshooting

CAPTCHA Issue: If CAPTCHA appears during login, it will require manual intervention. The script will log a warning and stop.

Element Not Found: If the script cannot find an element, it will log the error, and you can adjust the Xpath or CSS selector based on the actual page structure.

Browser Compatibility: Ensure that you are using the correct version of ChromeDriver for your Chrome browser version.


CONCLUSION

This Amazon automation test Task provides a basic end-to-end test scenario for online shopping using Selenium WebDriver. You can extend it by adding more features

like validating product details, handling different payment options, or testing on different browsers.


