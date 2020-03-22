package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int n = 3;
    public static String[][] matrix = new String[n][n];
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initField();
        boolean isFinished = false;
        String figure = "X";
        while (!isFinished) {
            printField();
            makeMove(figure);
            isFinished = isFinished();
            figure = figure.equals("X") ? "O" : "X";
        }
    }

    private static void printField() {
        String xBorder = "---------";;

        System.out.println(xBorder);

        for (int i = 0; i < n; i++) {
            System.out.print("| ");
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println(xBorder);
    }

    private static void makeMove(String figure) {
        int userX = 0;
        int userY = 0;
        boolean isCorrectInput = false;
        while (!isCorrectInput) {
            System.out.print("Enter the coordinates: ");

            String inputX = scanner.next();
            try {
                userX = Integer.parseInt(inputX);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            String inputY = scanner.next();
            try {
                userY = Integer.parseInt(inputY);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if ((userX < 1 || userX > 3) || (userY < 1 || userY > 3)) {
                System.out.println("Coordinates should be from 1 to 3");
                continue;
            }

            if (!matrix[Math.abs(userY - n)][userX - 1].equals(" ")) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            isCorrectInput = true;
        }

        matrix[Math.abs(userY-n)][userX-1] = figure;
    }

    private static boolean isFinished() {
        boolean isXWins = false;
        boolean isOWins = false;
        int allX = 0;
        int allO = 0;
        int spaceCount = 0;
        int countXMain = 0;
        int countOMain = 0;
        int countXSub = 0;
        int countOSub = 0;

        for (int i = 0; i < n; i++) {
            int countXI = 0;
            int countXJ = 0;
            int countOI = 0;
            int countOJ = 0;

            if (matrix[i][i].equals("X")) {
                countXMain++;
            } else if (matrix[i][i].equals("O")) {
                countOMain++;
            }

            if (matrix[n - i - 1][i].equals("X")) {
                countXSub++;
            } else if (matrix[i][i].equals("O")) {
                countOSub++;
            }

            if (countXMain == 3 || countXSub == 3) {
                isXWins = true;
            } else if (countOMain == 3 || countOSub == 3) {
                isOWins = true;
            }

            for (int j = 0; j < n; j++) {
                if (matrix[i][j].equals("X")) {
                    countXI++;
                    allX++;
                } else if (matrix[i][j].equals("O")) {
                    countOI++;
                    allO++;
                } else {
                    spaceCount++;
                }

                if (matrix[j][i].equals("X")) {
                    countXJ++;
                } else if (matrix[j][i].equals("O")) {
                    countOJ++;
                }

                if (countXI == 3 || countXJ == 3) {
                    isXWins = true;
                } else if (countOI == 3 || countOJ == 3) {
                    isOWins = true;
                }
            }
        }

        if (Math.abs(allX-allO) > 1 || (isOWins && isXWins)) {
            System.out.println("Impossible");
            return true;
        } else if (!isXWins && !isOWins && spaceCount == 0) {
            printField();
            System.out.println("Draw");
            return true;
        } else if (isXWins) {
            printField();
            System.out.println("X wins");
            return true;
        } else if (isOWins){
            printField();
            System.out.println("O wins");
            return true;
        }

        return false;
    }

    private static void initField() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = " ";
            }
        }
    }
}
