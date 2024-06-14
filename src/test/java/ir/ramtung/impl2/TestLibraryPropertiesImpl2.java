package ir.ramtung.impl2;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import ir.ramtung.sts01.ILibrary;
import ir.ramtung.sts01.LibraryException;

import org.junit.runner.RunWith;

import java.util.List;


@RunWith(JUnitQuickcheck.class)
public class TestLibraryPropertiesImpl2 {
    @Property public void studentHasNameID(String name, String sid) {
        assumeFalse(name.isEmpty() || sid.isEmpty());

        Student student = new Student(sid, name);
        assertEquals(student.getName(), name);
        assertEquals(student.id, sid);
    }

    @Property public void studentInvalid(String name, String sid) {
        assumeFalse(name.isEmpty() || sid.isEmpty());

        assertThrows(Exception.class, () -> new Student("", name));
        assertThrows(Exception.class, () -> new Student(sid, ""));
    }

    @Property public void profHasName(String name) {
        assumeFalse(name.isEmpty());

        Prof professor = new Prof(name);
        assertEquals(professor.getName(), name);
    }

    @Property public void professorInvalid() {
        assertThrows(Exception.class, () -> new Prof(""));
    }

    @Property public void documentProperties(String title, int copies) {
        assumeFalse(title.isEmpty());
        assumeThat(copies, greaterThan(0));

        Document document = new Book(title, copies);

        assertEquals(document.getCopies(), copies);
        document.barrowBook();
        assertEquals(document.getCopies(), copies - 1);
        document.returnBook();
        assertEquals(document.getCopies(), copies);
    }

    @Property public void documentInvalid(String title, int yr, int num, int copies) {
        assumeFalse(title.isEmpty());
        assumeThat(copies, lessThanOrEqualTo(0));
        assumeThat(yr, greaterThan(0));
        assumeThat(num, greaterThan(0));

        assertThrows(Exception.class, () -> new Book(title, copies));
        assertThrows(Exception.class, () -> new Book("", 1));
        assertThrows(Exception.class, () -> new Reference(title, copies));
        assertThrows(Exception.class, () -> new Reference("", 1));
        assertThrows(Exception.class, () -> new Magazine(title, yr, num, 0));
        assertThrows(Exception.class, () -> new Magazine(title, yr, 0, 1));
        assertThrows(Exception.class, () -> new Magazine(title, 0, num, 1));
        assertThrows(Exception.class, () -> new Magazine("", yr, num, 1));
        assertThrows(Exception.class, () -> new Magazine(title, -1 * yr, num, 1));
        assertThrows(Exception.class, () -> new Magazine(title, yr, -1 * num, 1));

        Document document = new Book(title, 1);

        assertThrows(Exception.class, document::returnBook);
        document.barrowBook();
        assertThrows(Exception.class, document::barrowBook);

        assertEquals(document.getDay(), 10);
        assertEquals((new Reference(title, 1)).getDay(), 5);
        assertEquals((new Magazine(title, yr, num, 1)).getDay(), 2);
    }

    @Property public void invalidPenalties(String title, int yr, int num, int days) {
        assumeFalse(title.isEmpty());
        assumeThat(yr, greaterThan(0));
        assumeThat(num, greaterThan(0));
        assumeThat(days, lessThanOrEqualTo(0));
        Document book = new Book(title, 1);
        Document reference = new Reference(title, 1);
        Document magazine = new Magazine(title, yr, num, 1);

        assertThrows(Exception.class, () -> book.calculatePenalty(days));
        assertThrows(Exception.class, () -> reference.calculatePenalty(days));
        assertThrows(Exception.class, () -> magazine.calculatePenalty(days));
    }

    @Property public void penaltyForBook(String title, int days) {
        assumeFalse(title.isEmpty());
        assumeThat(days, greaterThan(0));
        Document document = new Book(title, 1);

        int cost = Math.min(days, 7) * 2000 + (days > 7? Math.min(days - 7, 21 - 7) : 0) * 3000 + Math.max(days - 21, 0) * 5000;
        assertEquals(document.calculatePenalty(days), cost);
    }

    @Property public void penaltyForReference(String title, int days) {
        assumeFalse(title.isEmpty());
        assumeThat(days, greaterThan(0));
        Document document = new Reference(title, 1);

        int cost = Math.min(days, 3) * 5000 + Math.max(days - 3, 0) * 7000;
        assertEquals(document.calculatePenalty(days), cost);
    }

    @Property public void penaltyForMagazineBefore1390(String title, @InRange(min = "1", max = "1389") int yr, int num, int days) {
        assumeFalse(title.isEmpty());
        assumeThat(num, greaterThan(0));
        assumeThat(days, greaterThan(0));
        Document document = new Magazine(title, yr, num, 1);

        int cost = days * 2000;
        assertEquals(document.calculatePenalty(days), cost);
    }

    @Property public void penaltyForMagazineAfter1390(String title, int yr, int num, int days) {
        assumeFalse(title.isEmpty());
        assumeThat(yr, greaterThanOrEqualTo(1390));
        assumeThat(num, greaterThan(0));
        assumeThat(days, greaterThan(0));
        Document document = new Magazine(title, yr, num, 1);

        int cost = days * 3000;
        assertEquals(document.calculatePenalty(days), cost);
    }

    @Property public void libraryAddInvalidMember(String sid, String studentName, String profName) throws LibraryException {
        assumeFalse(studentName.isEmpty() || profName.isEmpty() || sid.isEmpty());

        ILibrary library = new Library();

        library.addStudentMember(sid, studentName);
        assertThrows(LibraryException.class, () -> library.addStudentMember(sid, studentName));
        assertThrows(LibraryException.class, () -> library.addProfMember(studentName));

        library.addProfMember(profName);
        assertThrows(LibraryException.class, () -> library.addStudentMember(sid, profName));
        assertThrows(LibraryException.class, () -> library.addProfMember(profName));
    }

    @Property public void libraryAddInvalidDocument(String bookTitle, String magazineTitle, int year, int number, String referenceTitle, int copies) throws LibraryException {
        assumeFalse(bookTitle.isEmpty() || magazineTitle.isEmpty() || referenceTitle.isEmpty());
        assumeThat(year, greaterThan(0));
        assumeThat(number, greaterThan(0));
        assumeThat(copies, greaterThan(0));

        ILibrary library = new Library();
        assertThrows(LibraryException.class, () -> library.addBook(bookTitle, 0));
        assertThrows(LibraryException.class, () -> library.addMagazine(magazineTitle, year, number, 0));
        assertThrows(LibraryException.class, () -> library.addReference(referenceTitle, 0));

        library.addBook(bookTitle, copies);
        assertThrows(LibraryException.class, () -> library.addBook(bookTitle, 1));
        assertThrows(LibraryException.class, () -> library.addMagazine(bookTitle, year, number, 1));
        assertThrows(LibraryException.class, () -> library.addReference(bookTitle, 1));

        library.addMagazine(magazineTitle, year, number, copies);
        assertThrows(LibraryException.class, () -> library.addBook(magazineTitle, 1));
        assertThrows(LibraryException.class, () -> library.addMagazine(magazineTitle, year, number, 1));
        assertThrows(LibraryException.class, () -> library.addReference(magazineTitle, 1));

        library.addReference(referenceTitle, copies);
        assertThrows(LibraryException.class, () -> library.addBook(referenceTitle, 1));
        assertThrows(LibraryException.class, () -> library.addMagazine(referenceTitle, year, number, 1));
        assertThrows(LibraryException.class, () -> library.addReference(referenceTitle, 1));
    }

    @Property public void libraryBorrow(String sid, String studentName, String bookTitle, int copies) throws LibraryException {
        assumeFalse(bookTitle.isEmpty() || sid.isEmpty() || studentName.isEmpty());
        assumeThat(copies, greaterThan(0));

        ILibrary library = new Library();
        library.addStudentMember(sid, studentName);
        library.addBook(bookTitle, copies);

        List<String> available = library.availableTitles();
        library.borrow(studentName, bookTitle);
        library.timePass(1);
        library.returnDocument(studentName, bookTitle);
        assertEquals(library.availableTitles(), available);
    }

    @Property public void libraryPenalty(String sid, String studentName, String bookTitle, String referenceTitle, int copies, @InRange(min = "1", max = "10000") int days) throws LibraryException {
        assumeFalse(bookTitle.isEmpty() || sid.isEmpty() || studentName.isEmpty());
        assumeThat(copies, greaterThan(0));
//        assumeThat(days, greaterThan(0));

        Document dummyBook = new Book("dummy", 1);
        Document dummyReference = new Reference("dummy", 1);

        ILibrary library = new Library();
        library.addStudentMember(sid, studentName);
        library.addBook(bookTitle, copies);
        library.addReference(referenceTitle, copies);

        library.borrow(studentName, bookTitle);
        library.timePass(10);
        library.extend(studentName, bookTitle);
        library.timePass(10);
        assertEquals(library.getTotalPenalty(studentName), 0);

        library.borrow(studentName, referenceTitle);
        library.timePass(days);
        assertEquals(
                library.getTotalPenalty(studentName),
                dummyBook.calculatePenalty(days - 10) + dummyReference.calculatePenalty(days - 5)
        );
    }

    @Property public void libraryInvalidBorrow(String sid, String studentName, String profName, String docTitle) throws LibraryException {
        assumeFalse(docTitle.isEmpty() || sid.isEmpty() || studentName.isEmpty() || profName.isEmpty());

        ILibrary library = new Library();
        library.addBook(docTitle, 10);

        library.addStudentMember(sid, studentName);
        for (int i = 0; i < 2; i++) {
            library.borrow(studentName, docTitle);
        }
        assertThrows(Exception.class, () -> library.borrow(studentName, docTitle));

        library.addProfMember(profName);
        for (int i = 0; i < 5; i++) {
            library.borrow(profName, docTitle);
        }
        assertThrows(Exception.class, () -> library.borrow(profName, docTitle));
    }

    @Property public void libraryInvalidExtend(String sid, String studentName, String profName, String docTitle) throws LibraryException {
        assumeFalse(docTitle.isEmpty() || sid.isEmpty() || studentName.isEmpty() || profName.isEmpty());

        ILibrary library = new Library();
        library.addStudentMember(sid, studentName);
        library.addProfMember(profName);
        library.addBook(docTitle, 2);

        library.borrow(studentName, docTitle);
        library.timePass(1);
        library.extend(studentName, docTitle);
        library.timePass(1);
        assertThrows(Exception.class, () -> library.extend(studentName, docTitle));

        library.borrow(profName, docTitle);
        library.timePass(1);
        library.extend(profName, docTitle);
        library.timePass(1);
        assertThrows(Exception.class, () -> library.extend(profName, docTitle));
    }

}