package platform.api.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import testObjects.Gist;
import testObjects.GistBuilder;
import utils.Logger;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;

public class DeleteGistTests extends BaseAPITest {

    @Test(description = "Test the Deletion of an existing Gist by Id")
    public void deleteGistById() {
        Gist gistToCreate = GistBuilder.generateRandomGist();

        Logger.addStep("Create a new Gist");
        Gist gistCreated = createGistAction.executeGistPost(gistToCreate);

        responseSpecBuilder
                .expectStatusCode(204)
                .expectContentType("application/octet-stream");

        deleteGistAction.executeDeleteGist(gistCreated.getId());
    }

    @Test(description = "Test Deletion of a non-existing Gist")
    public void deleteNonExistingGistById() {
        Gist gistToCreate = GistBuilder.generateRandomGist();

        Logger.addStep("Create a new Gist");
        Gist gistCreated = createGistAction.executeGistPost(gistToCreate);

        responseSpecBuilder.expectStatusCode(204)
                .expectContentType("application/octet-stream");

        deleteGistAction.executeDeleteGist(gistCreated.getId());

        responseSpecBuilder.expectStatusCode(404)
                .expectContentType(ContentType.JSON)
                .expectBody(matchesJsonSchemaInClasspath("postInvalidRequestMessageResponse.json"))
                .expectBody("message", containsString("Not Found"));

        deleteGistAction.executeDeleteGist(gistCreated.getId());
    }

    @Test(description = "Test Deletion of a Gist not providing Id")
    public void deleteGistWithoutId() {
        responseSpecBuilder.expectStatusCode(404)
                .expectContentType(ContentType.JSON)
                .expectBody(matchesJsonSchemaInClasspath("postInvalidRequestMessageResponse.json"))
                .expectBody("message", containsString("Not Found"));

        deleteGistAction.executeDeleteGist("");
    }
}
