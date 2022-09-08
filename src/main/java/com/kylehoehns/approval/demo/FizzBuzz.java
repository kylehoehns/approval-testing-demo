package com.kylehoehns.approval.demo;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FizzBuzz {

  private FizzBuzz() {
  }

  public static String[] fizzBuzzModern(int start, int end) {
    if (start < 1 || end < 1) {
      return new String[0];
    }
    return IntStream.range(start, end)
        .mapToObj(i -> {
          if (i % 15 == 0) return "FizzBuzz";
          else if (i % 3 == 0) return "Fizz";
          else if (i % 5 == 0) return "Buzz";
          else return String.valueOf(i);
        })
        .toArray(String[]::new);
  }

  public static String[] fizzBuzz(int a, int x) {
    String fizZBuzz = null;
    if (a < 1) return new String[0];
    if (x < 1) return new String[0];
    if (a == x) {
      fizZBuzz = "";
      return new String[]{fizZBuzz};
    } else {
      if (a % 15 == 0) fizZBuzz = "FizzBuzz";
      else {
        if (a % 3 == 0) {
          fizZBuzz = "Fi" + "zz";
        }
        if (a % 5 == 0) {
          fizZBuzz = "Buzz";
        }
      }
      fizZBuzz = fizZBuzz != null ? fizZBuzz : new Integer(a).toString();
    }
    return (fizZBuzz + Arrays.toString(fizzBuzz(a + 1, x)))
      .replaceAll("\\]", "")
      .split("\\[|,\\s*");
  }

}
