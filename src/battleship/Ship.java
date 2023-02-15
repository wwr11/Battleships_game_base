package battleship;

public class Ship {
    protected String name;
    protected int length;

    int[][] shipFields;

    int couterHittedFields;

    protected Ship(String name, int length){
        this.name = name;
        this.length = length;
        this.couterHittedFields = 0;
        this.shipFields = new int[length][2];
    }

    public String getName(){
        return this.name;
    }

    public int getLength(){
        return this.length;
    }



    protected static Ship[] defaultShips(){
        Ship[] ships = new Ship[5];

        ships[0] = new Ship("Aircraft Carrier",5);
        ships[1] = new Ship("Battleship",4);
        ships[2] = new Ship("Submarine",3);
        ships[3] = new Ship("Cruiser",3);
        ships[4] = new Ship("Destroyer",2);

        return ships;
    }
}
