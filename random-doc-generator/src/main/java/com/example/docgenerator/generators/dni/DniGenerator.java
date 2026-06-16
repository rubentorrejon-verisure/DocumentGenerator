package generators.dni;

import java.util.Random;

public class DniGenerator {

    private static final String LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";
    private static final Random RANDOM = new Random();

    public static String generate(String country) {
        if (!"ES".equalsIgnoreCase(country)) {
            throw new IllegalArgumentException("No existe generador de DNI para el país: " + country);
        }
        String number = generateDigits(8);
        char letter = LETTERS.charAt(Integer.parseInt(number) % LETTERS.length());
        return number + letter;
    }

    private static String generateDigits(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(RANDOM.nextInt(10));
        }
        return builder.toString();
    }
}
