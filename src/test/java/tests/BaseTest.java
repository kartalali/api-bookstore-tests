package tests;

import client.RestClient;
import service.BookService;
import org.junit.jupiter.api.BeforeEach;

import java.util.Locale;

public abstract class BaseTest {
    protected BookService bookService;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.ENGLISH);
        RestClient restClient = new RestClient();
        bookService = new BookService(restClient.spec());
    }
}