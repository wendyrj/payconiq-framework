package platform.api.actions;

import helpers.RESTRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import testObjects.Gist;

public class CreateGistAction extends BaseAction {
    public CreateGistAction(RequestSpecBuilder requestSpecBuilder, ResponseSpecBuilder responseSpecBuilder) {
        super(requestSpecBuilder, responseSpecBuilder);
    }

    public Gist executeGistPost(Gist requestGist) {
        Response response = executeGistPostResponse(requestGist);

        Gist responseGist = response.getBody().as(Gist.class);

        return responseGist;
    }

    public Response executeGistPostResponse(Gist requestGist) {
        RequestSpecification requestSpecification = requestSpecBuilder
                .setBody(requestGist, ObjectMapperType.JACKSON_2)
                .build();

        ResponseSpecification responseSpecification = responseSpecBuilder.build();

        Response response = RESTRequest
                .postCall("", requestSpecification, responseSpecification);

        return response;
    }
}
