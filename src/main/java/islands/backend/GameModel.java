package islands.backend;

/**
 * Class to model the play of the game
 *
 */
public class GameModel {
    Integer[][] playboard;
    public int size;

    public static final boolean WHITE = true;
    public static final boolean BLACK = false;

    /**
     * Construct a game with given sizexsize and an empty game board
     * @param sz the square size of the board
     */
    public GameModel(int sz) {
        this.size = sz;
        playboard = new Integer[sz][sz];


    }

    /**
     * Can a play be made at position row, col
     * @param row the row in question
     * @param col the col in question
     * @return true if row, col is empty, false o.w.
     * @throws IllegalArgumentException for invalid row and col
     */
    public boolean canPlay(int row, int col) {
        if(row < 0|| row >= playboard.length|| col < 0|| col >= playboard.length){
            throw new IllegalArgumentException("Invalid Row or Column");
        }
        if (playboard[row][col] == null){
            playboard[row][col] = 1;
            return true;
        }
        else return false;
    }


    /**
     * play a piece and report if the game is over (true) false, otherwise
     * @param row the row where a piece is played
     * @param col the col where a piece is played
     * @param clr -1 for WHITE and 1 for BLACK
     * @return true if the game is over and false otherwise
     * @throws IllegalArgumentException for invalid row and col
     */
    public boolean makePlay(int row, int col, boolean clr) {
        return false;
    }

    /**
     * Return the score for white
     * @return white score
     */
    public int whiteScore() {
        return 0;
    }

    /**
     * return the score for black
     * @return black score
     */
    public int blackScore() {
        return 0;
    }
}
