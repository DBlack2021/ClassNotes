import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.*;

class ConfirmInputsCalculator implements SimpleCalcModel {
  private StringBuilder log;

  public ConfirmInputsCalculator(StringBuilder log) {
    this.log = log;
  }

  @Override
  public int add(int num1, int num2) {
    log.append("add num1 = " + num1 + " num2 = " + num2 + "\n");
    return 1;
  }

  @Override
  public int sub(int num1, int num2) {
    log.append("sub num1 = " + num1 + " num2 = " + num2 + "\n");
    return -1;
  }
}

public class SimpleCalcControllerTest {

  @Test
  public void go() {
    SimpleCalcModel calc = new Calculator();
    Readable in;
    PrintStream out;
    int one, two;
    boolean op;

    Random r = new Random(100);
    // Fuzzy Testing / Random Sample Testing
    for (int i = 0; i < 100000; i++) {
      one = r.nextInt();
      op = r.nextBoolean();
      two = r.nextInt();

      String input = "" + one + " " + (op ? "+" : "-") + " " + two;

      in = new StringReader(input);

      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      out = new PrintStream(outStream);

      CalculatorController controller = new SimpleCalcController(in, out);

      try {
        controller.go(calc);
      } catch (IOException e) {

      }

      assertEquals("" + (op ? (one + two) : (one - two)), new String(outStream.toByteArray()));
    }
  }

  @Test
  public void testInputsSentToModel() {
    Readable in;
    Appendable out;
    int one, two;
    boolean op;

    Random r = new Random(100);
    // Fuzzy Testing / Random Sample Testing
    for (int i = 0; i < 100000; i++) {
      one = r.nextInt();
      op = r.nextBoolean();
      two = r.nextInt();

      String input = "" + one + " " + (op ? "+" : "-") + " " + two;

      in = new StringReader(input);

      out = new StringBuilder();

      StringBuilder log = new StringBuilder();
      SimpleCalcModel calc = new ConfirmInputsCalculator(log);

      CalculatorController controller = new SimpleCalcController(in, out);

      try {
        controller.go(calc);
      } catch (IOException e) {

      }

      assertEquals((op ? "add " : "sub ") + "num1 = " + one + " num2 = " + two + "\n",
              log.toString());
    }
  }
}