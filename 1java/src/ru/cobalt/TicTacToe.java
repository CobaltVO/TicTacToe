package ru.cobalt;

public class TicTacToe {
    private int mas[][] = new int[3][3];

    public void clearMap () {
        for (int i= 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mas[i][j] = 0;
            }
        }
    }

    public void showMap () {
        for (int i= 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(mas[i][j]);
            }
            System.out.println();
        }
    }

}
