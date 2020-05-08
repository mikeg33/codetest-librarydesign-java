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

    /* Finds the book by its ISBN.
     * If the ISBN does not exist in the library, returns null. */
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

    /* Finds the shelf name by one of its book's ISBN.
     * If the ISBN does not exist in the library, returns null. */
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

    private List<Long> getISBNsForPredicate(int limit, Predicate<Book> checker) {
        int i = 1;
        List<Long> isbnList = new ArrayList<>();
        for (Long isbn : isbnMap.keySet()) {
            if (checker.check(isbnMap.get(isbn))) {
                isbnList.add(isbn);
                if (i++ == limit)
                    break;
            }
        }
        return isbnList;
    }

    public List<Long> getISBNsForGenre(String genre, int limit) {
        return getISBNsForPredicate(limit, b -> b.getGenre().equals(genre));
    }

    public List<Long> getISBNsForAuthor(String author, int limit) {
        return getISBNsForPredicate(limit, b -> b.getAuthor().equals(author));
    }

    public List<Long> getISBNsForPublisher(String publisher, int limit) {
        return getISBNsForPredicate(limit, b -> b.getPublisher().equals(publisher));
    }

    public List<Long> getISBNsPublishedAfterYear(short publicationYear, int limit) {
        return getISBNsForPredicate(limit, b -> b.getPublicationYear() > publicationYear);
    }

    public List<Long> getISBNsWithMinimumPageCount(int minimumPageCount, int limit) {
        return getISBNsForPredicate(limit, b -> b.getPageCount() >= minimumPageCount);
    }
}
