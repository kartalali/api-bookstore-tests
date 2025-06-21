package crud;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;

import static io.restassured.RestAssured.given;

@RequiredArgsConstructor
public abstract class AbstractCrudService<T> implements CrudEndpoint<T> {

    private final RequestSpecification spec;
    private final String basePath;

    @Override public Response getAll()               { return request().get(basePath); }

    @Override public Response getById(int id)        { return request().get(basePath + "/{id}", id); }

    @Override public Response create(T dto)          { return request().body(dto).post(basePath); }

    @Override public Response update(int id, T dto)  { return request().body(dto).put(basePath + "/{id}", id); }

    @Override public Response delete(int id)         { return request().delete(basePath + "/{id}", id); }

    private io.restassured.specification.RequestSpecification request() {
        return given().spec(spec);
    }
}

