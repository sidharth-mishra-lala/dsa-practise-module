import java.util.Arrays;

/**
 * Lecture 1 (1D Array)
 * Question 2:
 * Given an integer A, which you can assume total no.of beggars who initially
 * have their pots empty and devotees donate them money
 * where Q is a 2D array of size Q X 3 representing the devotees and their
 * action
 * The devotees donate 'value' amount of money to consecutive beggars starting
 * from L to R (0-based)
 * For each devotee in 'Q', L = Q[i][0], R = Q[i][1] and value = Q[i][2]
 * 
 * ex: if A = 7 then inital beggars state = {0, 0, 0, 0, 0, 0, 0}
 * and Q[][] = {{1,3,2}, {2,5,3}, {5,6,-1}}
 * then result is beggars[] = {0, 2, 5, 5, 3, 2, -1} --> expected
 */

public class Operations {
    public static void main(String[] args) {
        Operations op = new Operations();
        int A = 7;
        int[][] Q = { { 1, 3, 2 }, { 2, 5, 3 }, { 5, 6, -1 } };

        // Approach 1: ---> expected output {0, 2, 5, 5, 3, 2, -1}
        System.out.println(Arrays.toString(op.operationsBF(A, Q)));

        // Approach 2: ---> expected output {0, 2, 5, 5, 3, 2, -1}
        System.out.println(Arrays.toString(op.operationsMarkerAndPrefix(A, Q)));
    }

    /*
     * Approach 1: Bruteforce
     * Iterate over 'Q' queries and for each one again iterate from i = 'L' to 'R'
     * over beggars and set of beggars[i] += value
     * TC: O(Q*N), SC = O(1)
     */
    public int[] operationsBF(int A, int[][] Q) {
        int[] beggars = new int[A];
        int left = 0, right = 0, value = 0;

        // for each query Q(L, R, X): iterate from i = L to R over beggars and add value
        // to beggars[i]
        for (int q = 0; q < Q.length; q++) {
            left = Q[q][0];
            right = Q[q][1];
            value = Q[q][2];

            // iterate over beggars from 'left' to 'right' and add 'value' to each
            // beggars[i]
            for (int i = left; i <= right; i++) {
                beggars[i] += value;
            }
        }

        return beggars;
    }

    /*
     * Approach 2: Marker and Prefix
     * we see that repeated task is being done inside inner loop in Approach 1, so
     * to reduce that if we do something like mark the index 'L' of beggars[] as
     * +value
     * then do a prefix calculation separately outside then it will auto roll and
     * add +value to all future indices, but we need to just add till 'R' index of
     * beggars[]
     * and not till end so if we again mark R + 1 index of beggars[] as -X then
     * prefix will do +X and -X and cumulatively do 0 for rest R + 1 to N - 1
     * indices.
     * TC: O(Q+N), SC:O(1)
     */
    public int[] operationsMarkerAndPrefix(int A, int[][] Q) {
        int[] beggars = new int[A];
        int left = 0, right = 0, value = 0;

        // for each query Q(i, j, X): mark A[i] as +X and A[j + 1] as -X
        for (int i = 0; i < Q.length; i++) {
            left = Q[i][0];
            right = Q[i][1];
            value = Q[i][2];

            beggars[left] += value;

            if ((right + 1) < A) {
                beggars[right + 1] += -value;
            }
        }

        // do in-place prefix over beggars[] to auto migrate the operations desired
        for (int i = 1; i < A; i++) {
            beggars[i] = beggars[i - 1] + beggars[i];
        }

        return beggars;
    }
}
