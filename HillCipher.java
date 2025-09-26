
import java.util.*;

public class HillCipher {
    private static final int MOD = 26;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] key = { { 5, 8 }, { 17, 3 } };
        String plaintext = sc.nextLine();
        System.out.println("original:" + plaintext);
        String encrypted = encrypt(plaintext, key);
        System.out.println("Encryppted:" + encrypted);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted:" + decrypted);
    }

    public static String encrypt(String plaintext, int[][] key) {
        int n = key.length;
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", " ");
        while (plaintext.length() % n != 0) {
            plaintext += "X";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += n) {
            int[] block = new int[n];
            for (int j = 0; j < n; j++) {
                block[j] = plaintext.charAt(i + j) - 'A';
            }
            int[] encryptedBlock = multiply(key, block);
            for (int val : encryptedBlock) {
                result.append((char) (val + 'A'));
            }
        }
        return result.toString();
    }

    public static String decrypt(String ciphertext, int[][] key) {
        int n = key.length;
        int[][] inverseKey = getInverse(key);
        if (inverseKey == null) {
            return "Cannot decrypt: key not invertible";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += n) {
            int[] block = new int[n];
            for (int j = 0; j < n; j++) {
                block[j] = ciphertext.charAt(i + j) - 'A';
            }
            int[] decryptedBlock = multiply(inverseKey, block);
            for (int val : decryptedBlock) {
                result.append((char) (val + 'A'));
            }
        }
        return result.toString();

    }

    private static int[] multiply(int[][] matrix, int[] vector) {
        int n = matrix.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];

            }
            result[i] = ((result[i] % MOD) + MOD) % MOD;
        }
        return result;
    }

    private static int[][] getInverse(int[][] matrix) {
        if (matrix.length == 2) {
            int det = (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0] % MOD);

            if (det < 0)
                det += MOD;
            int detInv = modInverse(det);
            if (detInv == -1)
                return null;
            return new int[][] {
                    {
                            (matrix[1][1] * detInv) % MOD, ((-matrix[0][1] + MOD) * detInv) % MOD
                    },
                    {
                            ((-matrix[1][0] + MOD) * detInv) % MOD, (matrix[0][0] * detInv % MOD)
                    }
            };
        }
        return null;
    }

    private static int modInverse(int a) {
        a = ((a % MOD) + MOD) % MOD;
        for (int x = 1; x < MOD; x++) {
            if ((a * x) % MOD == 1) {
                return x;
            }
        }
        return -1;
    }

}
