package ru.mail.polis.ads.step.ponomarev.task9;

import java.io.*;
import java.util.StringTokenizer;

public class Task2 {
    private static int WHITE = 0;
    private static int GREY = 1;
    private static int BLACK = 2;

    private static int[] color;
    private static boolean[] inCicle;
    private static boolean isCicled;
    private static int minCicled = Integer.MAX_VALUE;
    private static int[][] nodes;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        nodes = new int[n][n];
        color = new int[n];
        inCicle = new boolean[n];

        for (int i = 0; i < k; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            nodes[a][b] = 1;
        }

        for (int i = 0; i < n; i++) {
            bfd(i);
        }

        System.out.println(isCicled ? "Yes" : "No");
        if (isCicled) {
            for (int i = 0; i < n; i++) {
                if (inCicle[i] && minCicled > i + 1) {
                    minCicled = i + 1;
                }
            }

            System.out.println(minCicled);
        }
    }

    private static void bfd(int index) {
        color[index] = GREY;

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[index][i] == 1 && index != i) {
                if (color[i] == WHITE) {
                    bfd(i);
                }

                if (color[i] == GREY) {
                    inCicle[i] = true;
                    isCicled = true;
                }
            }
        }

        color[index] = BLACK;
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
