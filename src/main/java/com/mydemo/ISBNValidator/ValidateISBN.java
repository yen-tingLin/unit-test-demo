package com.mydemo.ISBNValidator;


public class ValidateISBN {
	
	public static void main(String[] args) {
		String t = "012000030X";
		System.out.println(t.matches("[0-9]+X"));
	}

	public boolean checkISBN(String input) {
		int size = input.length();
		if(size != 10) {
			throw new NumberFormatException("ISBN must be 10 digits");
		}
		if(!input.matches("[0-9]+")) {
			if(!input.matches("[0-9]+X")) {
				throw new NumberFormatException("last word of ISBN can only be digit or 'X'");
			} 		
		}
		
		int sum = 0;		
		for(int i = 0; i < size-1; i++) {
			// or Character.getNumericValue(input.charAt(i))
			sum += (input.charAt(i)-'0')*(10-i);
		}
		
		int remainder = sum % 11;
		// 檢查碼，初始值為整除的狀況
		int check = 0;
		if(remainder != 0) {
			check = 11 - remainder;
		}
		
		// or Character.getNumericValue(input.charAt(size-1))
		if((check == 10 && input.charAt(size-1) == 'X')) {
			return true;
		} else if(check == (input.charAt(size-1)-'0')) {
			return true;
		}
		return false;
		
	}

}
