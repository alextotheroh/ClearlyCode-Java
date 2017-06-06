import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class CommentedSolution {

    // Some constants given by the problem constraints
    static final int MAXBRICK = 15;
    static final long FINALMOD = (long)Math.pow(10, 9) + 7;

    public static void main(String[] args) throws Exception {

        // Get input from user
        Scanner in = new Scanner(System.in);
        int height = in.nextInt();
        int numberOfBricks = in.nextInt();
        int[] bricks = new int[numberOfBricks];

        for (int i = 0; i < numberOfBricks; i++) {
            bricks[i] = in.nextInt();
        }

        // We'll solve this problem using dynamic programming combined with matrix recurrence.

        // Let's first create a binary list depending on which bricks are available to use.
        // EX:
        // Bricks of height [4, 5, 9] being available gives:
        // [0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0] (zero indexed)

        // Our array is length 16 because the problem tells us that 15 is the max brick height,
        // and we also include the 0 brick.
        int[] brickVector = new int[MAXBRICK+1];

        // Sort so we can use the built-into-Java binarySearch method to check if
        // an element exists in the array.
        Arrays.sort(bricks);
        for (int i = 0; i < MAXBRICK+1; i++) {
            if (Arrays.binarySearch(bricks, i) >= 0) {
                brickVector[i] = 1;
            }
        }

        // Verify behavior
        // System.out.println("bricks: " + Arrays.toString(bricks));
        // System.out.println("brickVector: " + Arrays.toString(brickVector));

        // Calculate the answer for heights up to 15 using dynamic programming
        int[] answerVector = new int[MAXBRICK+1];
        answerVector[0] = 1;

        System.out.println("debugging array slicing...");
        System.out.println("Arrays.copyOfRange(brickVector, 0, 4+1) : " + Arrays.toString(Arrays.copyOfRange(brickVector, 0, 4+1)));
        System.out.println("Arrays.copyOfRange(reverseArray(answerVector), MAXBRICK+1-4-1, MAXBRICK+1) : " + Arrays.toString(Arrays.copyOfRange(reverseArray(answerVector), MAXBRICK+1-4-1, MAXBRICK+1)));

        for (int i = 1; i < MAXBRICK+1; i++) {
            answerVector[i] =  IntStream.of(multiplyArrays(Arrays.copyOfRange(brickVector, 0, i+1), Arrays.copyOfRange(reverseArray(answerVector), MAXBRICK+1-i-1, MAXBRICK+1)) ).sum();
        }

        System.out.println("answerVector: " + Arrays.toString(answerVector));

    }

    // takes two arrays and multiplies them element-by-element, returning a new array.
    static int[] multiplyArrays(int[] a, int[] b) throws Exception {
        if (a.length != b.length) {
            throw new Exception("the 2 input arrays must be the same length to multiply them");
        }
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] * b[i];
        }
        return result;
    }

    static int[] reverseArray(int[] a) {

        int[] solution = new int[a.length];
        int currentSolutionIndex = 0;

        for (int i = a.length - 1; i >= 0; i--) {
            solution[currentSolutionIndex] = a[i];
            currentSolutionIndex++;
        }

        return solution;
    }
}
