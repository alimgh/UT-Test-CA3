package ir.ramtung.impl1;

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
public class TestLibraryPropertiesImpl1 {
    @Property public void studentHasNameID(String name, String sid) throws InvalidArgumentEx {
        assumeFalse(name.isEmpty() || sid.isEmpty());

        Student student = new Student(sid, name);
        assertTrue(student.isNamed(name));
        assertEquals(student.id, sid);
        assertEquals(student.allowedToBorrow(), 2);
    }

    @Property public void studentSame(String name1, String sid1, String name2, String sid2) throws InvalidArgumentEx {
        assumeFalse(name1.isEmpty() || sid1.isEmpty());
        assumeFalse(name2.isEmpty() || sid2.isEmpty());

        Student student1 = new Student(sid1, name1);
        Student student2 = new Student(sid2, name2);
        assertTrue(student1.sameAs(student1));
        assertTrue(student2.sameAs(student2));
        assertFalse(student1.sameAs(student2));
        assertFalse(student2.sameAs(student1));

        assertNotEquals(student1.id, student2.id);
    }

    @Property public void studentInvalid(String name, String sid) throws InvalidArgumentEx {
        assumeFalse(name.isEmpty() || sid.isEmpty());

        assertThrows(InvalidArgumentEx.class, () -> new Student("", name));
        assertThrows(InvalidArgumentEx.class, () -> new Student(sid, ""));
    }

    @Property public void studentPenalize(String name, String sid, int penalty) throws InvalidArgumentEx {
        assumeFalse(name.isEmpty() || sid.isEmpty());
        assumeThat(penalty, greaterThan(0));

        Student student = new Student(sid, name);

        assertEquals(student.getPrevPenalty(), student.prevPenalty);
        assertEquals(student.getPrevPenalty(), 0);

        int oldPenalty = student.getPrevPenalty();
        student.penalize(penalty);
        assertEquals(oldPenalty + penalty, student.getPrevPenalty());
    }

    @Property public void studentInvalidPenalize(String name, String sid, int penalty) throws InvalidArgumentEx {
        assumeFalse(name.isEmpty() || sid.isEmpty());
        assumeThat(penalty, lessThanOrEqualTo(0));

        Student student = new Student(sid, name);
        assertThrows(InvalidArgumentEx.class, () -> student.penalize(penalty));
    }

    @Property public void profHasName(String name) throws InvalidArgumentEx {
        assumeFalse(name.isEmpty());

        Professor professor = new Professor(name);
        assertTrue(professor.isNamed(name));
        assertEquals(professor.allowedToBorrow(), 5);
    }

    @Property public void professorSame(String name1, String name2) throws InvalidArgumentEx {
        assumeFalse(name1.isEmpty() || name2.isEmpty());

        Professor professor1 = new Professor(name1);
        Professor professor2 = new Professor(name2);
        assertTrue(professor1.sameAs(professor1));
        assertTrue(professor2.sameAs(professor2));
        assertFalse(professor1.sameAs(professor2));
        assertFalse(professor2.sameAs(professor1));
    }

    @Property public void professorInvalid() throws InvalidArgumentEx {
        assertThrows(InvalidArgumentEx.class, () -> new Professor(""));
    }

    @Property public void professorPenalize(String name, int penalty) throws InvalidArgumentEx {
        assumeFalse(name.isEmpty());
        assumeThat(penalty, greaterThan(0));

        Professor professor = new Professor(name);

        assertEquals(professor.getPrevPenalty(), professor.prevPenalty);
        assertEquals(professor.getPrevPenalty(), 0);

        int oldPenalty = professor.getPrevPenalty();
        professor.penalize(penalty);
        assertEquals(oldPenalty + penalty, professor.getPrevPenalty());
    }

    @Property public void professorInvalidPenalize(String name, int penalty) throws InvalidArgumentEx {
        assumeFalse(name.isEmpty());
        assumeThat(penalty, lessThanOrEqualTo(0));

        Professor professor = new Professor(name);
        assertThrows(InvalidArgumentEx.class, () -> professor.penalize(penalty));
    }

    @Property public void bookProperties(String title) throws InvalidArgumentEx {
        assumeFalse(title.isEmpty());

        Document document = new Book(title);

        assertTrue(document.isTitled(document.getTitle()));
        assertEquals(document.loanDuration(), 10);
        assertThrows(InvalidArgumentEx.class, () -> new Book(""));
    }

    @Property public void referenceProperties(String title) throws InvalidArgumentEx {
        assumeFalse(title.isEmpty());

        Document document = new Reference(title);

        assertTrue(document.isTitled(document.getTitle()));
        assertEquals(document.loanDuration(), 5);
        assertThrows(InvalidArgumentEx.class, () -> new Reference(""));
    }

    @Property public void magazineProperties(String title, int yr, int num) throws InvalidArgumentEx {
        assumeFalse(title.isEmpty());
        assumeThat(yr, greaterThan(0));
        assumeThat(num, greaterThan(0));
        Document document = new Magazine(title, yr, num);

        assertTrue(document.isTitled(document.getTitle()));
        assertEquals(document.loanDuration(), 2);

        assertThrows(InvalidArgumentEx.class, () -> new Magazine("", yr, num));
        assertThrows(InvalidArgumentEx.class, () -> new Magazine(title, yr * -1, num));
        assertThrows(InvalidArgumentEx.class, () -> new Magazine(title, yr, num * -1));
        assertThrows(InvalidArgumentEx.class, () -> new Magazine(title, 0, num));
        assertThrows(InvalidArgumentEx.class, () -> new Magazine(title, yr, 0));
    }

    @Property public void invalidPenalties(String title, int yr, int num, int days) throws InvalidArgumentEx {
        assumeFalse(title.isEmpty());
        assumeThat(yr, greaterThan(0));
        assumeThat(num, greaterThan(0));
        assumeThat(days, lessThanOrEqualTo(0));
        Document book = new Book(title);
        Document reference = new Reference(title);
        Document magazine = new Magazine(title, yr, num);

        assertThrows(InvalidArgumentEx.class, () -> book.penaltyFor(days));
        assertThrows(InvalidArgumentEx.class, () -> reference.penaltyFor(days));
        assertThrows(InvalidArgumentEx.class, () -> magazine.penaltyFor(days));
    }

    @Property public void penaltyForBook(String title, int days) throws InvalidArgumentEx {
        assumeFalse(title.isEmpty());
        assumeThat(days, greaterThan(0));
        Document document = new Book(title);

        int cost = Math.min(days, 7) * 2000 + (days > 7? Math.min(days - 7, 21 - 7) : 0) * 3000 + Math.max(days - 21, 0) * 5000;
        assertEquals(document.penaltyFor(days), cost);
    }

    @Property public void penaltyForReference(String title, int days) throws InvalidArgumentEx {
        assumeFalse(title.isEmpty());
        assumeThat(days, greaterThan(0));
        Document document = new Reference(title);

        int cost = Math.min(days, 3) * 5000 + Math.max(days - 3, 0) * 7000;
        assertEquals(document.penaltyFor(days), cost);
    }

    @Property public void penaltyForMagazineBefore1390(String title, @InRange(min = "1", max = "1389") int yr, int num, int days) throws InvalidArgumentEx {
        assumeFalse(title.isEmpty());
        assumeThat(num, greaterThan(0));
        assumeThat(days, greaterThan(0));
        Document document = new Magazine(title, yr, num);

        int cost = days * 2000;
        assertEquals(document.penaltyFor(days), cost);
    }

    @Property public void penaltyForMagazineAfter1390(String title, int yr, int num, int days) throws InvalidArgumentEx {
        assumeFalse(title.isEmpty());
        assumeThat(yr, greaterThanOrEqualTo(1390));
        assumeThat(num, greaterThan(0));
        assumeThat(days, greaterThan(0));
        Document document = new Magazine(title, yr, num);

        int cost = days * 3000;
        assertEquals(document.penaltyFor(days), cost);
    }

    @Property public void invalidLoan(String name, String title, int date) throws InvalidArgumentEx {
        assumeFalse(name.isEmpty() || title.isEmpty());
        assumeThat(date, greaterThanOrEqualTo(0));
        Member member = new Professor(name);
        Document document = new Book(title);

        assertThrows(InvalidArgumentEx.class, () -> new Loan(null, document, date));
        assertThrows(InvalidArgumentEx.class, () -> new Loan(member, null, date));
        assertThrows(InvalidArgumentEx.class, () -> new Loan(member, document, date * -1));
    }

    @Property public void loanCreation(String name, String title, String nameWrong, String titleWrong, int date) throws InvalidArgumentEx {
        assumeFalse(name.isEmpty() || title.isEmpty());
        assumeFalse(nameWrong.isEmpty() || titleWrong.isEmpty());
        assumeFalse(name.equals(nameWrong));
        assumeFalse(title.equals(titleWrong));
        assumeThat(date, greaterThanOrEqualTo(0));

        Member member = new Professor(name);
        Document document = new Book(title);
        Member memberWrong = new Professor(nameWrong);
        Document documentWrong = new Book(titleWrong);

        Loan loan = new Loan(member, document, date);
        assertTrue(loan.isBy(member));
        assertTrue(loan.isFor(document));
        assertFalse(loan.isBy(memberWrong));
        assertFalse(loan.isFor(documentWrong));
    }

    @Property public void loanReturn(String name, String title, int date, int penaltyDuration) throws InvalidArgumentEx {
        assumeFalse(name.isEmpty() || title.isEmpty());
        assumeThat(date, greaterThanOrEqualTo(0));
        assumeThat(penaltyDuration, greaterThanOrEqualTo(0));

        Member member = new Professor(name);
        Document document = new Book(title);
        Loan loan = new Loan(member, document, date);

        //  Overflow problem!
        assertEquals(loan.getPenalty(date + document.loanDuration() + penaltyDuration), document.penaltyFor(penaltyDuration));
        assertEquals(loan.getPenalty(date + penaltyDuration % document.loanDuration()), 0);

        loan.return_(date + penaltyDuration % document.loanDuration());
        assertEquals(member.getPrevPenalty(), 0);

        loan.return_(date + document.loanDuration() + penaltyDuration);
        assertEquals(member.getPrevPenalty(), document.penaltyFor(penaltyDuration));
    }

    @Property public void loanInvalidExtend(String name, String title, int date, int now) throws InvalidArgumentEx, CannotExtendEx {
        assumeFalse(name.isEmpty() || title.isEmpty());
        assumeThat(date, greaterThanOrEqualTo(0));
        assumeThat(now, greaterThan(date));

        Member member = new Professor(name);
        Document document = new Book(title);
        Loan loan = new Loan(member, document, date);

        assertThrows(CannotExtendEx.class, () -> loan.extend(date));
        assertThrows(CannotExtendEx.class, () -> loan.extend(now + document.loanDuration()));

        loan.extend(now + 1);
        assertThrows(CannotExtendEx.class, () -> loan.extend(now + 1));  // two consecutive extends in the same day

        loan.extend(now + document.loanDuration() + 1);  // dueTime > now + document.loanDuration() after the first extend
        assertThrows(CannotExtendEx.class, () -> loan.extend(now + document.loanDuration() + 2));  // more than two extends
    }

    @Property public void loanExtend(String name, String title, int date, int penaltyDuration) throws InvalidArgumentEx, CannotExtendEx {
        assumeFalse(name.isEmpty() || title.isEmpty());
        assumeThat(date, greaterThanOrEqualTo(0));
        assumeThat(penaltyDuration, greaterThan(0));

        Member member = new Professor(name);
        Document document = new Book(title);

        Loan loan = new Loan(member, document, date);

        assertEquals(
                loan.getPenalty(date + document.loanDuration() + penaltyDuration % document.loanDuration()),
                document.penaltyFor(penaltyDuration % document.loanDuration())
        );

        loan.extend(date + 1);
        assertEquals(
                loan.getPenalty(date + document.loanDuration() + penaltyDuration % document.loanDuration()),
                0
        );
        assertEquals(
                loan.getPenalty(date + document.loanDuration() * 2 + penaltyDuration % document.loanDuration()),
                document.penaltyFor(penaltyDuration % document.loanDuration())
        );

        loan.extend(date + 2);
        assertEquals(
                loan.getPenalty(date + document.loanDuration() + penaltyDuration % document.loanDuration()),
                0
        );
        assertEquals(
                loan.getPenalty(date + document.loanDuration() * 2 + penaltyDuration % document.loanDuration()),
                0
        );
        assertEquals(
                loan.getPenalty(date + document.loanDuration() * 3 + penaltyDuration % document.loanDuration()),
                document.penaltyFor(penaltyDuration % document.loanDuration())
        );
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

    @Property public void libraryPenalty(String sid, String studentName, String bookTitle, String referenceTitle, int copies, int days) throws LibraryException {
        assumeFalse(bookTitle.isEmpty() || sid.isEmpty() || studentName.isEmpty());
        assumeThat(copies, greaterThan(0));
        assumeThat(days, greaterThan(0));

        Document dummyBook = new Book("dummy");
        Document dummyReference = new Reference("dummy");

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
                dummyBook.penaltyFor(days - 10) + dummyReference.penaltyFor(days - 5)
        );
    }


}
