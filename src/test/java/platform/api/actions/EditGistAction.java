package platform.api.actions;

import helpers.RESTRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import testObjects.Gist;

public class EditGistAction extends BaseAction {

    public EditGistAction(RequestSpecBuilder requestSpecBuilder, ResponseSpecBuilder responseSpecBuilder) {
        super(requestSpecBuilder, responseSpecBuilder);
    }

    public Gist executePatchUpdateGistById(Gist gistToUpdate) {
        RequestSpecification getRequestSpecification = requestSpecBuilder
                .addPathParam("gist_id", gistToUpdate.getId())
                .setBody(gistToUpdate, ObjectMapperType.JACKSON_2)
                .build();

        ResponseSpecification getResponseSpecification = responseSpecBuilder.build();

        Response response = RESTRequest.patchCall("/{gist_id}", getRequestSpecification, getResponseSpecification);

        Gist responseGist = response.getBody().as(Gist.class);
        return responseGist;
    }
}
