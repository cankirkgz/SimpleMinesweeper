import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int n = scanner.nextInt();
        System.out.println("Enter the number of columns:");
        int m = scanner.nextInt();
        int numberOfBombs;
        char[][] array = new char[n][m];

        numberOfBombs = (n * m) / 4;

        createArray(array, n, m);
        char[][] gameArray = new char[n][m];
        createArray(gameArray, n, m);

        placeBomb(array, numberOfBombs, n, m);

        writeArray(array, n, m);
        System.out.println("Welcome to the Minefield Game!");
        writeArray(gameArray, n, m);
        int i = 0;

        for (i = 0; i < (n * m) - numberOfBombs; i++) {
            System.out.print("Enter the row: ");
            int row = scanner.nextInt();
            System.out.print("Enter the column: ");
            int col = scanner.nextInt();
            if (isBomb(array, gameArray, row, col)) {
                System.out.print("Game Over!! ");
                break;
            } else {
                gameArray[row][col] = (char)(countSurroundingBombs(array, row, col) + '0');
                writeArray(gameArray, n, m);
            }
        }
        if (i == (n * m) - numberOfBombs)
            System.out.println("You Win!!!");
    }

    // Creates an array and initializes it with '-'
    public static void createArray(char[][] array, int row, int col) {
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                array[i][j] = '-';
    }

    // Places bombs randomly in the array
    public static void placeBomb(char[][] array, int numberOfBombs, int n, int m) {
        Random random = new Random();

        for (int i = 0; i < numberOfBombs; i++) {
            int row = random.nextInt(n);
            int col = random.nextInt(m);

            if (array[row][col] == '-')
                array[row][col] = '*';
        }
    }

    // Prints the contents of the array
    public static void writeArray(char[][] array, int row, int col) {
        System.out.println("==========================");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                System.out.print(array[i][j] + " ");
            System.out.println();
        }
    }

    // Checks if the selected cell contains a bomb
    public static boolean isBomb(char[][] array, char[][] gameArray, int row, int col) {
        return array[row][col] != gameArray[row][col];
    }
    // Counts the number of bombs in the surrounding cells
    public static int countSurroundingBombs(char[][] array, int row, int col) {
        int bombCount = 0;
        int numRows = array.length;
        int numCols = array[0].length;

        // Iterate through the surrounding cells of the checked cell
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;

                // Check the validity of the cell's position
                if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols) {
                    // Check for a bomb and increment the count
                    if (array[newRow][newCol] == '*') {
                        bombCount++;
                    }
                }
            }
        }
        return bombCount;
    }
}
