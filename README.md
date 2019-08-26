# payconiq-framework
**Payconiq framework** project automates the API Regression suite for the REST API v3 "GistHub Gists" reducing the manual testing time and increasing the automation coverage.

## API Documentation
Gist API documentation is available at [this link](https://developer.github.com/v3/gists)

 ## Environment Setup 
- Maven 3.6.1
- JDK 11.0.2

## Dependencies
- TestNG 6.14.3
- Rest Assured 4.0.0

## How to run
```
mvn clean install
```

*Run all the tests*
```
mvn clean test
```

*Run an specific test class*
```
mvn -Dtest=ReadGistTests test
```

*Run an specific test method*
```
mvn -Dtest=ReadGistTests#getGistByUserById test
```
## Test Arquitecture
**Macros**
- Contains logic to validate actions on a business domain.

**Actions**
- Contains methods responsible of executing operations over the API.

**Tests**
- Contains the test cases for the product.

## Code Example
```
  @Test(priority = 2, description = "Test the read operation on Gist by ID")
    public void getGistByUserById() {
        Gist gistCreated = createGistAction.executeGistPost(GistBuilder.generateRandomGist());
        Logger.addStep("New Gist created with Id " + gistCreated.getId());

        responseSpecBuilder.expectStatusCode(200)
                .expectHeader("Cache-Control", containsString("private"))
                .expectBody(matchesJsonSchemaInClasspath("postResponse.json"));

        Gist responseGist = readGistAction.executeGetGistById(gistCreated.getId());

        verifyGist.postValidate(gistCreated, responseGist);
        Logger.addStep("Validated the information obtained by Gist Id");
    }
```

