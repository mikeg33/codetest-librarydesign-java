package us.wellaware.library;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibrarySimulationTest {

    @Test
    void addBookToShelf() {
        Library myLibrary = new LibrarySimulation(5);
        boolean res = myLibrary.addBookToShelf(345404475, "Do Androids Dream of Electric Sheep?", "Dick, Philip",
                "Science Fiction", "Ballantine Books", 1996, 244);
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
        myLibrary.addBookToShelf(345404475, "Do Androids Dream of Electric Sheep?", "Dick, Philip",
                "Science Fiction", "Ballantine Books", 1996, 244);
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
        myLibrary.addBookToShelf(345404475, "Do Androids Dream of Electric Sheep?", "Dick, Philip",
                "Science Fiction", "Ballantine Books", 1996, 244);
        myLibrary.addBookToShelf(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);
        myLibrary.addBookToShelf(684818701, "The Joy of Cooking: Seventh Edition", "Rombauer, Irma",
                "Cooking", "Simon and Schuster", 1997, 1136);

        assertEquals(expected, myLibrary.getShelfNames());
    }

    @Test
    void findShelfNameForISBN() {
        Library myLibrary = new LibrarySimulation(1);
        myLibrary.addBookToShelf(345404475, "Do Androids Dream of Electric Sheep?", "Dick, Philip",
                "Science Fiction", "Ballantine Books", 1996, 244);
        myLibrary.addBookToShelf(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);

        assertNull(myLibrary.findShelfNameForISBN(684818701));

        assertEquals("Science Fiction - 1", myLibrary.findShelfNameForISBN(517542095));
        assertEquals("Science Fiction - 2", myLibrary.findShelfNameForISBN(345404475));
    }

    @Test
    void getISBNsOnShelf() {
        List<Long> expected1 = new ArrayList<>();
        expected1.add((long)517542095);
        expected1.add((long)1101904224);
        List<Long> expected2 = new ArrayList<>();
        expected2.add((long)345404475);

        Library myLibrary = new LibrarySimulation(2);
        myLibrary.addBookToShelf(345404475, "Do Androids Dream of Electric Sheep?", "Dick, Philip",
                "Science Fiction", "Ballantine Books", 1996, 244);
        myLibrary.addBookToShelf(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);
        myLibrary.addBookToShelf(1101904224, "Dark Matter", "Crouch, Blake",
                "Science Fiction", "Crown", 2016, 342);

        assertEquals(expected1, myLibrary.getISBNsOnShelf("Science Fiction - 1"));
        assertEquals(expected2, myLibrary.getISBNsOnShelf("Science Fiction - 2"));
    }

    private Library createLibrary() {
        Library myLibrary = new LibrarySimulation(2);
        myLibrary.addBookToShelf(345404475, "Do Androids Dream of Electric Sheep?", "Dick, Philip",
                "Science Fiction", "Ballantine Books", 1996, 244);
        myLibrary.addBookToShelf(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);
        myLibrary.addBookToShelf(684818701, "The Joy of Cooking: Seventh Edition", "Rombauer, Irma",
                "Cooking", "Simon and Schuster", 1997, 1136);
        myLibrary.addBookToShelf(70064520, "Aunt Erma's cope book", "Bombeck, Erma", "Humor",
                "McGraw-Hill", 1979, 180);
        myLibrary.addBookToShelf(609600672, "Dave Barry is not taking this sitting down!", "Barry, Dave",
                "Humor", "Crown Publishers", 2000, 229);
        myLibrary.addBookToShelf(1101904224, "Dark Matter", "Crouch, Blake",
                "Science Fiction", "Crown", 2016, 342);
        myLibrary.addBookToShelf(440238153, "The Amber Spyglass  (His Dark Materials, #3)", "Pullman, Philip",
                "Fantasy", "Laurel Leaf", 2000, 467);
        myLibrary.addBookToShelf(776655, "The Subtle Knife  (His Dark Materials, #2)", "Pullman, Philip",
                "Fantasy", "RHCP Digital", 1997, 370);
        return myLibrary;
    }

    @Test
    void getISBNsForGenre() {
        Library myLibrary = createLibrary();
        List<Long> expected = new ArrayList<>();
        expected.add((long)517542095);
        expected.add((long)345404475);

        assertEquals(expected, myLibrary.getISBNsForGenre("Science Fiction", 2));

        expected.add((long)1101904224);

        assertEquals(expected, myLibrary.getISBNsForGenre("Science Fiction", 3));
    }

    @Test
    void getISBNsForAuthor() {
        Library myLibrary = createLibrary();
        List<Long> expected = new ArrayList<>();
        expected.add((long)440238153);
        expected.add((long)776655);

        assertEquals(expected, myLibrary.getISBNsForAuthor("Pullman, Philip", 10));
    }

    @Test
    void getISBNsForPublisher() {
        Library myLibrary = createLibrary();
        List<Long> expected = new ArrayList<>();
        expected.add((long)1101904224);

        assertEquals(expected, myLibrary.getISBNsForPublisher("Crown", 10));
    }

    @Test
    void getISBNsPublishedAfterYear() {
        Library myLibrary = createLibrary();
        List<Long> expected = new ArrayList<>();
        expected.add((long)440238153);
        expected.add((long)609600672);
        expected.add((long)1101904224);

        assertEquals(expected, myLibrary.getISBNsPublishedAfterYear((short) 1999, 10));
    }

    @Test
    void getISBNsWithMinimumPageCount() {
        Library myLibrary = createLibrary();
        List<Long> expected = new ArrayList<>();
        expected.add((long)440238153);
        expected.add((long)684818701);

        assertEquals(expected, myLibrary.getISBNsWithMinimumPageCount(450, 10));
    }
}