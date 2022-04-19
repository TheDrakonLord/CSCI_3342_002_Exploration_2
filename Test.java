import java.util.Arrays;
import java.util.Random;

/**
 * Group Members: Neal Jamieson, James Gregory, Landdon Squiers
 * Class: CSCI_3342_002
 * Assignment: Exploration 3
 */



/**
 * @author Neal Jamieson
 * 
 * This class takes the BruteFoce and Dynamic algorithms and tests them based on a given set of input sizes.
 */
public class Test {

	/**
	 * tests the brute force and dynamic algorithms based on a predefined set of test cases
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		// list of cases to test (first value is always dummy)
		int[] testCases = {0,3,4,5,6,7,8,9,10,12,14,16};
		
		// list of test values to be used
		int[] testArray = {3,24,4,12,78,2,96,8,1,55,5,87,7,65,99,2};
		int T = 9;
		
		//test all of the test cases
		for(int i:testCases) {
			checkBrute(i, Arrays.copyOfRange(testArray, 0, i), T);
			checkDynamic(i, Arrays.copyOfRange(testArray, 0, i), T);
		}

	}
	
/**
 * tests the brute force algorithm and times the amount of time it takes to run
 * @param n the input size to be tested
 */
	private static void checkBrute(int n, int[]A, int T) {
		boolean B = false;
		
		
		//System nanoTimer nanosecond resolution
		long startTime = 0;
		long stopTime = 0;
		long overTime = 0;
		//nano second -- clock resolution
		startTime = System.nanoTime();
		stopTime = System.nanoTime();
		overTime = stopTime - startTime;
		
		//Start timer for execution timing
		startTime = System.nanoTime();
		
		//Execute algorithm to be tested
		if(n != 0) {
			B = TotalExist.totalExistBruteForce(A, T);
		}
				
		
		stopTime = System.nanoTime();
		long time = stopTime-startTime-overTime;
		
		
		//Display the results and reset the counter
		if(n != 0) {
			System.out.println(String.format("BruteForce took an input of %d and ran %d times and took %d nanoseconds to complete. The target number was %d. the result was %s", n, TotalExist.getRunCountA(), time,T,B));
		}
		
		TotalExist.resetRunCountA();
	}

/**
 * tests the dynamic algorithm and times the amount of time it takes to run
 * @param n the input size to be tested
 */
	private static void checkDynamic(int n, int[]A, int T) {
		boolean B = false;
		
		//System nanoTimer nanosecond resolution
		long startTime = 0;
		long stopTime = 0;
		long overTime = 0;
		//nano second -- clock resolution
		startTime = System.nanoTime();
		stopTime = System.nanoTime();
		overTime = stopTime - startTime;
		
		//Start timer for execution timing
		startTime = System.nanoTime();
		
		//Execute algorithm to be tested
		if(n != 0) {
			B = TotalExist.totalExistDynamic(A, T);
		}
				
		
		stopTime = System.nanoTime();
		long time = stopTime-startTime-overTime;
		
		
		//Display the results and reset the counter
		if(n != 0) {
			System.out.println(String.format("Dynamic took an input of %d and ran %d times and took %d nanoseconds to complete. The target number was %d. the result was %s", n, TotalExist.getRunCountB(), time,T,B));
		}
		
		TotalExist.resetRunCountB();
	}
}
