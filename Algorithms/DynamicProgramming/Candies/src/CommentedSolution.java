import java.util.*;

public class CommentedSolution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        long numberOfChildren = in.nextLong();

        // we'll keep one array for the ratings and one for the
        // number of candies per child
        long[] ratings = new long[(int)numberOfChildren];
        long[] candies = new long[(int)numberOfChildren];

        // we want the candies array to have each value initialized to 1
        for (int i=0; i<candies.length; i++) {
            candies[i] = (long)1;
        }

        // set the rating for each child
        for (int i=0; i < numberOfChildren; i++) {
            ratings[i] = in.nextLong();
        }

        // We can solve this one by looping through the ratings
        // twice: once forward and once backward, setting the
        // number of candies appropriately

        // loop through the children forward
        for (int i=1; i < numberOfChildren; i++) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1] + 1;
            }
        }

        // loop through the children backward
        for (int j=((int)numberOfChildren-2); j >= 0; j--) {
            if (ratings[j] > ratings[j+1]) {
                candies[j] = Math.max(candies[j], candies[j+1] + 1);
            }
        }

        // sum the candies, guaranteed to be the minimum.  Proof can be found online.
        long solution = (long)0;
        for (int k=0; k < candies.length; k++) {
            solution += candies[k];
        }

        System.out.println(solution);

    }
}
