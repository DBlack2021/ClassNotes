package connectn;

public class ConnectNModelImpl implements ConnectNModel {
  private final int width, height;
  private final int goal; //number in a row to win
  private final int numPlayers;
  private final int[][] board;

  private ConnectNModelImpl(int w, int h, int g, int n) {
    this.width = w;
    this.height = h;
    this.goal = g;
    this.numPlayers = n;
    this.board = new int[width][height];
  }

  public static ConnectNModelBuilder builder() {
    return new ConnectNModelBuilder();
  }

  @Override
  public void move(int who, int where) {

  }

  @Override
  public String[] getPlayers() {
    return new String[0];
  }

  @Override
  public String getNextPlayer() {
    return null;
  }

  @Override
  public String getPieceAt(int col, int row) {
    return null;
  }

  @Override
  public Status getStatus() {
    return null;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  /**
   * To create some customized version of the ConnectNModelImpl
   */
  ///// BUILDER PATTERN! /////
  private static class ConnectNModelBuilder {
    private int w, h, g, n;

    public ConnectNModelBuilder() {
      this.w = 7;
      this.h = 6;
      this.g = 4;
      this.n = 2;
    }

    public ConnectNModelBuilder goal(int g) {
      this.g = g;
      return this;
    }

    public ConnectNModelBuilder numPlayers(int n) {
      this.n = n;
      return this;
    }

    public ConnectNModel build() {
      return new ConnectNModelImpl(this.w, this.h, this.g, this.n);
    }
  }
}

/*
 * USAGE:
 *
 * ConnectNModel c3 = ConnectNModelImpl.builder()
 *                    .goal(3)
 *                    .numPlayers(4)
 *                    .build();
 */

