package data;

import dto.Author;

import java.util.concurrent.ThreadLocalRandom;

public final class AuthorFactory {
    private AuthorFactory() { }

    public static Author random(int bookId) {
        int id = randomId();
        return new Author(
                id,
                bookId,
                "Name_" + id,
                "Surname_" + id
        );
    }

    public static Author random() {
        int id = randomId();
        int bookId = randomId();
        return new Author(
                id,
                bookId,
                "Name_" + id,
                "Surname_" + id
        );
    }

    private static int randomId() {
        return ThreadLocalRandom.current().nextInt(10_000);
    }
}
