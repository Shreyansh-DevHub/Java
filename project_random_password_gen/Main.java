package project_random_password_gen;

import java.security.SecureRandom;
import java.util.Scanner;

public class Main {
    private static final int MIN_LENGTH = 4;
    private static final int MAX_LENGTH = 128;
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{}?";
    private static final String ALL_CHARACTERS = LOWERCASE + UPPERCASE + DIGITS + SYMBOLS;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter password length (" + MIN_LENGTH + "-" + MAX_LENGTH + "): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Error: please enter a whole number.");
                return;
            }

            int length = scanner.nextInt();
            if (length < MIN_LENGTH || length > MAX_LENGTH) {
                System.out.println("Error: length must be between " + MIN_LENGTH + " and " + MAX_LENGTH + ".");
                return;
            }

            System.out.println("Generated password: " + generatePassword(length));
        }
    }

    private static String generatePassword(int length) {
        char[] password = new char[length];
        password[0] = randomCharacter(LOWERCASE);
        password[1] = randomCharacter(UPPERCASE);
        password[2] = randomCharacter(DIGITS);
        password[3] = randomCharacter(SYMBOLS);

        for (int i = 4; i < length; i++) {
            password[i] = randomCharacter(ALL_CHARACTERS);
        }

        for (int i = password.length - 1; i > 0; i--) {
            int randomIndex = RANDOM.nextInt(i + 1);
            char temporary = password[i];
            password[i] = password[randomIndex];
            password[randomIndex] = temporary;
        }

        return new String(password);
    }

    private static char randomCharacter(String characters) {
        return characters.charAt(RANDOM.nextInt(characters.length()));
    }
}