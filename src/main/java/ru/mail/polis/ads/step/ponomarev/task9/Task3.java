package ru.mail.polis.ads.step.ponomarev.task9;

import java.io.*;
import java.util.*;

public class Task3 {
    private static int[] dist;

    private static class Node {
        int vertexTo;
        int value;

        public Node(int vertexTo, int value) {
            this.vertexTo = vertexTo;
            this.value = value;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Node>> list = new ArrayList<>();
        dist = new int[n];

        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int value = in.nextInt();

            list.get(from).add(new Node(to, value));
        }

        findMinLength(list, 0);

        for (int i : dist) {
            if (i == Integer.MAX_VALUE) {
                out.print(30000 + " ");
                continue;
            }
            out.print(i + " ");
        }
    }

    private static void findMinLength(List<List<Node>> list, int index) {

        dist[index] = 0;

        for (int i = 0; i < list.size(); i++) {
            for (int currentVertex = 0; currentVertex < list.size(); currentVertex++) {
                List<Node> nodes = list.get(currentVertex);
                if (nodes != null) {
                    for (Node node : nodes) {
                        if (dist[currentVertex] != Integer.MAX_VALUE && (dist[currentVertex] + node.value < dist[node.vertexTo])) {
                            dist[node.vertexTo] = dist[currentVertex] + node.value;
                        }
                    }
                }
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