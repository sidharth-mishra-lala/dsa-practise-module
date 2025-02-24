/**
 * Lecture 2 (2D Array)
 * Question 5:
 * Given a matrix 'A' of size N x M, return the sum of all submatrices sum.
 * ex: A[][] = {{4, 9, 6}, {5, -1, 2}}
 * expected ans = 166 (See lecture notes for better understanding)
 */

public class SubMatrixSum {
    public static void main(String[] args) {
        SubMatrixSum sum = new SubMatrixSum();

        int[][] A = {{4, 9, 6}, {5, -1, 2}};

        //Approach 1: Expected Output --> 166
        System.out.println(sum.sumBF(A));

        //Approach 1: Expected Output --> 166
        System.out.println(sum.sumContribution(A));
    }
    
    /*
     * Approach 1: Bruteforce
     * Iterate over each possible submatrices, then get sum and update it in global sum
     * To identify a submatrix, we need its Top-Left (TL) and Bottom-Right (BR) cell.
     * To get TL we need 2 loops over row and column
     * To get BR again we need 2 loops over row and column and this is from TL growing outwards.
     * After setting TL and BR we need to iterate over the cells of the defined submatix, so again we need 2 loops over row and column
     * TC: O(*N * M) * (N * M) * (N * M)) = O(N^3 * M^3) ~= O(N^6) if we say N = M, SC = O(1)
     */
    public int sumBF(int[][] A) {
        int N = A.length, M = A[0].length;

        //take 2 variables one for sum of each submatix, and other to hold total sum
        int sum = 0, totalSum = 0;

        //set TL
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {

                //set BR
                for(int k = i; k < N; k++) {
                    for(int l = j; l < M; l++) {

                        sum  = 0; //reset sum for each submatrix
                        //traverse from TL to BR and populate sum
                        for(int m = i; m <= k; m++) {
                            for(int n = j; n <= l; n++) {
                                sum += A[m][n];
                            }
                        }

                        totalSum += sum;
                    }
                }
            }
        
        }

        return totalSum;
    }

    /*
     * Approach 2: Contribution technique
     * From subarrays we know if an element is in 'x' no.of subarrays then if we multiple x * A[i] we get its contribution in total sum
     * And to know that we see how many SI and how mane EI is there and we multiply them to get the count of subarrays where A[i] is part of
     * Same if replicate in submatrices, if we know total count of submatrices where A[i][j] will be present then we can multiply A[i][j] to get its contribution towards total sum
     * To know count of submatrices, if we know all possible cells which can TL and BR and if we multiply them we get result.
     * So all TL cells can be [0, i] , [0, j] for element at (i, j) = (i - 0 + 1) * (j - 0 + 1) = (i + 1) * (j + 1)
     * So all BR cells can be [i, N - 1] , [j, M - 1] for element at (i, j) = (N - 1 - i + 1) * (M - 1 - j + 1) = (N - i) * (M - j)
     * So total count of submatrices = (i + 1) * (j + 1) * (N - i) * (M - j)
     * And if we multiple value , i.e., A[i][j] * count we get its contribution into total sum
     * Repeat this for each cell and we get out result
     */
    public int sumContribution(int[][] A) {
        int N = A.length, M = A[0].length;

        int count = 0, contribution = 0, totalSum = 0;

        //take each cell and find its contribution
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                count = (row + 1) * (col + 1) * (N - row) * (M - col);
                contribution = count * A[row][col];

                totalSum += contribution;
            }
        }

        return totalSum;
    }
}
