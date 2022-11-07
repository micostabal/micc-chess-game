package test.model.entities;

import model.entities.Piece;
import model.enums.Color;
import model.enums.PieceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PieceTest {

  @Test
  public void shouldBeAbleToCreateAllPieces() {
    Piece piece;
    int soFar=0;
    for (Color color: Color.values()) {
      for (PieceType type: PieceType.values() ) {
        piece = new Piece(color, type);
        assertNotEquals(null, piece);
        soFar++;
      }
    }
    assertEquals(12, soFar);
  }
}