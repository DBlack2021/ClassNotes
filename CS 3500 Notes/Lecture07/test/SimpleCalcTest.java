import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class SimpleCalcTest {

  @Test
  public void testCalc() {
    int num1, num2;
    Scanner scan = new Scanner(System.in);
    num1 = scan.nextInt();
    num2 = scan.nextInt();
    System.out.printf("%d", num1 + num2);
  }
}