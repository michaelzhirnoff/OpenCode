package opencode;
import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner factorialScanner = new Scanner(System.in);
        System.out.println("Add any number from 0 to 99");
        int number = factorialScanner.nextInt();
        long result = 1;
        int i;
        for (i = number; i > 0; i--) {
            result *= i;
        }
        System.out.format("Factorial of %d equals %d\n", number, result);
    }
}