package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7392989
public class Problem5 {
  final static Queue queue = new Queue();
  static boolean processing = true;

  private static class Queue {
    private Node first;
    private Node last;
    private int size;

    public Queue() {
      this.first = null;
      this.last = null;
      this.size = 0;
    }

    private class Node {
      private int data;
      private Node nextNode;

      public Node(int data, Node nextNode) {
        this.data = data;
        this.nextNode = nextNode;
      }
    }

    public void push(final int n) {
      if (size == 0) {
        this.first = new Node(n, null);
        this.last = this.first;
      } else {
        this.last.nextNode = new Node(n, null);
        this.last = this.last.nextNode;
      }

      this.size++;
    }

    public int pop() {
      int value = this.front();
      this.first = this.first.nextNode;
      this.size--;

      return value;
    }

    public int front() {
      return this.first.data;
    }

    public int size() {
      return this.size;
    }

    public void clear() {
      this.first = null;
      this.last = null;
      this.size = 0;
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
        queue.push(in.nextInt());
        answer = "ok";
        break;
      case "pop":
        answer = Integer.toString(queue.pop());
        break;
      case "front":
        answer = Integer.toString(queue.front());
        break;
      case "size":
        answer = Integer.toString(queue.size());
        break;
      case "clear":
        queue.clear();
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