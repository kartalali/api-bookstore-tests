package service;

import client.RestClient;
import dto.Author;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthorService extends RestClient {

    private static final String BASE_PATH = "/api/v1/Authors";

    public Response getAll() {
        return given().spec(spec())
                .when().get(BASE_PATH);
    }

    public Response getById(int id) {
        return given().spec(spec())
                .pathParam("id", id)
                .when().get(BASE_PATH + "/{id}");
    }

    public Response create(Author author) {
        return given().spec(spec())
                .body(author)
                .when().post(BASE_PATH);
    }

    public Response update(int id, Author author) {
        return given().spec(spec())
                .pathParam("id", id)
                .body(author)
                .when().put(BASE_PATH + "/{id}");
    }

    public Response delete(int id) {
        return given().spec(spec())
                .pathParam("id", id)
                .when().delete(BASE_PATH + "/{id}");
    }
}
