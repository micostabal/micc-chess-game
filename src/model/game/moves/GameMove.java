package model.game.moves;

import model.board.Board;
import model.board.Position;

public abstract class GameMove {

  protected final Position initial;
  protected final Position end;

  public GameMove(Position initial, Position end) {
    this.initial=initial;
    this.end=end;
  }

  public Position getInitial() {
    return this.initial;
  };

  public Position getEnd() {
    return this.end;
  };

  public abstract void execute(Board board);
}
