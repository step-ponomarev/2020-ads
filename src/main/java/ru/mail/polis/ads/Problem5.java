package ru.mail.polis.ads;
import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7498568 - Коровы - в стойла
public class Problem5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        final int q = in.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            int value = in.nextInt();
            array[i] = value;
        }

        out.println(minMaxDistance(array, q));
    }

    private static int minMaxDistance(int[] array, int cowAmount) {
        int left = 0;
        int right = array[array.length - 1];

        while (right - left > 1) {
            int mid = (left + right) / 2;

            if (check(cowAmount, array, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static boolean check(int cowAmount, int[] cowCoords, int distance) {
        int cows = 1;
        int last_cow = cowCoords[0];
        for (int c : cowCoords) {
            if (c - last_cow >= distance) {
                cows++;
                last_cow = c;
            }
        }
        return cows >= cowAmount;
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