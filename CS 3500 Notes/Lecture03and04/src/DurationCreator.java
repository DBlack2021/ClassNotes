/**
 * A class that creates {@code Duration} objects
 *
 * Example usage:
 * {@code Duration duration = DurationCreator.createDuration(470, DurationCreator.DurationType.HMS;}
 */

public class DurationCreator {

  // it's an eenooooom

  /**
   * {@code DurationType} states the possible types of {@code Duration} objects that can be made
   */
  public enum DurationType {
    HMS,
    COMPACT
  }

  /**
   * Creates a {@code Duration} object from seconds
   *
   * @param seconds the number of seconds in the duration
   * @param durationType the type of {@code Duration} to create
   * @return a new {@code Duration} specified by the client
   * @throws IllegalArgumentException if given an invalid type of duration
   */
  public static Duration createDuration(long seconds, DurationType durationType) throws IllegalArgumentException {
    if(durationType.equals(DurationType.HMS)) {
      return new HMSDuration(seconds);
    } else if(durationType.equals(DurationType.COMPACT)) {
      return new CompactDuration(seconds);
    } else {
      throw new IllegalArgumentException("Do not recognize type: " + durationType);
    }
  }
}
