package com.anirban.skiing.exception;

import java.util.Locale;

public class StupidInputException extends Exception {
	private static final long serialVersionUID = -1785999637873726675L;

	private StupidInputException(String message) {
		super(message);
	}

	private StupidInputException(String message,Throwable t) {
		super(message,t);
	}
	
	public static void throwMe(String message,Locale locale) throws StupidInputException{
		throw new StupidInputException(convertToLocalLanguage(message,locale));
	}
	public static void throwMe(String message,Throwable t,Locale locale) throws StupidInputException{
		throw new StupidInputException(convertToLocalLanguage(message,locale),t);
	}

	private static String convertToLocalLanguage(String message, Locale locale) {
		if(locale == Locale.ENGLISH){
			return message;
		}else{
			//DO some conversion and return message
			return message;
		}
	}

}
