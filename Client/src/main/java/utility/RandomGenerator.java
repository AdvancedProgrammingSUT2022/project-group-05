package utility;

import java.util.Random;

public class RandomGenerator{
    private static final Random random;
    static {
        random = new Random(System.currentTimeMillis());
    }
    public static int nextInt(int max) {
        return Math.abs(random.nextInt()) % max;
    }
}
