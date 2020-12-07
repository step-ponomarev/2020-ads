package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7424068
public class Problem4 {
    final static Stack stack = new Stack();
    static boolean processing = true;

    private static class Stack {
        private int[] array;
        private int size;

        public Stack() {
            this.array = new int[0];
        }

        public void push(final int n) {
            if (this.size == 0) {
                this.array = new int[1];
            } else if (size == this.array.length) {
                this.array = updatedArray();
            }

            this.array[size] = n;

            this.size++;
        }

        public int pop() {
            int value = this.array[size - 1];
            this.array = slice(this.array, 0, size - 1);
            this.size--;

            return value;
        }

        public int back() {
            return this.array[size - 1];
        }

        public int size() {
            return this.size;
        }

        public void clear() {
            this.array = new int[0];
            this.size = 0;
        }

        private int[] updatedArray() {
            int[] newArray = new int[size + ((size / 2) + 1)];
            for (int i = 0; i < this.array.length; i++) {
                newArray[i] = this.array[i];
            }

            return newArray;
        }

        private int[] slice(int[] array, int l, int r) {
            int[] newArray = new int[r - l];

            for (int i = l; i < r; i++) {
                newArray[i] = array[i];
            }

            return newArray;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        while (processing) {
            handleCommand(in, out);
        }
    }

    private static void handleCommand(final FastScanner in, final PrintWriter out) {
        String answer = "";

        switch (in.next()) {
            case "push":
                stack.push(in.nextInt());
                answer = "ok";
                break;
            case "pop":
                if (stack.size != 0) {
                    answer = Integer.toString(stack.pop());
                } else {
                    answer = "error";
                }

                break;
            case "back":
                if (stack.size != 0) {
                    answer = Integer.toString(stack.back());
                } else {
                    answer = "error";
                }
                break;
            case "size":
                answer = Integer.toString(stack.size());
                break;
            case "clear":
                stack.clear();
                answer = "ok";
                break;
            case "exit":
                processing = false;
                answer = "bye";
                break;
        }

        out.println(answer);
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