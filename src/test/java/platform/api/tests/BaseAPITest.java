package platform.api.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import platform.api.actions.CreateGistAction;
import platform.api.actions.DeleteGistAction;
import platform.api.actions.EditGistAction;
import platform.api.actions.ReadGistAction;
import platform.api.macros.GistMacro;
import testData.Config;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class BaseAPITest {
    protected RequestSpecBuilder requestSpecBuilder;
    protected ResponseSpecBuilder responseSpecBuilder;

    protected CreateGistAction createGistAction;
    protected ReadGistAction readGistAction;
    protected EditGistAction editGistAction;
    protected DeleteGistAction deleteGistAction;
    protected GistMacro gistMacro;

    public BaseAPITest() {
        RestAssured.baseURI = Config.BASE_URL;
        RestAssured.basePath = Config.BASE_PATH;
    }

    public void instantiateValidators() {
        gistMacro = new GistMacro();
    }

    public void instantiateActions() {
        createGistAction = new CreateGistAction(requestSpecBuilder, responseSpecBuilder);
        readGistAction = new ReadGistAction(requestSpecBuilder, responseSpecBuilder);
        editGistAction = new EditGistAction(requestSpecBuilder, responseSpecBuilder);
        deleteGistAction = new DeleteGistAction(requestSpecBuilder, responseSpecBuilder);
    }

    public void instantiateSpecBuilders() {
        this.requestSpecBuilder = new RequestSpecBuilder()
                .addHeader("Authorization", Config.AUTHORIZATION);

        this.responseSpecBuilder = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectHeader("Server", Config.RESPONSE_SERVER)
                .expectHeader("X-OAuth-Scopes", containsString("gist"))
                .expectResponseTime(lessThan(5000L), TimeUnit.MILLISECONDS);
    }

    @BeforeMethod
    public void setUp() {
        instantiateSpecBuilders();
        instantiateActions();
        instantiateValidators();
    }
}
