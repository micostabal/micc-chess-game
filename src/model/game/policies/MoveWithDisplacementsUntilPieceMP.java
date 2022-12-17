package model.game.policies;

import model.board.Board;
import model.board.BoardDisplacement;
import model.board.BoardDisplacementResult;
import model.board.Position;
import model.entities.Piece;
import model.enums.BoardOrientation;
import model.game.moves.Capture;
import model.game.moves.Displacement;
import model.game.moves.GameMove;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MoveWithDisplacementsUntilPieceMP extends MovePolicy {

  private List<BoardDisplacement> displacements;
  private int limit;

  public MoveWithDisplacementsUntilPieceMP(List<BoardDisplacement> displacements, int limit) {
    this.displacements=displacements;
    this.limit=limit;
  }

  @Override
  public Map<Position, GameMove> calculateGameMoves(Position position, Board board, BoardOrientation forwardOrientation) {
    Map<Position, GameMove> gameMoves = new HashMap<>();
    Position currentPosition = position;

    Piece pieceToMove = board.getPiece(position);

    for (int time=0;time<this.limit;time++) {
      BoardDisplacementResult displacementResult = Board.executeDisplacements(
        currentPosition,
        this.displacements
          .stream()
          .map( (displacement) -> displacement.flipIfNecessary(forwardOrientation) )
          .collect(Collectors.toList())
      );
      if (displacementResult.isOutOfBoard()) break;

      Position newPosition = Position.fromRawIndex(
        displacementResult.getFinalIndex()
      );

      if (board.hasPiece(newPosition)) {
        Piece pieceInPosition = board.getPiece(newPosition);

        if (pieceInPosition.getColor().equals(pieceToMove.getColor())) {
          break;
        } else {
          gameMoves.put(
            newPosition,
            new Capture(position, newPosition)
          );
        }
      } else {
        gameMoves.put(
          newPosition,
          new Displacement(position, newPosition)
        );
      }
      currentPosition = newPosition;
    }

    return gameMoves;
  }
}
