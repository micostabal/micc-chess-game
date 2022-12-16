package model.game.policies;

import model.board.Board;
import model.board.BoardDisplacement;
import model.board.BoardDisplacementResult;
import model.board.Position;
import model.entities.Piece;
import model.enums.BoardMoveDirection;
import model.enums.BoardOrientation;
import model.enums.Color;
import model.game.moves.Capture;
import model.game.moves.Displacement;
import model.game.moves.GameMove;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PawnMovePolicy extends MovePolicy {

  public PawnMovePolicy() {};

  public boolean pawnHasMovedBefore(Position position, Board board) {
    return board.getPiece(position).getMovesSoFar()>0;
  }

  @Override
  public Map<Position, GameMove> calculateGameMoves(Position position, Board board, BoardOrientation forwardOrientation) {
    Map<Position, GameMove> gameMoves = new HashMap<>();

    Piece thisPawn = board.getPiece(position);

    for (int stepSize=1;stepSize<=2;stepSize++) {
      if (stepSize==2 && this.pawnHasMovedBefore(position, board)) break;
      BoardDisplacement stepDisplacement = new BoardDisplacement(BoardMoveDirection.UP, stepSize)
          .flipIfNecessary(forwardOrientation);
      BoardDisplacementResult stepDisplacementResult = Board.executeDisplacements(
          position,
          List.of(stepDisplacement)
      );
      if (!stepDisplacementResult.isOutOfBoard()) {
        Position oneStepResultingPosition = stepDisplacementResult.getFinalIndex().toPosition();
        if (!board.hasPiece(oneStepResultingPosition)) {
          gameMoves.put(
              oneStepResultingPosition,
              new Displacement(position, oneStepResultingPosition)
          );
        };
      }
    }

    BoardDisplacement forwardDisplacement = new BoardDisplacement(BoardMoveDirection.UP, 1)
        .flipIfNecessary(forwardOrientation);
    List<BoardDisplacement> sideDisplacements = List.of(
        new BoardDisplacement(BoardMoveDirection.LEFT,1).flipIfNecessary(forwardOrientation),
        new BoardDisplacement(BoardMoveDirection.RIGHT,1).flipIfNecessary(forwardOrientation)
    );
    for (BoardDisplacement sideDisplacement: sideDisplacements) {
      BoardDisplacementResult diagonalDisplacementResult = Board.executeDisplacements(
          position,
          List.of(forwardDisplacement, sideDisplacement)
      );
      if (!diagonalDisplacementResult.isOutOfBoard()) {
        Position diagonalResultingPosition = diagonalDisplacementResult
            .getFinalIndex()
            .toPosition();
        if (board.positionHasPieceOfColor(diagonalResultingPosition, Color.getOppositeColor(thisPawn.getColor()))) {
          gameMoves.put(
              diagonalResultingPosition,
              new Capture(position, diagonalResultingPosition)
          );
        }
      }
    };

    return gameMoves;
  }
}
