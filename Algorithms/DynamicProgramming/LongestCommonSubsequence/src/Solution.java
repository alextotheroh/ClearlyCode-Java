import java.util.*;

public class Solution {

    public static void main(String[] args) {

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

        LCS(seq1, seq2);

    }

    static void LCS(int[] seq1, int[] seq2) {

        int[][] solutions = new int[seq1.length + 1][seq2.length + 1];

        for (int i = 0; i <= seq1.length; i++) {
            for (int j = 0; j <= seq2.length; j++) {
                if (i == 0 || j == 0) {
                    solutions[i][j] = 0;
                } else if (seq1[i-1] == seq2[j-1]) {
                    solutions[i][j] = solutions[i-1][j-1] + 1;
                } else {
                    solutions[i][j] = Math.max(solutions[i-1][j], solutions[i][j-1]);
                }
            }
        }

        int solutionLength = solutions[seq1.length][seq2.length];

        ArrayList<Integer> witness = new ArrayList<>();

        int row = seq1.length;
        int col = seq2.length;
        int currentVal = solutions[row][col];

        while (currentVal > 0) {
            if (solutions[row-1][col] == currentVal) {
                row = row - 1;
            } else if (solutions[row][col-1] == currentVal) {
                col = col - 1;
            } else {
                witness.add(seq2[col-1]);
                col = col - 1;
            }

            currentVal = solutions[row][col];
        }

        Collections.reverse(witness);
        for (Integer i : witness) {
            System.out.print(i + " ");
        }
    }
}

