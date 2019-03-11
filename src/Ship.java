public class Ship {

    String shipName;
    int shipLength;
    Ship(){}

    Ship(String shipName, int shipLength){
        this.shipName = shipName;
        this.shipLength = shipLength;
    }

    public String getShipName(){
        return shipName;
    }

    public int getShipLength() {
        return shipLength;
    }
}
