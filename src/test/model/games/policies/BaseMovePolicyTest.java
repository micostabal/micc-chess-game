package test.model.games.policies;

import model.board.Board;
import model.board.Position;
import model.entities.Piece;
import model.enums.BoardOrientation;
import model.enums.Color;
import model.enums.PieceType;
import org.junit.jupiter.api.BeforeEach;

public class BaseMovePolicyTest {

  public Board board;
  public Piece piece;
  public Position position;
  public BoardOrientation orientation = BoardOrientation.UP;

  @BeforeEach
  public void initTests() {
    this.board = new Board();
    this.position = new Position('A', 0);
    this.piece = new Piece(Color.WHITE, PieceType.KING);
    this.board.putPiece(this.position, this.piece);
  }

}
