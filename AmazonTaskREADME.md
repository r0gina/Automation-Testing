Amazon Automation Test with Selenium
Overview
This Task automates an end-to-end shopping process on Amazon using Selenium WebDriver. The script logs in to Amazon, applies filters, sorts products, adds eligible items to the cart, proceeds to checkout, and validates the total amount.

Requirements:
Java 8+
Maven for dependency management
Selenium WebDriver
ChromeDriver
Log4j for logging
Setup & Run
Clone the repository:

bash
Copy
git clone https://github.com/r0gina/Automation-Testing.git
cd C:\Users\lenovo\IdeaProjects\AZ_Task
Set up WebDriver:
Download ChromeDriver and place it in the src/test/resources folder.

Install dependencies:
bash
Copy
mvn clean install

Or 
include the following dependencies in your pom.xml file:


Run the test:
bash
Copy
mvn test

Key Methods
loginToAmazon(): Logs into Amazon with given credentials.

navigateToVideoGames(): Navigates to the "Video Games" section.

applyFilters(): Applies filters (Free Shipping, New Condition).

sortByPrice(): Sorts products by price (High to Low).

addAllEligibleProductsToCart(): Adds products under 15,000 EGP to the cart.

displayCartContents(): Displays cart items and prices.

checkout(): Proceeds to checkout and selects payment method.

validateTotalAmount(): Validates total amount in the cart.

Conclusion
This script provides a basic Amazon shopping flow with Selenium. It's easily extensible and can be adapted for additional features or browsers.
