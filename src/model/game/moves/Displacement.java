package model.game.moves;

import model.board.Board;
import model.board.BoardMove;
import model.board.Position;

public class Displacement extends GameMove {

  public Displacement(Position initial, Position end) {
    super(initial, end);
  }

  @Override
  public void execute(Board board) {
    board.movePiece( new BoardMove(this.initial, this.end) );
  }
}
