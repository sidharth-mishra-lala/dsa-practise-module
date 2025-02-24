

/**
 * Lecture 1 (1D Array)
 * Question 3:
 * Rain Water Trapped
 * Given 'N' building height in height[] assuming constant width and depth of 1 unit.
 * Calculate total amount of rainwater trapped by this buldings
 * ex:
 * height[] = {2,1,3,2,1,2,4,3,2,1,3,1}
 * 
 * ^
 * |
 * |              _
 * |      _      | |_     _
 * |  _  | |_   _| | |_  | |
 * | | |_| | |_| | | | |_| |_
 * | | | | | | | | | | | | | |
 * ------------------------------->
 *    0 1 2 3 4 5 6 7 8 9 10 11
 * 
 * ans = 8
 */

public class RainWaterTrapped {
    public static void main(String[] args) {
        RainWaterTrapped water = new RainWaterTrapped();
        int[] height = {2,1,3,2,1,2,4,3,2,1,3,1};

        //Approach 1: ---> expected output 8
        System.out.println(water.waterTrappedBF(height));

        //Approach 2: ---> expected output 8
        System.out.println(water.waterTrappedPrefix(height));

    }

    /*Approach 1: Bruteforce
     * We see that total water trapped is sum of water trapped on top of each building
     * Rain water will be accumulated on top of 'i'th building if there are supporting building left and right to it
     * Next is to find level to how much water can rise and be trapped, for that if we know the max height of building on left and right side of 'i'th building
     * and then find min among those left and right max, then it is level. So, leftMax = max(0, i-1) and rightMax = max(i+1, N-1)
     * If level is more than height of 'i'th building then we can get water trapped by level = height[i]
     * If we do for all 'i' buildings we will get the total water trapped.
     */
    public int waterTrappedBF(int[] height) {
        int level = 0, leftMax = 0, rightMax = 0, total = 0;

        //iterate over all building to find water trapped on top of each building
        for(int i = 0; i < height.length; i++) {
            //find left max
            leftMax = findMax(height, 0, i - 1);

            //find right max
            rightMax = findMax(height, i + 1, height.length - 1);

            //now find level = min(leftMax, rightMax)
            level = leftMax < rightMax ? leftMax : rightMax;

            //check if water can be trapped on top or not
            if(level > height[i]) {
                total += level - height[i];
            }
        }

        return total;
    }
    //supporting method 'findMax()' for the Bruteforce solution
    public int findMax(int[] height, int start, int end) {
        int max = Integer.MIN_VALUE;

        for(int i = start; i <= end; i++) {
            if(height[i] > max) {
                max = height[i];
            }
        }

        return max;
    }

    /*
     * Approach 2: Pre-calculate leftMax and rightMax
     * We see that inside main code, we are trying to find leftMax and rightMax, by again iteraing a loop inside main loop which lead to N^2 time complexity
     * We can reduce time by pre-calculating leftMax and rightMax separately outside using prefix array concept
     * Now we know leftMax(i) = max(0, i-1) and rightMax(i) = max(i+1, N-1)
     * so in logical prefix way its leftMax[i] = max(leftMax[i - 1], A[i - 1]) where leftMax[i-1] tells max height uptill i-1th building exluding i-1th index
     * same rightMax[i] = max(rightMax[i + 1], A[i + 1]) where rightMax[i+1] tells max height from i+1 till last excluding i+1th index.
     * to better put if we find if we find max till ith index by finding max till ith index excluding ith one and the current ith height and put it in i+1th index
     * to signify that left[i+1] is max till ith index, hence leftMax[i + 1] = max(leftMax[i] , A[i])
     * same we can say rightMax[i - 1] = max(rightMax[i], A[i]) and as nothing is there left of 0th index so leftMax[0] = 0, same nothing there right of N-1 so
     * rightMax[N-1] = 0, now go forward way to get leftMax[] and backward to get rightMax[]
     * Rest is same logic as above BF 
     */
    public int waterTrappedPrefix(int[] height) {
        int[] leftMax = new int[height.length], rightMax = new int[height.length];
        int level = 0, total = 0;

        //find leftMax[]
        leftMax[0] = 0;
        for(int i = 0; i < height.length - 1; i++) {
            leftMax[i + 1] = leftMax[i] > height[i] ? leftMax[i] : height[i];
        }

        //find rightMax[]
        rightMax[height.length - 1] = 0;
        for(int i = height.length - 1; i > 0; i--) {
            rightMax[i - 1] = rightMax[i] > height[i] ? rightMax[i] : height[i];
        }

        //find total water trapped on top of each building
        for(int i = 0; i < height.length; i++) {
            level = leftMax[i] < rightMax[i] ? leftMax[i] : rightMax[i];

            if(level > height[i]) {
                total += level - height[i];
            }
        }

        return total;
    }
}