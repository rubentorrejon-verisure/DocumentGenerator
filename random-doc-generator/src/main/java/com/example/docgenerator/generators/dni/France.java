package generators.dni;

import java.util.Random;

public class France {

    private static final String COUNTRY_NAME = "Francia";
    private static final Random RANDOM = new Random();

    private static final String[] DEPARTMENTS = {
        "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "21",
        "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
        "32", "33", "34", "35", "36", "37", "38", "39", "40", "41",
        "42", "43", "44", "45", "46", "47", "48", "49", "50", "51",
        "52", "53", "54", "55", "56", "57", "58", "59", "60", "61",
        "62", "63", "64", "65", "66", "67", "68", "69", "70", "71",
        "72", "73", "74", "75", "76", "77", "78", "79", "80", "81",
        "82", "83", "84", "85", "86", "87", "88", "89", "90", "91",
        "92", "93", "94", "95", "971", "972", "973", "974", "976"
    };

    public static String generate(String country) {
        if (!"FR".equalsIgnoreCase(country)) {
            throw new IllegalArgumentException("No existe generador de DNI para el país: " + country);
        }
        return generateNIR();
    }

    public static String getCountryName() {
        return COUNTRY_NAME;
    }

    private static String generateNIR() {
        StringBuilder nir = new StringBuilder();
        
        // Dígito 1: Sexo (1 = hombre, 2 = mujer)
        int sexo = RANDOM.nextInt(2) + 1;
        nir.append(sexo);
        
        // Dígitos 2-3: Año de nacimiento (1930-2010)
        int year = 1930 + RANDOM.nextInt(81);
        String yearStr = String.format("%02d", year % 100);
        nir.append(yearStr);
        
        // Dígitos 4-5: Mes de nacimiento (01-12)
        int month = 1 + RANDOM.nextInt(12);
        nir.append(String.format("%02d", month));
        
        // Dígitos 6-7: Departamento de nacimiento
        String department = DEPARTMENTS[RANDOM.nextInt(DEPARTMENTS.length)];
        if (department.length() == 2) {
            nir.append(department);
        } else {
            // Para departamentos de 3 dígitos (ultramar), usar solo los 2 primeros
            nir.append(department.substring(0, 2));
        }
        
        // Dígitos 8-10: Municipio de nacimiento (000-999)
        nir.append(String.format("%03d", RANDOM.nextInt(1000)));
        
        // Dígitos 11-13: Orden de registro (001-999)
        nir.append(String.format("%03d", RANDOM.nextInt(999) + 1));
        
        // Dígitos 14-15: Dígitos de control
        String nirWithoutControl = nir.toString();
        int controlDigits = calculateControlDigits(nirWithoutControl);
        nir.append(String.format("%02d", controlDigits));
        
        return nir.toString();
    }

    private static int calculateControlDigits(String nirBase) {
        long nirNumber = Long.parseLong(nirBase);
        int control = (int) (97 - (nirNumber % 97));
        return control;
    }
}
