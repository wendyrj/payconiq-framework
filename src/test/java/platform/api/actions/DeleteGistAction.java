package platform.api.actions;

import helpers.RESTRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class DeleteGistAction extends BaseAction {
    public DeleteGistAction(RequestSpecBuilder requestSpecBuilder, ResponseSpecBuilder responseSpecBuilder) {
        super(requestSpecBuilder, responseSpecBuilder);
    }

    public Response executeDeleteGist(String id) {
        RequestSpecification requestSpecification = requestSpecBuilder
                .addPathParam("gist_id", id)
                .build();

        ResponseSpecification responseSpecification = responseSpecBuilder.build();

        return RESTRequest.deleteCall("/{gist_id}", requestSpecification, responseSpecification);
    }
}
