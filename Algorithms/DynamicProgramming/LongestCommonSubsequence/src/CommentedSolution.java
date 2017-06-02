import java.util.*;

public class CommentedSolution {

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
        // allocate one extra slot per string for considering empty arrays
        int[][] solutions = new int[seq1.length + 1][seq2.length + 1];

        // TODO insert english description of what's happening in the nested for loops below.

        for (int i = 0; i <= seq1.length; i++) {
            for (int j = 0; j <= seq2.length; j++) {
                if (i == 0 || j == 0) { // empty string case
                    solutions[i][j] = 0;
                } else if (seq1[i-1] == seq2[j-1]) {
                    solutions[i][j] = solutions[i-1][j-1] + 1;
                } else {
                    solutions[i][j] = Math.max(solutions[i-1][j], solutions[i][j-1]);
                }

                // Extremely verbose, comment out this call if desired
                prettyPrint2dArray(solutions, seq1, seq2);
            }
        }

        // This will print the length of the LCS
        int solutionLength = solutions[seq1.length][seq2.length];
        System.out.println("The length of the longest common subsequence is: " + solutionLength);

        // We want one of the solutions, though, called a 'witness.'
        // Examining the final state of the pretty-printed 2D array,
        // we can get a sense of how to go about this:

        // Example input:
        //
        // 5 6
        // 1 2 3 4 1
        // 3 4 1 2 1 3

        // Example final 2D solutions array, pretty-printed:
        //
        //      3 4 1 2 1 3
        //    ______________
        //   |0 0 0 0 0 0 0
        // 1 |0 0 0 1 1 1 1
        // 2 |0 0 0 1 2 2 2
        // 3 |0 1 1 1 2 2 3
        // 4 |0 1 2 2 2 2 3
        // 1 |0 1 2 3 3 3 3
        //    - - - - - - -

        // We simply trace left and up through the table.
        // Any time we decrement the solution length, we know that that character
        // must be in an optimal solution.

        // In other words, we start at the bottom-right of the table.
        // We can only travel up or left.
        // We always pick the highest possible value we can- we don't want to go down in value.
        // Sometimes, though, we have to go down.  When we do, we know that 'this character' is in an optimal solution.
        // ('this character' means 'the character in the row or column we departed from.' See witness-finding implementation.)

        ArrayList<Integer> witness = new ArrayList<>();

        int row = seq1.length;
        int col = seq2.length;
        int currentVal = solutions[row][col];

        while (currentVal > 0) {
            // if the spot above this one has the same value, move there.
            if (solutions[row-1][col] == currentVal) {
                row = row - 1;

            // else if the spot to the left has the same value, move there.
            } else if (solutions[row][col-1] == currentVal) {
                col = col - 1;

            // if we MUST drop in value, then jump to the left and record the
            // character in the column we departed as part of the witness.
            } else {
                witness.add(seq2[col-1]); // this -1 adjusts for the empty cases
                col = col - 1;
            }

            currentVal = solutions[row][col];
        }

        // now print the witness, which we have to reverse since we built it backwards
        Collections.reverse(witness);
        System.out.println("An optimal subsequence is: ");
        for (Integer i : witness) {
            System.out.print(i + " ");
        }
    }

    static void prettyPrint2dArray(int[][] array2D, int[] seq1, int[] seq2) {

        // table header
        System.out.print("     ");
        for (int i : seq2) {
            System.out.print(i + " ");
        }
        System.out.print("\n   " + new String(new char[array2D[0].length]).replace("\0", "__"));

        for (int row = 0; row < array2D.length; row++) {
            if (row != 0) {
                System.out.print("\n" + seq1[row-1] + " |");
            } else {
                System.out.print("\n  |");
            }
            for (int col = 0; col < array2D[0].length; col++) {
                System.out.print(array2D[row][col] + " ");
            }
        }

        System.out.println("\n   " + new String(new char[array2D[0].length]).replace("\0", "- "));

    }
}
