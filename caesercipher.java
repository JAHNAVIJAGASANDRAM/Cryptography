
public class caesercipher {

    public static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                char c = (char) (((ch - 'A' + key) % 26) + 'A');
                result.append(c);
            } else if (Character.isLowerCase(ch)) {
                char c = (char) (((ch - 'a' + key) % 26) + 'a');
                result.append(c);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int key) {
        return encrypt(text, 26 - (key % 26)); // shifting backward is same as shifting forward by 26-key
    }

    public static void main(String[] args) {
        String plaintext = "Hello World!";
        int key = 3;

        String encrypted = encrypt(plaintext, key);
        System.out.println("Encryp text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted text: " + decrypted);
    }
}
