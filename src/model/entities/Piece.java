package model.entities;

import model.enums.Color;
import model.enums.PieceType;

import java.util.UUID;

public class Piece {

  private UUID id;
  private Color color;
  private PieceType type;
  private int turnsAlive;
  private int movesSoFar;

  public void setType(PieceType newType) {
    this.type=newType;
  }

  public Color getColor() {return this.color;}

  public PieceType getType() {return this.type;}

  public Piece(Color color, PieceType type) {
    this.id=UUID.randomUUID();
    this.type=type;
    this.color=color;
    this.turnsAlive=0;
    this.movesSoFar=0;
  }

  public boolean equals(Object o) {
    return ((Piece)o).getColor()==this.getColor()
        && ((Piece)o).getType()==this.getType()
        && ((Piece)o).getId().equals(this.getId());
  }

  public void increaseTurnsAlive() {
    this.turnsAlive++;
  }

  public void increaseMovesSoFar() {
    this.movesSoFar++;
  }

  public UUID getId() {
    return id;
  }

  public int getTurnsAlive() {
    return turnsAlive;
  }

  public int getMovesSoFar() {
    return movesSoFar;
  }
}
