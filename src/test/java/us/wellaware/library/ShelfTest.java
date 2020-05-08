package us.wellaware.library;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {

    @Test
    void addBook() {
        Shelf shelf = new Shelf(5, "Science Fiction");
        Book book = new Book(345404475, "Do Androids Dream of Electric Sheep?", "Dick, Philip",
                "Science Fiction", "Ballantine Books", 1996, 244);

        shelf.addBook(book);
        assertEquals(book.getISBN(), shelf.getBookSet().first().getISBN());
    }

    @Test
    void getNames() {
        List<String> expected = new ArrayList<>();
        expected.add("Science Fiction - 1");
        expected.add("Science Fiction - 2");

        Shelf shelf = new Shelf(2, "Science Fiction");
        Book b1 = new Book(345404475, "Do Androids Dream of Electric Sheep?", "Dick, Philip",
                "Science Fiction", "Ballantine Books", 1996, 244);
        Book b2 = new Book(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);
        Book b3 = new Book(1101904224, "Dark Matter", "Crouch, Blake",
                "Science Fiction", "Crown", 2016, 342);

        shelf.addBook(b1);
        shelf.addBook(b2);
        shelf.addBook(b3);
        assertEquals(expected, shelf.getNames());
    }

    @Test
    void getShelfName() {
        Shelf shelf = new Shelf(2, "Science Fiction");
        Book b1 = new Book(345404475, "Do Androids Dream of Electric Sheep?", "Dick, Philip",
                "Science Fiction", "Ballantine Books", 1996, 244);
        Book b2 = new Book(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);
        Book b3 = new Book(1101904224, "Dark Matter", "Crouch, Blake",
                "Science Fiction", "Crown", 2016, 342);

        shelf.addBook(b1);
        shelf.addBook(b2);
        shelf.addBook(b3);
        assertEquals("Science Fiction - 1", shelf.getShelfName(b3));
        assertEquals("Science Fiction - 2", shelf.getShelfName(b1));
    }

    @Test
    void getISBNs() {
        List<Long> expected1 = new ArrayList<>();
        expected1.add((long)517542095);
        expected1.add((long)1101904224);
        List<Long> expected2 = new ArrayList<>();
        expected2.add((long)345404475);

        Shelf shelf = new Shelf(2, "Science Fiction");
        Book b1 = new Book(345404475, "Do Androids Dream of Electric Sheep?", "Dick, Philip",
                "Science Fiction", "Ballantine Books", 1996, 244);
        Book b2 = new Book(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);
        Book b3 = new Book(1101904224, "Dark Matter", "Crouch, Blake",
                "Science Fiction", "Crown", 2016, 342);

        shelf.addBook(b1);
        shelf.addBook(b2);
        shelf.addBook(b3);
        assertEquals(expected1, shelf.getISBNs("Science Fiction - 1"));
        assertEquals(expected2, shelf.getISBNs("Science Fiction - 2"));
    }
}