import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TictactGAME implements ActionListener {
    int boardWidth = 600;
    int boardHeight = 600;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;

    TictactGAME(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        // Buttons banana
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.addActionListener(this);
            }
        }
    }

    public void actionPerformed(ActionEvent e){
        if(gameOver) return;
        JButton tile = (JButton) e.getSource();
        
        if(tile.getText().equals("")){
            tile.setText(currentPlayer);
            checkWinner();
            
            if(!gameOver){
                currentPlayer = currentPlayer.equals(playerX)? playerO : playerX;
                textLabel.setText(currentPlayer + "'s turn");
            }
        }
    }

    void checkWinner(){
        // 1. Check rows
        for(int r = 0; r < 3; r++){
            if(board[r][0].getText().equals("")) continue;
            if(board[r][0].getText().equals(board[r][1].getText()) && 
               board[r][1].getText().equals(board[r][2].getText())){
                for(int i = 0; i < 3; i++) setWinner(board[r][i]);
                return;
            }
        }
        
        // 2. Check columns
        for(int c = 0; c < 3; c++){
            if(board[0][c].getText().equals("")) continue;
            if(board[0][c].getText().equals(board[1][c].getText()) && 
               board[1][c].getText().equals(board[2][c].getText())){
                for(int i = 0; i < 3; i++) setWinner(board[i][c]);
                return;
            }
        }
        
        // 3. Check diagonals
        if(!board[0][0].getText().equals("") && 
           board[0][0].getText().equals(board[1][1].getText()) && 
           board[1][1].getText().equals(board[2][2].getText())){
            for(int i = 0; i < 3; i++) setWinner(board[i][i]);
            return;
        }
        if(!board[0][2].getText().equals("") && 
           board[0][2].getText().equals(board[1][1].getText()) && 
           board[1][1].getText().equals(board[2][0].getText())){
            setWinner(board[0][2]); 
            setWinner(board[1][1]); 
            setWinner(board[2][0]);
            return;
        }
        
        // 4. Check Tie
        boolean tie = true;
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                if(board[r][c].getText().equals("")) tie = false;
            }
        }
        if(tie){
            textLabel.setText("Tie!");
            gameOver = true;
        }
    }

    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
        gameOver = true;
    }

    public static void main(String[] args){
        new TictactGAME();
    }
}