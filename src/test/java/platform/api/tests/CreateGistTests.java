package platform.api.tests;

import org.testng.annotations.Test;
import testObjects.Gist;
import testObjects.GistBuilder;
import utils.Logger;
import utils.RandomUtils;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;

public class CreateGistTests extends BaseAPITest {

    @Test(description = "Test the creation of new Gists with valid data")
    public void postCreateGistTest() {
        Gist requestGist = GistBuilder.generateRandomGist();

        responseSpecBuilder.expectStatusCode(201)
                .expectHeader("Cache-Control", containsString("private"))
                .expectBody(matchesJsonSchemaInClasspath("postResponse.json"));

        Logger.addStep("Executed the Post to create the Gist");
        Gist responseGist = createGistAction.executeGistPost(requestGist);

        gistMacro.checkGistCreation(requestGist, responseGist);
        Logger.addStep("Gist created and Post data validated");
    }

    @Test(description = "Test the validation message for creation intent without required field")
    public void postCreateGistWithoutFiles() {
        Gist requestGist = new GistBuilder().withDescription("Test").build();

        responseSpecBuilder.expectStatusCode(422)
                .expectBody(matchesJsonSchemaInClasspath("postInvalidRequestMessageResponse.json"))
                .expectBody("message", containsString("Invalid request."));

        createGistAction.executeGistPostResponse(requestGist);
        Logger.addStep("Error message validated successfully");
    }

    @Test(description = "Test the validation message for invalid description with more than 256 characters " +
                        "**NOTE** it does actually accept up to 1000 without error even though the validation requirements is max 256")
    public void postCreateGistWithInvalidDescription() {
        Gist requestGist = GistBuilder.withFiles(RandomUtils.generateRandomFileMap(1))
                .withDescription(RandomUtils.generateRandomString(1500))
                .build();

        responseSpecBuilder.expectStatusCode(422)
                .expectBody(matchesJsonSchemaInClasspath("postInvalidDescriptionMessageResponse.json"))
                .expectBody("message", containsString("Validation Failed"));

        createGistAction.executeGistPostResponse(requestGist);
        Logger.addStep("Error message validated successfully");
    }
}
