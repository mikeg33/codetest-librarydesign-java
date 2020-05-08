package us.wellaware.library;

import java.util.*;

public class Shelf {
    private int numBooks = 0;
    private int numShelves = 0;
    private int shelfSize;
    private String genre;
    private List<String> shelfNames = new ArrayList<>();
    private TreeSet<Book> bookSet = new TreeSet<>();

    public Shelf(int shelfSize, String genre) {
        this.shelfSize = shelfSize;
        this.genre = genre;
        addShelf(genre);
    }

    public List<String> getNames() { return shelfNames; }

    public TreeSet<Book> getBookSet() { return bookSet; }

    private void addShelf(String genre) {
        numShelves++;
        shelfNames.add(genre + " - " + numShelves);
    }

    public void addBook(Book book) {
        bookSet.add(book);
        numBooks++;
        if (numBooks > shelfSize)
            addShelf(genre);
    }

    public String getShelfName(Book book) {
        if (!bookSet.contains(book))
            return "Book not found in shelf genre: " + genre;
        int shelfNum = bookSet.headSet(book).size() / shelfSize + 1;
        return genre + " - " + shelfNum;
    }

    public List<Long> getISBNs(String shelfName) {
        List<Long> isbnList = new ArrayList<>();
        if (!shelfNames.contains(shelfName))
            return isbnList;

        int shelfNum = shelfNames.indexOf(shelfName) + 1;
        Iterator<Book> books = bookSet.iterator();

        for (int i = 0; i < (shelfNum-1) * shelfSize && books.hasNext(); i++)
            books.next();

        for (int i = 0; i < shelfNum * shelfSize && books.hasNext(); i++)
            isbnList.add(books.next().getISBN());

        return isbnList;
    }

}
