package view;

import view.state.ChessBoardStateType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class ChessView extends JFrame {

  public ChessView(String title) {
    super(title);
  }

  public void initialize() {

    this.getContentPane().setLayout(new BorderLayout());

    this.addWindowListener(new java.awt.event.WindowAdapter(){
      public void windowClosing(WindowEvent e){
        System.exit(0);
      }
    });

    JMenu gameMenu = new JMenu("Game Menu");

    JMenuBar menuBar = new JMenuBar();
    JMenuItem newGame = new JMenuItem("New Game");
    JMenuItem loadGame = new JMenuItem("Load Game");
    JMenuItem saveGame = new JMenuItem("Save Game");

    gameMenu.add(newGame);
    gameMenu.add(loadGame);
    gameMenu.add(saveGame);

    menuBar.add(gameMenu);

    this.setJMenuBar(menuBar);

    JToolBar toolBar = new JToolBar();

    JButton loadGameButton = new JButton("Something");
    loadGameButton.setIcon(new ImageIcon(getClass().getResource("./icons/icons8-chessboard-24.png")));
    loadGameButton.setMargin(new Insets(0, 0, 0, 0));
    toolBar.add(loadGameButton);

    toolBar.addSeparator();

    JButton saveGameButton = new JButton("Cool");
    saveGameButton.setIcon(new ImageIcon(getClass().getResource("./icons/icons8-chessboard-24.png")));
    saveGameButton.setMargin(new Insets(0, 0, 0, 0));
    toolBar.add(saveGameButton);

    this.getContentPane().add(toolBar, BorderLayout.NORTH);

    // Left panel
    JList list=new JList();
    JScrollPane scrollPaneLeft=new JScrollPane(list);
    JButton openSelectedButton=new JButton("Abrir");

    JPanel leftPanel=new JPanel(new BorderLayout());
    leftPanel.add(scrollPaneLeft,BorderLayout.CENTER);
    leftPanel.add(openSelectedButton,BorderLayout.SOUTH);

    // Right panel

    ChessBoard chessBoard = new ChessBoard();
    chessBoard.setChessBoardState(ChessBoardStateType.ACTIVE);
    JScrollPane chessBoardPaneRight = new JScrollPane(chessBoard.getGui());

    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    splitPane.setRightComponent(chessBoardPaneRight);
    splitPane.setLeftComponent(leftPanel);
    this.getContentPane().add(splitPane, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    ChessView chessView = new ChessView("MICC Chess Desktop Application");
    chessView.initialize();

    chessView.pack();
    chessView.setSize(700, 650);
    chessView.setLocation(100, 100);
    chessView.setVisible(true);
  }

}
