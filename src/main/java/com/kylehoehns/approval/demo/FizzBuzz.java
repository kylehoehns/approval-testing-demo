package com.kylehoehns.approval.demo;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FizzBuzz {

  public static String[] fizzBuzzNew(int start, int end) {
    return IntStream.range(start, end)
      .mapToObj(i -> {
        if (i % 15 == 0) return "FizzBuzz";
        else if (i % 3 == 0) return "Fizz";
        else if (i % 5 == 0) return "Buzz";
        else return String.valueOf(i);
      })
      .toArray(String[]::new);
  }

  public static String[] fizzBuzzLegacy(int start, int end) {
    String currentValue = null;
    if (start == end) {
      currentValue = "";
      return new String[]{currentValue};
    } else {
      if (start % 15 == 0) {
        currentValue = "FizzBuzz";
      } else {
        if (start % 3 == 0) {
          currentValue = "Fizz";
        } else if (start % 5 == 0) {
          currentValue = "Buzz";
        }
      }
      if (currentValue == null) {
        currentValue = String.valueOf(start);
      }
    }
    return (currentValue + Arrays.toString(fizzBuzzLegacy(start + 1, end)))
      .replaceAll("\\]", "")
      .split("\\[|,\\s*");
  }
  
}
