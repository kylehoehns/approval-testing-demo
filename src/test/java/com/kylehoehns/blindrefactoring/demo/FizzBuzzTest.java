package com.kylehoehns.blindrefactoring.demo;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.kylehoehns.blindrefactoring.demo.FizzBuzz.fizzBuzz;

class FizzBuzzTest {

  @Test
  void testFizzBuzz() {
    // classic approach - dig into the code, determine what some possible values should be
    // time-consuming, and you're never sure what's "right" vs what it's actually doing.
    Assertions.assertArrayEquals(new String[]{"1"}, fizzBuzz(1, 2));
    Assertions.assertArrayEquals(new String[]{""}, fizzBuzz(2, 2));
    Assertions.assertArrayEquals(new String[]{"1", "2", "Fizz"}, fizzBuzz(1, 4));
    Assertions.assertArrayEquals(new String[]{"FizzBuzz"}, fizzBuzz(15, 16));
    Assertions.assertArrayEquals(new String[]{"Buzz"}, fizzBuzz(5, 6));
  }

  record FizzBuzzTestCase(int start, int end) {}

  @Test
  void testFizzBuzz_ApprovalTests() {
    Approvals.verifyAll(
        new FizzBuzzTestCase[]{
            new FizzBuzzTestCase(0, 3),
            new FizzBuzzTestCase(10, 0),
            new FizzBuzzTestCase(1, 1),
            new FizzBuzzTestCase(15, 16),
            new FizzBuzzTestCase(1, 4),
            new FizzBuzzTestCase(2, 5),
            new FizzBuzzTestCase(3, 6),
            new FizzBuzzTestCase(1, 100),
        },
        input -> Arrays.toString(
            fizzBuzz(input.start, input.end)
        )
    );
  }

}