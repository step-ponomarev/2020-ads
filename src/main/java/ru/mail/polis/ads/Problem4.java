package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class Problem4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        final int q = in.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            int value = in.nextInt();
            array[i] = value;
        }

        for (int i = 0; i < q; i++) {
            int value = in.nextInt();

            if (contains(array, value)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }

    private static boolean contains(int[] array, int value) {
        if (array[0] > value || array[array.length - 1] < value) {
            return false;
        }

        int lb = 0, mid = (array.length - 1) / 2, rb = array.length - 1;

        while (true) {
            if (array[mid] == value) {
                return true;
            }

            if (array[mid] < value) {
                if (mid == rb) {
                    break;
                }

                lb = mid + 1;
                mid = lb + (rb - lb) / 2;
            } else {
                if (mid == lb) {
                    break;
                }

                rb = mid - 1;
                mid /= 2;
            }
        }

        return array[mid] == value;
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