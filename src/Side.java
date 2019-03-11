public class Side {
    static final int ROWSIZE = 10;
    static final int COLUMNSIZE = 10;

    static final int CARRIERSIZE = 5;
    static final int BATTLESHIPSIZE = 4;
    static final int CRUISERSIZE = 3;
    static final int SUBMARINESIZE = 3;
    static final int DESTROYERSIZE = 2;

    Point[][] board = new Point[ROWSIZE][COLUMNSIZE];
    int hitsRemaining = CARRIERSIZE + BATTLESHIPSIZE + CRUISERSIZE + SUBMARINESIZE + DESTROYERSIZE;

    public Side(){
        for (int i = 0; i < ROWSIZE; i++)
            for (int j = 0; j < COLUMNSIZE; j++)
                board[i][j] = new Point();
    }

    public Point[][] getBoard(){
        return board;
    }

    public void printBoard(){
        char letter = 'A';
        System.out.print("  ");
        for (int top = 0; top < ROWSIZE; top++) {
            System.out.print(letter + " ");
            letter++;
        }
        System.out.println();

        for (int i = 0; i < ROWSIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < COLUMNSIZE; j++)
                System.out.print(board[i][j].getPointOnScreen() + " ");
            System.out.println();
        }
    }

    public void takeAHit(){
        hitsRemaining--;
    }

    public int getHitsRemaining(){
        return hitsRemaining;
    }

    public boolean isAlive() { return hitsRemaining > 0;}

    public void resetCharacters(){
        for (int i = 0; i < ROWSIZE; i++)
            for (int j = 0; j < COLUMNSIZE; j++)
                board[i][j].setEmptyChar();
    }
}
