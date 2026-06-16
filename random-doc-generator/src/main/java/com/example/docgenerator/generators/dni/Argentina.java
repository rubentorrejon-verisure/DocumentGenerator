package generators.dni;

import java.util.Random;

public class Argentina {

    private static final Random RANDOM = new Random();

    public static String generate() {
        return generateDni();
    }

    private static String generateDni() {
        int value = 10_000_000 + RANDOM.nextInt(90_000_000);
        return String.valueOf(value);
    }
}