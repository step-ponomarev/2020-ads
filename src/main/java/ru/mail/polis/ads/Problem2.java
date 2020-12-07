package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class Problem2 {
    private static class Heap {
        private int N;
        private final Integer[] data;

        public Heap(int maxSize) {
            this.N = 0;
            this.data = new Integer[maxSize + 1];
        }

        public void insert(int value) {
            N++;
            data[N] = value;
            swim(N);
        }

        public int getMax() {
            int value = data[1];
            data[1] = data[N];
            N--;
            data[N + 1] = null;

            sink(1);

            return value;
        }

        private void swim(int k) {
            while (k > 1) {
                if (data[k / 2] < data[k]) {
                    int tmp = data[k / 2];
                    data[k / 2] = data[k];
                    data[k] = tmp;

                    k = k / 2;
                } else {
                    break;
                }
            }
        }

        private void sink(int k) {
            while (k * 2 <= N) {
                int j = k * 2;
                if (j < N && data[j] < data[j + 1]) {
                    j++;
                }

                if (data[k] < data[j]) {
                    int tmp = data[k];
                    data[k] = data[j];
                    data[j] = tmp;

                    k = j;
                } else {
                    break;
                }

            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int k = in.nextInt();

        final Heap heap = new Heap(k);

        for (int i = 0; i < k; i++) {
            int command = in.nextInt();

            if (command == 0) {
                heap.insert(in.nextInt());
            } else {
                out.println(heap.getMax());
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