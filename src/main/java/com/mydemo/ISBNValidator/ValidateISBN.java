package com.mydemo.ISBNValidator;


public class ValidateISBN {
	
	private static final int _10_UNIT_LONG = 10;

	public boolean checkISBN(String input) {
		int size = input.length();
		// 檢查 input 的格式
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
		
		// 確認檢查碼 (int check) 是否和最後一位的數字相同；若檢查碼為 10，最後一位須為 X
		// or Character.getNumericValue(input.charAt(size-1))
		if((check == 10 && input.charAt(size-1) == 'X') || check == (input.charAt(size-1)-'0')) {
			return true;
		} 
		return false;		
	}

}
