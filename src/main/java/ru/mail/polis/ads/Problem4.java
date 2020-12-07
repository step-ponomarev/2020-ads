package ru.mail.polis.ads;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7475485
public class Problem4 {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int k = Integer.parseInt(in.next());
        BigInteger[] array = Arrays
                .stream(in.reader.readLine().split(" "))
                .map(BigInteger::new)
                .toArray(BigInteger[]::new);

        findK(array, 0, array.length - 1, array.length - k);

        out.println(array[array.length - k]);
    }

    public static void findK(BigInteger[] arr, int begin, int end, int k) {
        if (begin > end) {
            return;
        }

        int partitionIndex = partition(arr, begin, end);

        if (partitionIndex == k) {
            return;
        }

        findK(arr, begin, partitionIndex - 1, k);
        findK(arr, partitionIndex + 1, end, k);
    }

    private static int partition(BigInteger[] arr, int begin, int end) {
        BigInteger pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j].compareTo(pivot) == 0 || arr[j].compareTo(pivot) < 0) {
                i++;

                BigInteger swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        BigInteger swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
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
