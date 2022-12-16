package model.game.moves;

import model.board.Board;
import model.board.BoardMove;
import model.board.Position;
import model.entities.Piece;

public class Capture extends GameMove {

  public Capture(Position initial, Position end) {
    super(initial, end);
  }

  @Override
  public void execute(Board board) {
    Piece toRemovePiece = board.getPiece(this.end);
    board.eliminatePiece(this.end);
    this.removedPieces.add(toRemovePiece);
    board.movePiece(new BoardMove(this.initial, this.end));
  }
}
