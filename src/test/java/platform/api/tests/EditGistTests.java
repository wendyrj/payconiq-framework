package platform.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import testObjects.Gist;
import testObjects.GistBuilder;
import testObjects.GistFile;
import utils.Logger;
import utils.RandomUtils;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;

public class EditGistTests extends BaseAPITest {

    @Test(description = "Test the edition of an existing Gist with a different description and files")
    public void editGistTest() {
        Gist gistCreated = createGistAction.executeGistPost(GistBuilder.generateRandomGist());
        Logger.addStep("New Gist created with Id" + gistCreated.getId());

        gistCreated.setDescription("New Description " + RandomUtils.randomFromChoices());
        Map<String, GistFile> createdGistFilesMap = RandomUtils.generateRandomFileMap(3);
        gistCreated.setFiles(createdGistFilesMap);
        Logger.addStep("Description and Files fields updated to Gist with Id" + gistCreated.getId());

        responseSpecBuilder.expectStatusCode(200)
                .expectHeader("Cache-Control", containsString("private"))
                .expectBody(matchesJsonSchemaInClasspath("postResponse.json"));

        Gist responseGist = editGistAction.executePatchUpdateGistById(gistCreated);
        Assert.assertEquals(gistCreated.getDescription(), responseGist.getDescription());
        gistMacro.checkGistFileUpdate(createdGistFilesMap, responseGist.getFiles());
        Logger.addStep("Validated description and files updated successfully");
    }
}
