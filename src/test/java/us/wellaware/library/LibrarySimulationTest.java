package us.wellaware.library;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibrarySimulationTest {

    @Test
    void addBookToShelf() {
        Library myLibrary = new LibrarySimulation(5);
        boolean res = myLibrary.addBookToShelf(345404475, "Do Androids Dream of Electric Sheep?",
                "Dick, Philip", "Science Fiction", "Ballantine Books", 1996, 244);
        assertTrue(res);

        res = myLibrary.addBookToShelf(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);
        assertTrue(res);

        res = myLibrary.addBookToShelf(517542095, "Dark Matter", "Crouch, Blake",
                "Science Fiction", "Crown", 2016, 342);
        assertFalse(res);
    }

    @Test
    void getBookTitle() {
        Library myLibrary = new LibrarySimulation(5);
        myLibrary.addBookToShelf(345404475, "Do Androids Dream of Electric Sheep?",
                "Dick, Philip", "Science Fiction", "Ballantine Books", 1996, 244);
        myLibrary.addBookToShelf(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);

        assertEquals("Do Androids Dream of Electric Sheep?", myLibrary.getBookTitle(345404475));
    }

    @Test
    void getShelfNames() {
        List<String> expected = new ArrayList<>();
        expected.add("Cooking - 1");
        expected.add("Science Fiction - 1");
        expected.add("Science Fiction - 2");

        Library myLibrary = new LibrarySimulation(1);
        myLibrary.addBookToShelf(345404475, "Do Androids Dream of Electric Sheep?",
                "Dick, Philip", "Science Fiction", "Ballantine Books", 1996, 244);
        myLibrary.addBookToShelf(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);
        myLibrary.addBookToShelf(684818701, "The Joy of Cooking: Seventh Edition", "Rombauer, Irma",
                "Cooking", "Simon and Schuster", 1997, 1136);

        assertEquals(expected, myLibrary.getShelfNames());
    }

    @Test
    void findShelfNameForISBN() {
        Library myLibrary = new LibrarySimulation(1);
        myLibrary.addBookToShelf(345404475, "Do Androids Dream of Electric Sheep?",
                "Dick, Philip", "Science Fiction", "Ballantine Books", 1996, 244);
        myLibrary.addBookToShelf(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);

        assertNull(myLibrary.findShelfNameForISBN(684818701));

        assertEquals("Science Fiction - 1", myLibrary.findShelfNameForISBN(517542095));
        assertEquals("Science Fiction - 2", myLibrary.findShelfNameForISBN(345404475));
    }

    @Test
    void getISBNsOnShelf() {
        List<Long> expected1 = new ArrayList<>();
        expected1.add(Long.valueOf(517542095));
        expected1.add(Long.valueOf(1101904224));
        List<Long> expected2 = new ArrayList<>();
        expected2.add(Long.valueOf(345404475));

        Library myLibrary = new LibrarySimulation(2);
        myLibrary.addBookToShelf(345404475, "Do Androids Dream of Electric Sheep?",
                "Dick, Philip", "Science Fiction", "Ballantine Books", 1996, 244);
        myLibrary.addBookToShelf(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);
        myLibrary.addBookToShelf(1101904224, "Dark Matter", "Crouch, Blake",
                "Science Fiction", "Crown", 2016, 342);

        assertEquals(expected1, myLibrary.getISBNsOnShelf("Science Fiction - 1"));
        assertEquals(expected2, myLibrary.getISBNsOnShelf("Science Fiction - 2"));
    }

    @Test
    void getISBNsForGenre() {
    }

    @Test
    void getISBNsForAuthor() {
    }

    @Test
    void getISBNsForPublisher() {
    }

    @Test
    void getISBNsPublishedAfterYear() {
    }

    @Test
    void getISBNsWithMinimumPageCount() {
    }
}