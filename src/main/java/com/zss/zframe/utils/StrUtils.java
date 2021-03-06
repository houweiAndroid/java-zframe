package com.zss.zframe.utils;

public class StrUtils {

	/**
	 * Example: http://www.fujianquan.cn/image/xxxx.jpg = xxxx.jpg
	 * 
	 * @param src
	 * @return
	 */
	public static String subStringUrlToName(String src) {
		if(isEmpty(src)) {
			return "";
		}
		int indexFrom = src.lastIndexOf("/");
		return src.substring(indexFrom + 1, src.length());
	}
	
	/**
	 * Example: subString("12345","1","4")=23
	 * 
	 * @param src
	 * @param start
	 * @param to
	 * @return
	 */
	public static Integer subStringToInteger(String src, String start, String to) {
		return stringToInteger(subString(src, start, to));
	}

	/**
	 * Example: subString("abcd","a","c")="b"
	 * 
	 * @param src
	 * @param start
	 *            null while start from index=0
	 * @param to
	 *            null while to index=src.length
	 * @return
	 */
	public static String subString(String src, String start, String to) {
		int indexFrom = start == null ? 0 : src.indexOf(start);
		int indexTo = to == null ? src.length() : src.indexOf(to);
		if (indexFrom < 0 || indexTo < 0 || indexFrom > indexTo) {
			return null;
		}

		if (null != start) {
			indexFrom += start.length();
		}

		return src.substring(indexFrom, indexTo);

	}

	/**
	 * Example: subString("abcdc","a","c",true)="bcd"
	 * 
	 * @param src
	 * @param start
	 *            null while start from index=0
	 * @param to
	 *            null while to index=src.length
	 * @param toLast
	 *            true while to index=src.lastIndexOf(to)
	 * @return
	 */
	public static String subString(String src, String start, String to, boolean toLast) {
		if (!toLast) {
			return subString(src, start, to);
		}
		int indexFrom = start == null ? 0 : src.indexOf(start);
		int indexTo = to == null ? src.length() : src.lastIndexOf(to);
		if (indexFrom < 0 || indexTo < 0 || indexFrom > indexTo) {
			return null;
		}

		if (null != start) {
			indexFrom += start.length();
		}

		return src.substring(indexFrom, indexTo);

	}

	/**
	 * @param in
	 * @return
	 */
	public static Integer stringToInteger(String in) {
		if (in == null) {
			return null;
		}
		in = in.trim();
		if (in.length() == 0) {
			return null;
		}

		try {
			return Integer.parseInt(in);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static boolean isEmpty(CharSequence value) {
		if (value == null || value.length() == 0 || value.equals("null")) {
			return true;
		}

		return false;
	}

	public static boolean isNumber(String str) {
		if (isEmpty(str)) {
			return false;
		}
		char[] chars = str.toCharArray();
		int sz = chars.length;
		boolean hasExp = false;
		boolean hasDecPoint = false;
		boolean allowSigns = false;
		boolean foundDigit = false;
		// deal with any possible sign up front
		int start = (chars[0] == '-') ? 1 : 0;
		if (sz > start + 1) {
			if (chars[start] == '0' && chars[start + 1] == 'x') {
				int i = start + 2;
				if (i == sz) {
					return false; // str == "0x"
				}
				// checking hex (it can't be anything else)
				for (; i < chars.length; i++) {
					if ((chars[i] < '0' || chars[i] > '9') && (chars[i] < 'a' || chars[i] > 'f')
							&& (chars[i] < 'A' || chars[i] > 'F')) {
						return false;
					}
				}
				return true;
			}
		}
		sz--; // don't want to loop to the last char, check it afterwords
		// for type qualifiers
		int i = start;
		// loop to the next to last char or to the last char if we need another digit to
		// make a valid number (e.g. chars[0..5] = "1234E")
		while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				foundDigit = true;
				allowSigns = false;

			} else if (chars[i] == '.') {
				if (hasDecPoint || hasExp) {
					// two decimal points or dec in exponent
					return false;
				}
				hasDecPoint = true;
			} else if (chars[i] == 'e' || chars[i] == 'E') {
				// we've already taken care of hex.
				if (hasExp) {
					// two E's
					return false;
				}
				if (!foundDigit) {
					return false;
				}
				hasExp = true;
				allowSigns = true;
			} else if (chars[i] == '+' || chars[i] == '-') {
				if (!allowSigns) {
					return false;
				}
				allowSigns = false;
				foundDigit = false; // we need a digit after the E
			} else {
				return false;
			}
			i++;
		}
		if (i < chars.length) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				// no type qualifier, OK
				return true;
			}
			if (chars[i] == 'e' || chars[i] == 'E') {
				// can't have an E at the last byte
				return false;
			}
			if (!allowSigns && (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F')) {
				return foundDigit;
			}
			if (chars[i] == 'l' || chars[i] == 'L') {
				// not allowing L with an exponent
				return foundDigit && !hasExp;
			}
			// last character is illegal
			return false;
		}
		// allowSigns is true iff the val ends in 'E'
		// found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
		return !allowSigns && foundDigit;
	}
	

}
