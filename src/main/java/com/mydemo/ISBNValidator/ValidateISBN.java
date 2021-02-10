package com.mydemo.ISBNValidator;


public class ValidateISBN {
	
	private static final int _10_UNIT_LONG = 10;
	
	public static void main(String[] args) {
		String t = "012000030X";
		System.out.println(t.matches("[0-9]+X"));
	}

	public boolean checkISBN(String input) {
		int size = input.length();
		// check input by format
		if(size != _10_UNIT_LONG) {
			throw new NumberFormatException("ISBN must be 10 words");
		}
		if(!input.matches("[0-9]+")) {
			if(!input.matches("[0-9]+X")) {
				throw new NumberFormatException("last word of ISBN can only be digit or 'X'");
			} 		
		}
		
		return check10DigitsISBN(input, size);
		
	}
	
	private boolean check10DigitsISBN(String input, int size) {
		// 依照檢查邏輯計算總合
		int sum = 0;		
		for(int i = 0; i < size-1; i++) {
			// or Character.getNumericValue(input.charAt(i))
			sum += (input.charAt(i)-'0')*(_10_UNIT_LONG-i);
		}
		
		int remainder = sum % 11;
		// 檢查碼，初始值為整除的狀況
		int check = 0;
		if(remainder != 0) {
			// 負餘數
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
