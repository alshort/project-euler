package euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class MathsUtils {

	
	/**
	 * Calculates the factorial of a given integer n.
	 * @param n
	 * @return BigInteger representing the factorial of n (Note: BigInteger is like a String, so look out for reference equality!)
	 */
	public static BigInteger
	factorial(int n) {
		
		assert(n >= 0) : "Can't do a factorial < 0";
		if (n == 0) return BigInteger.valueOf((long)1);
		
		BigInteger[] factorial = new BigInteger[n];
		
		for (int i = 1; i <= n; i++) {
			if (i == 1) {
				factorial[i - 1] = BigInteger.valueOf((long)1);
			} else {
				factorial[i - 1] = factorial[i - 2].multiply(BigInteger.valueOf((long)i));
			}		
		}
		
		return factorial[n - 1];		
	}
	
	/**
	 * Slightly optimised way to test the primality of a number.
	 * @param n Number to test the primality of.
	 * @return true if n is prime, false otherwise.
	 */
	public static boolean
	isPrime(int n) {
		
		assert(n > 0) : "Negative numbers cannot be prime.";
		
		if (n == 1) return false;
		if (n % 2 == 0) return n == 2;
		
		int sqrt = (int)Math.ceil(Math.sqrt(n)) + 1;
		
		for (int i = 3; i < sqrt; i += 2)			
			if (n % i == 0)
				return false;
		
		return true;		
	}
	
	public static boolean
	isCircularPrime(int n) {
		
		String intStr = Integer.toString(n);
		int digitCount = Integer.toString(n).length();
		
		for (int i = 0; i < digitCount; i++) {
			int rotation = Integer.parseInt(StringUtils.rotateString(intStr, i));
			
			if (!MathsUtils.isPrime(rotation)) return false;
		}
		
		return true;
	}
	
	
	public static List<Long>
	primeSieve(int limit) {
		
		HashMap<Long, Boolean> primes = new HashMap<Long, Boolean>();
		for (int value : range(2, limit + 1)) {
			primes.put((long)value, true);
		}
		
		for (long i = 2; i <= limit; i++) {
			if (primes.get(i)) {
				for (long j = i; j * i <= limit; j++) {
					primes.replace(j * i, false);
				}
			}
		}
		
		Map<Long, Boolean> filteredPrimes = 
				primes.entrySet()
				.stream()
				.filter(p -> p.getValue())
				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		
		ArrayList<Long> filtered = new ArrayList<Long>(filteredPrimes.keySet());
		Collections.sort(filtered);
		
		return filtered;
	}
	
	
	public static List<Integer>
	range(int min, int max) {
		
		List<Integer> ints = new ArrayList<Integer>();
		
		for (int i = min; i < max; i++) {
			ints.add(i);
		}
		
		return ints;		
	}
	
	public static long
	sum(List<Long> ints) {
		long sum = 0;
		for (long i : ints) {
			sum += i;
		}
		return sum;
	}
	
	public static int
	abs(int a, int b) {
		if (b - a < 0) {
			return - (b - a);
		} else {
			return b - a;
		}
	}
	
	/*
	 * 
	 */
	public static boolean
	isPythagoreanTriplet(int a, int b, int c) {	
		
		return (a * a) + (b * b) == (c * c);		
	}
	
	/**
	 * Gets all the unique divisors of an integer.
	 * @param n The integer to find the divisors of.
	 * @return Sorted List of all unique divisors of integer n.
	 */
	public static List<Integer>
	getDivisorsOf(int n) {
		
		List<Integer> divisors = new ArrayList<Integer>();
		
		int sqrt = (int)Math.ceil(Math.sqrt(n)) + 1;
		
		for (int i = 1; i < sqrt; i++) {
			if (n % i == 0) {
				divisors.add(i);
				int pair = n / i;
				if (!divisors.contains(pair)) {
					divisors.add(pair);
				}
			}
		}
		
		Collections.sort(divisors);
		return divisors;
	}
	
	/**
	 * Produces the nth triangular number.
	 * @param n Index of triangular number to calculate.
	 * @return nth triangular number
	 */
	public static int
	triangular(int n) {
		
		assert(n >= 1) : "Can't have negative index of sequence.";
		
		int triangular = 0;
		for (int i = 1; i <= n; i++) {
			triangular += i;
		}
		
		return triangular;
	}
	
	
	public static boolean
	isTriangular(int n) {
		
		int index = 0;
		int triangularNum;
		
		do {
			index++;
			triangularNum = triangular(index);
			
			if (triangularNum == n) {
				return true;
			}
		}
		while(triangularNum < n);
		
		return false;
	}
	
	
	public static int
	pentagonal(int n) {
		
		assert(n >= 1);
		
		return (n * ((3 * n) - 1)) / 2;
	}
	
	
	public static boolean
	isPentagonal(int n) {
		
		int index = 0;
		int pentagonalNum;
		
		do {
			index++;
			pentagonalNum = pentagonal(index);
			
			if (pentagonalNum == n) {
				return true;
			}
		}
		while(pentagonalNum < n);
		
		return false;
	}
	
	public static int
	hexagonal(int n) {
		
		assert(n >= 1);
		
		return n * ((2 * n) - 1);
	}
	
	public static boolean
	isHexagonal(int n) {
		
		int index = 0;
		int hexagonalNum;
		
		do {
			index++;
			hexagonalNum = hexagonal(index);
			
			if (hexagonalNum == n) {
				return true;
			}
		}
		while(hexagonalNum < n);
		
		return false;
	}
	
	public static boolean
	isPandigital(int n) {
		List<Integer> digits = getDigitsOf(n);
		Collections.sort(digits);
		
		if (digits.get(0) != 1) return false;
		for (int i = 0; i < digits.size() - 1; i++) {
			if (digits.get(i) == digits.get(i + 1))
				return false;
			if (digits.get(i + 1) - digits.get(i) != 1)
				return false;
		}
		
		return true;
	}
	
	
	public static List<Integer>
	getDigitsOf(int n) {
		
		int number = n;
		Stack<Integer> digitStack = new Stack<Integer>();
		
		while (number > 0) {
			digitStack.push(number % 10);
			number = number / 10;
		}
		
		List<Integer> digits = new ArrayList<Integer>();
		while (!digitStack.isEmpty()) {
			digits.add(digitStack.pop());
		}
		
		return digits;
	}
	
	public static HashMap<Integer, Integer>
	digitFrequency(int n) {
		
		HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
		
		int num = n;
		
		while (num > 0) {
			int digit = num % 10;
			
			if (counts.containsKey(digit)) {
				int count = counts.get(digit);
				counts.replace(digit, count + 1);
			} else {
				counts.put(digit, 1);
			}
			
			num /= 10;
		}
		
		return counts;
	}
	
	public static List<Integer>
	primeFactorsOf(int n) {
		
		int num = n;
		List<Integer> primeFactors = new ArrayList<Integer>();
		
		for (int i = 2; i <= n; i++) {
			while(num % i == 0) {
				primeFactors.add(i);
				num /= i;
			}
		}
		
		return primeFactors;
	}
	
	public static Double[]
	quadraticFormula(double a, double b, double c) {
		
		Double[] possibilities = new Double[2];
		
		possibilities[0] = (-b + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
		possibilities[1] = (-b - Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
		
		return possibilities;
	}
	
	public static ArrayList<Long>
	collatzSequence(int n) {
		
		ArrayList<Long> sequence = new ArrayList<Long>();
		sequence.add((long)n);
		
		long num = (long)n;
		
		while (num > 1) {
			if (num % 2 == 0) {
				num /= 2;
			} else {
				num = (3 * num) + 1;
			}
			sequence.add(num);
		}
		
		
		return sequence;
	}
}
