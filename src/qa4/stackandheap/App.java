package qa4.stackandheap;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        System.out.print("Введите b:");
        int b = scanner.nextInt();
        System.out.println("a+b=" + pow(a,b));
    }
    public static Long pow(int base, int exponent) {
        Long result = 1L;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}
