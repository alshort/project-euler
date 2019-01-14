package euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Problems {
	
	private final static Logger LOGGER = Logger.getLogger(Problems.class.getName());
	
	private final static List<Integer> PROBLEMS = Arrays.asList(14); // Problems to execute
	private static HashMap<Integer, Problem> problems = new HashMap<Integer, Problem>();

	public static void main(String[] args) {
		
		// Submit jobs
		problems.put(4, new Problem(4, () -> largestPalindromicProduct())); 
		problems.put(5, new Problem(5, () -> smallestMultipleDivisbleByOneToTwenty()));
		problems.put(7, new Problem(7, () -> prime10001()));
		problems.put(9, new Problem(9, () -> specialPythagoreanTriplet()));
		problems.put(10, new Problem(10, () -> summationOfPrimesUnder(2000000)));
		problems.put(12, new Problem(12, () -> highlyDivisbleTriangularNumber()));
		problems.put(14, new Problem(14, () -> largestCollatzSequence()));		
		problems.put(20, new Problem(20, () -> factorialDigitSum()));
		problems.put(35, new Problem(35, () -> circularPrimesUnder(1000000)));
		problems.put(41, new Problem(41, () -> largestPandigitalPrime()));
		problems.put(44, new Problem(44, () -> pentagonalNumbers()));
		problems.put(45, new Problem(45, () -> triangularPentagonalAndHexagonal()));
		problems.put(47, new Problem(47, () -> distinctPrimeFactors(4)));
		
		
		/*for (int i = 1; i <= 1000; i++) {
			if (i > 1) {
				System.out.print(MathsUtils.triangular(i));
				System.out.println(" | Diff: " + (MathsUtils.triangular(i) - MathsUtils.triangular(i - 1)));
			} else {
				System.out.println(MathsUtils.triangular(i));
			}
		}*/
		
		ExecutorService execService = Executors.newFixedThreadPool(5);	
		
		for (int problem : PROBLEMS) {
			execService.execute(problems.get(problem));
		}
		
		execService.shutdown();
	}


	/*
	 * Problem 4: Largest Palindromic Product Composed of 2 3-digit Factors
	 * Correct!
	 * Solution: 906609 = 913 x 993
	 */
	public static void
	largestPalindromicProduct() {
		
		int largestPalindrome = 0;
		int[] factors = new int[2];
		
		for (int i = 100; i < 1000; i++) {
			for (int j = 100; j < 1000; j++) {
				
				int candidate = i * j;
				
				if(StringUtils.isPalindrome(Integer.toString(candidate))) {
					if (candidate > largestPalindrome) { 
						largestPalindrome = candidate;
						factors = new int[] { i, j };
					}
				}				
			}
		}
		
		System.out.println("Largest palindromic product: " + largestPalindrome);
		LOGGER.fine(String.format("Factors: %d x %d", factors[0], factors[1]));		
	}
	
	/*
	 * Problem 5: Find the smallest number that is evenly divisible by (1..20)
	 * Correct Solution: 232792560
	 */
	public static void
	smallestMultipleDivisbleByOneToTwenty() {
		
		boolean found = false;
		int candidate = 0;
		
		while (!found) {
			candidate++;
			if (divisibleUpto(candidate, 20))
				found = true;			
		}
		
		System.out.println("Smallest number divisible by (1..20): " + candidate);		
	}
	
	private static boolean 
	divisibleUpto(int n, int divisor) {
		for (int i = 1; i <= divisor; i++) {
			if (n % i != 0) {
				return false;
			}
		}
		return true;
	}
	

	/*
	 * Problem 7: Find the 10001st prime number
	 */
	public static void
	prime10001() {
		
		int candidate = 2;
		int pos = 1;
		
		while (pos < 10001) {
			candidate++;
			if (MathsUtils.isPrime(candidate)) {
				pos++;
				LOGGER.fine(String.format("p(%d) = %d", pos, candidate));
			}
		}
		
		System.out.println("Answer: " + candidate);
	}

	/*
	 * Problem 9: Find abc where a + b + c = 1000 and a^2 + b^2 = c^2
	 * Correct Solution: 31875000
	 */
	public static void
	specialPythagoreanTriplet() {
		
		for (int i = 1; i < 1000; i++) {
			for (int j = 1; j < 1000; j++) {
				for (int k = 1; k < 1000; k++) {					
					if (MathsUtils.isPythagoreanTriplet(i, j, k)) {						
						if (i + j + k == 1000) {
							System.out.println("Answer: " + i * j * k);
							LOGGER.fine(String.format("%d^2 + %d^2 = %d^2", i, j, k));
							return;
						}
					}
				}
			}
		}		
	}
	
	/*
	 * Problem 10: Summation of all primes under 2,000,000
	 * Correct solution: 142913828922
	 * Info: Should be 148,933 primes under 2,000,000
	 */
	public static void
	summationOfPrimesUnder(int limit) {
	
		List<Long> primesUnderLimit = MathsUtils.primeSieve(limit);
		for (Long i : primesUnderLimit) {
			LOGGER.finest(Long.toString(i));
		}
		
		LOGGER.fine("\nSize: " + primesUnderLimit.size());
		
		System.out.println("Answer: " + MathsUtils.sum(primesUnderLimit));
	}
	
	/*
	 * Problem 12: Find the first triangular number that has over 500 divisors.
	 * Correct solution: 76576500
	 */
	public static void
	highlyDivisbleTriangularNumber() {
		
		List<Integer> divisors = new ArrayList<Integer>();
		int n = 0;
		int triangular = 0;
		
		do {
			n += 1;
			triangular = MathsUtils.triangular(n);
			divisors = MathsUtils.getDivisorsOf(triangular);
			
			LOGGER.fine(triangular + " => " + StringUtils.join(", ", divisors));
		}
		while (divisors.size() <= 500);
		
		System.out.println("Answer: " + triangular);
	}
	
	/*
	 * Problem 14: Which number under 1,000,000 produces longest Collatz sequence?
	 * Correct solution: 837799
	 * 
	 * Issue during dev: Integer overflow - Changed ints to longs and worked fine (some went massively out of control)
	 */
	public static void
	largestCollatzSequence() {
		
		int currentMax = 0;
		int currentMaxSize = 0;
		
		for (int i = 1; i <= 1000000; i++) {
			ArrayList<Long> seq = MathsUtils.collatzSequence(i);
			
			if (seq.size() > currentMaxSize) {
				currentMax = i;
				currentMaxSize = seq.size();
				
				LOGGER.fine(String.format("(%d)\t| %s", seq.size(), seq.toString()));
			}
		}
		
		System.out.println(String.format("Answer: %d => %s", currentMax, MathsUtils.collatzSequence(currentMax).toString()));
	}
	
	
	/*
	 * Problem 20: Sum of all digits in 100!
	 * Correct Solution: 648
	 */
	public static void
	factorialDigitSum() {
		
		BigInteger factorial = MathsUtils.factorial(100);
		int digitSum = 0;
		
		for (char digit : factorial.toString().toCharArray()) {
			digitSum += Integer.parseInt("" + digit);
		}
		
		System.out.println(digitSum);		
	}
	
	/*
	 * Problem 35: How many circular prime numbers are there under 1000000?
	 * Correct Solution: 55
	 */
	public static void 
	circularPrimesUnder(int limit) {
		
		ArrayList<Integer> circularPrimes = new ArrayList<Integer>();
		
		int candidate = 1;
		
		while (candidate < limit) {
			
			if (MathsUtils.isCircularPrime(candidate)) {
				circularPrimes.add(candidate);
				LOGGER.fine("Circular Prime: " + candidate);
			}
			
			candidate++;			
		}
		
		System.out.println("Answer: " + circularPrimes.size());		
	}


	/*
	 * Problem 41: Find largest n-digit x prime such that x contains all the digits from 1 to n
	 * Correct solution: 7652413
	 */
	public static void
	largestPandigitalPrime() {
		
		for (int i = 987654321; i > 1000; i--) {
			
			if (MathsUtils.isPandigital(i)) {
				LOGGER.fine("Candidate: " + i);
				if (MathsUtils.isPrime(i)) {
					System.out.println("Answer: " + i);
					return;
				}
			}
			
		}
	}
	
	/*
	 * Problem 44: Find a pair of pentagonal numbers such that their sum and difference are themselves pentagonal
	 * 			   and that their difference is minimised.
	 * Correct solution: Pj = 1560090, Pk = 7042750, D = 5482660
	 */
	public static void
	pentagonalNumbers() {
		
		int limit = 10000;
		
		int[] smallest = new int[] { Integer.MIN_VALUE, Integer.MAX_VALUE };
		
		for (int i = 1; i < limit; i++) {
			for (int j = 1; j < limit; j++) {
				if (i == j) continue;
				
				int pj = MathsUtils.pentagonal(i);
				int pk = MathsUtils.pentagonal(j);
				
				int sum = pj + pk;
				int diff = Math.abs(pk - pj);
				
				LOGGER.fine(String.format("%d %d | sum = %d, diff = %d", pj, pk, sum, diff));
				LOGGER.finer("Sum pentagonal? " + MathsUtils.isPentagonal(sum));
				LOGGER.finer("Diff pentagonal? " + MathsUtils.isPentagonal(diff));
				
				if (MathsUtils.isPentagonal(sum) && MathsUtils.isPentagonal(diff)) {

					if (diff < Math.abs(smallest[1] - smallest[0])) {
						smallest = new int[] { pj, pk };
						//System.out.println(pj + " " + pk);
						System.out.println("Answer: " + (smallest[1] - smallest[0]));
						return;
					}
				}
			}
		}
		
		//System.out.println("Answer: " + (smallest[1] - smallest[0]));
	}
	
	
	/*
	 * Problem 45: Find the triangular number after T285 (40755) that is pentagonal and hexagonal.
	 * Correct solution: 1533776805 (H(27693) or T(31977))
	 * WARNING: Takes ~2 hours to do in its current iteration
	 */
	public static void
	triangularPentagonalAndHexagonal() {
		
		int triIdx = 286;
		int tri = 0;
		
		boolean found = false;
		
		while (!found) {
			tri = MathsUtils.triangular(triIdx);
			
			System.out.println(String.format("%d | 3?%b 5?%b 6?%b", 
					tri, 
					MathsUtils.isTriangular(tri), 
					MathsUtils.isPentagonal(tri),
					MathsUtils.isHexagonal(tri)));
			
			if (MathsUtils.isPentagonal(tri)) {
				if (MathsUtils.isHexagonal(tri)) {
					found = true;
				}
			}
			
			triIdx++;
		}
		
		System.out.println("Answer: " + tri);
	}
	
	
	/*
	 * Problem 47: Find first n consecutive numbers that all have n distinct prime factors.
	 * Correct solution: 134043
	 */
	public static void
	distinctPrimeFactors(int limit) {
		
		List<Integer> consecutiveNumbers = new ArrayList<Integer>();
		
		int candidate = 1;
		
		while (consecutiveNumbers.size() < limit) {
			Set<Integer> distinctPrimeFactors = new HashSet<Integer>(MathsUtils.primeFactorsOf(candidate));
			if (distinctPrimeFactors.size() == limit) {
				consecutiveNumbers.add(candidate);
			} else {
				consecutiveNumbers.clear();
			}
			candidate++;
		}
		
		System.out.println("Answer: " + consecutiveNumbers.toString());
	}
}
