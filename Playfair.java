import java.util.*;
public class Playfair {

    static char[][] matrix = new char[5][5];
    static String key = "MONARCHY";

    public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
        generateMatrix(key);
        String str = sc.nextLine();
        String prepared = prepareText(str);
        System.out.println("Encrypted: " + encrypt(prepared));
    }

    static void generateMatrix(String key) {
        boolean[] used = new boolean[26];
        used['J' - 'A'] = true; 
        StringBuilder sb = new StringBuilder();

        for (char c : key.toUpperCase().toCharArray())
            if (!used[c - 'A'] && c >= 'A' && c <= 'Z') {
                sb.append(c);
                used[c - 'A'] = true;
            }

        for (char c = 'A'; c <= 'Z'; c++)
            if (!used[c - 'A'])
                sb.append(c);

        for (int i = 0; i < 25; i++)
            matrix[i / 5][i % 5] = sb.charAt(i);
    }

    static String prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I');
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c1 = text.charAt(i);
            sb.append(c1);
            if (i + 1 == text.length()) {
                sb.append('X');
                break;
            }
            char c2 = text.charAt(i + 1);
            if (c1 == c2) sb.append('X');
            else { sb.append(c2); i++; }
        }
        return sb.toString();
    }

    static String encrypt(String text) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int[] p1 = findPos(text.charAt(i));
            int[] p2 = findPos(text.charAt(i + 1));

            if (p1[0] == p2[0]) { // same row
                res.append(matrix[p1[0]][(p1[1] + 1) % 5]);
                res.append(matrix[p2[0]][(p2[1] + 1) % 5]);
            } else if (p1[1] == p2[1]) { 
                res.append(matrix[(p1[0] + 1) % 5][p1[1]]);
                res.append(matrix[(p2[0] + 1) % 5][p2[1]]);
            } else { 
                res.append(matrix[p1[0]][p2[1]]);
                res.append(matrix[p2[0]][p1[1]]);
            }
        }

        return res.toString();
    }

    static int[] findPos(char c) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == c) return new int[] { i, j };
        return null;
    }
}
