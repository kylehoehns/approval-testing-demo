package com.kylehoehns.approval.demo;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.kylehoehns.approval.demo.FizzBuzz.fizzBuzzLegacy;
import static com.kylehoehns.approval.demo.FizzBuzz.fizzBuzzNew;
import static org.junit.jupiter.api.Assertions.*;

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

  record FizzBuzzInputs(int start, int end){}

  @Test
  void testFizzBuzz_ApprovalTests() {
    Approvals.verifyAll(
      new FizzBuzzInputs[]{
        new FizzBuzzInputs(1, 1),
        new FizzBuzzInputs(1, 2),
        new FizzBuzzInputs(2, 4),
        new FizzBuzzInputs(3, 8),
        new FizzBuzzInputs(1, 20)
      },
      (fizzBuzzInputs -> Arrays.toString(fizzBuzzNew(fizzBuzzInputs.start, fizzBuzzInputs.end)))
    );
  }

}