package generators.dni;

import java.util.Random;

public class Argentina {

    private static final String COUNTRY_NAME = "Argentina";
    private static final Random RANDOM = new Random();

    public static String generate(String country) {
        if (!"AR".equalsIgnoreCase(country)) {
            throw new IllegalArgumentException("No existe generador de DNI para el pais: " + country);
        }
        return generateDni();
    }

    public static String getCountryName() {
        return COUNTRY_NAME;
    }

    private static String generateDni() {
        int value = 10_000_000 + RANDOM.nextInt(90_000_000);
        return String.valueOf(value);
    }
}