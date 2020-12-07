package ru.mail.polis.ads;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

//https://www.e-olymp.com/ru/submissions/7457352
public class Problem2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int arraySize = in.nextInt();
        final Integer[] array = new Integer[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array[i] = in.nextInt();
        }

        trickSort(array, (l, r) ->
                l % 10 == r % 10
                        ? l < r :
                        l % 10 < r % 10
        );

        out.println(Arrays.stream(array).map(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static void trickSort(Integer[] array, BiPredicate<Integer, Integer> comparator) {
        int N = array.length;
        int h = 1;

        while (h < N / 3) {
            h = 1 + h * 3;
        }

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && comparator.test(array[j], array[j - h]); j -= h) {
                    int tmp = array[j];
                    array[j] = array[j - h];
                    array[j - h] = tmp;
                }
            }

            h /= 3;
        }
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