package com.mydemo.book.isbnvalidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidateISBNTest {

	@Test
	public void checkValidISBN() {
		ValidateISBN validator = new ValidateISBN();

		String input1 = "0525563385";
		boolean result = validator.checkISBN(input1);
		assertTrue("first result", result);	

		String input2 = "0307957896";
		result = validator.checkISBN(input2);
		assertTrue("second result", result);

		String input3 = "0385546130";
		result = validator.checkISBN(input3);
		assertTrue("third result", result);
	}
	
	@Test
	public void ISBNNumberEndingInAnXIsValid() {
		ValidateISBN validator = new ValidateISBN();

		String input1 = "012000030X";
		boolean result = validator.checkISBN(input1);
		assertTrue(result);	
	}

	
	@Test
	public void checkInvalidISBN() {
		ValidateISBN validator = new ValidateISBN();

		String input = "0525563386";
		boolean result = validator.checkISBN(input);
		assertFalse(result);
	}	
	
	@Test(expected = NumberFormatException.class)
	public void nineDigitISBNIsNotAllowed() {
		ValidateISBN validator = new ValidateISBN();

		String input = "038554613";
		validator.checkISBN(input);		
	}
	
	@Test(expected = NumberFormatException.class)
	public void nonNumericISBNIsNotAllowed() {
		ValidateISBN validator = new ValidateISBN();

		String input = "heymorning";
		validator.checkISBN(input);			
	}
	
}
