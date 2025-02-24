/**
 * Lecture 2 (2D Array)
 * Question 3:
 * Given a 2D square matrix 'A' of size 'N x N', print all its boundary elements only.
 * 
 * ex: A = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}
 * Print: 1 2 3 4 8 12 16 15 14 13 9 5
 */
public class PrintBoundary {
    public static void main(String[] args) {

        int[][] A = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

        //Expected output --> 1 2 3 4 8 12 16 15 14 13 9 5
        new PrintBoundary().printBoundary(A);

    }

    /*
     * Approach:
     * 1 -> Iterate over 0th row N - 1 times forward
     * 2 -> Iterate over last column N - 1 times down
     * 3 -> Iterate over last row N - 1 times backward
     * 4 -> Iterate over 0th column N - 1 times up
     * TC: O(4N) ~= O(N), SC: O(1)
     */
    public void printBoundary(int[][] A) {
        int N = A.length;
        //guide variable for row and column starting from TL cell
        int row = 0, col = 0;
        // 1 -> Iterate over 0th row N - 1 times forward
        for(int itr = 1; itr < N; itr++) {
            System.out.print(A[row][col]  + " ");
            col++;
        }
        //by the time we reach here row = 0, col = N - 1

        // 2 -> Iterate over last column N - 1 times down
        for(int itr = 1; itr < N; itr++) {
            System.out.print(A[row][col] + " ");
            row++;
        }
        //by the time we reach here row = N - 1, col = N - 1

        // 3 -> Iterate over last row N - 1 times backward
        for(int itr = 1; itr < N; itr++) {
            System.out.print(A[row][col] + " ");
            col--;
        }
        //by the time we reach here row = N - 1, col = 0

        // 4 -> Iterate over 0th column N - 1 times up
        for(int itr = 1; itr < N; itr++) {
            System.out.print(A[row][col] + " ");
            row--;
        }
        //by the time we reach here, row = 0, col = 0
    }
}
