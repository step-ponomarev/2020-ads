package ru.mail.polis.ads.step.ponomarev.task9;

import java.io.*;
import java.util.*;

public class Task4 {
    private static List<List<Node>> nodes;
    private static Set<Integer> handled;
    private static int[] dist;
    private static Queue<Integer> road = new LinkedList<>();

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

        for (int i = 0; i < k; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int v = in.nextInt();

            nodes.get(a).add(new Node(b, v));
        }

        findMinDist(searchStart);

        if (!road.contains(searchEnd)) {
            System.out.println(-1);
        } else {
            System.out.println(dist[searchEnd]);
            while (!road.isEmpty()) {
                int a = road.remove();

                System.out.print(a + 1 + " ");

                if (a == searchEnd) {
                    break;
                }
            }
        }
    }

    private static void findMinDist(int i) {
        road.add(i);

        if (nodes.get(i).isEmpty() || handled.size() == nodes.size()) {
            return;
        }

        for (Node node : nodes.get(i)) {
            if (!handled.contains(node.nodeIndex)) {
                dist[node.nodeIndex] = node.value + dist[i];
                handled.add(node.nodeIndex);
            }

            if (dist[i] + node.value < dist[node.nodeIndex]) {
                dist[node.nodeIndex] = dist[i] + node.value;
            }
        }

        int minIndex = nodes.get(i).get(0).nodeIndex;
        for (Node n : nodes.get(i)) {
            if (dist[n.nodeIndex] < dist[minIndex]) {
                minIndex = n.nodeIndex;
            }
        }

        findMinDist(minIndex);
    }


    private static void init(int n) {
        handled = new HashSet<>();

        dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

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
