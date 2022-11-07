package model.board;

import model.entities.Piece;
import model.enums.BoardMoveDirection;
import model.enums.Color;
import model.exceptions.NoPieceInBoardPositionException;
import model.exceptions.PieceInOccupiedPositionException;

import java.util.*;

import static model.constants.BoardConstants.BOARD_DIMENSION;
import static model.constants.BoardConstants.INDEX_CHARACTERS;


public class Board {

  private Map<Position, Optional<Piece>> pieces = new HashMap<>();

  public Board() {
    initBoard();
  }

  public boolean hasPiece(Position position) {
    Optional<Piece> optionalPiece = this.pieces.get(position);
    return !optionalPiece.isEmpty();
  }

  public Piece getPiece(Position position) {
    if (!this.hasPiece(position)) {
      throw new NoPieceInBoardPositionException();
    };
    return this.pieces.get(position).get();
  }

  public void putPiece(Position position, Piece piece) {
    if (piece==null) throw new RuntimeException("Piece does not exist!");
    if (this.hasPiece(position)) throw new PieceInOccupiedPositionException();
    this.pieces.put(position, Optional.of(piece));
  }

  public void eliminatePiece(Position position) {
    if (!this.hasPiece(position)) {
      throw new NoPieceInBoardPositionException();
    };
    this.pieces.put(position, Optional.empty());
  }

  public void movePiece(BoardMove boardMove) {
    Position initial = boardMove.getInitial();
    Position end = boardMove.getEnd();
    Piece toMovePiece = this.getPiece(initial);
    this.eliminatePiece(initial);
    this.putPiece(end, toMovePiece);
  }

  public boolean positionHasPieceOfColor(Position position, Color color) {
    if (!this.hasPiece(position)) return false;
    Piece piece = this.getPiece(position);
    return color.equals(piece.getColor());
  }

  public boolean hasPositionWhenDisplaced(Position initial, List<BoardDisplacement> displacements) {

    BoardDisplacementResult displacementResult = Board.executeDisplacements(
        initial,
        displacements
    );

    return !displacementResult.isOutOfBoard();
  }

  public Position getPositionWhenDisplaced(Position initial, List<BoardDisplacement> displacements) {

    BoardDisplacementResult displacementResult = Board.executeDisplacements(
        initial,
        displacements
    );

    return Position.fromRawIndex(displacementResult.getFinalIndex());
  }

  private void initBoard() {
    for (int i=0;i<BOARD_DIMENSION;i++) {
      for (int j=0;j< INDEX_CHARACTERS.length();j++) {
        Optional<Piece> value = Optional.empty();
        this.pieces.put(
            new Position(INDEX_CHARACTERS.charAt(j), i),
            value
        );
      }
    }
  }

  private List<Piece> getPieces() {
    List<Piece> piecesList = new LinkedList<>();
    for (int i=0;i<BOARD_DIMENSION;i++) {
      for (int j=0;j< INDEX_CHARACTERS.length();j++) {
        Optional<Piece> value = Optional.empty();
        Position position = new Position(INDEX_CHARACTERS.charAt(j), i);
        Optional<Piece> optionalPiece = this.pieces.get(position);
        if (!optionalPiece.isEmpty()) {
          piecesList.add(optionalPiece.get());
        }
      }
    }
    return piecesList;
  }

  public static BoardDisplacementResult executeDisplacements(Position initial, List<BoardDisplacement> displacements) {
    int vertical = initial.getVertical();
    char horizontalChar = initial.getHorizontal();
    int horizontal = INDEX_CHARACTERS.indexOf(horizontalChar);
    boolean outOfBoard=false;
    for (BoardDisplacement displacement: displacements) {

      RawIndex displacementRawIndex = Board.getRawIndexFromDisplacement(
          vertical,
          horizontal,
          displacement
      );

      if ( displacementRawIndex.isValid() ) {
        outOfBoard=true;
      }
      vertical=displacementRawIndex.getVertical();
      horizontal=displacementRawIndex.getHorizontal();
    }
    return new BoardDisplacementResult(
      outOfBoard,
      new RawIndex(vertical, horizontal)
    );
  }

  private static RawIndex getRawIndexFromDisplacement(int vertical, int horizontal, BoardDisplacement displacement) {
    BoardMoveDirection direction = displacement.getMoveDirection();
    int times = displacement.getTimes();
    if (direction==BoardMoveDirection.DOWN) {
      vertical-=times;
    } else if (direction==BoardMoveDirection.UP) {
      vertical+=times;
    } else if (direction==BoardMoveDirection.LEFT) {
      horizontal-=times;
    } else if (direction==BoardMoveDirection.RIGHT) {
      horizontal+=times;
    }
    return new RawIndex(vertical, horizontal);
  }

}
