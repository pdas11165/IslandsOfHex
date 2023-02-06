package islands.backend;

/**
 * Class to model the play of the game
 *
 */
public class GameModel {
    public int size;
    public int whiteScores;
    public int blackScores;
    public int[] parent;
    public int[] rank;
    Integer[][] playboard;

    public static final boolean WHITE = true;
    public static final boolean BLACK = false;

    /**
     * Construct a game with given sizexsize and an empty game board
     * @param sz the square size of the board
     */
    public GameModel(int sz) {
        this.size = sz;
        playboard = new Integer[sz][sz];
        parent = new int[sz*sz];
        rank = new int[sz*sz];
        for(int i = 0 ; i< sz*sz; i++){
            parent[i] = i;
            rank[i] = 1;
        }


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
        int clrValue = clr == WHITE ? 1 : -1;
        if (row < 0 || row >= playboard.length || col < 0 || col >= playboard.length) {
            throw new IllegalArgumentException("Invalid Row or Column");
        }

        playboard[row][col] = clrValue;

        int[][] directions = {{-1, -1}, {-1, 0}, {0, -1}, {0, 1}, {1, 0}, {1, 1}};
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (newRow >= 0 && newRow < playboard.length && newCol >= 0 && newCol < playboard.length && playboard[newRow][newCol] != null) {
                int root1 = find(newRow * playboard.length + newCol);
                int root2 = find(row * playboard.length + col);
                if (playboard[newRow][newCol] == 1 && clrValue == -1) {
                    blackScores++;}
                if (root1 != root2) {
                    parent[root1] = root2;
                    rank[root2] += rank[root1];
                    if (clr == WHITE) {
                        whiteScores--;
                    } else {
                        blackScores--;
                    }
                }
            }
        }

        if (clr == WHITE) {
            whiteScores++;
        } else {
            blackScores++;
        }
        int root = find(row * playboard.length + col);
        boolean gameOver = false;

        if (clrValue == 1) {
            gameOver = rank[root] == playboard.length;
            if (gameOver) {
                whiteScores = 1;
            }
        } else {
            gameOver = rank[root] == playboard.length;
            if (gameOver) {
                blackScores = 1;
            }
        }
        return gameOver;
    }



    /**
     * Return the score for white
     * @return white score
     */
    public int whiteScore() {
        return whiteScores;
    }


    /**
     * return the score for black
     * @return black score
     */
    public int blackScore() {
        return blackScores;
    }

private int find(int index){
        while(index != parent[index]){
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }

    private void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ){
            return;
        }
        if (rank[rootP] < rank[rootQ]){
            parent[rootP] = rootQ;
            rank[rootQ] += rank[rootP];
        }
        else{
            parent[rootQ] = rootP;
            rank[rootP] += rank[rootQ];
        }
    }


}
