import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

interface SimpleCalcModel {
  public int add(int num1, int num2);

  public int sub(int num1, int num2);
}

class Calculator implements SimpleCalcModel {
  public int add(int num1, int num2) {
    return num1 + num2;
  }

  public int sub(int num1, int num2) {
    return num1 - num2;
  }
}

interface CalculatorController {
  void go(SimpleCalcModel model) throws IOException;
}

class SimpleCalcController implements CalculatorController {
  private final Readable in;
  private final Appendable out;

  public SimpleCalcController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  public void go(SimpleCalcModel model) throws IOException {
    int num1, num2;
    String op;
    Scanner scan = new Scanner(this.in);
    num1 = scan.nextInt();
    op = scan.next();
    num2 = scan.nextInt();

    switch (op) {
      case "+":
        this.out.append(String.format("%d", model.add(num1, num2)));
        break;
      case "-":
        this.out.append(String.format("%d", model.sub(num1, num2)));
        break;
      default:
        throw new IllegalArgumentException("Argument " + op + " not understood");
    }
  }
}

public class SimpleCalc {
  public static void main(String[] args) {
    try {
      new SimpleCalcController(new InputStreamReader(System.in), System.out).go(new Calculator());
    } catch (IOException e) {

    }

    /*
     * To print an object: calls its toString()
     * System.out.print(scan);
     */
  }
}
