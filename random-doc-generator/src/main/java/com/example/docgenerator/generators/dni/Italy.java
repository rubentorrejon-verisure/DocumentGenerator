package generators.dni;

import java.util.Random;

public class Italy {

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    public static String generate() {
        return generateCdi();
    }

    private static String generateCdi() {
        String prefix = randomLetters(2);
        String serial = randomDigits(7);
        char control = calculateControlCharacter(prefix + serial);
        return prefix + serial + control;
    }

    private static char calculateControlCharacter(String base) {
        int sum = 0;
        for (int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            int value;
            if (Character.isDigit(c)) {
                value = c - '0';
            } else {
                value = c - 'A' + 10;
            }
            int weight = (i % 2 == 0) ? 3 : 1;
            sum += value * weight;
        }
        return LETTERS.charAt(sum % 26);
    }

    private static String randomLetters(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        }
        return builder.toString();
    }

    private static String randomDigits(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(RANDOM.nextInt(10));
        }
        return builder.toString();
    }
}
