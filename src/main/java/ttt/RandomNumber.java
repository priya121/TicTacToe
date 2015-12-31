package ttt;

import java.util.Random;

public class RandomNumber {

    public int generate(int max) {
        Random randomGenerator  = new Random();
        return randomGenerator.nextInt(max);
    }
}
