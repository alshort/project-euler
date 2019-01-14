package euler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class MathsUtilsTests {

	@Test
	public void factorialTests() {
		assertTrue(MathsUtils.factorial(1).equals(BigInteger.valueOf((long)1)));
		assertTrue(MathsUtils.factorial(2).equals(BigInteger.valueOf((long)2)));
		assertTrue(MathsUtils.factorial(5).equals(BigInteger.valueOf((long)120)));
		assertTrue(MathsUtils.factorial(0).equals(BigInteger.valueOf((long)1)));
		assertTrue(MathsUtils.factorial(10).equals(BigInteger.valueOf((long)3628800)));
	}
	
	@Test
	public void primeTests() {
		assertTrue(MathsUtils.isPrime(2));
		assertTrue(MathsUtils.isPrime(5));
		assertTrue(MathsUtils.isPrime(19));
		assertTrue(MathsUtils.isPrime(197));
		assertTrue(MathsUtils.isPrime(7927));
		assertTrue(MathsUtils.isPrime(2903));
		
		assertFalse(MathsUtils.isPrime(1));
		assertFalse(MathsUtils.isPrime(9));
		assertFalse(MathsUtils.isPrime(4));
		assertFalse(MathsUtils.isPrime(78));
		assertFalse(MathsUtils.isPrime(9009));
		assertFalse(MathsUtils.isPrime(502));
		assertFalse(MathsUtils.isPrime(1000));
	}
	
	@Test
	public void primeSieveTests() {
		HashMap<Integer, List<Long>> expectedResults = new HashMap<Integer, List<Long>>();
		
		expectedResults.put(10, Arrays.asList(2l, 3l, 5l, 7l));
		expectedResults.put(20, Arrays.asList(2l, 3l, 5l, 7l, 11l, 13l, 17l, 19l));
		expectedResults.put(50, Arrays.asList(2l, 3l, 5l, 7l, 11l, 13l, 17l, 19l, 23l, 29l, 31l, 37l, 41l, 43l, 47l));
		expectedResults.put(100, Arrays.asList(2l, 3l, 5l, 7l, 11l, 13l, 17l, 19l, 23l, 29l, 31l, 37l, 41l, 43l, 47l, 53l, 59l, 61l, 67l, 71l, 73l, 79l, 83l, 89l, 97l));
		
		for (Integer limit : expectedResults.keySet()) {
			assertEquals(MathsUtils.primeSieve(limit), expectedResults.get(limit));
		}
	}
	
	@Test
	public void pandigitals() {		
		assertTrue(MathsUtils.isPandigital(123456789));
		assertTrue(MathsUtils.isPandigital(1234567));
		assertTrue(MathsUtils.isPandigital(7654321));
		assertTrue(MathsUtils.isPandigital(2143));
		
		assertFalse(MathsUtils.isPandigital(214));
		assertFalse(MathsUtils.isPandigital(12345789));
		assertFalse(MathsUtils.isPandigital(8453021));
		assertFalse(MathsUtils.isPandigital(1203456789));
		assertFalse(MathsUtils.isPandigital(887645321));
	}
	
	@Test
	public void getDigitsOfTests() {
		assertEquals(MathsUtils.getDigitsOf(123456789), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
	}
	
	@Test
	public void isPentagonalTests() {
		List<Integer> expectedPentagonals = Arrays.asList(1, 5, 12, 22, 35, 51, 70, 92, 117, 145);
		
		List<Integer> foundPentagonals = new ArrayList<Integer>();
		
		for (int i = 1; i <= 150; i++) {
			if (MathsUtils.isPentagonal(i)) {
				foundPentagonals.add(i);
			}
		}
		
		assertEquals(foundPentagonals, expectedPentagonals);		
	}

}
