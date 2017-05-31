import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        // reading input

        int seq1Length = 0;
        int seq2Length = 0;
        int[] seq1 = null;
        int[] seq2 = null;

        Scanner scanner = new Scanner(System.in);

        String lengths = scanner.nextLine();

        seq1Length = Integer.parseInt(lengths.split(" ")[0]);
        seq2Length = Integer.parseInt(lengths.split(" ")[1]);
        seq1 = new int[seq1Length];
        seq2 = new int[seq2Length];

        String seq1String = scanner.nextLine();
        for (int i = 0; i < seq1String.split(" ").length; i++) {
            seq1[i] = Integer.parseInt(seq1String.split(" ")[i]);
        }

        String seq2String = scanner.nextLine();
        for (int i = 0; i < seq2String.split(" ").length; i++) {
            seq2[i] = Integer.parseInt(seq2String.split(" ")[i]);
        }

        // for debugging
        //
        // System.out.println("seq1 length: " + seq1Length);
        // System.out.println("seq2 length: " + seq2Length);
        // System.out.println("seq1: " + Arrays.toString(seq1));
        // System.out.println("seq2: " + Arrays.toString(seq2));

        LCS(seq1, seq2);

    }

    static void LCS(int[] seq1, int[] seq2) {

        // let's think about the basic cases:
        //
        // LCS('', '')      --> 0
        // LCS('x', '')     --> 0 (if either string is empty, the answer is 0)
        // LCS('x', 'x')    --> 1
        // LCS('x', 'xy')   --> 1

        // initialize 2-d array for doing bottom-up dynamic programming
        // allocate one extra slot per string for considering empty strings
        int[][] solutions = new int[seq1.length + 1][seq2.length + 1];

        for (int i = 0; i < seq1.length; i++) {
            for (int j = 0; j < seq2.length; j++) {
                if (i == 0 || j == 0) { // empty string case
                    solutions[i][j] = 0;
                } else if (seq1[i-1] == seq2[j-1]) {
                    solutions[i][j] = solutions[i-1][j-1] + 1;
                } else {
                    solutions[i][j] = Math.max(solutions[i-1][j], solutions[i][j-1]);
                }
            }
        }

        System.out.println(solutions[seq1.length][seq2.length]);

    }
}
