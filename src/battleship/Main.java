package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Board myBoard = new Board();
        myBoard.placeShips();
        System.out.println("Press Enter and pass the move to another player");
        String changePerson = scanner.nextLine();
        Board enemysBoard = new Board();
        enemysBoard.placeShips();
        System.out.println("The game starts!");

        while(!myBoard.lastSank || !enemysBoard.lastSank) {
            System.out.println("Press Enter and pass the move to another player");
            changePerson = scanner.nextLine();
            System.out.println("Player 1, it's your turn: ");
            enemysBoard.displayEnemysBoard();
            System.out.println("---------------------");
            myBoard.displayBoard();

            enemysBoard.shot();

            System.out.println("Press Enter and pass the move to another player");
            changePerson = scanner.nextLine();

            System.out.println("Player 2, it's your turn: ");
            myBoard.displayEnemysBoard();
            System.out.println("---------------------");
            enemysBoard.displayBoard();
            myBoard.shot();

        }

    }
}
