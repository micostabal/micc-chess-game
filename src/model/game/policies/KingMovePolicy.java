package model.game.policies;

import model.board.Board;
import model.board.Position;
import model.enums.BoardOrientation;
import model.game.moves.GameMove;

import java.util.HashMap;
import java.util.Map;

public class KingMovePolicy extends MovePolicy {

  public KingMovePolicy() {
    super();
  }

  @Override
  public Map<Position, GameMove> calculateGameMoves(
      Position position,
      Board board,
      BoardOrientation forwardOrientation
  ) {
    return new HashMap<Position, GameMove>();
  }
}
