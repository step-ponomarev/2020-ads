package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class Problem1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int N = in.nextInt();
        int[] array = new int[N];

        for (int i = 0; i < N; i++) {
            array[i] = in.nextInt();
        }

        if (isHeap(array)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    private static boolean isHeap(final int[] array) {
        for (int i = 1; i <= array.length / 2; i++) {
            if (i * 2 <= array.length && array[i * 2 - 1] < array[i - 1]) {
                return false;
            }

            if (i * 2 + 1 <= array.length && array[i * 2] < array[i - 1]) {
                return false;
            }
        }

        return true;
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