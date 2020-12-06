package ru.mail.polis.ads.step.ponomarev.task9;

import java.io.*;
import java.util.*;

public class Task4 {
    private static List<List<Node>> nodes;
    private static Set<Integer> handled;
    private static int[] dist;
    private static int[] road;

    private static class Node {
        public Integer nodeIndex;
        public Integer value;

        public Node(int nodeIndex, int value) {
            this.nodeIndex = nodeIndex;
            this.value = value;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int searchStart = in.nextInt() - 1;
        int searchEnd = in.nextInt() - 1;

        init(n);

        handled.add(searchStart);
        dist[searchStart] = 0;
        road[searchStart] = searchStart;

        for (int i = 0; i < k; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int v = in.nextInt();

            nodes.get(a).add(new Node(b, v));
        }

        findMinDist(searchStart);

        if (road[searchEnd] == -1) {
            System.out.println(-1);
        } else {
            System.out.println(dist[searchEnd]);
            StringBuffer vu = new StringBuffer();
            int i = searchEnd;
            while (i != searchStart) {
                vu.append(i + 1).append(" ");
                i = road[i];
            }

            vu.append(i + 1);

            System.out.println(vu.reverse().toString());
        }
    }

    private static void findMinDist(int i) {
        if (nodes.get(i).isEmpty() || handled.size() == nodes.size()) {
            return;
        }

        int minIndex = Integer.MAX_VALUE;

        for (Node node : nodes.get(i)) {
            if (minIndex == Integer.MAX_VALUE) {
                minIndex = node.nodeIndex;
            }

            if (!handled.contains(node.nodeIndex)) {
                dist[node.nodeIndex] = node.value + dist[i];
                handled.add(node.nodeIndex);
                road[node.nodeIndex] = i;

            }

            if (dist[i] + node.value < dist[node.nodeIndex]) {
                dist[node.nodeIndex] = dist[i] + node.value;
                road[node.nodeIndex] = i;
            }
        }


        for (Node n : nodes.get(i)) {
            if (dist[n.nodeIndex] < dist[minIndex]) {
                minIndex = n.nodeIndex;
            }
        }

        findMinDist(minIndex);
    }


    private static void init(int n) {
        road = new int[n];
        dist = new int[n];
        nodes = new ArrayList(n);
        handled = new HashSet<>();
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            nodes.add(i, new ArrayList<>());
            road[i] = -1;
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
