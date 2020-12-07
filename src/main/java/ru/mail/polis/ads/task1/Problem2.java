package ru.mail.polis.ads.task1;

import java.io.*;
import java.util.*;

// https://www.e-olymp.com/ru/submissions/7426036
public class Problem2 {
    public static class Node {
        public Node() {
            left = null;
            right = null;
        }

        private Character data;
        private Node left;
        private Node right;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();

        for (int i = 0; i < count; i++) {
            Stack<Node> queue = new Stack<>();

            String str = in.next();
            for (int j = 0; j < str.length(); j++) {
                final char ch = str.charAt(j);
                final Node node = new Node();
                node.data = ch;

                if (!Character.isLowerCase(ch)) {
                    node.right = queue.pop();
                    node.left = queue.pop();
                }

                queue.add(node);
            }

            System.out.println(contLevelOrder(queue.pop()));
        }
    }

    static String contLevelOrder(Node top) {
        ArrayDeque<Node> deque = new ArrayDeque<>();
        StringBuilder srt = new StringBuilder("");
        deque.add(top);
        while (!deque.isEmpty()) {
            Node node = deque.pollFirst();

            if (node.left != null) {
                deque.addLast(node.left);
            }

            if (node.right != null) {
                deque.addLast(node.right);
            }
            srt.append(node.data);
        }

        return srt.reverse().toString();
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