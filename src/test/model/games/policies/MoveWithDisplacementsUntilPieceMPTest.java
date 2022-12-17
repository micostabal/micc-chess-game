package test.model.games.policies;

import model.board.BoardDisplacement;
import model.board.Position;
import model.enums.BoardMoveDirection;
import model.game.moves.Displacement;
import model.game.moves.GameMove;
import model.game.policies.MovePolicy;
import model.game.policies.MoveWithDisplacementsUntilPieceMP;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveWithDisplacementsUntilPieceMPTest extends BaseMovePolicyTest {

  @Test
  public void ShouldBeAbleToGiveAllPossibleDisplacements() {
    LinkedList<BoardDisplacement> displacements = new LinkedList<>();
    displacements.add(new BoardDisplacement(BoardMoveDirection.UP, 1));

    MovePolicy movePolicy = new MoveWithDisplacementsUntilPieceMP(
      displacements,
      7
    );

    Map<Position, GameMove> calculatedGameMoves = movePolicy.calculateGameMoves(
      this.position,
      this.board,
      this.orientation
    );

    assertEquals(7, calculatedGameMoves.keySet().size());

    for (Position movePosition : calculatedGameMoves.keySet()) {

      GameMove currentGameMove = calculatedGameMoves.get(movePosition);

      assertEquals(currentGameMove.getInitial(), this.position);
      assertEquals(currentGameMove.getEnd(), movePosition);
      assertTrue(currentGameMove instanceof Displacement);
    }

  }
}
