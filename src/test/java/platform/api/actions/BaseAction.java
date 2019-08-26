package platform.api.actions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

public class BaseAction {
    protected RequestSpecBuilder requestSpecBuilder;
    protected ResponseSpecBuilder responseSpecBuilder;

    public BaseAction(RequestSpecBuilder requestSpecBuilder, ResponseSpecBuilder responseSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.responseSpecBuilder = responseSpecBuilder;
    }

}
