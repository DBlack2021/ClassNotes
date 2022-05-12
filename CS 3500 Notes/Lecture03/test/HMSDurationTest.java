import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HMSDurationTest {

  Duration d1;
  Duration d2;
  Duration d3;
  DurationCreator.DurationType type = DurationCreator.DurationType.COMPACT;

  @Before
  public void init() {
    this.d1 = new HMSDuration(0, 37, 25);
    this.d2 = new HMSDuration(0, 0, 90); //Going over the limit on a unit is OK
   // this.d3 = new HMSDuration(3, -4, 20); //This should throw an error/exception
    this.d3 = DurationCreator.createDuration(375, type);
  }

  @Test
  public void testValidInitialization() {
    this.d1 = new HMSDuration(0, 30, 25);
    assertEquals("0:30:25", d1.asHms());
    this.d2 = new HMSDuration(0, 0, 90);
    assertEquals("0:01:30", d2.asHms());
  }

  @Test
  public void testInvalidInitialization() {
    try {
      this.d1 = new HMSDuration(3, -4, 20);
      fail("Should have thrown an IllegalArgumentException");
    } catch(IllegalArgumentException e) {
      if (!e.getMessage().equals("Hours cannot be negative")) {
        fail("Wrong exception thrown: Expected minutes");
      }
      //do nothing and go pas this try-catch-block
    } catch(OutOfMemoryError e) { }

    try {
      this.d2 = new HMSDuration(-2, 0, 0);
    } catch (IllegalArgumentException e) {
      //for minutes specifically
    }
  }

  // Passes if this Exception is EVER seen
  @Test(expected=IllegalArgumentException.class)
  public void testAnExceptionBeingThrown(){
    this.d1 = new HMSDuration(3,-4,20);
  }

  @org.junit.Test
  public void inSeconds() {
  }

  @org.junit.Test
  public void asHms() {
  }

  @org.junit.Test
  public void add() {
  }

  @org.junit.Test
  public void compareTo() {
  }
}