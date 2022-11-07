package model.board;

public class BoardDisplacementResult {
  private final boolean outOfBoard;
  private final RawIndex finalIndex;

  public BoardDisplacementResult(boolean oob, RawIndex fi) {
    this.finalIndex=fi;
    this.outOfBoard=oob;
  }

  public boolean isOutOfBoard() {
    return outOfBoard;
  }

  public RawIndex getFinalIndex() {
    return finalIndex;
  }
}
