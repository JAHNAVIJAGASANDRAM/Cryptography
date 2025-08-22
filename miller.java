
// Use this editor to write, compile and run your Java code online
import java.util.Random;
import java.util.*;

public class miller {

    // Modular exponentiation (x^y % p)
    static long modExp(long x, long y, long p) {
        long result = 1;
        x = x % p;
        while (y > 0) {
            if ((y & 1) == 1)
                result = (result * x) % p;
            y = y >> 1;
            x = (x * x) % p;
        }
        return result;
    }

    // Miller test for a single base 'a'
    static boolean millerTest(long d, long n, long a) {
        long x = modExp(a, d, n);

        if (x == 1 || x == n - 1)
            return true;

        while (d != n - 1) {
            x = (x * x) % n;
            d *= 2;

            if (x == 1)
                return false;
            if (x == n - 1)
                return true;
        }
        return false;
    }

    // Miller-Rabin primality test
    static boolean isPrime(long n, int k) {
        if (n <= 1 || n == 4)
            return false;
        if (n <= 3)
            return true;

        // Find d such that n-1 = d * 2^r
        long d = n - 1;
        while (d % 2 == 0)
            d /= 2;

        Random rand = new Random();

        for (int i = 0; i < k; i++) {
            long a = 2 + Math.abs(rand.nextLong()) % (n - 4);
            if (!millerTest(d, n, a))
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
       
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number greater than 3");
    long num=sc.nextLong();

        if (isPrime(num, iterations))
            System.out.println(num + " is probably prime.");
        else
            System.out.println(num + " is composite.");
    }
}
