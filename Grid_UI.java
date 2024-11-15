import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Arrays; // delete later

public class Grid_UI extends JFrame {
    private JPanel gridPanel;
    private int rows = 4;     // Default number of rows
    private int cols = 15;     // Default number of columns
    Color[] pieceColor = new Color[13];

    Pentominos game;

    public Grid_UI() {
        setTitle("Pentomino Solver for 4x15");
        setSize(cols * 60, rows * 80);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create grid panel
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, cols));
        add(gridPanel, BorderLayout.CENTER);

        // Create button to change grid size
        JButton changeSizeButton = new JButton("Change Grid Size");
        add(changeSizeButton, BorderLayout.SOUTH);

        JButton play_game = new JButton("Play Game");
        add(play_game, BorderLayout.NORTH);

        game = new Pentominos(rows, cols);
        populateGrid();

        changeSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String rowInput = JOptionPane.showInputDialog("Enter number of rows:");
                String colInput = JOptionPane.showInputDialog("Enter number of columns:");

                try {
                    int newRows = Integer.parseInt(rowInput);
                    int newCols = Integer.parseInt(colInput);

                    // Updating the rows and columns here
                    rows = newRows;
                    cols = newCols;
                    // to do  1. not allowed less than 3 rows and more than 60 squares on the grid
                    if((rows *  cols) % 5 != 0){
                        JOptionPane.showMessageDialog(null, "Rows and columns must be a multiple of 5");
                        actionPerformed(e);
                        return;
                    }
                    if(rows * cols != 60){
                        JOptionPane.showMessageDialog(null, "Twelve pentominos each 5 squares equals 60");
                        actionPerformed(e);
                        return;
                    }
                    if(rows < 3){
                        JOptionPane.showMessageDialog(null, "Rows must be at least 3");
                        actionPerformed(e);
                        return;
                    }
                    if(cols > rows){
                        setSize(cols * 60, rows * 80);
                    }
                    else{
                        setSize(cols * 80, rows * 60);
                    }
                    game = new Pentominos(rows, cols);
                    setTitle("Pentomino Solver for " + rows + "x" + cols);
                    // Rebuild the grid
                    rebuildGrid();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid integers for size.");
                }
            }
        });

        play_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rows == 3){
                    game.playX();
                }
                game.play(0, 0);
//                game.showBoard();
                populateGrid();
                JOptionPane.showMessageDialog(null, "Solution found in " + game.getMoves() + " moves");

            }
        });
    }


    private void populateGrid() {
        gridPanel.removeAll(); // Clear previous components
        MakeColors();

//        System.out.println(game.board.length);


        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                JPanel square = new JPanel();
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                square.setBackground(pieceColor[game.board[r][c]]);

//                System.out.println(Arrays.toString(Arrays.stream(this.game.board).toArray()));
                gridPanel.add(square);
            }
        }
//        game.showBoard();
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    // Method to rebuild grid when size changes
    private void rebuildGrid() {
        gridPanel.setLayout(new GridLayout(rows, cols));
        populateGrid();
    }


    void MakeColors() {
//        pieceColor = new Color[13];
        pieceColor[0] = Color.white;
        pieceColor[1] = new Color(200,0,0);
        pieceColor[2] = new Color(150,150,255);
        pieceColor[3] = new Color(0,200,200);
        pieceColor[4] = new Color(255,150,255);
        pieceColor[5] = new Color(0,200,0);
        pieceColor[6] = new Color(150,255,255);
        pieceColor[7] = new Color(200,200,0);
        pieceColor[8] = new Color(0,0,200);
        pieceColor[9] = new Color(255,150,150);
        pieceColor[10] = new Color(200,0,200);
        pieceColor[11] = new Color(255,255,150);
        pieceColor[12] = new Color(150,255,150);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Grid_UI frame = new Grid_UI();
                frame.setVisible(true);
            }
        });
    }
}