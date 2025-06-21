package service;


import crud.AbstractCrudService;
import dto.Book;
import io.restassured.specification.RequestSpecification;

public final class BookService extends AbstractCrudService<Book> {

    public BookService(RequestSpecification spec) {
        super(spec, "/api/v1/Books");
    }
}
