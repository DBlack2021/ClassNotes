// Be default, protected access
// Can access this item from:
//  1) any subclass of this class
//  2) is accessible from the same package
abstract class AbstractDuration implements Duration {
  @Override
  public int compareTo(Duration that) {
    return Long.compare(this.inSeconds(), that.inSeconds());
  }

  // ptr equality == intentional equality: The exact same instance of an object in memory
  // extensional equality: do they represent the same thing? (comparing fields)
  @Override
  public boolean equals(Object o){
    if(this == o) {
      return true;
    }

    if(!(o instanceof Duration)) {
      return false;
    }
    Duration that = (Duration)o;
    return this.inSeconds() == that.inSeconds();
  }

  @Override
  public int hashCode() {
    // Objects.hashCode() for any number of hash code
    return Long.hashCode(this.inSeconds());
  }

  @Override
  public String toString() {
    return asHms();
  }

  protected static String asHms(int hours, int minutes, int seconds) {
    return String.format("%d:%02d:%02d", hours, minutes, seconds);
  }
  /**
   *
   * Creates a {@code Duration} object using the number of seconds
   *
   * @param seconds the length of the duration
   * @return new {@code Duration} with seconds time
   */

  // A Factory Method is a method that does the following:
  // 1) Primarily creates objects
  // 2) can create objects and return objects of several related types
  // 3) the object returned is determined at runtime
  protected abstract Duration fromSeconds(long seconds);

  @Override
  public Duration add(Duration other) {
    return this.fromSeconds(this.inSeconds() + other.inSeconds());
  }
}
