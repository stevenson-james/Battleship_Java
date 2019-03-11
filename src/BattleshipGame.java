import java.util.Scanner;

public class BattleshipGame {
    static final int ROWSIZE = 10;
    static final int COLUMNSIZE = 10;

    static final int CARRIERSIZE = 5;
    static final int BATTLESHIPSIZE = 4;
    static final int CRUISERSIZE = 3;
    static final int SUBMARINESIZE = 3;
    static final int DESTROYERSIZE = 2;

    static final char HITCHAR = 'X';

    Side player1 = new Side();
    Side player2 = new Side();
    Scanner input = new Scanner(System.in);

    BattleshipGame(){}

    public void runGame(){
        player1 = setBoardForPlayer();
        player1.resetCharacters();
        //player2 = setBoardForPlayer();
        //player2.resetCharacters();

        while(player1.isAlive() && player2.isAlive()) {
            System.out.println("Player 1 Turn");
            player2.printBoard();
            shootAtShips(player2);

            System.out.println("Player 2 Turn");
            player1.printBoard();
            shootAtShips(player1);
        }

        if (!player1.isAlive() && !player2.isAlive())
            System.out.println("It's a tie!");
        else if (!player1.isAlive())
            System.out.println("Player 2 wins!");
        else
            System.out.println("Player 1 wins!");
    }

    public Side setBoardForPlayer(){
        Side player = new Side();

        player.printBoard();
        player = setShip("carrier", CARRIERSIZE, player);
        player = setShip("battleship", BATTLESHIPSIZE, player);
        player = setShip("cruiser", CRUISERSIZE, player);
        player = setShip("submarine", SUBMARINESIZE, player);
        player = setShip("destroyer", DESTROYERSIZE, player);

        return player;
    }

    public Side setShip(String shipName, int shipSize, Side player){
        Ship ship = new Ship (shipName, shipSize);

        int firstCol, firstRow, secondCol, secondRow;
        firstCol = firstRow = secondCol = secondRow = -1000;

        while(!isGoodSpacing(firstCol, firstRow, secondCol, secondRow, ship.getShipLength(), player)) {
            System.out.print("\nEnter spaces for your " + ship.getShipName() + " of length " + ship.getShipLength()
                    + " ex: 'A 0 A " + (ship.getShipLength() - 1) + "') : ");
            firstCol = (input.next().charAt(0) - 'A');
            firstRow = input.nextInt();
            secondCol= (input.next().charAt(0) - 'A');
            secondRow = input.nextInt();
        }
        for (int i = firstRow; i <= secondRow; i++)
            for (int j = firstCol; j <= secondCol; j++)
                player.getBoard()[i][j].setToShip();
        System.out.println();
        player.printBoard();
        return player;
    }

    public boolean isGoodSpacing(int firstCol, int firstRow, int secondCol, int secondRow, int shipLength, Side player) {
        if (firstCol == -1000)
            return false;

        if (!(isColumnInRange(firstCol) && isColumnInRange(secondCol) && isRowInRange(firstRow) && isRowInRange(secondRow)))
            return false;
        else if (firstRow != secondRow && firstCol != secondCol){
            System.out.println("Ship must either be placed vertically or horizontally");
            return false;
        }
        else if (firstRow > secondRow || firstCol > secondCol) {
            System.out.println("Spaces for the ship cannot jump across the board (ex: A 9 A 1)");
            return false;
        }
        else if (Math.abs(firstCol - secondCol) != (shipLength - 1) && Math.abs(firstRow - secondRow) != (shipLength - 1)) {
            System.out.println("Make sure you select " + shipLength + " consecutive spaces");
            return false;
        }

        for(int i = firstRow; i <= secondRow; i++)
            for (int j = firstCol; j <= secondCol; j++) {
                if (player.getBoard()[i][j].getIsShip()) {
                    int ascii = i + 'A';
                    char letter = (char) ascii;
                    System.out.println("A ship is already in space " + letter + " " + j);
                    return false;
                }
            }
        return true;
    }

    public boolean isRowInRange(int row){
        if (row < ROWSIZE && row >= 0)
            return true;
        System.out.print("'" + row + "' is not a row on the board");
        return false;
    }

    public boolean isColumnInRange(int col){
        if (col < ROWSIZE && col >= 0)
            return true;
        int ascii = col + 'A';
        char letter = (char) ascii;
        System.out.print( "'" + letter + "' is not a column on the board");
        return false;
    }

    public Side shootAtShips(Side player){
        int firstRow, firstCol;
        firstRow = firstCol = -1000;

        while(!isGoodShot(firstRow, firstCol, player)) {
            System.out.println("Enter coordinate to attack (ex: A 0)");
            firstCol = (input.next().charAt(0) - 'A');
            firstRow = input.nextInt();
        }
        player.getBoard()[firstRow][firstCol].setToHit();
        if (player.getBoard()[firstRow][firstCol].getPointOnScreen() == HITCHAR) {
            System.out.println("HIT!");
            player.takeAHit();
        }
        else
            System.out.println("Get em next time bud");
        return player;
    }

    public boolean isGoodShot(int firstRow, int firstCol, Side player){
        if (firstRow == -1000)
            return false;
        else if (!(isRowInRange(firstRow) || isRowInRange(firstCol)))
            return false;
        else if (player.getBoard()[firstRow][firstCol].isHit)
        {
            System.out.println("Space has already been hit");
            return false;
        }
        return true;
    }
}
