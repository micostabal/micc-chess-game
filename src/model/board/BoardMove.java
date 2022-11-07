package model.board;


public class BoardMove {
  private final Position initial;
  private final Position end;

  public Position getInitial() {return this.initial;}
  public Position getEnd() {return this.end;}

  public BoardMove(Position initial, Position end) {
    this.initial=initial;
    this.end=end;
  }
}
