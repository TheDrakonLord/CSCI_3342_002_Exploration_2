/**
 * Group Members: Neal Jamieson, James Gregory, Landdon Squiers
 * Class: CSCI_3342_002
 * Assignment: Exploration 3
 */

import java.util.*;

/**
 * @author Neal Jamieson
 * 
 * this class establishes two algorithms to determine if some combination of integers given by the user add up to another given integer.
 */
public class TotalExist {
	// establish string constants to return depending on algorithm result
	public final static String notFoundMsg = "No combination of integers accumulates to the total.";
	public final static String foundMsg = "There is at least one combination of integers that accumulates the total.";
	
	// establish static data structures to assist the dynamic algorithm
	public static List<Integer> B = new ArrayList<Integer>();
	public static Hashtable<String,Integer> dynamic = new Hashtable<String,Integer>();
	
	// establish run count variables
	private static int runCountA = 0;
	private static int runCountB = 0;
	
	/**
	 * Main method to test the algorithms without the timer methods and with custom user input
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		// get user input
		Scanner in = new Scanner(System.in);
		System.out.print("Enter an integer for the total: ");
		int T = in.nextInt();
		System.out.print("Enter the number of elements in the array: ");
		int N = in.nextInt();
		System.out.println("Enter the array elements: ");
		in.close();
		
		// declare array of user input integers
		int[] A = new int[N];
		
		// get user input integers
		for(int i = 0; i < A.length; i++) {
			System.out.print("Enter next array element: ");
			A[i] = in.nextInt();
		}
		
		// run brute force algorithm
		if(totalExistBruteForce(A,T)) {
			System.out.println(foundMsg);
		}
		else {
			System.out.println(notFoundMsg);
		}
		
		// run dynamic algorithm
		if(totalExistDynamic(A,T)) {
			System.out.println(foundMsg);
		}
		else {
			System.out.println(notFoundMsg);
		}
	}
	
	/**
	 * checks for integers that add up to the target integer
	 * @param A the array of integers to check
	 * @param T the target integer
	 * @return true or false based on if any integers add up to the target
	 */
	public static boolean totalExistBruteForce(int[] A, int T) {	
		for(int i = 0; i < A.length; i++) {
			for(int j = i + 1; j < A.length; j++) {
				runCountA++;
				
				// check if two integers add up to the target
				if(A[i] + A[j] == T) {
					return true;
				}
				
				// check if 3 or more integers add up to the target
				if(checkBruteExists(i, A.length, A, A[i] + A[j], T, 3)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * checks if 3 or more integers add up to the target by a brute force method
	 * @param I The first integer being tested
	 * @param L the length of the starting array
	 * @param A The array to be tested
	 * @param B the value of all previous integers being tested summed
	 * @param T The target integer
	 * @param K tracks the number integers being tested at once
	 * @return true or false based on if any integers add up to the target
	 */
	private static boolean checkBruteExists(int I, int L, int[] A, int B, int T, int K) {
		boolean result = false;
		
		// checks if an index out of bounds error would occur
		if (K < L) {
			for(int i = I + 1; i < L; i++) {
				runCountA++;
				
				// check if the integers add up to the target
				if(B + A[i] == T) {
					return true;
				}
				
				// check if K or more integers add up to the target
				result = checkBruteExists(i, L, A, B + A[i], T, K+1);
			}
		}
		
		return result;
	}
	
	/**
	 * checks for integers that add up to the target integer
	 * @param A the array of integers to check
	 * @param T the target integer
	 * @return true or false based on if any integers add up to the target
	 */
	public static boolean totalExistDynamic(int[] A, int T) {
		// remove any integers that are greater than the target as they cannot possibly be used
		for (int n: A) {
			if(n < T) {
				B.add(n);
			}
			else if (n == T) {
				return true;
			}
		}
		
		for(int i = 0; i < B.size(); i++) {
			for(int j = i+1; j < B.size(); j++) {
				runCountB++;
				
				// generate a key for the current value being tested
				String key = String.format("%s-%s", B.get(i),B.get(j));
				int V;
				
				// calculate the current sum of the tested integers
				V = B.get(i) + B.get(j);
				
				// store the sum in the dictionary
				dynamic.put(key, V);
				
				// check for 3 or more integers that add up to the value
				checkDynamicExists(V, T, key, j + 1);			
			}
		}
		
		// check if the target is in the dictionary
		return dynamic.containsValue(T);
						
	}
	
	/**
	 * checks for 3 or more integers that add up to the target integer
	 * @param V the value of all previous integers currently being tested summed
	 * @param T the target integer
	 * @param key the current dictionary key so far
	 * @param k the number of integers being tested currently
	 */
	private static void checkDynamicExists(int V, int T, String key, int k) {
		// checks if a index out of bounds error would occur
		if(k<B.size()) {
			for(int i = k; i < B.size(); i++) {
				runCountB++;
				
				// generate the new dictionary key
				String newKey = String.format("%s-%s", key,B.get(i));
				
				// check if the integers add up to the target
				V = dynamic.get(key) + B.get(i);
				
				// store the current sum in the dictionary
				dynamic.put(newKey, V);
				
				// check for k+1 integers that add up to the value
				checkDynamicExists(V, T, key, i + 1);
			}
		}
	}
	
	/**
	 * Resets the counter variable to zero for the brute force algorithm
	 */	
	public static void resetRunCountA() {
		runCountA = 0;
	}
	
	/**
	 * gets the current value of the counter variable for the brute force algorithm
	 * @return returns the value of the counter variable for the brute force algorithm
	 */	
	public static int getRunCountA() {
		return runCountA;
	}
	
	/**
	 * Resets the counter variable to zero for the dynamic algorithm
	 */	
	public static void resetRunCountB() {
		runCountB = 0;
	}
	
	/**
	 * gets the current value of the counter variable for the dynamic algorithm
	 * @return returns the value of the counter variable for the dynamic algorithm
	 */	
	public static int getRunCountB() {
		return runCountB;
	}
}
