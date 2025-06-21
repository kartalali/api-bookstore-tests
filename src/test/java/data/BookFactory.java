package data;

import dto.Book;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public final class BookFactory {
    private BookFactory() {}

    public static Book random() {
        return randomWithId(randomId());
    }

    public static Book randomWithId(Integer id) {
        int base = ThreadLocalRandom.current().nextInt(10_000);

        return new Book(
                id,
                "Title_" + base,
                "Description_" + base,
                ThreadLocalRandom.current().nextInt(50, 500),
                "Excerpt_" + base,
                DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        );
    }

    public static Book invalid() {
        return new Book(0, "", "", -1, "", "");
    }

    private static int randomId() {
        return ThreadLocalRandom.current().nextInt(1_000, 1_000_000);
    }
}
