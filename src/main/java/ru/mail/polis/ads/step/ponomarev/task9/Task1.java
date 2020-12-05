package ru.mail.polis.ads.step.ponomarev.task9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Task1 {
    private static int WHITE = 0;
    private static int GREY = 1;
    private static int BLACK = 2;

    private static boolean isCicled;
    private static Stack<Integer> stack = new Stack<>();

    private static int[] color;

    private static List<List<Integer>> nodes;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        init(n);

        for (int i = 0; i < k; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            nodes.get(a).add(b);
        }

        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                bfd(i);
            }
        }

        if (isCicled) {
            System.out.println(-1);
        } else {
            while (!stack.isEmpty()) {
                out.print(stack.pop() + " ");
            }
        }
    }

    private static void init(int n) {
        nodes = new ArrayList(n);
        for (int i = 0; i < n; i++) {
            nodes.add(i, new ArrayList<>());
        }

        color = new int[n];
    }

    private static void bfd(int index) {
        color[index] = GREY;

        for (int i : nodes.get(index)) {
            if (color[i] == WHITE) {
                bfd(i);
            }

            if (color[i] == GREY) {
                isCicled = true;
            }
        }

        color[index] = BLACK;
        stack.push(index + 1);
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