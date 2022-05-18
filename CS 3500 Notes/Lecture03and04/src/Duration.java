/**
 * Represents a duration of time in seconds
 */

public interface Duration extends Comparable<Duration>{
  /**
   * Converts the current duration into seconds
   * @return the number of seconds equivalent to this duration
   */
  long inSeconds();

  /**
   * Formats the duration into H:MM:SS
   * @return the String format of this duration
   */
  String asHms();

  //Add durations

  /**
   * Adds this duration to another duration
   * @param other The duration added to this one
   * @return new duration representing the sum of both durations' time
   */
  Duration add(Duration other);

  /**
   * Tests if this duration and the other object (if a duration)
   * have the same seconds value
   * @param o the other object to compare
   * @return true if both objects are durations and have the same seconds value<br>
   *         false otherwise
   */
  boolean equals(Object o);

  /**
   * Returns the hash code for this duration
   * @return an integer representing the hash code
   */
  int hashCode();

  /**
   * Compares two durations
   * @param that the object to be compared.
   * @return &lt;0 if this &lt; that in terms of seconds<br>
   *         =0 if this == that in terms of seconds<br>
   *         &gt;0 if this &gt; that in terms of seconds
   */
  int compareTo(Duration that);
}








