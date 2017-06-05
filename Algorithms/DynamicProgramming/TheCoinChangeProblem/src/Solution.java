import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long getWays(long goal, long[] coins){

        long[] solutionsTable = new long[(int)goal+1];

        solutionsTable[0] = (long)1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = (int)coins[i]; j <= goal; j++) {
                solutionsTable[j] += solutionsTable[j - (int)coins[i]];
            }
        }

        return solutionsTable[(int)goal];

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int goal = in.nextInt();
        int m = in.nextInt();
        long[] coins = new long[m];
        for(int c_i=0; c_i < m; c_i++){
            coins[c_i] = in.nextLong();
        }
        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'
        long ways = getWays(goal, coins);
    }
}
