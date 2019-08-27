package platform.api.tests;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import testData.Config;
import testObjects.Gist;
import testObjects.GistBuilder;
import utils.Logger;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;

public class ReadGistTests extends BaseAPITest {

    @Test(priority = 2, description = "Test the read operation on Gist by ID")
    public void getGistByUserById() {
        Gist gistCreated = createGistAction.executeGistPost(GistBuilder.generateRandomGist());
        Logger.addStep("New Gist created with Id " + gistCreated.getId());

        responseSpecBuilder.expectStatusCode(200)
                .expectHeader("Cache-Control", containsString("private"))
                .expectBody(matchesJsonSchemaInClasspath("postResponse.json"));

        Gist responseGist = readGistAction.executeGetGistById(gistCreated.getId());

        gistMacro.checkGistCreation(gistCreated, responseGist);
        Logger.addStep("Validated the information obtained by Gist Id");
    }

    @Test(priority = 1, description = "Test the read operation in all the Gists of an authenticated user since the specified date")
    public void getGistByUserAuthByDateTest() {
        Gist gistCreated = createGistAction.executeGistPost(GistBuilder.generateRandomGist());
        Logger.addStep("New Gist created with Id " + gistCreated.getId());

        responseSpecBuilder.expectStatusCode(200)
                .expectHeader("Cache-Control", containsString("private"))
                .expectBody(matchesJsonSchemaInClasspath("getResponse.json"));

        Gist[] responseGists = readGistAction.executeGetGistByDate(gistCreated.getUpdated_at());
        Logger.addStep("Read the information from the authenticated user since " + gistCreated.getUpdated_at() + " of the new Gist");

        Assert.assertTrue(responseGists.length == 1);
        gistMacro.checkGistRead(gistCreated, responseGists[0]);
        Logger.addStep("Validated the information obtained of the Gist created since " + gistCreated.getUpdated_at());
    }

    @Test(priority = 3, description = "Test the read operation of all public Gists")
    public void getAllPublicGists() {
        responseSpecBuilder.expectStatusCode(200)
                .expectHeader("Cache-Control", containsString("private"))
                .expectBody(matchesJsonSchemaInClasspath("getResponse.json"));

        Gist[] responseGists = readGistAction.executeGetPublicGists();
        Logger.addStep("Public Gists information read from a total of " + responseGists.length + " gists");

        gistMacro.checkGistIsPublicField(responseGists);
        Logger.addStep("Validated that all the obtained gists are public");
    }

    @Test(priority = 4, description = "Test the read operation of all the Gists by username from a user with Gists created")
    public void getGistsByUsername() {
        responseSpecBuilder.expectStatusCode(200)
                .expectHeader("Cache-Control", containsString("private"))
                .expectBody(matchesJsonSchemaInClasspath("getResponse.json"));

        Response response = readGistAction.executeGetGistByUsername();
        Logger.addStep("Read the information of all Gists by username " + Config.USERNAME);

        JSONArray JSONResponseBody = new JSONArray(response.body().asString());

        for (int i = 0; i < JSONResponseBody.length(); i++) {
            Assert.assertEquals(JSONResponseBody.getJSONObject(i).getJSONObject("owner").getString("login"), Config.USERNAME);
        }
        Logger.addStep("Validated that the owner in all the returned Gists is " + Config.USERNAME);
    }
}
