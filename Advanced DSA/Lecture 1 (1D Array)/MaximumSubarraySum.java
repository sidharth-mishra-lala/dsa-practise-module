import java.util.Arrays;

/**
 * Lecture 1 (1D Array)
 * Question 1:
 * Given Array 'A' of size 'N', find the maximum subarray sum out of all
 * subarrays of 'A'
 * ex: A = {-2, 3, 4, -1, 5, -10, 7}
 * for subarray = {3, 4, -1, 5} --> we get maximum sum among all subarrays which
 * is 11 and we should return 11
 */

public class MaximumSubarraySum {
    public static void main(String[] args) {
        int[] A = { -2, 3, 4, -1, 5, -10, 7 };
        MaximumSubarraySum sum = new MaximumSubarraySum();

        // For Approach 1: --> expected output is 11
        System.out.println(sum.maximumSumBF(A));

        // For Approach 2: --> expected output is 11
        System.out.println(sum.maximumSumPrefix(A));

        // For Approach 3: --> expected output is 11
        System.out.println(sum.maximumSumCarryForward(A));

        // For Approach 4: --> expected output is 11
        System.out.println(sum.maximumSumKadaneAlgorithm(A));

        // For finding subarray --> expected output is [1, 4] i.e the subarray SI is 1
        // and EI is 4: {3, 4, -1, 5}
        System.out.println(Arrays.toString(sum.findMaxSumSubarray(A)));

        int[] subarray = sum.findMaxSumSubarray(A);

        for (int i = subarray[0]; i <= subarray[1]; i++) {
            System.out.print(A[i] + " ");
        }
    }

    /*
     * Approach 1: Bruteforce
     * Iterate over all possible subarrays and then find sum and store maxSum.
     * TC: O(N^3), SC: O(1)
     */
    public int maximumSumBF(int[] A) {
        int sum = 0, maxSum = Integer.MIN_VALUE;

        // define a subarray by setting up SI and EI
        for (int SI = 0; SI < A.length; SI++) {
            for (int EI = SI; EI < A.length; EI++) {
                sum = 0; // reset sum for each subarray

                for (int i = SI; i <= EI; i++) {
                    sum += A[i];
                }

                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }

        return maxSum;
    }

    /*
     * Approach 2: Prefix Sum
     * Pre-calculate prefix for whole array and next separately iterate over each
     * subarray to directly get sum in constant time.
     * TC: O(N^2), SC: O(N)
     */
    public int maximumSumPrefix(int[] A) {
        // define prefix array
        int[] prefix = new int[A.length];
        int sum = 0, maxSum = Integer.MIN_VALUE;

        // pre-calculate prefix sum
        prefix[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefix[i] = prefix[i - 1] + A[i];
        }

        // define subarray and in constant time find sum and check for maxSum via help
        // of prefix[]
        for (int SI = 0; SI < A.length; SI++) {
            for (int EI = SI; EI < A.length; EI++) {
                if (SI == 0) {
                    sum = prefix[EI];
                } else {
                    sum = prefix[EI] - prefix[SI - 1];
                }

                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    /*
     * Approach 3: Carry Forward
     * We will save extra space by carry forwarding sum at each step, it will be
     * same TC but space will be reduced
     * TC: O(N^2), SC: O(1)
     */
    public int maximumSumCarryForward(int[] A) {
        int sum = 0, maxSum = Integer.MIN_VALUE;

        // define subarray
        for (int SI = 0; SI < A.length; SI++) {
            sum = 0; // reset for each subarray
            for (int EI = SI; EI < A.length; EI++) {
                sum += A[EI];

                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }

        return maxSum;
    }

    /*
     * Approach 4: Kadane's Algorithm
     * We will add A[i] to sum and check if its more than maxSum store it in maxSum,
     * but if at any point the sum < 0 reject till there
     * as they will now not contribut to maxSum and whetever better result would
     * already have been stored, now start from next index and go on till another
     * better result found
     * TC: O(N), SC:O(1)
     */

    public int maximumSumKadaneAlgorithm(int[] A) {
        int sum = 0, maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; i++) {
            sum += A[i];

            // check and update maxSum
            if (sum > maxSum) {
                maxSum = sum;
            }

            // reset sum and reject all till 'i' if sum < 0
            if (sum < 0) {
                sum = 0;
            }
        }

        return maxSum;
    }

    /*
     * If it is asked to also get the subarray then we can leverage Kadane's
     * Algorithm and find it out
     * we will do same thing, just if we anytime see a better maxSum store that 'i'
     * index as EI of subarray.
     * If at any time sum < 0 we will say no longer a better subarray will be there
     * so just reset sum as well update SI and EI to i + 1;
     */

    public int[] findMaxSumSubarray(int[] A) {
        int sum = 0, maxSum = Integer.MIN_VALUE;
        int SI = 0, EI = 0; // at start assume 0th index to be max sum subarray and expand logic
        int[] subarray = new int[2]; // this will store start and end index of maximum sum subarray

        // do same as Kadane's algorithm
        for (int i = 0; i < A.length; i++) {
            sum += A[i];

            if (sum > maxSum) {
                maxSum = sum;
                EI = i;
                subarray[0] = SI;
                subarray[1] = EI;
            }

            if (sum < 0) {
                sum = 0;
                SI = i + 1;
                EI = i + 1;
            }
        }

        return subarray;
    }
}