package model.game.moves;

import model.board.Board;
import model.board.Position;
import model.entities.Piece;

import java.util.LinkedList;
import java.util.List;

public abstract class GameMove {

  protected final Position initial;
  protected final Position end;
  protected final List<Piece> removedPieces = new LinkedList<>();

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

  public List<Piece> getRemovedPieces() {
    return removedPieces;
  }

  public abstract void execute(Board board);
}
