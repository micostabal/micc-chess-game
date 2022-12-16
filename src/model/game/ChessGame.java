package model.game;

import model.board.Board;
import model.board.Position;
import model.entities.Piece;
import model.enums.Color;
import model.game.logic.ChessLogic;
import model.game.moves.GameMove;

public class ChessGame {

  private ChessLogic chessLogic;
  private Board board;
  private Color currentPlayerTurn;

  private void switchCurrentPlayerTurn() {
    this.currentPlayerTurn=Color.getOppositeColor(
        this.currentPlayerTurn
    );
  }

  public void playMove(GameMove move) {
    Position initialPosition = move.getInitial();
    Piece piece = this.board.getPiece(initialPosition);

    move.execute(this.board);

    piece.increaseMovesSoFar();

    this.switchCurrentPlayerTurn();
  }


}
