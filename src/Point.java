public class Point {
    static final char HITCHAR = 'X';
    static final char MISSCHAR = 'O';
    static final char EMPTYCHAR = '-';


    boolean isHit = false;
    boolean isShip = false;
    char pointOnScreen = EMPTYCHAR;

    public Point(){}

    public boolean getIsShip() {
        return this.isShip;
    }

    public char getPointOnScreen() {
        return pointOnScreen;
    }

    public void setToShip() {
        this.isShip = true;
        pointOnScreen = 'S';
    }

    public void setToHit() {
        this.isHit = true;
        if (this.isShip)
            pointOnScreen = HITCHAR;
        else
            pointOnScreen = MISSCHAR;
    }

    public void setEmptyChar(){
        pointOnScreen = EMPTYCHAR;
    }
}
