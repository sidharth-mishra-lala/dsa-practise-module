/**
 * Lecture 2 (2D Array)
 * Question 1:
 * Given a 2D matrix 'A' of size 'N x M' and a number 'k', find whether the number 'k' is present in the matrix 'A' or not.
 * Imp. NOTE: The given matrix is sorted in both row and column wise.
 * 
 * ex: A = {{-5, -2, 1, 13}, {-4, 0, 3, 14}, {-3, 2, 6, 18}} and k = 0
 * return true as 'k' or 0 is there in the matrix
 * ex: A = {{-5, -2, 1, 13}, {-4, 0, 3, 14}, {-3, 2, 6, 18}} and k = 5
 * return false as 'k' or 0 is not there in the matrix
 */

public class FindNumber {
    public static void main(String[] args) {
        FindNumber find = new FindNumber();

        int[][] A = {{-5, -2, 1, 13}, {-4, 0, 3, 14}, {-3, 2, 6, 18}};
        int k = 0, l = 5;

        //Approach 1: Expected Output --> true if its passed 'k' or false if its passed 'l'
        System.out.println(find.checkNumBF(A, k));
        System.out.println(find.checkNumBF(A, l));

        //Approach 2: Expected Output --> true if its passed 'k' or false if its passed 'l'
        System.out.println(find.checkNumOP(A, k));
        System.out.println(find.checkNumOP(A, l));
    }

    /*
     * Approach 1: Bruteforce
     * Iterate over all elements and check if A[i][j] == k, if yes there itslef return true.
     * If iteration ends and 'k' not found then it can be assumed it is not there in matrix, so return false.
     * TC: O(N * M) where N = total rows and M = total columns, SC: O(1)
     */
    public boolean checkNumBF(int[][] A, int k) {

        int N = A.length, M = A[0].length;

        //iterate over each cell, check if 'k' there or not and return if found
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                //check if A[row][col] = k or not
                if(A[row][col] == k) {
                    return true; //it tells we found 'k'
                }
            }
        }
        return false; // we reached here means we never found 'k'
    }

    /*
     * Approach 2: Optimized
     * As we know the matrix is sorted in both row wise and column wise, so we can leverage that
     * We will start from TR or BL cell, lets say TR and we will check if A[i][j]
     * IF A[i][j] = k, we hit 'k' so return true
     * IF A[i][j] > k, it tells that the following all elements in column are even greater than 'k' so its no point to search there, so we reject that column by going left, i.e. j--
     * IF A[i][j] < k, it tells that rest of elements in that row before are evel lesser than 'k' so no point to check in that row, we reject that row and go down, i.e., i++
     * we will do this till we go out of bound and since 'j' is reducing and 'i' in increasing so we can run loop till i < N and j >= 0
     * TC: O(N + M), SC: O(1)
     */
    public boolean checkNumOP(int[][] A, int k) {
        
        int N = A.length, M = A[0].length;

        //take 2 variable to guide over rows and columns of matrix, since we will start from TR, so i / row = 0 and j / col = M - 1
        int row = 0, col = M - 1;

        //run and try to find 'k' or else reject as discussed above
        while(row < N && col >= 0) {
            if(A[row][col] == k) { // it is hit
                return true;
            } else if(A[row][col] > k) {
                col--;
            } else {
                row++;
            }
        }

        return false; // we reached here means we never found 'k'
    }

}
