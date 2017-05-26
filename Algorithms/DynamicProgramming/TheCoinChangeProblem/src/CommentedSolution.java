import java.util.*;

public class CommentedSolution {

    static long getWays(long goal, long[] coins){

        // We'll solve the actual problem using dynamic programming

        // The element at solutionsTable[i] represents the number of solutions for the value i.
        // The 'goal' variable contains our desired total. So we will need
        // an array of length 'goal' items (plus one since we start our table at zero).
        long[] solutionsTable = new long[(int)goal+1];

        // How many ways are there to select coins that add up to zero?
        // Well, no matter what values of coins we have, there is exactly one way. You take no coins.
        // So we'll initialize our table with one.
        solutionsTable[0] = (long)1;

        // Now, we build up the solutions, using previous solutions to find new ones.
        // In this way, we ensure that we don't duplicate work, which is the whole point of dynamic programming.
        for (int i=0; i < (coins.length); i++) { // for each coin
            for (int j = (int)coins[i]; j <= goal; j++) { // for each sum greater than the current coin value and less than the goal value
                solutionsTable[j] += solutionsTable[j - (int)coins[i]]; // the number of ways to make this sum is equal to the ways we currently know of plus the number of ways to make this sum minus this coin
                // In other words, we use what we already know! (the number of ways to make a lesser value, more precisely the value that is exactly the current value minus the current coin)
                // So we're building our solutions from the ground up without duplicating work: dynamic programming.
            }
        }

        return solutionsTable[(int)goal];
    }

    public static void main(String[] args) {

        // Boilerplate given by HackerRank site
        Scanner in = new Scanner(System.in);
        int goal = in.nextInt();
        int m = in.nextInt();
        long[] coins = new long[m];
        for(int c_i=0; c_i < m; c_i++){
            coins[c_i] = in.nextLong();
        }

        long ways = getWays(goal, coins);
        System.out.println(ways);
    }
}