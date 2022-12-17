package test.model.game.logic;

import model.board.Board;
import model.board.Position;
import model.entities.Piece;
import model.enums.Color;
import model.enums.PieceType;
import model.game.logic.ChessLogic;
import model.game.moves.Capture;
import model.game.moves.Displacement;
import model.game.moves.GameMove;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChessLogicTest {

  private Board board;
  private static ChessLogic chessLogic;

  @BeforeEach
  public void initTests() {
    board = new Board();
  }

  @BeforeAll
  public static void initChessLogic() {
    chessLogic = new ChessLogic();
  }

  @Test
  public void shouldHaveSoundPawnLogic() {
    Position position = new Position('A', 2);
    Position opponentPosition = new Position('B', 3);

    Piece pawn = new Piece(Color.WHITE, PieceType.PAWN);
    pawn.increaseMovesSoFar();
    Piece opponentPiece = new Piece(Color.BLACK, PieceType.PAWN);
    board.putPiece(position, pawn);
    board.putPiece(opponentPosition, opponentPiece);

    List<GameMove> possibleMoves = chessLogic.getValidMoves(position, board);

    assertEquals(2, possibleMoves.size());

    GameMove captureMove = possibleMoves.stream()
        .filter(gameMove -> gameMove.getEnd().equals(opponentPosition) )
        .findAny()
        .orElse(null);

    MatcherAssert.assertThat(captureMove,instanceOf(Capture.class));

    GameMove displacementMove = possibleMoves.stream()
        .filter(gameMove -> gameMove.getEnd().equals(new Position('A', 3)) )
        .findAny()
        .orElse(null);

    MatcherAssert.assertThat(displacementMove,instanceOf(Displacement.class));
  }

  @Test
  public void shouldHaveSoundKingLogic() {
    Position position = new Position('C', 2);
    Set<Position> expectedPositions = Set.of(
        new Position('C', 1),
        new Position('C', 3),
        new Position('B', 1),
        new Position('B', 2),
        new Position('B', 3),
        new Position('D', 1),
        new Position('D', 2),
        new Position('D', 3)
    );

    Piece king = new Piece(Color.WHITE, PieceType.KING);
    board.putPiece(position, king);

    List<GameMove> possibleMoves = chessLogic.getValidMoves(position, board);

    assertEquals(8, possibleMoves.size());

    Set<Position> actualEndPositions = possibleMoves
        .stream()
        .map( validMove -> validMove.getEnd() )
        .collect(Collectors.toSet());

    assertEquals(expectedPositions, actualEndPositions);
  }

  @Test
  public void shouldHaveSoundQueenLogic() {
    Position position = new Position('A', 0);

    Piece queen = new Piece(Color.WHITE, PieceType.QUEEN);
    board.putPiece(position, queen);

    List<GameMove> possibleMoves = chessLogic.getValidMoves(position, board);

    assertEquals(21, possibleMoves.size());
  }

  // TODO
  @Test
  public void shouldHaveSoundBishopLogic() {}

  @Test
  public void shouldHaveSoundRookLogic() {}

  @Test
  public void shouldHaveSoundKnightLogic() {}
}
