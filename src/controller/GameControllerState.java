package controller;

public class GameControllerState {
  private boolean pieceSelected;
  private int selectedHorizontal;
  private int selectedVertical;

  public int getSelectedHorizontal() {
    return selectedHorizontal;
  }

  public int getSelectedVertical() {
    return selectedVertical;
  }

  public boolean isPieceSelected() {
    return pieceSelected;
  }

  public void setPieceSelected(boolean pieceSelected) {
    this.pieceSelected = pieceSelected;
  }

  public void setSelectedHorizontal(int selectedHorizontal) {
    this.selectedHorizontal = selectedHorizontal;
  }

  public void setSelectedVertical(int selectedVertical) {
    this.selectedVertical = selectedVertical;
  }
}
