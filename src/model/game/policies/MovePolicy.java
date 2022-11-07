package model.game.policies;

import model.board.Board;
import model.board.Position;
import model.enums.BoardOrientation;
import model.game.moves.GameMove;

import java.util.Map;

public abstract class MovePolicy {

  public MovePolicy() {}

  public abstract Map<Position, GameMove> calculateGameMoves(
      Position position,
      Board board,
      BoardOrientation forwardOrientation
  );

}
