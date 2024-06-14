package ir.ramtung.impl2;

import org.junit.Test;
import static org.evosuite.runtime.EvoAssertions.verifyException;
import static org.junit.Assert.*;
import java.util.List;

public class EvoTestLibraryPropertiesImpl2 {


	@Test(timeout = 4000)
	public void testAddBookWithZero() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.addBook(" ", 0);
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // book name is empty
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAddStudentMemberThrowsNullPointerException() throws Throwable {
	    Library library0 = new Library();
	    // Undeclared exception!
	    library0.addStudentMember((String) null, (String) null);
	}

	@Test(timeout = 4000)
	public void testAddMagazineWithZero() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.addMagazine("", 0, 0, 0);
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // magazine`s year is incorrect
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAddMagazineThrowsException() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.addMagazine(" ", 3398, 3398, 3398);
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // magazine name is empty
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAddStudentMemberWithEmptyStringAndNonEmptyString() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.addStudentMember("!", "");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // student name is empty
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAddProfMemberThrowsExceptionAndAddStudentMember() throws Throwable {
	    Library library0 = new Library();
	    library0.addStudentMember("Fo1^]l3wQ:Wn%", "Fo1^]l3wQ:Wn%");
	    try {
	        library0.addProfMember("Fo1^]l3wQ:Wn%");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // the member has already added
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testBorrow() throws Throwable {
	    Library library0 = new Library();
	    library0.addStudentMember("0", "0");

		// WRONG: invalid member and reference
		try {
			library0.borrow("ir.ramtung.impl2.Document", "(oDL<~U");
			fail("Expecting exception: Exception");
		} catch (Exception e) {
			//
			// magazine`s number is incorrect
			//
			verifyException("ir.ramtung.impl2.Library", e);
		}
	}

	@Test(timeout = 4000)
	public void testBorrowThrowsException() throws Throwable {
	    Library library0 = new Library();

		// WRONG: Negative copies
	    try {
			library0.addReference("ir.ramtung.impl2.Person", (-1));
//			library0.borrow("ir.ramtung.impl2.Person", "ir.ramtung.impl2.Person");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // this book doesnt exist
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAddReferenceAndAvailableTitles() throws Throwable {
	    Library library0 = new Library();
		// WRONG: prefer exception
	    library0.addReference("magazine`s number is incorrect", 0);
	    List<String> list0 = library0.availableTitles();
	    assertFalse(list0.contains("magazine`s number is incorrect"));
	}

	@Test(timeout = 4000)
	public void testAvailableTitles() throws Throwable {
	    Library library0 = new Library();
	    library0.addProfMember("magazine`s year is incorrect");
		// WRONG: prefer exception
	    library0.addReference("magazine`s year is incorrect", 0);

		// WRONG: exception
//	    library0.borrow("magazine`s year is incorrect", ";z:");
	    List<String> list0 = library0.availableTitles();
	    assertFalse(list0.contains("magazine`s year is incorrect"));
	}

	@Test(timeout = 4000)
	public void testAddStudentMemberThrowsException() throws Throwable {
	    Library library0 = new Library();
	    library0.addProfMember("QnRCl]Aa;$");
	    try {
	        library0.addStudentMember("be\"Ij0Y^[]", "QnRCl]Aa;$");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // the member has already added
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testExtend() throws Throwable {
	    Library library0 = new Library();
	    library0.addProfMember(";aiO9m_");

		// WRONG: exception
		try {
			library0.extend(";aiO9m_", (String) null);
			fail("Expecting exception: Exception");
		} catch (Exception e) {
			//
			// magazine`s number is incorrect
			//
			verifyException("ir.ramtung.impl2.Library", e);
		}
	}

	@Test(timeout = 4000)
	public void testAddStudentMember() throws Throwable {
	    Library library0 = new Library();
	    library0.addProfMember("%");
	    library0.addStudentMember("%", "magazine`s number is incorrect");
	}

	@Test(timeout = 4000)
	public void testAddBook() throws Throwable {
	    Library library0 = new Library();
	    library0.addReference("QnRCl]Aa;$", 2200);
	    library0.addBook("be\"Ij0Y^[]", 296);
	}

	@Test(timeout = 4000)
	public void testAddProfMemberThrowsException() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.addProfMember(" ");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // prof name is empty
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testExtendThrowsException() throws Throwable {
	    Library library0 = new Library();
		// WRONG: exceptions a lot.....
	    try {
			library0.addProfMember("");
			library0.addBook("magazine name is empty", (-1994362782));
			library0.borrow("", "");
			library0.extend("", "he-x f-9;");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // cant extend
	        //
	        verifyException("ir.ramtung.impl2.Person", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAddBookWithEmptyString() throws Throwable {
	    Library library0 = new Library();

		// WRONG: exception
	    library0.addBook("", 18);
	    try {
	        library0.addReference("", 1345);
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // the reference has already added
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAddReferenceAndBorrow1() throws Throwable {
	    Library library0 = new Library();
	    library0.addReference("%", 1536);
	    library0.addReference("(oDL<~U", 31);
		// WRONG: exception
	    library0.borrow("ir.ramtung.impl2.Document", "(oDL<~U");
	}

	@Test(timeout = 4000)
	public void testAddReferenceAndBorrow0() throws Throwable {
	    Library library0 = new Library();
	    library0.addReference("(oDL<~U", 31);
		// WRONG: exception
	    library0.borrow("ir.ramtung.impl2.Document", "(oDL<~U");
	}

//	@Test(timeout = 4000)
//	public void testAddProfMemberAndExtend0() throws Throwable {
//	    Library library0 = new Library();
//	    library0.addProfMember(";aiO9m_");
//	    library0.extend(";aiO9m_", (String) null);
//	}

	@Test(timeout = 4000)
	public void testAddProfMemberAndExtend1() throws Throwable {
	    Library library0 = new Library();
	    library0.addProfMember("[7^d_i8");
		// WRONG: exception
	    library0.extend("$d|lj1", (String) null);
	}

	@Test(timeout = 4000)
	public void testReturnDocument() throws Throwable {
	    Library library0 = new Library();
	    library0.addProfMember("cant extend");
		// WRONG: exception
	    library0.returnDocument("cant extend", "");
	}

	@Test(timeout = 4000)
	public void testGetTotalPenaltyReturningPositive() throws Throwable {
	    Library library0 = new Library();
	    library0.addReference("%", 1536);
	    library0.addStudentMember("0", "0");
	    library0.borrow("0", "0");
	    library0.timePass(31);
	    int int0 = library0.getTotalPenalty("0");
		// WRONG: wrong penalty
	    assertEquals(176000, int0);
	}

//	@Test(timeout = 4000)
//	public void testGetTotalPenalty() throws Throwable {
//	    Library library0 = new Library();
//	    library0.addReference("%", 1536);
//	    library0.addStudentMember("0", "0");
//	    library0.borrow("0", "0");
//	    library0.timePass(31);
//	    int int0 = library0.getTotalPenalty("0");
//	    assertEquals(176000, int0);
//	}

	@Test(timeout = 4000)
	public void testAddReferenceThrowsNullPointerException() throws Throwable {
	    Library library0 = new Library();
	    // Undeclared exception!
	    library0.addReference((String) null, (-1));
	}

	@Test(timeout = 4000)
	public void testAddReferenceWithEmptyStringAndBorrowWithEmptyString() throws Throwable {
	    Library library0 = new Library();

		// WRONG: exception
	    library0.addReference("", 0);
	    try {
	        library0.borrow("YMo", "");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // this book doesnt exist
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAddProfMemberAndAddStudentMember0() throws Throwable {
	    Library library0 = new Library();
	    library0.addStudentMember("xL\"&", "xL\"&");
	    library0.addProfMember("W&=.f?_4-QVLD/~]x");
	}

	@Test(timeout = 4000)
	public void testReturnDocumentWithNonEmptyString() throws Throwable {
	    Library library0 = new Library();
	    library0.addStudentMember("xL\"&", "xL\"&");
		// WRONG: exception
	    library0.returnDocument("L0!:", "Ty;v");
	}

	@Test(timeout = 4000)
	public void testCreatesLibraryAndCallsTimePass() throws Throwable {
	    Library library0 = new Library();
		try {
			library0.timePass(0);
			fail("Expecting exception: Exception");
		} catch (Exception e) {
			//
			// magazine`s number is incorrect
			//
			verifyException("ir.ramtung.impl2.Library", e);
		}
	}

//	@Test(timeout = 4000)
//	public void testAddStudentMemberAndBorrow() throws Throwable {
//	    Library library0 = new Library();
//	    library0.addStudentMember("0", "0");
//	    library0.borrow("ir.ramtung.impl2.Document", "(oDL<~U");
//	}

//	@Test(timeout = 4000)
//	public void testAddProfMemberAndAddStudentMember1() throws Throwable {
//	    Library library0 = new Library();
//	    library0.addProfMember("%");
//	    library0.addStudentMember("%", "magazine`s number is incorrect");
//	}

	@Test(timeout = 4000)
	public void testAddMagazineWithNegative() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.addMagazine("ir.ramtung.impl2.Document", 3073, (-1968783920), 3073);
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // magazine`s number is incorrect
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAddBookThrowsException() throws Throwable {
	    Library library0 = new Library();
	    library0.addReference("\"", 530);
	    try {
	        library0.addBook("\"", 530);
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // the book has already added
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

//	@Test(timeout = 4000)
//	public void testTimePassWithZero() throws Throwable {
//	    Library library0 = new Library();
//	    library0.timePass(0);
//	}

//	@Test(timeout = 4000)
//	public void testAddProfMember() throws Throwable {
//	    Library library0 = new Library();
//	    library0.addStudentMember("xL\"&", "xL\"&");
//	    library0.addProfMember("W&=.f?_4-QVLD/~]x");
//	}

	@Test(timeout = 4000)
	public void testAddMagazineThrowsExceptionAndAddReference() throws Throwable {
	    Library library0 = new Library();
	    library0.addReference("\"", 510);
	    try {
	        library0.addMagazine("\"", 510, 510, 510);
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // the magazine has already added
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

//	@Test(timeout = 4000)
//	public void testReturnDocumentWithEmptyString() throws Throwable {
//	    Library library0 = new Library();
//	    library0.addProfMember("cant extend");
//	    library0.returnDocument("cant extend", "");
//	}

	@Test(timeout = 4000)
	public void testAddProfMemberThrowsNullPointerException() throws Throwable {
	    Library library0 = new Library();
	    // Undeclared exception!
	    library0.addProfMember((String) null);
	}

	@Test(timeout = 4000)
	public void testAddMagazine() throws Throwable {
	    Library library0 = new Library();
	    library0.addReference("%", 1536);
	    library0.addMagazine("uM#f%f@IV0&TmD&^,", 176000, 31, 3073);
	}

	@Test(timeout = 4000)
	public void testTimePassThrowsException() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.timePass((-2028578983));
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // days cant be negative
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testTimePassThrowsTooManyResourcesException() throws Throwable {
	    Library library0 = new Library();
	    // Undeclared exception!
	    library0.timePass(2147483645);
	}

	@Test(timeout = 4000)
	public void testAddMagazineWithNegativeAndAddMagazineWithZeroAndAddMagazineWithEmptyString() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.addMagazine("", 2265, 0, (-52));
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // magazine`s number is incorrect
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testBorrowThrowsIndexOutOfBoundsException() throws Throwable {
	    Library library0 = new Library();
	    library0.addStudentMember("0", "0");
	    // Undeclared exception!
	    assertThrows(Exception.class, () -> library0.borrow("0", "0"));
	}

	@Test(timeout = 4000)
	public void testAddStudentMemberWithEmptyStringAndEmptyString() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.addStudentMember("", "");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // student id is empty
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAddMagazineWithNegativeAndAddMagazineWithZeroAndAddMagazineWithNonEmptyString() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.addMagazine("ir.ramtung.impl2.Book", (-1163), (-966), 0);
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // magazine`s year is incorrect
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAddBookThrowsNullPointerException() throws Throwable {
	    Library library0 = new Library();
	    // Undeclared exception!
	    library0.addBook((String) null, 0);
	}

	@Test(timeout = 4000)
	public void testAddMagazineThrowsNullPointerException() throws Throwable {
	    Library library0 = new Library();
	    // Undeclared exception!
	    library0.addMagazine((String) null, 1611, 1611, 1611);
	}

	@Test(timeout = 4000)
	public void testAddReferenceThrowsException() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.addReference(" ", (-52));
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // reference name is empty
	        //
	        verifyException("ir.ramtung.impl2.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAvailableTitlesReturningListWhereIsEmptyIsFalse() throws Throwable {
	    Library library0 = new Library();
	    library0.addReference("%", 1536);
	    List<String> list0 = library0.availableTitles();
	    assertTrue(list0.contains("%"));
	}

	@Test(timeout = 4000)
	public void testGetTotalPenaltyReturningZero() throws Throwable {
	    Library library0 = new Library();
	    library0.addStudentMember("Fo1^]l3wQ:Wn%", "Fo1^]l3wQ:Wn%");
		// WRONG: exception
	    int int0 = library0.getTotalPenalty("");
	    assertEquals(0, int0);
	}

}
