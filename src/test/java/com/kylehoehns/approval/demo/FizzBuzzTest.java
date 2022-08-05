package com.kylehoehns.approval.demo;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.kylehoehns.approval.demo.FizzBuzz.fizzBuzzLegacy;
import static com.kylehoehns.approval.demo.FizzBuzz.fizzBuzzModern;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FizzBuzzTest {

  @Test
  void testFizzBuzz() {
    // classic approach - dig into the code, determine what some possible values should be
    // time-consuming, and you're never sure what's "right" vs what it's actually doing.
    assertArrayEquals(new String[]{"1"}, fizzBuzzLegacy(1, 2));
    assertArrayEquals(new String[]{""}, fizzBuzzLegacy(2, 2));
    assertArrayEquals(new String[]{"1", "2", "Fizz"}, fizzBuzzLegacy(1, 4));
    assertArrayEquals(new String[]{"FizzBuzz"}, fizzBuzzLegacy(15, 16));
    assertArrayEquals(new String[]{"Buzz"}, fizzBuzzLegacy(5, 6));
  }

  record FizzBuzzInputs(int start, int end) {
  }

  @Test
  void testFizzBuzz_ApprovalTests() {
    Approvals.verifyAll(
        new FizzBuzzInputs[]{
            new FizzBuzzInputs(0, 3),
            new FizzBuzzInputs(10, 0),
            new FizzBuzzInputs(1, 1),
            new FizzBuzzInputs(15, 16),
            new FizzBuzzInputs(1, 4),
            new FizzBuzzInputs(2, 5),
            new FizzBuzzInputs(3, 6),
            new FizzBuzzInputs(1, 100),
        },
        input -> Arrays.toString(
            fizzBuzzLegacy(input.start, input.end)
        )
    );
  }

}