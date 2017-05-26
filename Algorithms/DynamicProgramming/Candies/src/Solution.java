import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        long numberOfChildren = in.nextLong();

        long[] ratings = new long[(int)numberOfChildren];
        long[] candies = new long[(int)numberOfChildren];

        for (int i=0; i<candies.length; i++) {
            candies[i] = (long)1;
        }

        for (int i=0; i < numberOfChildren; i++) {
            ratings[i] = in.nextLong();
        }

        for (int i=1; i < numberOfChildren; i++) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1] + 1;
            }
        }

        for (int j=((int)numberOfChildren-2); j >= 0; j--) {
            if (ratings[j] > ratings[j+1]) {
                candies[j] = Math.max(candies[j], candies[j+1] + 1);
            }
        }

        long solution = (long)0;
        for (int k=0; k < candies.length; k++) {
            solution += candies[k];
        }

        System.out.println(solution);

    }

}