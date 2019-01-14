package euler;

import java.util.List;

public class StringUtils {
	
	/**
	 * Ascertains whether a given input string is a palindrome.
	 * @param candidate Possible palindromic string.
	 * @return true if the input string is a palindrome, false otherwise.
	 */
	public static boolean
	isPalindrome(String candidate) {
		for (int i = 0; i < candidate.length() / 2; i++) {
			if (candidate.charAt(i) != candidate.charAt(candidate.length() - 1 - i)){
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * @param str
	 * @param rotation
	 * @return
	 */
	public static String
	rotateString(String str, int rotation) {
		return str.substring(rotation) + str.substring(0, rotation);
	}
	
	/**
	 * Joins multiple elements together with the same separator.
	 * @param separator String to fit between elements.
	 * @param elements The elements to concatenate together.
	 * @return A String with all elements included with the separator string between them.
	 */
	public static String
	join(String separator, List<? extends Object> elements) {
		
		StringBuilder sb = new StringBuilder();
		for (Object element : elements) {
			sb.append(element);
			if (elements.lastIndexOf(element) != elements.size() - 1) {
				sb.append(separator);
			}
		}
		return sb.toString();		
	}
}
