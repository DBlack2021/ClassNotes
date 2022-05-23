package connectn;

/**
 * Represents the game of ConnectN and enforces its rules
 */

// Clients see players as String[]
public interface ConnectNModel {

  enum Status {PLAYING, WON, STALEMATE};

  /**
   * Places a disc for the player indicated by who
   * @param who is the player playing
   * @param where do they drop their disc
   * @throws IllegalStateException when the where col is full
   *  OR the game is over
   *  OR it is not who's turn
   * @throws IllegalArgumentException when where is an invalid col
   *         OR the who is not actually a player in the game
   */
  void move(int who, int where);

  String[] getPlayers();

  String getNextPlayer();

  /**
   * Returns the player whose piece is at row/col
   */
  String getPieceAt(int col, int row);

  Status getStatus();

  boolean isGameOver();

}
