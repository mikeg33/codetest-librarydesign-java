package us.wellaware.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    public void testCompareTo1() {
        Book b1 = new Book(345404475, "Do Androids Dream of Electric Sheep?", "Dick, Philip",
                "Science Fiction", "Ballantine Books", 1996, 244);
        Book b2 = new Book(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);

        assertTrue(b1.compareTo(b2) > 0);
    }

    @Test
    public void testCompareTo2() {
        Book b1 = new Book(345404475, "Do Androids Dream of Electric Sheep?", "Dick, Philip",
                "Science Fiction", "Ballantine Books", 1996, 244);
        Book b2 = new Book(517542095, "The Hitchhiker's Guide to the Galaxy", "Dick, Philip",
                "Science Fiction", "Harmony Books", 1989, 224);

        assertTrue(b1.compareTo(b2) < 0);
    }
}