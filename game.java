import java.util.Random;
import java.util.Scanner;

class NumberGame {
    private int num, tries, range;
    private boolean hint;
    private Random ran = new Random();

    private void setDifficulty(Scanner inp) {
        System.out.println();

        System.out.println("1. Easy (Guess number between 1-10 | Tries: 5)");
        System.out.println("2. Medium (Guess number between 1-50 | Tries: 5)");
        System.out.println("3. Hard (Guess number between 1-100 | Tries: 8)");

        System.out.print("Enter Difficulty Mode: ");
        int mode = inp.nextInt();

        switch (mode) {
            case 1:
                range = 10;
                tries = 5;
                break;

            case 2:
                tries = 5;
                range = 50;
                break;

            case 3:
                tries = 8;
                range = 100;
                break;

            default:
                System.out.println("Enter correct index");
                setDifficulty(inp);
        }
        inp.nextLine();
        hints(inp);
    }

    private void hints(Scanner inp) {
        System.out.print("Do you want to use hints? (y/n): ");
        String ans = inp.nextLine();
        ans = ans.toLowerCase();

        if (ans.equals("y")) {
            hint = true;
        } else if (ans.equals("n")) {
            hint = false;
        } else {
            System.out.println("Enter correct answer! ");
            hints(inp);
        }
    }

    private int[] getFactorArr(int x) {

        int cnt = 0;
        for (int i = 2; i < x + 1; i++) {
            if (x % i == 0) {
                cnt += 1;
            }
        }
        int[] arr = new int[cnt];
        cnt = 0;
        for (int i = 2; i < x + 1; i++) {
            if (x % i == 0) {
                arr[cnt] = i;
                cnt += 1;
            }
        }
        return arr;
    }

    private void selectFactor() {
        int arr[] = getFactorArr(num);
        if (!hint) {

        } else if (arr.length == 1) {
            System.out.println("The number is a prime number!! ");
        } else {
            System.out.println("One of the factors of the number is " + arr[ran.nextInt(arr.length - 1)]);
        }

    }

    private void pickRandom() {
        num = ran.nextInt(range + 1);
        if (num == 0) {
            pickRandom();
        }
    }

    private void guess(Scanner inp) {
        int gue;
        boolean ans = false;
        System.out.println("\nStart guessing!!!");

        for (int i = 0; i < tries; i++) {
            System.out.print("Guess (Tries remaining: " + (tries - i) + "): ");
            gue = inp.nextInt();
            if (gue > num) {
                System.out.println("Your guess is greater than the number.");
            } else if (gue < num) {
                System.out.println("Your guess is smaller than the number.");
            } else if (gue == num) {
                System.out.println("You are right!!!");
                ans = true;
                break;
            }

        }
        if (!ans) {
            System.out.println("Better luck next time!!");
        }
    }

    public void run(Scanner inp) {
        setDifficulty(inp);
        pickRandom();
        selectFactor();
        guess(inp);

        while (true) {
            System.out.println();
            System.out.println("1. Play again");
            System.out.println("2. Change Difficulty");
            System.out.println("3. Exit");
            System.out.print("Enter index: ");
            int index = inp.nextInt();

            switch (index) {
                case 1:
                    pickRandom();
                    selectFactor();
                    guess(inp);
                    break;
                case 2:
                    setDifficulty(inp);
                    pickRandom();
                    selectFactor();
                    guess(inp);
                    break;
                case 3:
                    System.out.println("Thank You for playing :)");
                    System.exit(0);
                    break;

                default:
                    break;
            }
        }
    }

}

public class game {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        NumberGame g = new NumberGame();
        g.run(inp);
    }
}
