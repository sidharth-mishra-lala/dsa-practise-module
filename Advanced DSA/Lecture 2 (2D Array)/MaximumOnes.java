/**
 * Lecture 2 (2D Array)
 * Question 2:
 * Given a binary 2D matrix 'A' of size 'N x M', find and return the index of the row which has maximum number of '1's in it.
 * Imp. NOTE: The given binary matrix is sorted as per data, i.e., all '0' are on LHS and all '1's are on RHS for each row of matrix
 * Imp. NOTE: If there is a tie between 2 rows for same count then return the row which is lexicographically lower, i.e., which has lower index value.
 * 
 * ex: A = {{0, 0, 0, 0, 1, 1}, {0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 0, 1, 1, 1}}
 * here if we follow 0-based indexing then 4th row has maximum '1's so we should return '4' as our ans
 */
public class MaximumOnes {
   public static void main(String[] args) {
    MaximumOnes max = new MaximumOnes();

    int[][] A = {{0, 0, 0, 0, 1, 1}, {0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 0, 1, 1, 1}};

    //Approach 1: Expected output --> 4
    System.out.println(max.maxOnesBF(A));

    //Approach 2: Expected output --> 4
    System.out.println(max.maxOnesOP(A));
   } 

   /*
    * Approach 1: Bruteforce
    * Iterate over each row cell by cell and check for count of '1's and also a global variable to store maxCount.
    * If count > maxCount then store that index of row and finally return it.
    * Every time reset count = 0 for each row iteration starting point to avoid over calculation
    * TC: O(N * M) where N = total rows and M = total columns, SC = O(1)
    */
    public int maxOnesBF(int[][] A) {

        int N = A.length, M = A[0].length;

        //take 3 variables as helpers
        int count = 0, maxCount = 0, index = -1;

        //iterate over each cell in row-by-row fashion
        for(int row = 0; row < N; row++) {
            count = 0; //reset for each row
            for(int col = 0; col < M; col++) {
                if(A[row][col] == 1) {
                    count++;
                }
            }
            //check and update maxCount and store index as well
            if(count > maxCount) {
                maxCount = count;
                index = row;
            }
        }

        return index;
    }

    /*
    * Approach 2: Optimized
    * Since we know each row is sorted, so same approach as Q1, we will start from TR and check if its '1' or '0'
    * IF its '1' we can tell out of all columns till we have checked from last side this particular row has maxOnes and is lexicographically lesser, so we move left in-search of better result, i.e., j--
    * IF its '0' we can say that all previous elements of that row are going to have '0' so no point to check in that row just go down for better result, i.e., i++
    * Since we may go out of bound so run loop till i < N and j >= 0
    * TC: O(N + M) where N = total rows and M = total columns, SC = O(1)
    */
    public int maxOnesOP(int[][] A) {
        int N = A.length, M = A[0].length;

        //take helper varibles to check and iterate
        int row = 0, col = M - 1, index = -1;

        while(row < N && col >= 0) {
            if(A[row][col] == 1) {
                index = row;
                col--;
            } else {
                row++;
            }
        }

        return index;
    }
}
