---
# try also 'default' to start simple
theme: seriph
# random image from a curated Unsplash collection by Anthony
# like them? see https://unsplash.com/collections/94734566/slidev

background: /assets/head-in-box.jpg

# apply any windi css classes to the current slide
class: 'text-center'
# https://sli.dev/custom/highlighters.html
highlighter: shiki
# show line numbers in code blocks
lineNumbers: false

# persist drawings in exports and build
drawings:
  persist: false
# use UnoCSS (experimental)
css: unocss
---

# Blind Refactoring

Changing Code You Don't Understand

<div class="abs-br m-6 flex gap-2">
  <a href="https://github.com/kylehoehns/approval-testing-demo" target="_blank" alt="GitHub"
    class="text-xl icon-btn opacity-50 !border-none !hover:text-white">
    <carbon-logo-github />
  </a>
</div>

---
layout: default
---

# The Scenario

<div class="text-left">
    <p>The Business needs an enhancement to our legacy FizzBuzz code that needs implemented <u>today</u></p>
    <p v-click>...and there aren't any existing tests.</p>
    <img v-after src="assets/adventure.gif"/>
</div>

---
layout: default
---

# Legacy Code

```java {all|1|3-4|11-13|18|20|all}
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
  return fizZBuzz + Arrays.toString(fizzBuzz(a + 1, x))
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


<v-click>

```shell
740cc984 (Kyle Hoehns 2022-08-04) 1) ...
```

</v-click>

<img v-after src="assets/nobody-likes-you.gif"/>

---

# Real First Step When Working With Legacy Code

## Get Test Coverage
* unit
* component/module
* integration

Without test coverage whatever you decide to change has a very real chance of impacting already happy customers.


---
layout: default
---

# Getting Comprehensive Test Coverage Is Hard


## You need to
* read long and unmanageable methods
* research years of changes to a method
* become an expert in a domain you may not be comfortable with

---
layout: fact
---

# Mindset
Untested Production Code Has No Errors

---
layout: default
---

# Coding Blind

Test inputs and outputs

<div v-click="1">

```java
public static String[] fizzBuzz(int a, int x) {
  // doesn't matter
}
```

</div>

<br />

<div v-click="2">

```java
@Test
void testFizzBuzz() {
  assertArrayEquals(new String[]{"foo"}, fizzBuzz(0, 3));
}
```

</div>

<br />

<div v-click="3">

```shell
AssertionFailedError: array lengths differ, expected: <1> but was: <0>
```

</div>

<br />

<div v-click="4">

```java
@Test
void testFizzBuzz() {
  assertArrayEquals(new String[]{}, fizzBuzz(0, 3));
}
```

</div>

---
layout: fact
---

# These Assertions
Are going to take a while to write

---
layout: default
---

# Approval Tests

Don't care about the output in your tests

[//]: # (graph/chart here)

Get coverage, store a snapshot of the output

---
layout: default
---

# Example

Code demonstration (?) right/left code to test changes

Output File

Image of Coverage Red/Green


