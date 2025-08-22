import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class DiscreteLog {

    
    public static BigInteger sqrt(BigInteger n) {
        BigInteger a = BigInteger.ONE;
        BigInteger b = n.shiftRight(5).add(BigInteger.valueOf(8)); // approximation

        while (b.compareTo(a) >= 0) {
            BigInteger mid = a.add(b).shiftRight(1);
            int cmp = mid.multiply(mid).compareTo(n);
            if (cmp > 0) {
                b = mid.subtract(BigInteger.ONE);
            } else {
                a = mid.add(BigInteger.ONE);
            }
        }
        return a.subtract(BigInteger.ONE);
    }

    
    public static BigInteger dislog(BigInteger g, BigInteger b, BigInteger p) {
        BigInteger m = sqrt(p).add(BigInteger.ONE);

        Map<BigInteger, BigInteger> babySteps = new HashMap<>();

        
        BigInteger giantStep = b;
        for (BigInteger j = BigInteger.ZERO; j.compareTo(m) < 0; j = j.add(BigInteger.ONE)) {
            babySteps.put(giantStep, j);
            giantStep = giantStep.multiply(g).mod(p);
        }

        
        BigInteger factor = g.modPow(m, p).modInverse(p);

        giantStep = BigInteger.ONE;

        
        for (BigInteger i = BigInteger.ZERO; i.compareTo(m) < 0; i = i.add(BigInteger.ONE)) {
            if (babySteps.containsKey(giantStep)) {
                BigInteger j = babySteps.get(giantStep);
                return i.multiply(m).add(j);
            }
            giantStep = giantStep.multiply(factor).mod(p);
        }

        return BigInteger.valueOf(-1); 
    }

    public static void main(String[] args) {
        
        BigInteger g = BigInteger.valueOf(2);
        BigInteger p = BigInteger.valueOf(1019);
        BigInteger b = BigInteger.valueOf(5);

        BigInteger x = dislog(g, b, p);
        if (x.compareTo(BigInteger.valueOf(-1)) == 0) {
            System.out.println("No solution found");
        } else {
            System.out.println("Discrete log is: " + x);
            System.out.println("Check: g^x mod p = " + g.modPow(x, p));
        }
    }
}
