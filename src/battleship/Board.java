package battleship;

import java.util.Scanner;

public class Board {
    int rows;
    int columns;
    final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};

    String[][] board;

    private int hittedFields = 0;
    boolean lastSank = false;

    Scanner scanner = new Scanner(System.in);

    public Board(int rows, int columns){
        if (rows > 15 || columns > 15){
            System.out.println("to big values, max is 15");
        }
        this.columns=columns;
        this.rows = rows;
        this.board = new String[rows+1][columns+1];
    }

    public Board(){
        this.columns=10;
        this.rows = 10;
        this.board = new String[11][11];
    }

    protected void displayBoard(){
        //System.out.println("displaing");
        for (String[] strings : this.board) {
            for (int j = 0; j < this.board[0].length; j++) {
                System.out.print(strings[j] + " ");
            }
            System.out.println();
        }
    }

    void displayEnemysBoard(){

        for (int i=0; i<board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                if(board[i][j].equals("O")) {
                    System.out.print("~ ");
                    continue;
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
    public void createBoard(){
        board[0][0] = " ";
        for(int i=0; i<this.board.length; i++){
            for (int j =0; j<this.board[0].length; j++){
                if(i==0 && j!=0){
                    this.board[i][j]= String.valueOf(j);
                }
                if(j==0 && i!=0){
                    this.board[i][j] = letters[i-1];
                } else if (i!=0) {
                    this.board[i][j] = "~";
                }
                //System.out.print(board[i][j]);
            }
            //System.out.println("");
        }
        //displayBoard();
    }

    private int[] convertCoordinates(String coordinates){
        String ac1row = coordinates.substring(0,1);
        String ac1column = coordinates.substring(1);
        int[] convertedCoordinates = {0,0};

        for (int i= 0; i<board.length; i++){
            if (board[i][0].equals(ac1row)){
                convertedCoordinates[0] = i;
                for (int j =0; j<board[0].length; j++){
                    if(board[0][j].equals(ac1column)){
                        convertedCoordinates[1] = j;
                        return convertedCoordinates;
                    }
                }
            }
        }
        return convertedCoordinates;
    }

    private boolean checkingCoordinates(int[] begCo, int[] finCo, Ship ship){

        int length = ship.length-1;


        //System.out.println(begCo[0] + " " + begCo[1] + " " + finCo[0] + " " + finCo[1]);

       if ((begCo[0] == 0 || begCo[1] ==0) || (finCo[0] == 0 || finCo[1] ==0))
        {
            System.out.println("Error! Wrong ship location! Try again: ");
           return false;
        } else if (begCo[0] != finCo[0] && begCo[1] != finCo[1]) {
           System.out.println("Error! Wrong ship location! (can't be diagonal) Try again: ");
           return false;
        } else if ((begCo[0] + length == finCo[0] ^ begCo[0] - length == finCo[0])
               == (begCo[1] + length == finCo[1] ^ begCo[1] - length == finCo[1])) {
           System.out.println("Error! Wrong length of the " + ship.name + "! Try again: ");
           return false;
        }

        else{ return true;}

    }

    private boolean checkingNeigborowShip(int[] fromCo, int[] toCo, int length){

        if(toCo[0]<fromCo[0] || toCo[1]<fromCo[1]){
            for(int k=0; k<2; k++) {
                int swap = toCo[k];
                toCo[k] = fromCo[k];
                fromCo[k] = swap;
                //System.out.println(fromCo[k] + ", " + toCo[k]);
            }
        }

        int boardLength = board.length;
        boolean noAnotherShip = true;
        int currentPointLetter = fromCo[0];
        int currentPointNumber = fromCo[1];

        for(int in = 0; in<length+1; in++){
            if(fromCo[0]!=toCo[0]) {                    //vertical
                if (currentPointLetter-1 == 0) {
                    in++;                               // ZMIANA Z CONTINUE
                }
                if (currentPointLetter+in > boardLength) {
                    //System.out.println("last element");
                    return noAnotherShip;
                }
                if (board[(currentPointLetter-1) + in][currentPointNumber - 1].equals("O") && currentPointNumber - 1 > 0) { //lewy sasiad
                    System.out.println("Error! You placed it too close to another one. Try again: ");
                    return false;
                }
                if(currentPointNumber + 1 < boardLength) {
                    if (board[(currentPointLetter - 1) + in][currentPointNumber + 1].equals("O")) { //prawy sasiad
                        System.out.println("Error! You placed it too close to another one. Try again: ");
                        return false;
                    }
                }
                if(board[currentPointLetter - 1][currentPointNumber].equals("O")){ //sasiad na gorze
                    System.out.println("Error! You placed it too close to another one. Try again: ");
                    return false;
                }
                if((currentPointLetter + length) < boardLength){
                    if (board[(currentPointLetter) + in][currentPointNumber].equals("O")) // lub sasiad na dole
                    {
                        System.out.println("Error! You placed it too close to another one. Try again: ");
                        return false;
                    }
                    //System.out.println(currentPointLetter);
                }
                if (board[(currentPointLetter-1) + in][currentPointNumber].equals("O")) {
                    System.out.println("Error! You placed it too close to another one. Try again: ");
                    return false;
                }
            }

            else if (fromCo[1]!=toCo[1]) { // horizontal
                if ((currentPointNumber-1) == 0) {
                    in++;
                }
                if (currentPointNumber+in > boardLength) {
                    return noAnotherShip;
                }
                if (board[currentPointLetter - 1][(currentPointNumber-1) + in].equals("O") && currentPointLetter - 1 > 0) { //sasiad na gorze
                    System.out.println("Error! You placed it too close to another one. Try again: (sng)");
                    return false;

                }
                if(currentPointLetter + 1 < boardLength){
                    if (board[currentPointLetter + 1][(currentPointNumber-1) + in].equals("O")) { // sasiad na dole
                        System.out.println("Error! You placed it too close to another one. Try again: (snd)");
                        return false;
                    }
                }

                if (board[currentPointLetter][currentPointNumber - 1].equals("O")){ //sasiad z lewej
                        System.out.println("Error! You placed it too close to another one. Try again: szl");
                        return false;
                }
                if(currentPointNumber + length < boardLength){
                    if(board[currentPointLetter][currentPointNumber + in].equals("O")) { // sasiad z prawej
                        System.out.println("Error! You placed it too close to another one. Try again: szp");
                        return false;
                    }
                }
                /*if (board[currentPointLetter][(currentPointNumber-1) + in].equals("O")) { //sasiad pod spodem
                    System.out.println("Error! You placed it too close to another one. Try again: sps");
                    return false;
                }*/
            }
        }

        return noAnotherShip;
    }

    void printingShip(int[] fromCo, int[] toCo, Ship ship){
        for(int printIndex = 0; printIndex<ship.length; printIndex++) {
            if (fromCo[0] != toCo[0]) { // vertical
                board[fromCo[0] + printIndex][fromCo[1]] = "O";
                ship.shipFields[printIndex][0] = fromCo[0]+printIndex;
                ship.shipFields[printIndex][1] = fromCo[1];
            } else if (fromCo[1] != toCo[1]) { //horizontal
                board[fromCo[0]][fromCo[1]+ printIndex] = "O";
                ship.shipFields[printIndex][0] = fromCo[0];
                ship.shipFields[printIndex][1] = fromCo[1]+printIndex;
            }
        }
         displayBoard();
    }

    Ship[] ships = Ship.defaultShips();
    public void placeShips(){
        createBoard();
        displayBoard();
        for (int i=0; i<ships.length; i++) {

            boolean printing = false;

            while (!printing) {
                System.out.println("Enter the coordinates of the " + ships[i].name + " ("+ships[i].length + " cells):");
                String coordinates = scanner.nextLine().toUpperCase();
                while(!coordinates.matches("[A-J][1-9]0? [A-J][1-9]0?")){
                    System.out.println("Error! Wrong format of coordinates, try again.");
                    coordinates = scanner.nextLine().toUpperCase();
                }
                String[] splitCoordinates = coordinates.split(" ");
                String begginingCoordinates = splitCoordinates[0];
                String endingCoordinates = splitCoordinates[1];
                int[] fromCo = convertCoordinates(begginingCoordinates);
                int[] toCo = convertCoordinates(endingCoordinates);
                if (checkingCoordinates(fromCo, toCo, ships[i])) {
                    if (checkingNeigborowShip(fromCo, toCo, ships[i].length)) {
                        printingShip(fromCo, toCo, ships[i]);
                        printing = true;
                    }
                }
            }
        }
    }

    public void shot(){

        String shot = scanner.nextLine();

        while(!shot.matches("[A-J][1-9]0?")){
            System.out.println("Error! You entered the wrong coordinates! Try again: ");
            shot = scanner.nextLine();
        }
        int[] splitCoo = convertCoordinates(shot);

        if (board[splitCoo[0]][splitCoo[1]].equals("~") || board[splitCoo[0]][splitCoo[1]].equals("M")){ //DRUGI WARUNEK ZE WZGLEDU NA WYMOGI PROJEKTU (BEZ SENSU)
            board[splitCoo[0]][splitCoo[1]] = "M";
            displayEnemysBoard();
            System.out.println();
            System.out.println("You missed! Try again: ");
            System.out.println();
        }

        /*if (board[splitCoo[0]][splitCoo[1]].equals("X") || board[splitCoo[0]][splitCoo[1]].equals("M")){ // SPRAWDZANIE CZY JUŻ UDERZANE W TO MIEJSCE
            board[splitCoo[0]][splitCoo[1]] = "M";
            displayEnemysBoard();
            System.out.println();
            System.out.println("You missed! Try again: ");
            System.out.println();
        }*/

        if (board[splitCoo[0]][splitCoo[1]].equals("X")){ //MASLO MASLANE KOLEJNY WARUNEK ZE WZGLEDU NA WYMOGI PROJEKTU (BEZ SENSU)
            displayEnemysBoard();
            System.out.println();
            System.out.println("You hit a ship! Try again: ");
            System.out.println();
        }

        if (board[splitCoo[0]][splitCoo[1]].equals("O")){
            board[splitCoo[0]][splitCoo[1]] = "X";
            hittedFields++;
            if(hittedFields==17){
                System.out.println("You sank the last ship. You won. Congratulations!");
                lastSank = true;
            }
            for (int i=0; i<ships.length; i++){
                for (int j = 0; j<ships[i].length; j++){
                    if(ships[i].shipFields[j][0] == splitCoo[0] && ships[i].shipFields[j][1]==splitCoo[1] && !lastSank){
                        ships[i].shipFields[j][0] = 99;
                        ships[i].shipFields[j][1] = 99;
                        ships[i].couterHittedFields++;
                        if (ships[i].couterHittedFields == ships[i].length){
                            System.out.println("You sank a ship!");
                            break;
                        }
                    }
                }
            }

            System.out.println();
            //displayEnemysBoard();
            if(!lastSank){System.out.println("You hit a ship! Try again: ");}
            System.out.println();
            //shot();
        }
        //displayBoard();
    }

}
