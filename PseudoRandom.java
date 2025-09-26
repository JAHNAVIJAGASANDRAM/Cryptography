
import java.util.Scanner;
public class PseudoRandom {
    private long seed;
    private final long a;
    private final long c;
    private final long m;

    public PseudoRandom(long seed, long a, long c, long m) {
        this.seed = seed;
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public long next() {
        seed = (a * seed + c) % m;
        return seed;
    }
    public static void main(String[] args) {
       //pseudo-random number generator using Linear Congruential Generator (LCG) method
       Scanner scanner = new Scanner(System.in);

        System.out.print("Enter seed (X0): ");
        long seed = scanner.nextLong();

        System.out.print("Enter multiplier (a): ");
        long a = scanner.nextLong();

        System.out.print("Enter increment (c): ");
        long c = scanner.nextLong();

       
        long m =  (long) Math.pow(2, 31); 

        PseudoRandom lcg = new PseudoRandom(seed, a, c, m);

        System.out.println("How many random numbers do you want to generate?");
        int n = scanner.nextInt();

        System.out.println("Generated pseudo-random numbers:");
        for (int i = 0; i < n; i++) {
            System.out.println(lcg.next());
        }

        scanner.close();

    }

    
}
