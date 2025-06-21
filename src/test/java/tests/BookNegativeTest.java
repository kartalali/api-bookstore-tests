package tests;

import data.BookFactory;
import dto.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

        HtmlLogManager.step("Generating invalid test data");
        Book invalidBook = BookFactory.invalid();

        HtmlLogManager.step("Sending POST /Books request with invalid data");
        bookService.create(invalidBook)
                .then().statusCode(400);

        HtmlLogManager.pass("POST with invalid data returned 400 as expected");
        HtmlLogManager.clear();
    }


    @Test
    @DisplayName("GET /Books/{id} with non-existent id returns 404")
    @Order(2)
    void getNonExistentBookShouldReturn404() {
        HtmlLogManager.onStart("GET /Books/{id} with non-existent id returns 404");

        int nonExistentId = 999999;
        HtmlLogManager.step("Sending GET /Books/" + nonExistentId + " request");
        bookService.getById(nonExistentId)
                .then().statusCode(404);

        HtmlLogManager.pass("GET with non-existent id returned 404 as expected");
        HtmlLogManager.clear();
    }

    @Test
    @DisplayName("PUT /Books/{id} for non-existent book returns 404")
    @Order(3)
    void updateNonExistentBookShouldReturn404() {
        HtmlLogManager.onStart("PUT /Books/{id} for non-existent book returns 404");

        int nonExistentId = 888888;
        HtmlLogManager.step("Generating updated data for non-existent book id: " + nonExistentId);
        Book updated = BookFactory.randomWithId(nonExistentId);
        HtmlLogManager.step("Sending PUT /Books/" + nonExistentId + " request");
        try {
        bookService.update(nonExistentId, updated)
                .then().statusCode(404);

        HtmlLogManager.pass("PUT for non-existent book returned 404 as expected");
        } catch (AssertionError e) {
            HtmlLogManager.fail("Expected status code <404> but received a different status. " + e.getMessage());
            throw e;
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("DELETE /Books/{id} for non-existent book returns 404")
    @Order(4)
    void deleteNonExistentBookShouldReturn404() {
        HtmlLogManager.onStart("DELETE /Books/{id} for non-existent book returns 404");

        int nonExistentId = 777777;
        HtmlLogManager.step("Sending DELETE /Books/" + nonExistentId + " request");
        try {
            bookService.delete(nonExistentId)
                    .then().statusCode(404);
            HtmlLogManager.pass("DELETE for non-existent book returned 404 as expected");
        } catch (AssertionError e) {
            HtmlLogManager.fail("Expected status code <404> but received a different status. " + e.getMessage());
            throw e;
        } finally {
            HtmlLogManager.clear();
        }
    }
}