package com.ite5pjtbackoffice.backoffice.util;

public class PhoneNumUtil {
	
	public static String changeToFormat(String str) {
		 if (str == null) {
		      return "";
		 }else {
			if (str.length() == 8) {
		      return str.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
		    } else if (str.length() == 12) {
		      return str.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
		    }
		    return str.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
		 }
	}
}
