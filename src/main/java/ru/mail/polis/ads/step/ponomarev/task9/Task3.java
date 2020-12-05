package ru.mail.polis.ads.step.ponomarev.task9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task3 {
    private static List<List<Node>> nodes;

    private static class Node {
        public int nodeIndex;
        public int value;

        public Node(int nodeIndex, int value) {
            this.nodeIndex = nodeIndex;
            this.value = value;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        init(n);

        for (int i = 0; i < k; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int v = in.nextInt();

            nodes.get(a).add(new Node(b, v));
        }
    }

    private static void init(int n) {
        nodes = new ArrayList(n);
        for (int i = 0; i < n; i++) {
            nodes.add(i, new ArrayList<>());
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
