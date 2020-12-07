package ru.mail.polis.ads.task1;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7392981
public class Problem3 {
  private static void solve(final FastScanner in, final PrintWriter out) {
    String str = in.next();
    String answer = "YES";
    int openBracketCount = 0;

    for (int i = 0; i < str.length(); i++) {
      final char bracket = str.charAt(i);
      final boolean isOpenBracket = bracket == '(';
      final boolean isCloseBracket = bracket == ')';

      if (isOpenBracket) {
        openBracketCount++;
      }

      if (isCloseBracket) {
        if (openBracketCount == 0) {
          answer = "NO";
          break;
        }

        openBracketCount--;
      }
    }

    if (openBracketCount != 0) {
      answer = "NO";
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