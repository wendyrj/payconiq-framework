package platform.api.actions;

import helpers.RESTRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import testData.Config;
import testObjects.Gist;

public class ReadGistAction extends BaseAction {

    public ReadGistAction(RequestSpecBuilder requestSpecBuilder, ResponseSpecBuilder responseSpecBuilder) {
        super(requestSpecBuilder, responseSpecBuilder);
    }

    public Gist[] executeGetGistByDate(String date) {
        RequestSpecification getRequestSpecification = requestSpecBuilder
                .addQueryParam("since", date)
                .build();

        ResponseSpecification getResponseSpecification = responseSpecBuilder.build();

        Response response = RESTRequest.getCall(getRequestSpecification, getResponseSpecification);

        Gist[] responseGist = response.getBody().as(Gist[].class);
        return responseGist;
    }

    public Gist executeGetGistById(String id) {
        RequestSpecification getRequestSpecification = requestSpecBuilder
                .addPathParam("gist_id", id)
                .build();

        ResponseSpecification getResponseSpecification = responseSpecBuilder.build();

        Response response = RESTRequest.getCall("/{gist_id}", getRequestSpecification, getResponseSpecification);

        Gist responseGist = response.getBody().as(Gist.class);
        return responseGist;
    }

    public Gist[] executeGetPublicGists() {
        RequestSpecification getRequestSpecification = requestSpecBuilder.build();

        ResponseSpecification getResponseSpecification = responseSpecBuilder.build();

        Response response = RESTRequest.getCall("/public", getRequestSpecification, getResponseSpecification);

        Gist[] responseGist = response.getBody().as(Gist[].class);
        return responseGist;
    }

    public Response executeGetGistByUsername() {
        RequestSpecification getRequestSpecification = requestSpecBuilder
                .setBasePath("/users")
                .addPathParam("username", Config.USERNAME)
                .build();

        ResponseSpecification getResponseSpecification = responseSpecBuilder.build();

        Response response = RESTRequest.getCall("{username}/gists", getRequestSpecification, getResponseSpecification);

        return response;
    }
}
