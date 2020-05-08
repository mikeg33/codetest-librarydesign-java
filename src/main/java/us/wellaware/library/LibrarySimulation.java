package us.wellaware.library;

import java.util.*;

public class LibrarySimulation implements Library {
    private final int maxShelfSize;
    private HashMap<Long, Book> isbnMap = new HashMap<>();
    private HashMap<String, Shelf> shelfMap = new HashMap<>();

    public LibrarySimulation(int shelfSize) {
        maxShelfSize = shelfSize;
    }

    public boolean addBookToShelf(long isbn, String title, String author, String genre, String publisher,
                               int publicationYear, int pageCount) {
        Book book = new Book(isbn, title, author, genre, publisher, publicationYear, pageCount);

        if (isbnMap.containsKey(isbn))
            return false;
        isbnMap.put(isbn, book);

        Shelf shelf = shelfMap.get(genre);
        if (shelf == null) {
            shelf = new Shelf(maxShelfSize, genre);
            shelfMap.put(genre, shelf);
        }
        shelf.addBook(book);
        return true;
    }

    public String getBookTitle(long isbn) {
        Book book = isbnMap.get(isbn);
        if (book == null) {
            System.err.println("ISBN: " + isbn + " does not exist");
            return null;
        }
        return book.getTitle();
    }

    public List<String> getShelfNames() {
        List<String> shelfNames = new ArrayList<>();
        for (Shelf s : shelfMap.values())
            shelfNames.addAll(s.getNames());
        return shelfNames;
    }

    public String findShelfNameForISBN(long isbn) {
        Book book = isbnMap.get(isbn);
        if (book == null) {
            System.err.println("ISBN: " + isbn + " does not exist");
            return null;
        }
        Shelf shelf = shelfMap.get(book.getGenre());
        return shelf.getShelfName(book);
    }

    public List<Long> getISBNsOnShelf(String shelfName) {
        int i = shelfName.lastIndexOf(" - ");
        String genre = shelfName.substring(0, i);
        Shelf shelf = shelfMap.get(genre);
        return shelf.getISBNs(shelfName);
    }

    public List<Long> getISBNsForGenre(String genre, int limit) {
        throw new UnsupportedOperationException();
    }

    public List<Long> getISBNsForAuthor(String author, int limit) {
        throw new UnsupportedOperationException();
    }

    public List<Long> getISBNsForPublisher(String publisher, int limit) {
        throw new UnsupportedOperationException();
    }

    public List<Long> getISBNsPublishedAfterYear(short publicationYear, int limit) {
        throw new UnsupportedOperationException();
    }

    public List<Long> getISBNsWithMinimumPageCount(int minimumPageCount, int limit) {
        throw new UnsupportedOperationException();
    }
}
