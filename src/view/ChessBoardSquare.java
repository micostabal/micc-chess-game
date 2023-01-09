package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ChessBoardSquare extends JButton {
  private boolean isClicked;
  private int vertical;
  private int horizontal;

  public ChessBoardSquare(int vertical, int horizontal) {
    super();
    this.isClicked=false;
    this.vertical=vertical;
    this.horizontal=horizontal;
  }

  public void initialize(ChessBoard chessBoard) {
    setMargin(new Insets(0,0,0,0));
    setOpaque(true);
    setContentAreaFilled(true);
    setBorderPainted(false);

    String imagePath = "./icons/pieces/w_bishop_1x.png";

    if ((vertical+horizontal)>5) {
      ImageIcon icon = new ImageIcon(
          new BufferedImage(45, 45, BufferedImage.TYPE_INT_ARGB));
      this.setIcon(icon);
    } else {
      ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));

      Image img = icon.getImage() ;
      Image newImg = img.getScaledInstance( 45, 45,  java.awt.Image.SCALE_SMOOTH ) ;
      icon = new ImageIcon( newImg );
      this.setIcon(icon);
    }
    setBackground( (vertical+horizontal)%2==0 ? Color.WHITE : Color.BLACK );

    this.addActionListener(new SquareClickActionListener(chessBoard, vertical, horizontal));
  }



  public boolean isClicked() {
    return isClicked;
  }

  public void setClicked(boolean clicked) {
    isClicked = clicked;
  }
}
