import java.util.Arrays;
import java.util.Collections;

public class Pentominos {

    int rows = 4;
    int cols = 15; // default size

    int[][] board = new int[rows][cols];
    boolean[] used = new boolean[13];
    int moves = 0;
    int placed_parts = 0;

    private boolean shuffled = false;
    private boolean solved = false;

    public Pentominos(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.board = new int[rows][cols];
         // fix later
    }

    public Pentominos(boolean shuffled) {
        this.shuffled = shuffled;
        Collections.shuffle(Arrays.asList(pieces));
    }

    public Pentominos(){
        this.shuffled = false;
    }

    private final int[][] pieces = {
            { 1, 0,1,0,2,0,3,0,4 },
            { 1, 1,0,2,0,3,0,4,0 },

            { 2, 1,-1,1,0,1,1,2,0 },

            { 3, 0,1,1,0,2,-1,2,0 },
            { 3, 1,0,1,1,1,2,2,2 },
            { 3, 0,1,1,1,2,1,2,2 },
            { 3, 1,-2,1,-1,1,0,2,-2 },

            { 4, 1,0,2,0,2,1,2,2 },
            { 4, 0,1,0,2,1,0,2,0 },
            { 4, 1,0,2,-2,2,-1,2,0 },
            { 4, 0,1,0,2,1,2,2,2 },

            { 5, 0,1,0,2,1,1,2,1 },
            { 5, 1,-2,1,-1,1,0,2,0 },
            { 5, 1,0,2,-1,2,0,2,1 },
            { 5, 1,0,1,1,1,2,2,0 },

            { 6, 1,0,1,1,2,1,2,2 },
            { 6, 1,-1,1,0,2,-2,2,-1 },
            { 6, 0,1,1,1,1,2,2,2 },
            { 6, 0,1,1,-1,1,0,2,-1 },

            { 7, 0,1,0,2,1,0,1,2 },
            { 7, 0,1,1,1,2,0,2,1 },
            { 7, 0,2,1,0,1,1,1,2 },
            { 7, 0,1,1,0,2,0,2,1 },

            { 8, 1,0,1,1,1,2,1,3 },
            { 8, 1,0,2,0,3,-1,3,0 },
            { 8, 0,1,0,2,0,3,1,3 },
            { 8, 0,1,1,0,2,0,3,0 },
            { 8, 0,1,1,1,2,1,3,1 },
            { 8, 0,1,0,2,0,3,1,0 },
            { 8, 1,0,2,0,3,0,3,1 },
            { 8, 1,-3,1,-2,1,-1,1,0 },

            { 9, 0,1,1,-2,1,-1,1,0 },
            { 9, 1,0,1,1,2,1,3,1 },
            { 9, 0,1,0,2,1,-1,1,0 },
            { 9, 1,0,2,0,2,1,3,1 },
            { 9, 0,1,1,1,1,2,1,3 },
            { 9, 1,0,2,-1,2,0,3,-1 },
            { 9, 0,1,0,2,1,2,1,3 },
            { 9, 1,-1,1,0,2,-1,3,-1 },

            { 10, 1,-2,1,-1,1,0,1,1 },
            { 10, 1,-1,1,0,2,0,3,0 },
            { 10, 0,1,0,2,0,3,1,1 },
            { 10, 1,0,2,0,2,1,3,0 },
            { 10, 0,1,0,2,0,3,1,2 },
            { 10, 1,0,1,1,2,0,3,0 },
            { 10, 1,-1,1,0,1,1,1,2 },
            { 10, 1,0,2,-1,2,0,3,0 },

            { 11, 1,-1,1,0,1,1,2,1 },
            { 11, 0,1,1,-1,1,0,2,0 },
            { 11, 1,0,1,1,1,2,2,1 },
            { 11, 1,0,1,1,2,-1,2,0 },
            { 11, 1,-2,1,-1,1,0,2,-1 },
            { 11, 0,1,1,1,1,2,2,1 },
            { 11, 1,-1,1,0,1,1,2,-1 },
            { 11, 1,-1,1,0,2,0,2,1 },

            { 12, 0,1,1,0,1,1,2,1 },
            { 12, 0,1,0,2,1,0,1,1 },
            { 12, 1,0,1,1,2,0,2,1 },
            { 12, 0,1,1,-1,1,0,1,1 },
            { 12, 0,1,1,0,1,1,1,2 },
            { 12, 1,-1,1,0,2,-1,2,0 },
            { 12, 0,1,0,2,1,1,1,2 },
            { 12, 0,1,1,0,1,1,2,0 }
    };

    public int getMoves(){
        return this.moves;
    }

    public void playX(){
        this.used[2] = true;
        this.placed_parts++;
        this.board[0][17] = 2;
        this.board[1][16] = 2;
        this.board[1][17] = 2;
        this.board[1][18] = 2;
        this.board[2][17] = 2;
    }

    public void showBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean canPlace(int piece, int row, int col){
        if(this.board[row][col] != 0){
            return false;
        }
        for(int i = 1; i < 9; i += 2){
            if(     row + pieces[piece][i] < 0 || row + pieces[piece][i] >= rows // checks for rows that are out of the boundary
                    || col + pieces[piece][i+1] < 0 || col + pieces[piece][i+1] >= cols){ // the same but for rows
                return false;
            }
            else if(this.board[row + pieces[piece][i]][col + pieces[piece][i + 1]] != 0){
                return false;
            }
        }
        return true;
    }

    public void removePiece(int piece, int row, int col){
        this.board[row][col] = 0;
        for(int i = 1; i < 9; i += 2){
            this.board[row + pieces[piece][i]][col + pieces[piece][i + 1]] = 0;
        }
    }

    public void play(int row, int col){

        if(this.solved) return;

        for(int piece = 0; piece < pieces.length; piece++){

            if(used[pieces[piece][0]] == false){

                if(!canPlace(piece, row, col)){
                    continue;
                }

                used[pieces[piece][0]] = true;
                placed_parts++;
                this.board[row][col] = pieces[piece][0];
                for(int i = 1; i < 9; i += 2){
                    this.board[row + pieces[piece][i]][col + pieces[piece][i + 1]] = pieces[piece][0];
                }
                moves++;

                if(placed_parts == 12){
                    System.out.println("Solution found in " + moves + " moves");
                    showBoard();
                    this.solved = true;
//                    System.exit(1);
                    return;
                }
                else{
                    int next_col = col;
                    int next_row = row;
                    while(true){
                        if(this.board[next_row][next_col] != 0){
                            next_col++;
                            if(next_col == cols){
                                next_col = 0;
                                next_row++;
                                if(next_row == rows){
                                    System.out.println("Beyond the board");
                                }
                            }
                        }
                        else{
                            break;
                        }
                    }

                    play(next_row, next_col);
                }
                if(!this.solved){
                    removePiece(piece, row, col);
                    used[pieces[piece][0]] = false;
                    placed_parts--;
                }

            }
        }
    }

//    public static void main(String[] args) {
//        Pentominos p = new Pentominos();
//
//        p.showBoard();
//
//        p.play(0, 0);
//
//
//    }
}