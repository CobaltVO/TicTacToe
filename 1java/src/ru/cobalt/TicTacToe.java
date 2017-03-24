package ru.cobalt;

import java.util.Scanner;

class TicTacToe {
    private char map[][] = new char[3][3]; // for storing marks
    private int count = 0;
    private char currentPlayer = 'X';

    TicTacToe() {
        clearMap();
        startGameMessage();
        showMap();
        takeData();
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

    private void setMark(int pos) {
        pos -= 1; // 0, 1, 2, 3, 4, 5, 6, 7, 8
        int i = pos / 3;
        int j = pos % 3;
        if (map[i][j] == '_') {
            map[i][j] = currentPlayer;
        } else {
            System.out.println("This place has been already taken\nChoose another one");
            takeData();
            return;
        }
        if (count > 3) {
            if (didPlayerWin(currentPlayer, pos)) {
                System.out.println("Player " + currentPlayer + " won!!!");
                showMap();
                return;
            }
        }

        showMap();
        if (count < 8) {
            count++;
            swapCurrentPlayer();
            takeData();
        } else System.out.println("Draw");

    }

    private boolean didPlayerWin(char mark, int pos) {
        switch (pos) {
            case 4: // 4 pos to ensure
                return (map[0][0] == mark) && (map[2][2] == mark) ||
                        (map[0][1] == mark) && (map[2][1] == mark) ||
                        (map[0][2] == mark) && (map[2][0] == mark) ||
                        (map[1][0] == mark) && (map[1][2] == mark);

            case 0: // 3 pos to ensure
                return (map[0][1] == mark) && (map[0][2] == mark) ||
                        (map[1][0] == mark) && (map[2][0] == mark) ||
                        (map[1][1] == mark) && (map[2][2] == mark);

            case 2: // 3 pos to ensure
                return (map[0][0] == mark) && (map[0][1] == mark) ||
                        (map[1][2] == mark) && (map[2][2] == mark) ||
                        (map[1][1] == mark) && (map[2][0] == mark);

            case 6: // 3 pos to ensure
                return (map[0][0] == mark) && (map[1][0] == mark) ||
                        (map[2][1] == mark) && (map[2][2] == mark) ||
                        (map[1][1] == mark) && (map[0][2] == mark);

            case 8: // 3 pos to ensure
                return (map[0][2] == mark) && (map[1][2] == mark) ||
                        (map[2][0] == mark) && (map[2][1] == mark) ||
                        (map[1][1] == mark) && (map[0][0] == mark);

            case 1: // 2 pos to ensure
                return (map[0][0] == mark) && (map[0][2] == mark) || ((map[1][1] == mark) && (map[2][1] == mark));
            case 3: // 2 pos to ensure
                return (map[0][0] == mark) && (map[2][0] == mark) || ((map[1][1] == mark) && (map[1][2] == mark));
            case 5: // 2 pos to ensure
                return (map[0][2] == mark) && (map[2][2] == mark) || ((map[1][0] == mark) && (map[1][1] == mark));
            case 7: // 2 pos to ensure
                return (map[0][1] == mark) && (map[1][1] == mark) || ((map[2][0] == mark) && (map[2][2] == mark));
            default:
                return false;
        }
    }

    private void takeData() {
        System.out.println("Player " + currentPlayer + " goes");
        Scanner in = new Scanner(System.in);
        String str;
        int pos;
        while (true) {
            str = in.nextLine(); // split strings by delimiter
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
        setMark(pos);
    }
}
