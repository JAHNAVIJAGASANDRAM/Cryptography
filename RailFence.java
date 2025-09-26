
import java.util.Scanner;

public class RailFence {

    public static String encrypt(String plaintext, int rails) {
        if (rails <= 1 || plaintext.length() <= 1) {
            return plaintext;
        }
        char[][] railMatrix = new char[rails][plaintext.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < plaintext.length(); j++) {
                railMatrix[i][j] = '\0';
            }
        }
        boolean directionDown = false;
        int row = 0, col = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            if (row == 0 || row == rails - 1) {
                directionDown = !directionDown;
            }
            railMatrix[row][col++] = plaintext.charAt(i);
            row += directionDown ? 1 : -1;
        }
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < plaintext.length(); j++) {
                if (railMatrix[i][j] != '\0') {
                    ciphertext.append(railMatrix[i][j]);
                }
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int rails) {
        if (rails <= 1 || ciphertext.length() <= 1) {
            return ciphertext;
        }
        char[][] railMatrix = new char[rails][ciphertext.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < ciphertext.length(); j++) {
                railMatrix[i][j] = '\0';
            }
        }

        boolean directionDown = false;
        int row = 0, col = 0;

        // Mark the positions with '*'
        for (int i = 0; i < ciphertext.length(); i++) {
            if (row == 0 || row == rails - 1) {
                directionDown = !directionDown;
            }
            railMatrix[row][col++] = '*';
            row += directionDown ? 1 : -1;
        }

        // Fill the characters in the marked positions
        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < ciphertext.length(); j++) {
                if (railMatrix[i][j] == '*' && index < ciphertext.length()) {
                    railMatrix[i][j] = ciphertext.charAt(index++);
                }
            }
        }

        // Now read the matrix in zig-zag manner to get the original text
        StringBuilder plainText = new StringBuilder();
        row = 0;
        col = 0;
        directionDown = false;
        for (int i = 0; i < ciphertext.length(); i++) {
            if (row == 0 || row == rails - 1) {
                directionDown = !directionDown;
            }
            plainText.append(railMatrix[row][col++]);
            row += directionDown ? 1 : -1;
        }
        return plainText.toString();
    }

    public static void displayRailPattern(String text, int rails, boolean isEncryption) {
        if (rails <= 1) {
            System.out.println(text);
            return;
        }
        char[][] railMatrix = new char[rails][text.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                railMatrix[i][j] = ' ';
            }
        }
        boolean directionDown = false;
        int row = 0, col = 0;
        for (int i = 0; i < text.length(); i++) {
            if (row == 0 || row == rails - 1) {
                directionDown = !directionDown;
            }
            railMatrix[row][col++] = text.charAt(i);
            row += directionDown ? 1 : -1;
        }
        System.out.println("Rail Fence Pattern (" + (isEncryption ? "Encryption" : "Decryption") + "):");
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                System.out.print(railMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Rail Fence Cipher Implementation ===");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }

            if (choice == 1) {
                System.out.print("Enter plaintext: ");
                String plaintext = sc.nextLine();
                System.out.print("Enter number of rails: ");
                int rails = sc.nextInt();
                sc.nextLine(); // consume newline

                String ciphertext = encrypt(plaintext, rails);
                System.out.println("Encrypted text: " + ciphertext);
                displayRailPattern(plaintext, rails, true);

            } else if (choice == 2) {
                System.out.print("Enter ciphertext: ");
                String ciphertext = sc.nextLine();
                System.out.print("Enter number of rails: ");
                int rails = sc.nextInt();
                sc.nextLine(); // consume newline

                String plaintext = decrypt(ciphertext, rails);
                System.out.println("Decrypted text: " + plaintext);
                displayRailPattern(plaintext, rails, false);

            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }
}
