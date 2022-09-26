---
# try also 'default' to start simple
theme: apple-basic
layout: intro-image
image: /head-in-box.jpg
title: Blind Refactoring
selectable: true
---

<div class="absolute bottom-10">
  <h1>Blind Refactoring</h1>
  <p>Changing Code You Don’t Understand</p>
</div>

---
layout: default
---

# The Scenario

<div class="text-left">
    <p>The business needs an enhancement to its legacy FizzBuzz application</p>
    <p v-click="1">...that needs implemented today</p>
    <p v-click="2">...and there aren't any existing tests</p>
    <img v-click="3" style="margin: auto" src="/adventure.gif"/>
</div>

---
layout: default
---

# Legacy Code

```java
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
```

---

# First Step When Working With Legacy Code

<v-click>

```shell
git blame FizzBuzz.java
```

</v-click>

<br />

<v-click>

```shell
740cc984 (Kyle Hoehns 2022-08-04) 1) ...
```

<br />

<img style="margin: auto" src="/cry.gif"/>

</v-click>

---

# Real First Step When Working With Legacy Code

Get test coverage however you can
* unit
* component/module
* integration

Without test coverage whatever you decide to change has a very real chance of impacting already happy customers.


---
layout: default
---

# Getting Comprehensive Test Coverage Is Hard

You need to
* read long and unmanageable methods
* research years of changes to a method
* become an expert in a domain you may not be comfortable with
* wrangle code that may not be easily testable in the first place

---
layout: default
---

# Usual Approach

Test inputs and outputs

```java
public static String[] fizzBuzz(int a, int x) {
  String fizZBuzz = null;
  if (a < 1) return new String[0];
  // ...
}
```

```java
@Test
void givenAFizzBuzzStartThatIsLessThan1_whenIExecuteFizzBuzz_thenIShouldGetAnEmptyArray() {
  assertArrayEquals(new String[]{}, fizzBuzz(0, 3));
}
```

<v-click>
<img style="margin: auto" src="/insane.gif"/>
</v-click>

---
layout: fact
---

# Mindset
Untested Production Code Has No Errors


---
layout: default
---

# Approval Tests

Stop caring about what the code does or the output of your tests. Just add enough test cases to get to 100% coverage.

```java
public static String[] fizzBuzz(int a, int x){
  String fizZBuzz = null;
  if (a < 1) return new String[0];
  // ...
}
```

```java
record FizzBuzzTestCase(int start, int end) {}
  
@Test
void testFizzBuzz() {
    Approvals.verifyAll(
      new FizzBuzzTestCase[]{
        new FizzBuzzTestCase(0, 3)
      },
      testCase -> Arrays.toString(
        fizzBuzz(testCase.start, testCase.end)
      )
    );
}
```

---
layout: default
---

# Output
Running the tests will create a new file that will contain the output of all the executed test cases.

```text
FizzBuzzTest.testFizzBuzz.received.txt
---
[]
```

Utilizing code coverage while running the tests allows us to see paths through the code we haven't tested yet.

<img style="margin: auto" src="/code-coverage-1.png"/>

---
layout: default
---

# Get More Coverage

Keep writing as many test cases as you want until you get adequate test coverage
```java
public static String[] fizzBuzz(int a, int x) {
    // ...
    if (x < 1) return new String[0];
    // ...
```

```java {6}
@Test
void testFizzBuzz() {
    Approvals.verifyAll(
      new FizzBuzzTestCase[]{
        new FizzBuzzTestCase(0, 3),
        new FizzBuzzTestCase(10, 0)
      },
      // ...
    );
}
```

---
layout: default
---

# Get More Coverage

Keep writing as many test cases as you want until you get adequate test coverage
```java
public static String[] fizzBuzz(int a, int x) {
    // ...
    if (a == x) {
      fizZBuzz = "";
      return new String[]{fizZBuzz};
    }
    // ...
```

```java {7-8}
@Test
void testFizzBuzz() {
    Approvals.verifyAll(
      new FizzBuzzTestCase[]{
        new FizzBuzzTestCase(0, 3),
        new FizzBuzzTestCase(10, 0),
        new FizzBuzzTestCase(1, 1),
        new FizzBuzzTestCase(6, 6),
      },
      // ...
    );
}
```

---
layout: default
---

# Get More Coverage

Keep writing as many test cases as you want until you get adequate test coverage
```java
public static String[] fizzBuzz(int a, int x) {
    // ...
    if (a % 15 == 0) fizZBuzz = "FizzBuzz";
    // ...
```

```java {9}
@Test
void testFizzBuzz() {
    Approvals.verifyAll(
      new FizzBuzzTestCase[]{
        new FizzBuzzTestCase(0, 3),
        new FizzBuzzTestCase(10, 0),
        new FizzBuzzTestCase(1, 1),
        new FizzBuzzTestCase(6, 6),
        new FizzBuzzTestCase(15, 16),
      },
      // ...
    );
}
```

---
layout: default
---

# Get More Coverage

Keep writing as many test cases as you want until you get adequate test coverage
```java
public static String[] fizzBuzz(int a, int x) {
    // ...
    else {
      if (a % 3 == 0) {
        fizZBuzz = "Fi" + "zz";
      }
    }
    // ...
```

```java {10}
@Test
void testFizzBuzz() {
    Approvals.verifyAll(
      new FizzBuzzTestCase[]{
        new FizzBuzzTestCase(0, 3),
        new FizzBuzzTestCase(10, 0),
        new FizzBuzzTestCase(1, 1),
        new FizzBuzzTestCase(6, 6),
        new FizzBuzzTestCase(15, 16),
        new FizzBuzzTestCase(3, 6),
      },
      // ...
    );
}
```

---
layout: default
---

# Get More Coverage

Keep writing as many test cases as you want until you get adequate test coverage
```java
public static String[] fizzBuzz(int a, int x) {
    // ...
    if (a % 5 == 0) {
      fizZBuzz = "Buzz";
    }
    // ...
```

```java {11}
@Test
void testFizzBuzz() {
    Approvals.verifyAll(
      new FizzBuzzTestCase[]{
        new FizzBuzzTestCase(0, 3),
        new FizzBuzzTestCase(10, 0),
        new FizzBuzzTestCase(1, 1),
        new FizzBuzzTestCase(6, 6),
        new FizzBuzzTestCase(15, 16),
        new FizzBuzzTestCase(3, 6),
        new FizzBuzzTestCase(10, 12),
      },
      // ...
    );
}
```

---
layout: default
---

# Output

<img style="margin: auto" src="/code-coverage-2.png"/>

---
layout: default
---

# Output

When you've achieved 100% code coverage you'll have an output resource showing current outputs for a variety of combinations of inputs on production code.

```text
[]
[]
[]
[FizzBuzz]
[1, 2, Fizz]
[2, Fizz, 4]
[Fizz, 4, Buzz]
[1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz]
```

With the ApprovalTests framework, rename the output file from `*.received.txt` to `*.approved.txt` when you're finished getting coverage. From then on, when the test runs it will compare the output against the approved test results and show an error if output changed.

With thorough test coverage you can now be confident that your changes will not break existing functionality.

---
layout: default
---

# Do I Have to Use A Library?

No! Instead of a library like Approval Tests you could...

- Serialize response object as JSON and do a diff
- Store binary files and compare
- Hard-code normal assertions based on existing output

---
layout: default
---

# Implement Changes

```java
public static String[] fizzBuzz(int start, int end) {
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
```

<img style="margin: auto" src="/activities.gif"/>

---
layout: default
---

# Takeaways

<br />

## The business probably makes money from the code you're changing
 - It may not be *pretty*, but that doesn't make it *incorrect*. 

## It's OK to not understand what a method does as long as you can guarantee you didn't accidentally break it

---
layout: default
---

# More Info

<ul>
  <li><a href="https://approvaltests.com/">Approval Tests Library</a> (supports Node, Java, .Net, Python)</li>
  <li>Example Code can be found on <a href="https://github.com/kylehoehns/approval-testing-demo">GitHub</a></li>
</ul>

<br />

<img style="margin: auto" src="/finished.gif"/>
