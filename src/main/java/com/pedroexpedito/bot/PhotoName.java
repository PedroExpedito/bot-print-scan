package com.pedroexpedito.bot;

import java.util.Date;

// static
public class PhotoName {
	private PhotoName() {
	}
	public static String getNewName() {
		Date date = new Date();
		Long unixTime = date.getTime() / 1000L;
		return unixTime.toString()+".jpg";
	}
}
