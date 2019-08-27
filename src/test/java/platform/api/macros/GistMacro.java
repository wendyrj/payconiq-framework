package platform.api.macros;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import testObjects.Gist;
import testObjects.GistFile;

import java.util.Iterator;
import java.util.Map;

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

    public void checkGistFileUpdate( Map<String, GistFile> updated,  Map<String, GistFile> response){
        Iterator<GistFile> iterator = updated.values().iterator();
        while (iterator.hasNext()){
            GistFile gistFile = iterator.next();
            Assert.assertTrue(response.containsValue(gistFile));
        }
    }

    public void checkGistIsPublicField(Gist[] responseGists){
        for (int i = 0; i < responseGists.length; i++) {
            Assert.assertTrue(responseGists[i].isPublicField());
        }
    }

    public void doAssert() {
        softAssert.assertAll();
    }
}
