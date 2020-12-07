package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7458407
public class Problem5 {
    static int[][] aux;

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int arraySize = in.nextInt();
        final int[][] array = new int[arraySize][2];

        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < 2; j++) {
                array[i][j] = in.nextInt();
            }
        }

        aux = new int[array.length][2];
        mergeSort(array, 0, array.length - 1);

        for (int i = 0; i < arraySize; i++) {
            out.println(array[i][0] + " " + array[i][1]);
        }
    }

    public static void mergeSort(int[][] array, int lb, int rb) {
        if (rb <= lb) {
            return;
        }

        int mid = lb + (rb - lb) / 2;

        mergeSort(array, lb, mid);
        mergeSort(array, mid + 1, rb);
        merge(array, lb, mid, rb);
    }

    public static void merge(int[][] array, int lb, int mid, int rb) {
        int l = lb, r = mid + 1;

        for (int i = lb; i <= rb; i++) {
            aux[i] = array[i];
        }

        for (int i = lb; i <= rb; i++) {
            if (l > mid) {
                array[i] = aux[r];
                r++;
            } else if (r > rb) {
                array[i] = aux[l];
                l++;
            } else if (aux[r][0] < aux[l][0]) {
                array[i] = aux[r];
                r++;
            } else {
                array[i] = aux[l];
                l++;
            }
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