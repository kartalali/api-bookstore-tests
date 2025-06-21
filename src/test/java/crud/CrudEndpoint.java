package crud;

import io.restassured.response.Response;

public interface CrudEndpoint<T> {

    Response getAll();

    Response getById(int id);

    Response create(T dto);

    Response update(int id, T dto);

    Response delete(int id);
}

