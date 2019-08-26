package platform.api.macros;

import io.restassured.RestAssured;
import org.testng.asserts.SoftAssert;
import testObjects.Gist;
import testObjects.GistFile;

public class GistMacro {
    private SoftAssert softAssert;

    public GistMacro() {
        softAssert = new SoftAssert();
    }

    public void checkGistCreation(Gist requestGist, Gist responseGist) {
        softAssert.assertEquals(responseGist.getUrl(), RestAssured.baseURI + RestAssured.basePath + "/" + responseGist.getId());
        softAssert.assertEquals(requestGist.getDescription(), responseGist.getDescription());
        softAssert.assertEquals(requestGist.isPublicField(), responseGist.isPublicField());
        softAssert.assertTrue(requestGist.getFiles().equals(responseGist.getFiles()));

        doAssert();
    }

    public void checkGistRead(Gist requestGist, Gist responseGist) {
        softAssert.assertEquals(responseGist.getUrl(), RestAssured.baseURI + RestAssured.basePath + "/" + responseGist.getId());
        softAssert.assertEquals(requestGist.getDescription(), responseGist.getDescription());
        softAssert.assertEquals(requestGist.isPublicField(), responseGist.isPublicField());
        softAssert.assertTrue(responseGist.getFiles().keySet().equals(requestGist.getFiles().keySet()));

        for (GistFile entry : responseGist.getFiles().values()) {
            softAssert.assertNull(entry.getContent());
        }
        doAssert();
    }

    public void doAssert() {
        softAssert.assertAll();
    }
}
