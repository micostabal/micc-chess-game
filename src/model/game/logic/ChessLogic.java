package model.game.logic;

import model.board.Board;
import model.board.BoardDisplacement;
import model.board.BoardMove;
import model.board.Position;
import model.entities.Piece;
import model.enums.BoardMoveDirection;
import model.enums.BoardOrientation;
import model.enums.Color;
import model.enums.PieceType;
import model.game.moves.GameMove;
import model.game.policies.MovePolicy;
import model.game.policies.MoveWithDisplacementsUntilPieceMP;
import model.game.policies.PawnMovePolicy;

import java.util.*;
import java.util.stream.Collectors;

public class ChessLogic {

  private Map<Color, BoardOrientation> directions;
  private Map<PieceType, List<MovePolicy>> piecesLogic;

  public ChessLogic() {
    this.initDirections();
    this.initPiecesLogic();
  }

  public List<GameMove> getValidMoves(Position position, Board board) {
    List<GameMove> validMoves = new LinkedList<>();
    Piece pieceToBeMoved = board
        .getPiece(position);

    for (MovePolicy movePolicy: piecesLogic.get(pieceToBeMoved.getType())) {
      validMoves.addAll(
          movePolicy.calculateGameMoves(
            position,
            board,
            directions.get(pieceToBeMoved.getColor()))
              .values()
              .stream()
              .collect(Collectors.toList())
      );
    }
    return validMoves;
  }

  // TODO: Maybe remove this as fixed
  private void initDirections() {
    this.directions = new EnumMap<Color, BoardOrientation>(Color.class);
    directions.put(Color.WHITE, BoardOrientation.UP);
    directions.put(Color.BLACK, BoardOrientation.DOWN);
  }

  public void updateTurnsAlive(Board board) {
    for (Piece piece: board.getPieces() ) {
      piece.increaseTurnsAlive();
    }
  }

  private void initPiecesLogic() {
    this.piecesLogic = new HashMap<>();

    this.piecesLogic.put(PieceType.PAWN, List.of(new PawnMovePolicy()));

    this.piecesLogic.put(
        PieceType.KING,
        getAllDirectionsBoardDisplacements(1)
            .stream()
            .map( listOfBoardDisplacements ->
                new MoveWithDisplacementsUntilPieceMP(
                    listOfBoardDisplacements,
                    1
                )
            )
            .collect(Collectors.toList())
    );

    this.piecesLogic.put(
        PieceType.QUEEN,
        getAllDirectionsBoardDisplacements(1)
            .stream()
            .map( listOfBoardDisplacements ->
                new MoveWithDisplacementsUntilPieceMP(
                    listOfBoardDisplacements,
                    7
                )
            )
            .collect(Collectors.toList())
    );

    this.piecesLogic.put(
        PieceType.BISHOP,
        getDiagonalBoardDisplacements(1)
            .stream()
            .map( listOfBoardDisplacements ->
                new MoveWithDisplacementsUntilPieceMP(
                    listOfBoardDisplacements,
                    7
                )
            )
            .collect(Collectors.toList())
    );

    this.piecesLogic.put(
        PieceType.ROOK,
        getBasicDirectionsBoardDisplacements(1)
            .stream()
            .map( listOfBoardDisplacements ->
                new MoveWithDisplacementsUntilPieceMP(
                    List.of(listOfBoardDisplacements),
                    7
                )
            )
            .collect(Collectors.toList())
    );

    this.piecesLogic.put(
        PieceType.KNIGHT,
        getKnightBoardDisplacements()
            .stream()
            .map( listOfBoardDisplacements ->
                new MoveWithDisplacementsUntilPieceMP(
                    listOfBoardDisplacements,
                    1
                )
            )
            .collect(Collectors.toList())
    );

  }

  private static List<BoardDisplacement> getBasicDirectionsBoardDisplacements(int length) {
    return List.of(
        new BoardDisplacement(BoardMoveDirection.UP, length),
        new BoardDisplacement(BoardMoveDirection.DOWN, length),
        new BoardDisplacement(BoardMoveDirection.LEFT, length),
        new BoardDisplacement(BoardMoveDirection.RIGHT, length)
    );
  }

  private static List<List<BoardDisplacement>> getDiagonalBoardDisplacements(int length) {
    List<List<BoardDisplacement>> diagonals = new LinkedList<>();

    for (BoardMoveDirection mainDirection: List.of(BoardMoveDirection.UP, BoardMoveDirection.DOWN)) {
      for (BoardMoveDirection sideDirection: List.of(BoardMoveDirection.LEFT, BoardMoveDirection.RIGHT)) {
        diagonals.add(List.of(
            new BoardDisplacement(mainDirection, length),
            new BoardDisplacement(sideDirection, length))
        );
      }
    }

    return diagonals;
  }

  private static List<List<BoardDisplacement>> getAllDirectionsBoardDisplacements(int length) {
    List<List<BoardDisplacement>> allDirections = new LinkedList<>();
    allDirections.addAll(
        getBasicDirectionsBoardDisplacements(length)
            .stream()
            .map(displacement -> List.of(displacement))
            .collect(Collectors.toList())
    );
    for (List<BoardDisplacement> diagonal: getDiagonalBoardDisplacements(length)) {
      allDirections.add(diagonal);
    }
    return allDirections;
  }

  private static List<List<BoardDisplacement>> getKnightBoardDisplacements() {
    List<List<BoardDisplacement>> rookDisplacements = new LinkedList<>();
    for (BoardMoveDirection mainDirection: List.of(BoardMoveDirection.UP, BoardMoveDirection.DOWN)) {
      for (BoardMoveDirection sideDirection: List.of(BoardMoveDirection.LEFT, BoardMoveDirection.RIGHT)) {
        rookDisplacements.add(List.of(
            new BoardDisplacement(mainDirection, 2),
            new BoardDisplacement(sideDirection, 1))
        );

        rookDisplacements.add(List.of(
            new BoardDisplacement(sideDirection, 2),
            new BoardDisplacement(mainDirection, 1))
        );
      }
    }
    return rookDisplacements;
  }

}
