package helpers;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class RESTRequest {

    public static Response getCall(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        Response response =
                given(requestSpecification, responseSpecification)
                        .get()
                        .then().log().all()
                        .extract()
                        .response();

        return response;
    }

    public static Response getCall(String resource, RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        Response response =
                given(requestSpecification, responseSpecification)
                        .get(resource)
                        .then().log().all()
                        .extract()
                        .response();

        return response;
    }

    public static Response postCall(String resource, RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        Response response =
                given(requestSpecification, responseSpecification)
                        .post(resource)
                        .then().log().all()
                        .extract()
                        .response();

        return response;
    }

    public static Response patchCall(String resource, RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        Response response =
                given(requestSpecification, responseSpecification)
                        .patch(resource)
                        .then().log().all()
                        .extract()
                        .response();

        return response;
    }

    public static Response deleteCall(String resource, RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        Response response =
                given(requestSpecification, responseSpecification)
                        .delete(resource)
                        .then().log().all()
                        .extract()
                        .response();

        return response;
    }
}
