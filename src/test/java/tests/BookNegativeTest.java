package tests;

import data.BookFactory;
import dto.Book;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.HtmlLogManager;
import extentReport.CustomHtmlLoggerExtension;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Books API - Negative Test Cases")
@ExtendWith(CustomHtmlLoggerExtension.class)
public class BookNegativeTest extends BaseTest {

    @Test
    @DisplayName("POST /Books with invalid data returns 400")
    @Order(1)
    void createWithInvalidDataShouldReturn400() {
        HtmlLogManager.onStart("POST /Books with invalid data returns 400");
        try {
            HtmlLogManager.step("Generating invalid test data");
            Book invalidBook = BookFactory.invalid();

            HtmlLogManager.step("Sending POST /Books request with invalid data");
            bookService.create(invalidBook)
                    .then().statusCode(400);

            HtmlLogManager.pass("POST with invalid data returned 400 as expected");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("POST with invalid data test failed: " + e.getMessage());
            Assertions.fail(e);
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("GET /Books/{id} with non-existent id returns 404")
    @Order(2)
    void getNonExistentBookShouldReturn404() {
        HtmlLogManager.onStart("GET /Books/{id} with non-existent id returns 404");
        try {
            int nonExistentId = 999999;
            HtmlLogManager.step("Sending GET /Books/" + nonExistentId + " request");
            bookService.getById(nonExistentId)
                    .then().statusCode(404);

            HtmlLogManager.pass("GET with non-existent id returned 404 as expected");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("GET with non-existent id test failed: " + e.getMessage());
            Assertions.fail(e);
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("PUT /Books/{id} for non-existent book returns 404")
    @Order(3)
    void updateNonExistentBookShouldReturn404() {
        HtmlLogManager.onStart("PUT /Books/{id} for non-existent book returns 404");
        try {
            int nonExistentId = 888888;
            HtmlLogManager.step("Generating updated data for non-existent book id: " + nonExistentId);
            Book updated = BookFactory.randomWithId(nonExistentId);

            HtmlLogManager.step("Sending PUT /Books/" + nonExistentId + " request");
            bookService.update(nonExistentId, updated)
                    .then().statusCode(404);

            HtmlLogManager.pass("PUT for non-existent book returned 404 as expected");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("PUT for non-existent book test failed: " + e.getMessage());
            Assertions.fail(e);
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("DELETE /Books/{id} for non-existent book returns 404")
    @Order(4)
    void deleteNonExistentBookShouldReturn404() {
        HtmlLogManager.onStart("DELETE /Books/{id} for non-existent book returns 404");
        try {
            int nonExistentId = 777777;
            HtmlLogManager.step("Sending DELETE /Books/" + nonExistentId + " request");
            bookService.delete(nonExistentId)
                    .then().statusCode(404);

            HtmlLogManager.pass("DELETE for non-existent book returned 404 as expected");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("DELETE for non-existent book test failed: " + e.getMessage());
            Assertions.fail(e);
        } finally {
            HtmlLogManager.clear();
        }
    }
}