package test.model.board;

import model.board.*;
import model.entities.Piece;
import model.enums.BoardMoveDirection;
import model.enums.Color;
import model.enums.PieceType;
import model.exceptions.PieceInOccupiedPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

  private static Board board;

  @BeforeEach
  public void beforeTest() {
    board = new Board();
  }

  @Test
  public void HasPositionShouldReturnFalseIfNoPieceIsPresent() {
    assertFalse(
        board.hasPiece(new Position('A', 1))
    );
  }

  @Test
  public void ShouldPutPieceAndReturnTrueForPositionHasPiece() {
    Piece whiteKing = new Piece(Color.WHITE, PieceType.KING);

    Position position = new Position('C', 2);

    board.putPiece(position, whiteKing);

    assertTrue(
        board.hasPiece(position)
    );

  }

  @Test
  public void ShouldPutPieceAndReturnItWhenCallingGetPiece() {
    Piece whiteKing = new Piece(Color.WHITE, PieceType.KING);

    Position position = new Position('C', 2);

    board.putPiece(position, whiteKing);

    Piece fetchedPiece = board.getPiece(position);

    assertEquals(whiteKing, fetchedPiece);
  }

  @Test
  public void ShouldThrowExceptionWhenPieceIsPutInOccupiedPosition() {
    Piece whiteKing = new Piece(Color.WHITE, PieceType.KING);
    Piece blackKing = new Piece(Color.BLACK, PieceType.KING);

    Position position = new Position('C', 2);

    board.putPiece(position, whiteKing);

    assertThrows(
        PieceInOccupiedPositionException.class,
        () -> board.putPiece(position, blackKing)
    );
  }

  @Test
  public void ShouldBeAbleToEliminatePiecesCorrectly() {
    Piece whiteKing = new Piece(Color.WHITE, PieceType.KING);

    Position position = new Position('C', 2);

    board.putPiece(position, whiteKing);

    board.eliminatePiece(position);

    assertFalse(board.hasPiece(position));
  }

  @Test
  public void ShouldBeAbleToMovePiecesCorrectly() {
    Piece whiteKing = new Piece(Color.WHITE, PieceType.KING);

    Position initial = new Position('C', 2);
    Position end = new Position('A', 4);

    board.putPiece(initial, whiteKing);

    board.movePiece(
        new BoardMove(initial, end)
    );

    assertFalse(board.hasPiece(initial));
    assertTrue(board.hasPiece(end));

    Piece fetchedPiece = board.getPiece(end);

    assertEquals(fetchedPiece, whiteKing);
  }

  @Test
  public void ShouldNotGoOutOfBoard() {
    Position initialPosition = new Position('A', 0);
    List<BoardDisplacement> displacements = new LinkedList<>();

    displacements.add(new BoardDisplacement(BoardMoveDirection.UP, 7));
    displacements.add(new BoardDisplacement(BoardMoveDirection.RIGHT, 7));
    displacements.add(new BoardDisplacement(BoardMoveDirection.DOWN, 8));
    displacements.add(new BoardDisplacement(BoardMoveDirection.LEFT, 7));

    BoardDisplacementResult displacementResult = Board.executeDisplacements(
        initialPosition,
        displacements
    );

    RawIndex resultIndex = displacementResult.getFinalIndex();
    boolean outOfBoard = displacementResult.isOutOfBoard();

    assertTrue(outOfBoard);
    assertEquals(resultIndex.getVertical(), -1);
    assertEquals(resultIndex.getHorizontal(), 0);

  }

  @Test
  public void ShouldGoOutOfBoard() {
    Position initialPosition = new Position('A', 0);
    List<BoardDisplacement> displacements = new LinkedList<>();

    displacements.add(new BoardDisplacement(BoardMoveDirection.UP, 7));
    displacements.add(new BoardDisplacement(BoardMoveDirection.RIGHT, 7));
    displacements.add(new BoardDisplacement(BoardMoveDirection.DOWN, 7));
    displacements.add(new BoardDisplacement(BoardMoveDirection.LEFT, 7));

    BoardDisplacementResult displacementResult = Board.executeDisplacements(
      initialPosition,
      displacements
    );

    RawIndex resultIndex = displacementResult.getFinalIndex();
    boolean outOfBoard = displacementResult.isOutOfBoard();

    assertFalse(outOfBoard);
    assertEquals(resultIndex.getVertical(), 0);
    assertEquals(resultIndex.getHorizontal(), 0);
  }

  @Test
  public void ShouldGetToTheSamePlaceWhenGoingIncircles() {
    Position initialPosition = new Position('C', 2);
    List<BoardDisplacement> displacements = new LinkedList<>();

    int TOUR_LENGTH=2;

    displacements.add(new BoardDisplacement(BoardMoveDirection.UP, TOUR_LENGTH));
    displacements.add(new BoardDisplacement(BoardMoveDirection.RIGHT, TOUR_LENGTH));
    displacements.add(new BoardDisplacement(BoardMoveDirection.DOWN, TOUR_LENGTH));
    displacements.add(new BoardDisplacement(BoardMoveDirection.LEFT, TOUR_LENGTH));

    BoardDisplacementResult displacementResult = Board.executeDisplacements(
        initialPosition,
        displacements
    );

    RawIndex resultIndex = displacementResult.getFinalIndex();
    boolean outOfBoard = displacementResult.isOutOfBoard();

    assertFalse(outOfBoard);
    assertEquals(resultIndex.getVertical(), 2);
    assertEquals(resultIndex.getHorizontal(), 2);
  }

  @Test
  public void ShouldReturnTrueCallingHasPositionWhenDisplaced() {
    Position initialPosition = new Position('C', 2);
    List<BoardDisplacement> displacements = new LinkedList<>();

    displacements.add(new BoardDisplacement(BoardMoveDirection.UP, 2));
    displacements.add(new BoardDisplacement(BoardMoveDirection.RIGHT, 1));

    BoardDisplacementResult displacementResult = Board.executeDisplacements(
        initialPosition,
        displacements
    );

    boolean hasPosition = board.hasPositionWhenDisplaced(
        initialPosition,
        displacements
    );

    assertTrue(hasPosition);
  }

  @Test
  public void ShouldReturnFalseCallingHasPositionWhenDisplaced() {
    Position initialPosition = new Position('C', 2);
    List<BoardDisplacement> displacements = new LinkedList<>();

    displacements.add(new BoardDisplacement(BoardMoveDirection.LEFT, 3));

    BoardDisplacementResult displacementResult = Board.executeDisplacements(
        initialPosition,
        displacements
    );

    boolean hasPosition = board.hasPositionWhenDisplaced(
        initialPosition,
        displacements
    );

    assertFalse(hasPosition);
  }

  @Test
  public void ShouldBeAbleToPutPiecesOnBoard() {
    Position position = new Position('C', 2);

    Piece whiteKing = new Piece(Color.WHITE, PieceType.KING);

    board.putPiece(position, whiteKing);

    Piece fetchedPiece = board.getPiece(position);

    fetchedPiece.equals(whiteKing);
  }

  @Test
  public void HasPositionWhenDisplacedShouldReturnFalseIfInvalidDisplacement() {
    Piece whiteKing = new Piece(Color.WHITE, PieceType.KING);

    Position position = new Position('C', 4);

    board.putPiece(position, whiteKing);

    List<BoardDisplacement> displacements = new LinkedList<>();
    displacements.add(new BoardDisplacement(BoardMoveDirection.UP, 5));

    boolean hasDisplacement = board.hasPositionWhenDisplaced(
      position,
      displacements
    );

    assertFalse(hasDisplacement);
  }

  @Test
  public void HasPositionWhenDisplacedShouldReturnTrueIfValidDisplacement() {
    Piece whiteKing = new Piece(Color.WHITE, PieceType.KING);

    Position position = new Position('C', 2);

    board.putPiece(position, whiteKing);

    List<BoardDisplacement> displacements = new LinkedList<>();
    displacements.add(new BoardDisplacement(BoardMoveDirection.UP, 4));

    boolean hasDisplacement = board.hasPositionWhenDisplaced(
        position,
        displacements
    );

    assertTrue(hasDisplacement);
  }

  @Test
  public void GetPositionWhenDisplacedShouldReturnPositionIfValidDisplacement() {
    Piece whiteKing = new Piece(Color.WHITE, PieceType.KING);

    Position position = new Position('C', 2);

    board.putPiece(position, whiteKing);

    List<BoardDisplacement> displacements = new LinkedList<>();
    displacements.add(new BoardDisplacement(BoardMoveDirection.UP, 4));

    Position end = board.getPositionWhenDisplaced(
        position,
        displacements
    );

    Position expectedEnd = new Position(
        'C',
        6
    );

    assertEquals(expectedEnd, end);
  }

  @Test
  public void PositionHasPieceOfColorShouldReturnCorrectColor() {
    Piece whiteKing = new Piece(Color.WHITE, PieceType.KING);
    Piece blackKing = new Piece(Color.BLACK, PieceType.KING);

    Position whitePosition = new Position('C', 2);
    board.putPiece(whitePosition, whiteKing);
    Position blackPosition = new Position('C', 6);
    board.putPiece(blackPosition, blackKing);

    assertTrue(
        board.positionHasPieceOfColor(whitePosition, Color.WHITE)
    );
    assertTrue(
        board.positionHasPieceOfColor(blackPosition, Color.BLACK)
    );
  }
}