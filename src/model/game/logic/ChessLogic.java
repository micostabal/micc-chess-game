package model.game.logic;

import model.board.Board;
import model.board.BoardMove;
import model.board.Position;
import model.enums.BoardOrientation;
import model.enums.Color;
import model.enums.PieceType;
import model.game.moves.GameMove;

import java.util.EnumMap;
import java.util.Map;

public class ChessLogic {

  private Map<Color, BoardOrientation> directions;
  private Map<PieceType, PieceMovesLogic> piecesLogic;

  public ChessLogic() {
    this.initDirections();
    this.initPiecesLogic();
  }

  public Map<BoardMove, GameMove> getValidMoves(Position position, Board board) {
    return null;
  }

  private void initDirections() {
    this.directions = new EnumMap<Color, BoardOrientation>(Color.class);
    directions.put(Color.WHITE, BoardOrientation.UP);
    directions.put(Color.BLACK, BoardOrientation.DOWN);
  }

  private void initPiecesLogic() {
    // Logic for setting logic of pieces
  }

}
