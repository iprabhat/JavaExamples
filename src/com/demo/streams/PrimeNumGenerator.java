package com.demo.streams;

public class PrimeNumGenerator {
    private long curPrime = 0L;

    public long next() {
        curPrime = nextPrime(curPrime);
        return curPrime;
    }

    public static long nextPrime(Long num) {
        long primeNumAfter = num;
        while(!isPrime(++primeNumAfter));
        return primeNumAfter;
    }

    public static boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }

        long maxDiv = (long)Math.sqrt(num);
        for (int i = 3; i <= maxDiv; i += 2) {
            if(num % i == 0){
                return false;
            }            
        }
        return true;
    }
}
