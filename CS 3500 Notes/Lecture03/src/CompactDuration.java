/**
 * Represents a duration entirely in seconds
 */

public final class CompactDuration extends AbstractDuration {
  private final long seconds;

  CompactDuration(long seconds) throws IllegalArgumentException {
    if(seconds < 0) {
      throw new IllegalArgumentException("Seconds cannot be negative");
    }
    this.seconds = seconds;
  }

  @Override
  public long inSeconds() {
    return this.seconds;
  }

  @Override
  public String asHms() {
    int hours = (int)(this.seconds / 3600);
    int minutes = (int)((this.seconds % 3600) / 60);
    int seconds = (int)(this.seconds % 60);
    return AbstractDuration.asHms(hours, minutes, seconds);
  }

  @Override
  public Duration add(Duration other) {
    return fromSeconds(this.inSeconds() + other.inSeconds());
  }

  protected Duration fromSeconds(long seconds) {
    return new CompactDuration(seconds);
  }

  @Override
  public int compareTo(Duration that) {
    return 0;
  }
}
