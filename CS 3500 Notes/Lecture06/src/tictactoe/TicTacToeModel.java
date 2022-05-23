package tictactoe;

/**
 * Represent the game of TicTacToe and enforce the rules
 * of playing TicTacToe
 */
public interface TicTacToeModel {

  enum Player {X, O}; //Type is TicTacToeModel.Player outside of this class

  /**
   * Places an X/O in the specified row and col of the board
   * depending on whose turn it is
   * @param row row the symbol is placed (zero-based)
   * @param col col the symbol is placed (zero-based)
   *
   * @throws IllegalStateException when
   * there is already a symbol in row,col OR
   * the game is already over
   * @throws IllegalArgumentException when row and/or col
   * refer to an invalid board location
   */
  void move(int row, int col);

  /*
  Options for return types:

  - char : 'x' and 'o' : a lot of invalid values
  - String: "X" and "O" : like char BUT worse b/c a lot more values, null, ""
  - int/byte: 1 and 2 : invalid values
  - boolean: true/false : not extensible, semantic reasoning can be lost
  - enum Player {X, O} : ref type so an be null, more memory than a primitive data ty, switch
  - class : mutation
   */

  /**
   * Retrieves the next player to play (aka whose turn is it?)
   * @return the player OR null if the game is over
   */
  Player getNextPlayer();

  /*
   * This is OK for a model, but we need to make sure
   * mutating this return value does NOT affect the
   * representation in the model itself
   */
  Player[][] getBoard();

  /**
   * Return the player/symbol at the row,col on the board
   * @param row row from the board (zero-based)
   * @param col col from the board (zero-based)
   * @return an X or an O or null if empty
   * @throws IllegalArgumentException if row or col are &lt; 0 or &gt; 2
   */
  Player getPlayerAt(int row, int col);

  /**
   * Returns if the game is over or not (won/tie)
   * @return true if the game is over and false other
   */
  boolean isGameOver();

  /**
   * Returns who won the game
   * @return X if X won, O if O won, null if a tie
   * @throws IllegalStateException if the game is not over
   */
  Player getWinner();











}






