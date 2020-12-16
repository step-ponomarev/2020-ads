package ru.mail.polis.ads.step.ponomarev;

import java.io.*;
import java.util.*;

// https://www.e-olymp.com/ru/problems/3835
public class Problem2 {
    private static int[] road;
    private static List<Edge> list;

    private static class Edge {
        int from;
        int to;
        int value;

        public Edge(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        init(n);
        fillArray(in, m);

        list.sort(Comparator.comparingInt(o -> o.value));
        int way = getMinCarcase(n);
        out.println(way);
    }

    private static void init(int n) {
        list = new ArrayList<>();
        road = new int[n];

        for (int i = 0; i < n; i++) {
            road[i] = i;
        }
    }

    private static void fillArray(final FastScanner in, int n) {
        for (int i = 0; i < n; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int value = in.nextInt();

            list.add(new Edge(from, to, value));
        }
    }

    private static int getMinCarcase(int n) {
        int counter = 0;
        int way = 0;

        for (Edge edge : list) {
            if (counter == n - 1) {
                return way;
            }
            if (edge != null) {
                if (findSet(edge.from) != findSet(edge.to)) {
                    unionSets(edge.from, edge.to);
                    way += edge.value;
                    counter++;
                }
            }
        }

        return way;
    }


    private static int findSet(int v) {
        if (v == road[v]) {
            return v;
        } else {
            return road[v] = findSet(road[v]);
        }
    }

    private static void unionSets(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a != b) {
            road[a] = b;
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