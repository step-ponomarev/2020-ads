package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7541239 - Количество инверсий
public class Problem5 {
    private static int[] aux;
    private static int inv = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int k = in.nextInt();
        int[] array = new int[k];
        int[] story = new int[k];

        aux = new int[k];

        for (int i = 0; i < k; i++) {
            array[i] = in.nextInt();
        }

        mergeSort(array, 0, array.length - 1);
        out.println(inv);
    }

    static void mergeSort(int[] array, int lb, int rb) {
        if (lb >= rb) {
            return;
        }

        int mid = lb + (rb - lb) / 2;

        mergeSort(array, lb, mid);
        mergeSort(array, mid + 1, rb);
        merge(array, lb, mid, rb);
    }

    private static void merge(int[] array, int lb, int mid, int rb) {
        int i = lb, j = mid + 1;
        for (int k = lb; k <= rb; k++) {
            aux[k] = array[k];
        }

        for (int k = lb; k <= rb; k++) {
            if (i > mid) {
                array[k] = aux[j++];
            } else if (j > rb) {
                array[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                inv += mid - i + 1;
                array[k] = aux[j++];
            } else {
                array[k] = aux[i++];
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