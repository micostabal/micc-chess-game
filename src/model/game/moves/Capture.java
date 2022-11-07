package model.game.moves;

import model.board.Board;
import model.board.BoardMove;
import model.board.Position;

public class Capture extends GameMove {

  public Capture(Position initial, Position end) {
    super(initial, end);
  }

  @Override
  public void execute(Board board) {
    board.eliminatePiece(this.end);
    board.movePiece(new BoardMove(this.initial, this.end));
  }
}
