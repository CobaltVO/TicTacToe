package cobalt;

import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    private char map[][] = new char[3][3]; // for storing marks
    private int count = 0;
    private char currentPlayer = 'X';
    private int pos = 0;

    TicTacToe() {
        clearMap();
        startGameMessage();
    }

    private void swapCurrentPlayer() {
        if (currentPlayer == 'X')
            currentPlayer = 'O';
        else
            currentPlayer = 'X';
    }

    private void clearMap() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map[i][j] = '_';
            }
        }
    }

    private void showMap() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void startGameMessage() {
        System.out.println("Greetings! This is TicTacToe game");
        System.out.println("There are allowed positions: 1, 2, 3, 4, 5, 6, 7, 8, 9");
        System.out.println("First player begins game with X mark");
        System.out.println("Type position, e.g.: 3");
    }

    private boolean checkIfWon() {
        switch (pos) {
            case 4: // 4 pos to ensure
                return (map[0][0] == currentPlayer) && (map[2][2] == currentPlayer) ||
                        (map[0][1] == currentPlayer) && (map[2][1] == currentPlayer) ||
                        (map[0][2] == currentPlayer) && (map[2][0] == currentPlayer) ||
                        (map[1][0] == currentPlayer) && (map[1][2] == currentPlayer);

            case 0: // 3 pos to ensure
                return (map[0][1] == currentPlayer) && (map[0][2] == currentPlayer) ||
                        (map[1][0] == currentPlayer) && (map[2][0] == currentPlayer) ||
                        (map[1][1] == currentPlayer) && (map[2][2] == currentPlayer);

            case 2: // 3 pos to ensure
                return (map[0][0] == currentPlayer) && (map[0][1] == currentPlayer) ||
                        (map[1][2] == currentPlayer) && (map[2][2] == currentPlayer) ||
                        (map[1][1] == currentPlayer) && (map[2][0] == currentPlayer);

            case 6: // 3 pos to ensure
                return (map[0][0] == currentPlayer) && (map[1][0] == currentPlayer) ||
                        (map[2][1] == currentPlayer) && (map[2][2] == currentPlayer) ||
                        (map[1][1] == currentPlayer) && (map[0][2] == currentPlayer);

            case 8: // 3 pos to ensure
                return (map[0][2] == currentPlayer) && (map[1][2] == currentPlayer) ||
                        (map[2][0] == currentPlayer) && (map[2][1] == currentPlayer) ||
                        (map[1][1] == currentPlayer) && (map[0][0] == currentPlayer);

            case 1: // 2 pos to ensure
                return (map[0][0] == currentPlayer) && (map[0][2] == currentPlayer) || ((map[1][1] == currentPlayer) && (map[2][1] == currentPlayer));
            case 3: // 2 pos to ensure
                return (map[0][0] == currentPlayer) && (map[2][0] == currentPlayer) || ((map[1][1] == currentPlayer) && (map[1][2] == currentPlayer));
            case 5: // 2 pos to ensure
                return (map[0][2] == currentPlayer) && (map[2][2] == currentPlayer) || ((map[1][0] == currentPlayer) && (map[1][1] == currentPlayer));
            case 7: // 2 pos to ensure
                return (map[0][1] == currentPlayer) && (map[1][1] == currentPlayer) || ((map[2][0] == currentPlayer) && (map[2][2] == currentPlayer));
            default:
                return false;
        }
    }

    private void setUserMark() {
        pos -= 1; // 0, 1, 2, 3, 4, 5, 6, 7, 8
        int i = pos / 3;
        int j = pos % 3;
        if (map[i][j] == '_') {
            map[i][j] = currentPlayer;
        } else {
            System.out.println("This place has been already taken\nChoose another one");
            takePos();
        }
    }

    private void takePos() {
        Scanner in = new Scanner(System.in);
        String str;
        while (true) {
            str = in.nextLine();
            try {
                pos = Integer.valueOf(str);
            } catch (NumberFormatException e) {
                System.err.println("Wrong data!\nTry again");
                continue;
            }
            if ((pos > 9) || (pos < 1)) {
                System.err.println("Wrong position!\nType data again: ");
                continue;
            }
            break;
        }
    }

    public void p2p() {
        showMap();
        System.out.println("Player " + currentPlayer + " goes");
        takePos();
        setUserMark();
        if (count < 8) {
            count++;
        } else {
            showMap();
            System.out.println("Draw");
            return;
        }
        if (count > 4) {
            if (checkIfWon()) {
                showMap();
                System.out.println("Player " + currentPlayer + " won!!!");
            } else {
                swapCurrentPlayer();
                p2p();
            }
        } else {
            swapCurrentPlayer();
            p2p();
        }
    }

    public void playWithComp() {
        showMap();
        swapCurrentPlayer();
        while (!checkIfWon()) {
            System.out.println("Player goes");
            swapCurrentPlayer();
            playerGo();
            showMap();
            if (checkIfWon()) {
                System.out.println(currentPlayer);
                System.out.println("Player won!!!");
                break;
            }
            if (count == 4) break;
            System.out.println("Computer goes");
            swapCurrentPlayer();
            compGo();
            showMap();
            if (checkIfWon()) {
                System.out.println(currentPlayer);
                System.out.println("Computer won");
                break;
            }
            count++;
        }
    }

    private void playerGo() {
        takePos();
        setUserMark();
    }

    private void compGo(){
        Random random = new Random();
        int i = random.nextInt(3);
        int j = random.nextInt(3);
        if (map[i][j] != '_'){
            while (map[i][j] != '_') {
                i = random.nextInt(3);
                j = random.nextInt(3);
            }
        }
        map[i][j] = currentPlayer;
    }

}
/*
X X _   X X _   X _ _   X _ _   _ _ _   _ _ _   _ _ _   _ _ _
O O X   O _ X   O _ X   O _ X   O _ X   _ _ X   _ _ X   _ _ X
O X O   O X O   O X O   O X _   O X _   O X _   O _ _   _ _ _

*/