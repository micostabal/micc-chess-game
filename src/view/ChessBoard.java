package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessBoard {
  private final JPanel gui = new JPanel(new BorderLayout(3, 3));
  private JPanel chessBoard;
  private JButton[][] chessBoardSquares = new JButton[8][8];

  public ChessBoard() {
    super();
  }

  public void initializeGUI () {
    gui.setBorder(new EmptyBorder(5, 5, 5, 5));

    chessBoard = new JPanel(new GridLayout(8, 8));
    chessBoard.setBorder(new LineBorder(Color.BLACK));

    Insets buttonMargin = new Insets(0,0,0,0);
    for(int i=0;i<8;i++) {
      for(int j=0;j<8;j++) {
        JButton newButton = new JButton();
        newButton.setMargin(buttonMargin);
        newButton.setOpaque(true);
        newButton.setContentAreaFilled(true);
        newButton.setBorderPainted(false);

        String imagePath = "./icons/pieces/w_bishop_1x.png";

        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));

        Image img = icon.getImage() ;
        Image newImg = img.getScaledInstance( 45, 45,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon( newImg );

        newButton.setIcon(icon);

        newButton.setBackground( (i+j)%2==0 ? Color.WHITE : Color.BLACK );
        // Color.RED works too!

        chessBoardSquares[i][j] = newButton;
        chessBoard.add(newButton);
      }
    }
    gui.add(chessBoard);
  }

  public JComponent getGui() {
    return gui;
  }

  public JComponent getChessBoard() {
    return chessBoard;
  }
}
