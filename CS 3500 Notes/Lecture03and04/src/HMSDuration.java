/**
 * A {@code Duration} that represents time in hours, minutes, and seconds
 */

// final on a class means no subclasses allowed
public final class HMSDuration extends AbstractDuration {
  private final int hours;
  private final int minutes;
  private final int seconds;

  HMSDuration(int hours, int minutes, int seconds) throws IllegalArgumentException {
    if(hours < 0) {
      throw new IllegalArgumentException("Hours cannot be negative");
    }
    if(minutes < 0) {
      throw new IllegalArgumentException("Minutes cannot be negative");
    }
    if(seconds < 0) {
      throw new IllegalArgumentException("Seconds cannot be negative");
    }

    if(seconds >= 60){
      minutes += seconds / 60;
      seconds = seconds % 60;
    }

    if(minutes >= 60) {
      hours += minutes / 60;
      minutes = minutes % 60;
    }

    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  }

  HMSDuration(long seconds) throws IllegalArgumentException {
    if(seconds < 0) {
      throw new IllegalArgumentException("Seconds cannot be negative");
    }

    this.hours = (int)seconds/3600;
    this.minutes = (int)(seconds % 3600)/60;
    this.seconds = (int)seconds %60;
  }

  @Override
  public long inSeconds() {
    return 3600*(long)this.hours + 60*this.minutes + this.seconds;
  }

  @Override
  public String asHms() {
    return AbstractDuration.asHms(this.hours, this.minutes, this.seconds);
  }

  @Override
  public Duration add(Duration other) {
    return fromSeconds(this.inSeconds() + other.inSeconds());
  }

  protected Duration fromSeconds(long seconds) {
    return new HMSDuration(seconds);
  }


}
