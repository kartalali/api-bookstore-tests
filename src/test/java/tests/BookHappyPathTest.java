package tests;

import data.BookFactory;
import dto.Book;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.HtmlLogManager;
import extentReport.CustomHtmlLoggerExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Books API CRUD – Happy Path Tests")
@ExtendWith(CustomHtmlLoggerExtension.class)
class BookHappyPathTest extends BaseTest {

    @Test
    @DisplayName("POST /Books preserves request body")
    @Order(1)
    void createShouldReturnSameBody() {
        try {
            HtmlLogManager.step("Generating test data");
            Book book = BookFactory.random();

            HtmlLogManager.step("Sending POST /Books request");
            String publishDate = bookService.create(book)
                    .then().statusCode(200)
                    .body("id", Matchers.equalTo(book.id()))
                    .body("title", Matchers.equalTo(book.title()))
                    .extract().path("publishDate");

            HtmlLogManager.step("Validating publishDate equality");
            Instant expected = Instant.parse(book.publishDate()).truncatedTo(ChronoUnit.MILLIS);
            Instant actual = Instant.parse(publishDate).truncatedTo(ChronoUnit.MILLIS);
            Assertions.assertEquals(expected, actual);

            HtmlLogManager.pass("POST /Books test passed successfully");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("POST /Books test failed: " + e.getMessage());
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("GET /Books always returns 200 and a JSON array")
    @Order(2)
    void getAllShouldReturnArray() {
        try {
            HtmlLogManager.step("Sending GET /Books request");
            bookService.getAll()
                    .then().statusCode(200)
                    .contentType("application/json")
                    .body("$", Matchers.isA(java.util.List.class))
                    .body("size()", Matchers.greaterThan(0));
            HtmlLogManager.pass("GET /Books test passed successfully");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("GET /Books test failed: " + e.getMessage());
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("GET /Books/{id} returns 200 for an existing book (ID: 1)")
    @Order(3)
    void getExistingBookById() {
        try {
            HtmlLogManager.step("Sending GET /Books/1 request");
            bookService.getById(1)
                    .then().statusCode(200)
                    .body("id", Matchers.equalTo(1));
            HtmlLogManager.pass("GET /Books/{id} test passed successfully");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("GET /Books/{id} test failed: " + e.getMessage());
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("PUT /Books/{id} updates record (ID: 2) and returns 200")
    @Order(4)
    void updateExistingBook() {
        try {
            HtmlLogManager.step("Generating updated data for book ID:2");
            Book updated = BookFactory.randomWithId(2);

            HtmlLogManager.step("Sending PUT /Books/2 request");
            bookService.update(2, updated)
                    .then().statusCode(200)
                    .body("id", Matchers.equalTo(2))
                    .body("title", Matchers.equalTo(updated.title()));
            HtmlLogManager.pass("PUT /Books/{id} test passed successfully");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("PUT /Books/{id} test failed: " + e.getMessage());
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("DELETE /Books/{id} returns 200 or 204 for an existing book (ID: 3)")
    @Order(5)
    void deleteExistingBook() {
        try {
            HtmlLogManager.step("Sending DELETE /Books/3 request");
            bookService.delete(3)
                    .then().statusCode(Matchers.anyOf(Matchers.is(200), Matchers.is(204)));
            HtmlLogManager.pass("DELETE /Books test passed successfully");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("DELETE /Books test failed: " + e.getMessage());
            Assertions.fail(e);
        }
    }
}