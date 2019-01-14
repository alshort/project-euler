package euler;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTests {

	@Test
	public void checkPalindromes() {
		
		String[] palindromes = {
			"toot",
			"ABBA",
			"A Toyota's a Toyota",
			"On a clover, if alive, erupts a vast, pure evil; a fire volcano.",
			"Go hang a salami, I’m a lasagna hog."
		};
		
		for (String palindrome : palindromes) {
			palindrome = palindrome.toLowerCase().replaceAll("\\s+", "").replaceAll("[^a-z]", "");
			assertTrue(StringUtils.isPalindrome(palindrome));
		}		
	}
	
	@Test
	public void checkNotPalindromes() {
		String[] notPalindromes = {
			"tooting",
			"Pirates of the Caribbean",
			"This is not a palindrome"
		};

		for (String candidate : notPalindromes) {
			candidate = candidate.toLowerCase().replaceAll("\\s+", "").replaceAll("[^a-z]", "");
			assertFalse(StringUtils.isPalindrome(candidate));
		}	
	}

}
