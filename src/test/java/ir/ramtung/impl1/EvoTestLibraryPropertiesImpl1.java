package ir.ramtung.impl1;

import org.junit.Test;
import static org.evosuite.runtime.EvoAssertions.verifyException;
import static org.junit.Assert.*;
import java.util.List;

public class EvoTestLibraryPropertiesImpl1 {


	@Test(timeout = 4000)
	public void testCreatesLibraryAndCallsAddStudentMember() throws Throwable {
	    Library library0 = new Library();
	    library0.addStudentMember("$", "lA\"lYzE4!");
	}

	@Test(timeout = 4000)
	public void testAddReference() throws Throwable {
	    Library library0 = new Library();
	    library0.addReference("DXlCTv~:", 7);
	}

	@Test(timeout = 4000)
	public void testAddMagazineThrowsException() throws Throwable {
	    Library library0 = new Library();
	    library0.addMagazine("3", 5, 5, 5);
	    try {
	        library0.addMagazine("3", 3, 1, 1);
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // Document with the same title exists
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testReturnDocumentThrowsException() throws Throwable {
	    Library library0 = new Library();
	    library0.addMagazine("$", 1, 1, 1);
	    library0.addProfMember("$");
	    library0.borrow("$", "$");
	    try {
	        library0.returnDocument("$", "(\"Su7");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // The document is not in member's loan
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testReturnDocumentWithEmptyStringAndEmptyString() throws Throwable {
	    Library library0 = new Library();
	    library0.addMagazine("r7*YPM#%h?", 1, 1, 1);
	    library0.addProfMember("r7*YPM#%h?");
	    library0.borrow("r7*YPM#%h?", "r7*YPM#%h?");
	    try {
	        library0.returnDocument("", "");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // The document is not in member's loan
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testBorrowThrowsException() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.borrow("Cannot borrow more documents", "Cannot borrow more documents");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // Cannot find document to borrow
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testBorrow() throws Throwable {
	    Library library0 = new Library();
	    library0.addMagazine("j&", 2320, 2320, 2320);
	    library0.addProfMember("j&");
	    library0.borrow("j&", "j&");
	    library0.addProfMember(" f^Lr+_pZJl|ZpgF");
	    library0.borrow(" f^Lr+_pZJl|ZpgF", "j&");
	}

//	@Test(timeout = 4000)
//	public void testReturnDocumentWithNonEmptyString() throws Throwable {
//	    Library library0 = new Library();
//	    library0.addMagazine("$", 1, 1, 1);
//	    library0.addProfMember("$");
//	    library0.borrow("$", "$");
//	    try {
//	        library0.returnDocument("$", "(\"Su7");
//	        fail("Expecting exception: Exception");
//	    } catch (Exception e) {
//	        //
//	        // The document is not in member's loan
//	        //
//	        verifyException("ir.ramtung.impl1.Library", e);
//	    }
//	}

	@Test(timeout = 4000)
	public void testCreatesLibraryAndCallsTimePass() throws Throwable {
	    Library library0 = new Library();

		// WRONG: throw exception
		try {
			library0.timePass(0);
			fail("Expecting exception: Exception");
		} catch (Exception e) {
			//
			// The document is not in member's loan
			//
			verifyException("ir.ramtung.impl1.Library", e);
		}

	}

	@Test(timeout = 4000)
	public void testAvailableTitles() throws Throwable {
	    Library library0 = new Library();
	    library0.addMagazine("r7*YPM#%h?", 1, 1, 1);
	    library0.addProfMember("r7*YPM#%h?");
	    library0.borrow("r7*YPM#%h?", "r7*YPM#%h?");
	    List<String> list0 = library0.availableTitles();
	    assertFalse(list0.contains("r7*YPM#%h?"));
	}

//	@Test(timeout = 4000)
//	public void testCreatesLibraryAndCallsAddReference() throws Throwable {
//	    Library library0 = new Library();
//	    library0.addReference("DXlCTv~:", 7);
//	}

	@Test(timeout = 4000)
	public void testAddStudentMemberThrowsException() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.addStudentMember("", "");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // Empty member name not allowed
	        //
	        verifyException("ir.ramtung.impl1.Member", e);
	    }
	}

	@Test(timeout = 4000)
	public void testExtend() throws Throwable {
	    Library library0 = new Library();
	    library0.addStudentMember("=-k=gAfm2sf", "=-k=gAfm2sf");
	    library0.addMagazine("=-k=gAfm2sf", 1, 1, 1);
	    library0.borrow("=-k=gAfm2sf", "=-k=gAfm2sf");
	    library0.timePass(1);
	    library0.extend("=-k=gAfm2sf", "=-k=gAfm2sf");
	}

//	@Test(timeout = 4000)
//	public void testAddStudentMember() throws Throwable {
//	    Library library0 = new Library();
//	    library0.addStudentMember("$", "lA\"lYzE4!");
//	}

	@Test(timeout = 4000)
	public void testAddMagazineWithNegative() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.addMagazine((String) null, 1495, 1495, (-53));
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // Negative or zero copies of a document cannot be added
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testCreatesLibraryAndCallsAddBook() throws Throwable {
	    Library library0 = new Library();
	    library0.addBook("Member with the same name exists", 3438);
	}

	@Test(timeout = 4000)
	public void testAddBookThrowsException() throws Throwable {
	    Library library0 = new Library();
	    library0.addMagazine("$", 1, 1, 1);
	    try {
	        library0.addBook("$", 1);
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // Document with the same title exists
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testTimePassWithZero() throws Throwable {
	    Library library0 = new Library();
	    library0.timePass(0);
	}

//	@Test(timeout = 4000)
//	public void testAddBook() throws Throwable {
//	    Library library0 = new Library();
//	    library0.addBook("Member with the same name exists", 3438);
//	}

	@Test(timeout = 4000)
	public void testGetTotalPenaltyThrowsException() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.getTotalPenalty("");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // Cannot find member
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

//	@Test(timeout = 4000)
//	public void testAvailableTitlesReturningListWhereIsEmptyIsTrue() throws Throwable {
//	    Library library0 = new Library();
//	    library0.addMagazine("r7*YPM#%h?", 1, 1, 1);
//	    library0.addProfMember("r7*YPM#%h?");
//	    library0.borrow("r7*YPM#%h?", "r7*YPM#%h?");
//	    List<String> list0 = library0.availableTitles();
//	    assertFalse(list0.contains("r7*YPM#%h?"));
//	}

	@Test(timeout = 4000)
	public void testBorrowThrowsExceptionAndAddStudentMember() throws Throwable {
	    Library library0 = new Library();
	    library0.addStudentMember("l.]OLi-", "l.]OLi-");
	    library0.addMagazine("l.]OLi-", 1699, 1699, 1699);
	    library0.borrow("l.]OLi-", "l.]OLi-");
	    library0.borrow("l.]OLi-", "l.]OLi-");
	    try {
	        library0.borrow("l.]OLi-", "l.]OLi-");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // Cannot borrow more documents
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testBorrowThrowsExceptionAndAddProfMember() throws Throwable {
	    Library library0 = new Library();
	    library0.addMagazine("3", 1, 1, 1);
	    library0.addProfMember("3");
	    library0.borrow("3", "3");
	    try {
	        library0.borrow("3", "3");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // Document is not available to borrow
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testTimePassThrowsException() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.timePass((-680));
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // Cannot go back in time
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAddProfMemberThrowsException() throws Throwable {
	    Library library0 = new Library();
	    library0.addProfMember("TBe documenr is not in member's loan");
	    try {
	        library0.addProfMember("TBe documenr is not in member's loan");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // Member with the same name exists
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testExtendThrowsException() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.extend("r7*YPM#%h?", "");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // The document is not in member's loan
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testReturnDocument() throws Throwable {
	    Library library0 = new Library();
	    library0.addStudentMember("F=-k=gm2Gsf", "F=-k=gm2Gsf");
	    library0.addMagazine((String) null, 2588, 2588, 2805);
	    library0.borrow("F=-k=gm2Gsf", (String) null);
	    library0.returnDocument("F=-k=gm2Gsf", (String) null);
	}

	@Test(timeout = 4000)
	public void testGetTotalPenaltyReturningPositive() throws Throwable {
	    Library library0 = new Library();
	    library0.addStudentMember("l.]OLi-", "l.]OLi-");
	    library0.addMagazine("l.]OLi-", 1699, 1699, 1699);
	    library0.borrow("l.]OLi-", "l.]OLi-");
	    library0.timePass(1699);
	    int int0 = library0.getTotalPenalty("l.]OLi-");
	    assertEquals(5091000, int0);
	}

//	@Test(timeout = 4000)
//	public void testGetTotalPenalty() throws Throwable {
//	    Library library0 = new Library();
//	    library0.addStudentMember("l.]OLi-", "l.]OLi-");
//	    library0.addMagazine("l.]OLi-", 1699, 1699, 1699);
//	    library0.borrow("l.]OLi-", "l.]OLi-");
//	    library0.timePass(1699);
//	    int int0 = library0.getTotalPenalty("l.]OLi-");
//	    assertEquals(5091000, int0);
//	}

	@Test(timeout = 4000)
	public void testAddReferenceThrowsException() throws Throwable {
	    Library library0 = new Library();
	    try {
	        library0.addReference("ir.ramtung.impl1.CannotBorrowEx", 0);
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // Negative or zero copies of a document cannot be added
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

	@Test(timeout = 4000)
	public void testAvailableTitlesReturningListWhereIsEmptyIsFalse() throws Throwable {
	    Library library0 = new Library();
	    library0.addMagazine("3", 1920, 1, 1920);
	    List<String> list0 = library0.availableTitles();
	    assertTrue(list0.contains("3"));
	}

	@Test(timeout = 4000)
	public void testGetTotalPenaltyReturningZero() throws Throwable {
	    Library library0 = new Library();
	    library0.addStudentMember("%0?jS6Z_l&:2?E8~$I", "%0?jS6Z_l&:2?E8~$I");
	    library0.addMagazine("$", 2327, 2327, 2327);
	    library0.addProfMember("$");
	    library0.borrow("$", "$");
	    int int0 = library0.getTotalPenalty("%0?jS6Z_l&:2?E8~$I");
	    assertEquals(0, int0);
	}

	@Test(timeout = 4000)
	public void testBorrowThrowsExceptionAndAddMagazine() throws Throwable {
	    Library library0 = new Library();
	    library0.addMagazine("$", 1, 1, 1);
	    try {
	        library0.borrow("$", "$");
	        fail("Expecting exception: Exception");
	    } catch (Exception e) {
	        //
	        // Cannot find member to borrow
	        //
	        verifyException("ir.ramtung.impl1.Library", e);
	    }
	}

}
