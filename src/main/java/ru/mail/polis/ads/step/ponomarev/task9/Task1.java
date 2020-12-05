package ru.mail.polis.ads.step.ponomarev.task9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Task1 {
    private static boolean isCicled;
    private static Stack<Integer> stack = new Stack<>();

    private static int[] color;

    private static List<List<Integer>> nodes2;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        init(n);

        for (int i = 0; i < k; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            nodes2.get(a).add(b);
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
        nodes2 = new ArrayList(n);
        for (int i = 0; i < n; i++) {
            nodes2.add(i, new ArrayList<>());
        }

        color = new int[n];
    }

    private static void bfd(int index) {
        color[index] = 1;

        for (int i : nodes2.get(index)) {
            if (color[i] == 0) {
                bfd(i);
            }

            if (color[i] == 1) {
                isCicled = true;
            }
        }

        color[index] = 2;
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