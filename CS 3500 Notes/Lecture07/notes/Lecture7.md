# Lecture 07: Mocks
###### May 19th, 2022

## Recap From Last Time:
- We have a simple calculator
- Used `System.in` and `System.out` to take in and print out user input
  - This cannot be tested properly, so we abstracted these properties of the controller

```java
private final InputStream in;
private final PrintStream out;

public SimpleCalcController(InputStream in, PrintStream out) {
  this.in = in;
  this.out = out;
}
```

In the `Controller` test:

```java
SimpleCalcModel calc = new Calculator();
InputStream in;

int one, two;

one = 2;
two = 5;

String input = "" + 2 + " " + 5;

in = new ByteArrayInputStream(input.getBytes());

CalculatorController controller = new SimpleCalcController(in, System.out);

controller.go(calc);
```

We also have to test output!

### Random Sample Testing

```java
@Test
public void go() {
  SimpleCalcModel calc = new Calculator();
  InputStream in;
  PrintStream out;
  int one, two;
  Random r = new Random();

  // Fuzzy Testing / Random Sample Testing
  for (int i = 0; i < 100; i++) {
    one = r.nextInt(100);
    two = r.nextInt(100);

    String input = "" + one + " " + two;

    in = new ByteArrayInputStream(input.getBytes());

    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    out = new PrintStream(outStream);

    CalculatorController controller = new SimpleCalcController(in, out);
    controller.go(calc);

    assertEquals("" + (one + two), new String(outStream.toByteArray()));
  }
}
```

ByteArray is an arbitrary choice: we could have used a `FileInputStream`

### Testing That Inputs Are Correct

Create a model that spits out inputs:

#### THIS IS A MOCK!
```java
class ConfirmInputsCalculator implements SimpleCalcModel {
  private StringBuilder log;

  public ConfirmInputsCalculator(StringBuilder log) {
    this.log = log;
  }

  @Override
  public int add(int num1, int num2) {
    log.append("num1 = " + num1 + " num2 = " + num2 + "\n");
    return 0;
  }
}
```

### In The Tests:

```java
@Test
public void testInputsSentToModel() {
  InputStream in;
  PrintStream out;
  int one, two;

  Random r = new Random(100);
  // Fuzzy Testing / Random Sample Testing
  for (int i = 0; i < 100000; i++) {
    // generate two numbers
    one = r.nextInt();
    two = r.nextInt();

    // make them one input
    String input = "" + one + " " + two;

    // create the input stream
    in = new ByteArrayInputStream(input.getBytes());

    // create the output stream
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    out = new PrintStream(outStream);

    // this is the model logic for this test
    StringBuilder log = new StringBuilder();
    SimpleCalcModel calc = new ConfirmInputsCalculator(log);

    // create the controller and make it go --> this will use the input/output streams for input 
    // and then also run add() on the ConfirmInputsCalculator() which will write to our log
    
    // controller w/ input/output streams --> go with our ConfirmInputsCalc --> feed input 
    // stream to ConfirmInputsCalc --> modify log --> test log
    CalculatorController controller = new SimpleCalcController(in, out);
    controller.go(calc);

    assertEquals("num1 = " + one + " num2 = " + two + "\n", log.toString());
  }
}
```