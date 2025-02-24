
import java.util.Arrays;

/**
 * Lecture 2 (2D Array)
 * Question 4:
 * Given an integer 'A', populate a square matrix 'mat' of size 'A x A'.
 * Then populate or fill the matrix from number 1 to A^2 in spiral order
 * ex: A = 4
 * expected: mat[][] = {{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}}
 * Visually 
 * [1,  2,  3,  4]
 * [12, 13, 14, 5]
 * [11, 16, 15, 6]
 * [10, 9,  8,  7]
 */

public class SetMatrix {
    public static void main(String[] args) {
        SetMatrix set = new SetMatrix();

        int A = 4;

        //Expected Output -> {{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}}
        int[][] mat = set.setMatrix(A);
        for(int[] row : mat) {
            System.out.println(Arrays.toString(row));
        }
    }

    /*
     * Approach:
     * Same as in Q3 we will iterate over boundary, and populate values denoted by a counter variable and keep it updated
     * Once outer boundary done, treat the inner remaining cells as new matrix of size (A-2 * A-2) and jump there
     * By time we reached end of 1st boundary we would be at i = 0, j = 0, so we need to jump inwards
     * To jump we will do  i++, j++ and also reduce matrix size so A - 2 as well
     * We the repeat the iterator 4 loops as done in Q3 and place them inside a guided while loop which runs till A > 1
     * After all loop ends, if still A = 1 then just simply put the A[i][j] = count, why as odd A will always leave innermost cell empty, so we need to handle this
     */
    public int[][] setMatrix(int A) {
        int[][] mat = new int[A][A];

        int row = 0, col = 0, count = 0;

        //run guide loop where to stop

        while(A > 1) {
            // 1 -> Iterate over 0th row N - 1 times forward
            for(int itr = 1; itr < A; itr++) {
                mat[row][col] = ++count;
                col++;
            }

            // 2 -> Iterate over last column N - 1 times down
            for(int itr = 1; itr < A; itr++) {
                mat[row][col] = ++count;
                row++;
            }

            // 3 -> Iterate over last row N - 1 times backward
            for(int itr = 1; itr < A; itr++) {
                mat[row][col] = ++count;
                col--;
            }

            // 4 -> Iterate over 0th column N - 1 times up
            for(int itr = 1; itr < A; itr++) {
                mat[row][col] = ++count;
                row--;
            }

            //after this jump inwards
            row++;
            col++;
            A -= 2;
        }

        //for odd A put the value inside innermost cell
        if(A == 1) {
            mat[row][col] = ++count;
        }

        return mat;
    }
}
