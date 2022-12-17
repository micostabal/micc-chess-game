package test.model.games.policies;

import model.board.BoardDisplacement;
import model.board.Position;
import model.entities.Piece;
import model.enums.BoardMoveDirection;
import model.enums.Color;
import model.enums.PieceType;
import model.game.moves.Displacement;
import model.game.moves.GameMove;
import model.game.moves.Capture;
import model.game.policies.MovePolicy;
import model.game.policies.PawnMovePolicy;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;

public class PawnMovePolicyTest extends BaseMovePolicyTest {

  @Test
  public void ShouldGenerateTwoDisplacementsInTheInitialSetting() {
    LinkedList<BoardDisplacement> displacements = new LinkedList<>();
    displacements.add(new BoardDisplacement(BoardMoveDirection.UP, 1));

    Piece opponentPiece = new Piece(Color.BLACK, PieceType.PAWN);
    this.board.putPiece(new Position('B', 2), opponentPiece);

    MovePolicy pawnMovePolicy = new PawnMovePolicy();

    Map<Position, GameMove> calculatedGameMoves = pawnMovePolicy.calculateGameMoves(
        this.position,
        this.board,
        this.orientation
    );

    GameMove firstDisplacement = calculatedGameMoves.get(new Position('C', 2));
    GameMove secondDisplacement = calculatedGameMoves.get(new Position('C', 3));
    GameMove leftCapture = calculatedGameMoves.get(new Position('B', 2));

    MatcherAssert.assertThat(leftCapture,instanceOf(Capture.class));
    MatcherAssert.assertThat(firstDisplacement, instanceOf(Displacement.class));
    MatcherAssert.assertThat(secondDisplacement,instanceOf(Displacement.class));

  }

}
