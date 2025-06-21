package tests;

import dto.Book;
import extentReport.CustomHtmlLoggerExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.HtmlLogManager;

@ExtendWith(CustomHtmlLoggerExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Books API - Edge Case Test Scenarios")
public class BookEdgeCaseTest extends BaseTest {

    @Test
    @DisplayName("GET /Books/{id} with negative id should return error")
    @Order(1)
    void getBookWithNegativeIdShouldFail() {
        HtmlLogManager.onStart("GET /Books/{id} with negative id edge case");
        int negativeId = -1;
        HtmlLogManager.step("Sending GET request with id: " + negativeId);
        try {
            bookService.getById(negativeId)
                    .then().statusCode(404);
            HtmlLogManager.pass("GET with negative id returned 404 as expected");
        } catch (AssertionError e) {
            HtmlLogManager.fail("GET with negative id did not return expected 404. Error: " + e.getMessage());
            throw e;
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("GET /Books/{id} with id zero should return error")
    @Order(2)
    void getBookWithZeroIdShouldFail() {
        HtmlLogManager.onStart("GET /Books/{id} with id zero edge case");
        int zeroId = 0;
        HtmlLogManager.step("Sending GET request with id: " + zeroId);
        try {
            bookService.getById(zeroId)
                    .then().statusCode(404);
            HtmlLogManager.pass("GET with id zero returned 404 as expected");
        } catch (AssertionError e) {
            HtmlLogManager.fail("GET with id zero did not return expected 404. Error: " + e.getMessage());
            throw e;
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("POST /Books with missing required fields should fail")
    @Order(3)
    void createBookWithMissingFieldsShouldFail() {
        HtmlLogManager.onStart("POST /Books with missing required fields edge case");
        Book incompleteBook = new Book(0, "", null, 100, "Some excerpt", "2021-01-01T00:00:00Z");
        HtmlLogManager.step("Sending POST request with incomplete book data");
        try {
            bookService.create(incompleteBook)
                    .then().statusCode(400);
            HtmlLogManager.pass("POST with missing fields returned error as expected");
        } catch (AssertionError e) {
            HtmlLogManager.fail("POST with missing fields did not return expected error. Error: " + e.getMessage());
            throw e;
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("POST /Books with overly long title should fail")
    @Order(4)
    void createBookWithOverlyLongTitleShouldFail() {
        HtmlLogManager.onStart("POST /Books with overly long title edge case");
        String longTitle = "A".repeat(10000);
        Book longTitleBook = new Book(0, longTitle, "Valid Description", 150, "Excerpt", "2021-01-01T00:00:00Z");
        HtmlLogManager.step("Sending POST request with overly long title");
        try {
            bookService.create(longTitleBook)
                    .then().statusCode(400);
            HtmlLogManager.pass("POST with overly long title returned error as expected");
        } catch (AssertionError e) {
            HtmlLogManager.fail("POST with overly long title did not return expected error. Error: " + e.getMessage());
            throw e;
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("PUT /Books with extreme id should fail")
    @Order(5)
    void updateBookWithExtremeIdShouldFail() {
        HtmlLogManager.onStart("PUT /Books with extreme id edge case");
        int extremeId = Integer.MAX_VALUE;
        Book validBook = new Book(extremeId, "Title", "Description", 200, "Excerpt", "2021-01-01T00:00:00Z");
        HtmlLogManager.step("Sending PUT request for book with id: " + extremeId);
        try {
            bookService.update(extremeId, validBook)
                    .then().statusCode(404);
            HtmlLogManager.pass("PUT with extreme id returned 404 as expected");
        } catch (AssertionError e) {
            HtmlLogManager.fail("PUT with extreme id did not return expected 404. Error: " + e.getMessage());
            throw e;
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("DELETE /Books with extreme id should fail")
    @Order(6)
    void deleteBookWithExtremeIdShouldFail() {
        HtmlLogManager.onStart("DELETE /Books with extreme id edge case");
        int extremeId = Integer.MAX_VALUE;
        HtmlLogManager.step("Sending DELETE request for book with id: " + extremeId);
        try {
            bookService.delete(extremeId)
                    .then().statusCode(404);
            HtmlLogManager.pass("DELETE with extreme id returned 404 as expected");
        } catch (AssertionError e) {
            HtmlLogManager.fail("DELETE with extreme id did not return expected 404. Error: " + e.getMessage());
            throw e;
        } finally {
            HtmlLogManager.clear();
        }
    }
}