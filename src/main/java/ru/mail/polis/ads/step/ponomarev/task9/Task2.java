package ru.mail.polis.ads.step.ponomarev.task9;

import java.io.*;
import java.util.*;

public class Task2 {
    private static int WHITE = 0;
    private static int GREY = 1;
    private static int BLACK = 2;

    private static boolean isCicled;
    private static Stack<Integer> stack = new Stack();

    private static List<List<Integer>> nodes;
    private static Set<Integer> cicledValues = new HashSet<>();

    private static int[] color;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        init(n);

        for (int i = 0; i < k; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            nodes.get(a).add(b);
            nodes.get(b).add(a);
        }

        for (int i = 0; i < n; i++) {
            if (color[i] == WHITE) {
                bfd(i, i);
            }
        }

        System.out.println(isCicled ? "Yes" : "No");
        if (isCicled) {
            System.out.println(cicledValues.stream().min(Integer::compareTo).get());
        }
    }

    private static void bfd(int index, int parent) {
        color[index] = GREY;

        for (int i : nodes.get(index)) {
            if (parent != i) {
                if (color[i] == WHITE) {
                    bfd(i, index);
                }

                if (color[i] == GREY) {
                    isCicled = true;

                    cicledValues.add(i + 1);
                }
            }
        }

        color[index] = BLACK;
    }

    private static void init(int n) {
        nodes = new ArrayList(n);
        for (int i = 0; i < n; i++) {
            nodes.add(i, new ArrayList<>());
        }

        color = new int[n];
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
