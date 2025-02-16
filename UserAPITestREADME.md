User API Test- README

-OVERVIEW:

This Task provides a test for interacting with the https://reqres.in API,

specifically focusing on the user management functionalities. 

The test cases are written using RestAssured for API interaction, TestNG for organizing and 

executing tests, and Log4j for logging test details.

-YOU'LL FIND THE CODE OF THE TASK IN JAVA CLASS NAMED [UserApiTest] INSIDE THE PROJECT, AS I MADE ONE PROJECT FOR BOTH 2 TASKS, BUT YOU CAN RUN IT INDEPENDENTLY.

TECHNOLOGIES USED:

-RestAssured: A Java library for testing RESTful APIs.

-TestNG: A testing framework for organizing and running tests.

-Log4j: A logging framework used to log test execution details.

-JUnit/Assert: For assertions to validate API responses.

-API Endpoints Tested

-Create User (POST /api/users): Creates a new user with specific details.

-Retrieve User (GET /api/users/{id}): Retrieves details of an existing user based on user ID.

-Update User (PUT /api/users/{id}): Updates details of an existing user based on user ID.

-Handle Error Scenario (GET /api/users/{invalid_id}): Verifies the error response for non-existing users.

TEST CASES:

-createUser()

Description: Creates a new user with the name "Rogina", job as "QA Engineer", and age as 23.

Assert: Checks if the user is created successfully (Status code: 201). Logs the created user’s ID.

-retrieveUser()

Description: Retrieves user details for a user with ID "7".

Assert: Validates that the retrieved user’s first name, last name, email, and avatar are correct. (Status code: 200)

-updateUser()

Description: Updates the user with ID "7", changing the job to "Senior QA Engineer".

Assert: Checks that the user’s job has been updated correctly (Status code: 200).

-handleErrorScenario()

Description: Tries to retrieve a non-existing user with ID "99999".

Assert: Verifies the response status is 404 to handle error scenarios correctly.

SETUP:

Dependencies: Make sure you have the following dependencies in your pom.xml (Maven):

<dependencies>
  
    <!-- RestAssured Dependency -->
    
    <dependency>
    
        <groupId>io.rest-assured</groupId>
        
        <artifactId>rest-assured</artifactId>
        
        <version>5.3.0</version> <!-- Use the latest version available -->
        
        <scope>test</scope>
        
    </dependency>

    <!-- TestNG Dependency -->
    
    <dependency>
    
        <groupId>org.testng</groupId>
        
        <artifactId>testng</artifactId>
        
        <version>7.7.0</version> <!-- Use the latest version available -->
        
        <scope>test</scope>
        
    </dependency>

    <!-- Log4j Dependency -->
    
    <dependency>
    
        <groupId>org.apache.logging.log4j</groupId>
        
        <artifactId>log4j-api</artifactId>
        
        <version>2.17.1</version> <!-- Use the latest version available -->
        
    </dependency>
    
    <dependency>
    
        <groupId>org.apache.logging.log4j</groupId>
        
        <artifactId>log4j-core</artifactId>
        
        <version>2.17.1</version> <!-- Use the latest version available -->
        
    </dependency>
    
</dependencies>






CONFIGURATION: 

Base URL for the API is set to https://reqres.in.

Running the Tests

Using TestNG:

The tests can be executed using TestNG's testng.xml or by running each test method individually.

Log Output: Test logs will be printed using Log4j.

SAMPLE LOGS:

For a successful user creation, the following log will appear:


INFO: User created successfully with ID: 698

For a failed user retrieval (non-existent user):


ERROR: Attempted to retrieve non-existing user with ID: 99999

CONCLUSION
This test suite provides basic functionality to ensure the API behaves correctly for the user-related endpoints, including successful creation, retrieval, update, and error handling for non-existing users.

