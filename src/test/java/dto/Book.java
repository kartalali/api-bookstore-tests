package dto;

public record Book(
        Integer id,
        String title,
        String description,
        Integer pageCount,
        String excerpt,
        String publishDate) {}


