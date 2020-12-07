package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7546896 - Мышки и зернышки

public class Problem2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n1 = in.nextInt(), n2 = in.nextInt();
        int[][] array = new int[n1][n2];
        int[][] story = new int[n1][n2];

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                array[i][j] = in.nextInt();
            }
        }

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = 0; j < n2; j++) {
                if (i < n1 - 1) {
                    story[i][j] = story[i + 1][j];
                }

                if (j > 0 && story[i][j] < story[i][j - 1]) {
                    story[i][j] = story[i][j - 1];
                }

                story[i][j] += array[i][j];
            }
        }

        int i = 0;
        int j = n2 - 1;
        StringBuilder path = new StringBuilder("");
        while ((i != n1 - 1) || (j != 0)) {
            if (i == n1 - 1) {
                path.append("R");
                j--;
                continue;
            }

            if (j == 0) {
                path.append("F");
                i++;
                continue;
            }

            if (story[i + 1][j] > story[i][j - 1]) {
                path.append("F");
                i++;
            } else {
                path.append("R");
                j--;
            }
        }

        out.println(path.reverse().toString());
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
        }
    }
}