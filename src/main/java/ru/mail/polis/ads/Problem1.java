package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7575295 - Скобочная последовательность

public class Problem1 {
    private static int[][] story;
    private static int[][] kSaver;

    private static void solve(final FastScanner in, final PrintWriter out) throws Exception {
        String sequence = in.next();
        int n = sequence.length();
        story = new int[n][n];
        kSaver = new int[n][n];

        for (int i = 0; i < n; i++) {
            story[i][i] = 1;
        }

        for (int j = 0; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                final char openedBracket = sequence.charAt(i), closedBracket = sequence.charAt(j);
                int min = Integer.MAX_VALUE;
                kSaver[i][j] = i;

                if (areMatched(openedBracket, closedBracket)) {
                    min = story[i + 1][j - 1];
                    kSaver[i][j] = Integer.MIN_VALUE;
                }

                for (int k = i; k < j; k++) {
                    int newMin = story[i][k] + story[k + 1][j];

                    if (newMin < min) {
                        min = newMin;
                        kSaver[i][j] = k;
                    }
                }

                story[i][j] = min;
            }
        }

        String str = createCorrectSequence(sequence, 0, story.length - 1);
        out.println(str);
    }

    private static String createCorrectSequence(String sequence, int i, int j) {
        if (i == j) {
            return sequence.charAt(i) == '(' || sequence.charAt(i) == ')'
                    ? "()"
                    : "[]";
        }

        if (kSaver[i][j] == Integer.MIN_VALUE) {
            return sequence.charAt(i) + createCorrectSequence(sequence, i + 1, j - 1) + sequence.charAt(j);
        }

        if (story[i][j] == 0) {
            return sequence.substring(i, j + 1);
        }

        return createCorrectSequence(sequence, i, kSaver[i][j]) + createCorrectSequence(sequence, kSaver[i][j] + 1, j);
    }

    private static boolean areMatched(final char open, final char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']');
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}