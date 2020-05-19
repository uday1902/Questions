package src.main.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class PrimePalindrome {
    Map<Integer, Integer> primes = new LinkedHashMap<>();
    int lastIndex = 0;

    public PrimePalindrome() {
        primes.put(2, 1);
        primes.put(3, 2);
        primes.put(5, 3);
        primes.put(7, 4);
        primes.put(11, 5);
        lastIndex = 5;
    }

    public int primePalindrome(int N) {
        if (N > 0 && N < 3) {
            return N + 1;
        }
        populatePrimes(N);
        for (int i = N + 1; ; i++) {
            if (isPalindrome(i) && isPrime(i)) {
                return i;
            }
        }
    }

    public void populatePrimes(int N) {
        for (int i = 13; i <= N; i = i + 2) {
            if (isPrime(i)) {
                primes.put(i, ++lastIndex);
            }
        }
    }

    public boolean isPrime(int N) {
        if (N < 12) {
            return primes.containsKey(N);
        }
        Iterator<Integer> it = primes.keySet().iterator();
        for (int ele = it.next(); it.hasNext() && ele * ele <= N; ele = it.next()) {
            if (N % ele == 0) {
                return false;
            }
        }
        primes.put(N,++lastIndex);
        return true;
    }

    private boolean isPalindrome(int N) {
        return N == getReverse(N);
    }

    private int getReverse(int N) {
        int reverse = 0;
        while (N > 0) {
            reverse = reverse * 10 + N % 10;
            N = N / 10;
        }
        return reverse;
    }

    @Test
    public void testPrimePalindrome() {
        //Assert.assertEquals(11, primePalindrome(7));
        //Assert.assertEquals(101, primePalindrome(13));
        Assert.assertEquals(101, primePalindrome(102));
    }
}
