package us.wellaware.library;

public class Book implements Comparable<Book> {
    private long isbn;
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private int publicationYear;
    private int pageCount;

    public Book(long isbn, String title, String author, String genre, String publisher,
                int publicationYear, int pageCount) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.pageCount = pageCount;
    }

    @Override
    public int compareTo(Book other) {
        int result = this.author.compareTo(other.getAuthor());
        if (result == 0)
            result = this.title.compareTo(other.getTitle());
        if (result == 0) {
            return Long.compare(this.isbn, other.getISBN());
        }
        return result;
    }

    public long getISBN() { return isbn; }

    public String getTitle() { return title; }

    public String getAuthor() { return author; }

    public String getGenre() { return genre; }

    public String getPublisher() { return publisher; }

    public int getPublicationYear() { return publicationYear; }

    public int getPageCount() { return pageCount; }
}