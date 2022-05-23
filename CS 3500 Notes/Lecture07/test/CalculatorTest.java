import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

  Calculator calc;
  @Test
  public void add() {
    calc = new Calculator();
    assertEquals(7, calc.add(3,4));
  }
}