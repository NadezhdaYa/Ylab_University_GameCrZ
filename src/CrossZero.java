import java.io.*;
import java.util.Scanner;
 class CrossZero {
    static boolean playAgain = true;
        final char SIGN_X = 'x';
        final char SIGN_O = 'o';
        final char SIGN_EMPTY = '_';
       static int x, o;
        char[][] table;
       Scanner scanner;

        public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
         System.out.println("1-ый игрок введите свое имя:");
           String nameX = sc.nextLine();
         System.out.println("2-ой игрок введите свое имя:");
          String nameO = sc.nextLine();
            while (playAgain) {
            new CrossZero().game();}
// Запись в файл
      //File file = new File("Result.txt");
          //  PrintWriter pw = new PrintWriter(file);
            PrintWriter pw = new PrintWriter((new FileWriter("Result.txt", true)));
            pw.println(nameX);
            pw.println(nameO);
            if (x == 1) {
                pw.println("Крестики выиграли!");
            }
           else if (o == 1) {
                pw.println("Нолики выиграли!");
            }
            pw.close();
          }

        CrossZero() {
            scanner = new Scanner(System.in);
            table = new char[3][3];
        }
// Логика игры
        void game() {

                initTable();
                while (true) {
                    turnCross();
                    if (checkWin(SIGN_X)) {
                       x = 1;
                        System.out.println("Ура! Крестики выиграли!");
                        break;
                    }
                    if (isTableFull()) {
                        System.out.println("Прости, ничья!");
                        break;
                    }
                    turnZero();
                    printTable();
                    if (checkWin(SIGN_O)) {
                        o = 1;
                        System.out.println("Ура! Нолики выиграли!");
                    break;
                }
                    if (isTableFull()) {
                        System.out.println("Прости, ничья!");
                        break;
                    }
                }
            System.out.println("Конец игры.");
            printTable();
                // Сыграть еще
            playAgain = false; //тут храним ответ игрока - да или нет
            System.out.println("Хотите сыграть еще раз? да - для продолжения, любой символ - выход");
            String answer = scanner.next();
            if (answer.equals("да")) {
                System.out.println("Продолжаем!");
                playAgain = true;
            } else
            System.out.println("Жаль! До Встречи!");
           }
       // начальная инициализация игровой таблицы
        void initTable() {
            for (int row = 0; row < 3; row++)
                for (int col = 0; col < 3; col++)
                    table[row][col] = SIGN_EMPTY;
        }
        // отображение текущего состояния игровой таблицы
        void printTable() {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++)
                    System.out.print(table[row][col] + " | ");
                System.out.println();
            }
        }
      // Ход крестика
        void turnCross() {
            int x, y;
            do {
                System.out.println("Введите координаты точек X и Y (1..3) крестика:");
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
            } while (!isCellValid(x, y));
            table[y][x] = SIGN_X;
        }
        // валидность ячейки таблицы
        boolean isCellValid(int x, int y) {
            if (x < 0 || y < 0 || x >= 3|| y >= 3)
                return false;
            return table[y][x] == SIGN_EMPTY;
        }
       // Ход нолика
       void turnZero() {
           int x, y;
           do {
               System.out.println("Введите координаты точек X и Y (1..3) нолика:");
               x = scanner.nextInt() - 1;
               y = scanner.nextInt() - 1;
           } while (!isCellValid(x, y));
           table[y][x] = SIGN_O;
       }
        // проверка победы
        boolean checkWin(char dot) {
            for (int i = 0; i < 3; i++)
                if ((table[i][0] == dot && table[i][1] == dot &&
                        table[i][2] == dot) ||
                        (table[0][i] == dot && table[1][i] == dot &&
                                table[2][i] == dot))
                    return true;
            if ((table[0][0] == dot && table[1][1] == dot &&
                    table[2][2] == dot) ||
                    (table[2][0] == dot && table[1][1] == dot &&
                            table[0][2] == dot))
                return true;
            return false;
        }
     // проверка на ничью
        boolean isTableFull() {
            for (int row = 0; row < 3; row++)
                for (int col = 0; col < 3; col++)
                    if (table[row][col] == SIGN_EMPTY)
                        return false;
            return true;
        }
    }
