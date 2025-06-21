package tests;

import dto.Book;
import extentReport.CustomHtmlLoggerExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.HtmlLogManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Books API - Edge Case Test Scenarios")
@ExtendWith(CustomHtmlLoggerExtension.class)
public class BookEdgeCaseTest extends BaseTest {

    @Test
    @DisplayName("GET /Books/{id} with negative id should return error")
    @Order(1)
    void getBookWithNegativeIdShouldFail() {
        HtmlLogManager.onStart("GET /Books/{id} with negative id edge case");
        try {
            int negativeId = -1;
            HtmlLogManager.step("Sending GET request with id: " + negativeId);
            bookService.getById(negativeId)
                    .then().statusCode(404);
            HtmlLogManager.pass("GET with negative id returned 404 as expected");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("GET with negative id test failed: " + e.getMessage());
            Assertions.fail(e);
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("GET /Books/{id} with id zero should return error")
    @Order(2)
    void getBookWithZeroIdShouldFail() {
        HtmlLogManager.onStart("GET /Books/{id} with id zero edge case");
        try {
            int zeroId = 0;
            HtmlLogManager.step("Sending GET request with id: " + zeroId);
            bookService.getById(zeroId)
                    .then().statusCode(404);
            HtmlLogManager.pass("GET with id zero returned 404 as expected");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("GET with id zero test failed: " + e.getMessage());
            Assertions.fail(e);
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("POST /Books with missing required fields should fail")
    @Order(3)
    void createBookWithMissingFieldsShouldFail() {
        HtmlLogManager.onStart("POST /Books with missing required fields edge case");
        try {
            Book incompleteBook = new Book(0, "", null, 100, "Some excerpt", "2021-01-01T00:00:00Z");
            HtmlLogManager.step("Sending POST request with incomplete book data");
            bookService.create(incompleteBook)
                    .then().statusCode(400);
            HtmlLogManager.pass("POST with missing fields returned error as expected");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("POST with missing fields test failed: " + e.getMessage());
            Assertions.fail(e);
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("POST /Books with overly long title should fail")
    @Order(4)
    void createBookWithOverlyLongTitleShouldFail() {
        HtmlLogManager.onStart("POST /Books with overly long title edge case");
        try {
            String longTitle = "A".repeat(10000);
            Book longTitleBook = new Book(0, longTitle, "Valid Description", 150, "Excerpt", "2021-01-01T00:00:00Z");
            HtmlLogManager.step("Sending POST request with overly long title");
            bookService.create(longTitleBook)
                    .then().statusCode(400);
            HtmlLogManager.pass("POST with overly long title returned error as expected");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("POST with overly long title test failed: " + e.getMessage());
            Assertions.fail(e);
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("PUT /Books with extreme id should fail")
    @Order(5)
    void updateBookWithExtremeIdShouldFail() {
        HtmlLogManager.onStart("PUT /Books with extreme id edge case");
        try {
            int extremeId = Integer.MAX_VALUE;
            Book validBook = new Book(extremeId, "Title", "Description", 200, "Excerpt", "2021-01-01T00:00:00Z");
            HtmlLogManager.step("Sending PUT request for book with id: " + extremeId);
            bookService.update(extremeId, validBook)
                    .then().statusCode(404);
            HtmlLogManager.pass("PUT with extreme id returned 404 as expected");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("PUT with extreme id test failed: " + e.getMessage());
            Assertions.fail(e);
        } finally {
            HtmlLogManager.clear();
        }
    }

    @Test
    @DisplayName("DELETE /Books with extreme id should fail")
    @Order(6)
    void deleteBookWithExtremeIdShouldFail() {
        HtmlLogManager.onStart("DELETE /Books with extreme id edge case");
        try {
            int extremeId = Integer.MAX_VALUE;
            HtmlLogManager.step("Sending DELETE request for book with id: " + extremeId);
            bookService.delete(extremeId)
                    .then().statusCode(404);
            HtmlLogManager.pass("DELETE with extreme id returned 404 as expected");
        } catch (AssertionError | Exception e) {
            HtmlLogManager.fail("DELETE with extreme id test failed: " + e.getMessage());
            Assertions.fail(e);
        } finally {
            HtmlLogManager.clear();
        }
    }
}