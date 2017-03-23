package ru.cobalt;

import java.util.Scanner;

public class TicTacToe {
    private char map[][] = new char[3][3]; // for storing marks
    private char mark;
    private int pos;
    private int count = 0;

    TicTacToe () {
        clearMap();
        startGameMessage();
        showMap();
    }

    public void clearMap () {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map[i][j] = '_';
            }
        }
    }

    public void showMap () {
        for (int i= 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void startGameMessage () {
        System.out.println("Greetings! This is TicTacToe game");
        System.out.println("There are allowed marks: X, O; There are allowed positions: 1, 2, 3, 4, 5, 6, 7, 8, 9");
        System.out.println("First player begins game with X mark");
        System.out.println("Type data in right order: firstly mark then position, e.g.: X 3");
    }

    public void setMark (char mark, int pos) {
        if ((pos > 9) || (pos < 1)) {
            System.err.println("Wrong position!\nType data again: ");
            takeData();
        }
        else if ((mark == 'X') || (mark == 'O') || (mark == 'x') || (mark == 'o')) {
            pos -= 1; // 0, 1, 2, 3, 4, 5, 6, 7, 8
            int i = pos / 3;
            int j = pos % 3;
            if (map[i][j] == '_') {
                map[i][j] = mark;
            } else {
                System.out.println("This place has been already taken\nChoose another one");
                takeData();
                return;
            }
            if (count > 3) {
                if (didPlayerWin(mark, pos)) {
                    System.out.println("Player " + mark + " won!!!");
                    showMap();
                    return;
                }

            }
            showMap();
            if (count < 8) {
                count++;
                takeData();
            } else System.out.println("Draw");
        }
        else {
            System.err.println("Wrong symbol\nType data again: ");
            takeData();
        }
    }

    public boolean didPlayerWin (char mark, int pos) {
        switch (pos) {
            case 4: // 4 pos to ensure
                         if ( (map[0][0] == mark) && (map[2][2] == mark) ) return true;
                    else if ( (map[0][1] == mark) && (map[2][1] == mark) ) return true;
                    else if ( (map[0][2] == mark) && (map[2][0] == mark) ) return true;
                else return ( (map[1][0] == mark) && (map[1][2] == mark) );

            case 0: // 3 pos to ensure
                         if ( (map[0][1] == mark) && (map[0][2] == mark) ) return true;
                    else if ( (map[1][0] == mark) && (map[2][0] == mark) ) return true;
                else return ( (map[1][1] == mark) && (map[2][2] == mark) );

            case 2: // 3 pos to ensure
                         if ( (map[0][0] == mark) && (map[0][1] == mark) ) return true;
                    else if ( (map[1][2] == mark) && (map[2][2] == mark) ) return true;
                else return ( (map[1][1] == mark) && (map[2][0] == mark) );

            case 6: // 3 pos to ensure
                         if ( (map[0][0] == mark) && (map[1][0] == mark) ) return true;
                    else if ( (map[2][1] == mark) && (map[2][2] == mark) ) return true;
                else return ( (map[1][1] == mark) && (map[0][2] == mark) );

            case 8: // 3 pos to ensure
                         if ( (map[0][2] == mark) && (map[1][2] == mark) ) return true;
                    else if ( (map[2][0] == mark) && (map[2][1] == mark) ) return true;
                else return ( (map[1][1] == mark) && (map[0][0] == mark) );

            case 1: // 2 pos to ensure
                         if ( (map[0][0] == mark) && (map[0][2] == mark) ) return true;
                else return ( (map[1][1] == mark) && (map[2][1] == mark) );
            case 3: // 2 pos to ensure
                         if ( (map[0][0] == mark) && (map[2][0] == mark) ) return true;
                else return ( (map[1][1] == mark) && (map[1][2] == mark) );
            case 5: // 2 pos to ensure
                         if ( (map[0][2] == mark) && (map[2][2] == mark) ) return true;
                else return ( (map[1][0] == mark) && (map[1][1] == mark) );
            case 7: // 2 pos to ensure
                         if ( (map[0][1] == mark) && (map[1][1] == mark) ) return true;
                else return ( (map[2][0] == mark) && (map[2][2] == mark) );
                default: return false;
        }
    }

    public void takeData () {
        System.out.println("Type data (e.g.: X 3), space is necessary");
        Scanner in = new Scanner(System.in);
        String buf = "";
        String[] masStr;
        if (in.hasNextLine()) buf = in.nextLine();
        masStr = buf.split(" "); // split strings by delimeter
        if (masStr.length != 2) {
            System.err.println("Wrong data!\ntry again");
            takeData();
        }
        mark = masStr[0].charAt(0);
        pos = Integer.valueOf(masStr[1]);
        setMark(mark, pos);
    }
}
