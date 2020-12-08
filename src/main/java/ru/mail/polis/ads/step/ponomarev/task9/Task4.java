package ru.mail.polis.ads.step.ponomarev.task9;

import java.io.*;
import java.util.*;

public class Task4 {
    private static List<List<Node>> nodes;
    private static Queue<Integer> queue = new LinkedList<>();
    private static final Stack<Integer> stack = new Stack<>();
    private static int[] dist;
    private static int[] parent;
    private static boolean[] visited;

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

        for (int i = 0; i < k; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int v = in.nextInt();

            nodes.get(a).add(new Node(b, v));
            nodes.get(b).add(new Node(a, v));
        }

        dist[searchStart] = 0;
        queue.add(searchStart);
        parent[searchStart] = searchStart;

        findMinDist();

        if (parent[searchEnd] == -1) {
            System.out.println(-1);
        } else {
            System.out.println(dist[searchEnd]);

            StringBuffer vu = new StringBuffer();
            int i = searchEnd;
            while (i != searchStart) {
                stack.push(i);
                i = parent[i];
            }
            stack.push(i);

            while (!stack.isEmpty()) {
                System.out.print(stack.pop() + 1 + " ");
            }
        }
    }

    private static void findMinDist() {
        while (!queue.isEmpty()) {
            int i = queue.poll();

            visited[i] = true;
            for (Node node : nodes.get(i)) {
                if (dist[i] + node.value < dist[node.nodeIndex]) {
                    dist[node.nodeIndex] = dist[i] + node.value;
                    parent[node.nodeIndex] = i;
                }
                if (!visited[node.nodeIndex]) {
                    queue.add(node.nodeIndex);
                }
            }
        }
    }

    private static void init(int n) {
        parent = new int[n];
        dist = new int[n];
        nodes = new ArrayList(n);
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            nodes.add(i, new ArrayList<>());
            parent[i] = -1;
            visited[i] = false;
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
