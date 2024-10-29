package Inptter;

import java.util.Scanner;

public class Inputted {
    private static Scanner sc = new Scanner(System.in);

    public static String GetString(String str){
        System.out.println(str);
        String input = sc.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("Input cannot be empty. Please enter again.");
            System.out.println(str);
            input = sc.nextLine().trim();
        }
        return input;
    }

    public static String NewString(String str, String pre){
        System.out.print(str);
        String input = sc.nextLine().trim();
        return input.isEmpty() ? pre : input;
    }

    


    public static int GetNewInt(String prompt, int currentValue){
        System.out.print(prompt + " (Current: " + currentValue + "): ");
        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return currentValue;
        }
        int value = Integer.parseInt(input);
        return value;
    }

    public static int GetInt(String prompt) {
    return GetInt(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE); // Gọi phương thức với giới hạn mặc định
    }
    public static int GetInt(String prompt, int min, int max) {
    int value;
    while (true) {
        System.out.print(prompt);
        try {
            value = Integer.parseInt(sc.nextLine().trim());
            if (value >= min && value <= max) {
                return value;
            } else {
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid integer.");
        }
    }
}

}