import generators.dni.Spain;
import generators.dni.France;
import generators.dni.Italy;
import generators.dni.Argentina;

public class DocumentGenerator {

    public static String generateDocument(String country, String documentType) {
        switch (documentType.toLowerCase()) {
            case "dni":
                switch (country.toUpperCase()) {
                    case "ES":
                        return Spain.generate(country);
                    case "FR":
                        return France.generate(country);
                    case "AR":
                        return Argentina.generate(country);
                    default:
                        throw new IllegalArgumentException("No existe generador de DNI para el país: " + country);
                }
            case "cni":
                switch (country.toUpperCase()) {
                    case "FR":
                        return France.generate(country);
                    default:
                        throw new IllegalArgumentException("No existe generador de CNI para el país: " + country);
                }
            case "cdi":
                switch (country.toUpperCase()) {
                    case "IT":
                        return Italy.generate(country);
                    default:
                        throw new IllegalArgumentException("No existe generador de CDI para el país: " + country);
                }
            case "iban":
                switch (country.toUpperCase()) {
                    case "ES":
                        return generators.accounts.Spain.generateRandomIBAN();
                    default:
                        throw new IllegalArgumentException("No existe generador de IBAN para el país: " + country);
                }
            case "cbu":
                switch (country.toUpperCase()) {
                    case "AR":
                        return generators.accounts.Argentina.generateRandomCBU();
                    default:
                        throw new IllegalArgumentException("No existe generador de CBU para el país: " + country);
                }
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado: " + documentType);
        }
    }

    public static String getCountryName(String country) {
        switch (country.toUpperCase()) {
            case "ES":
                return Spain.getCountryName();
            case "FR":
                return France.getCountryName();
            case "IT":
                return Italy.getCountryName();
            case "AR":
                return Argentina.getCountryName();
            default:
                throw new IllegalArgumentException("País no soportado: " + country);
        }
    }

    public static String[] getAvailableDocuments(String country) {
        switch (country.toUpperCase()) {
            case "ES":
                return new String[]{"DNI", "IBAN"};
            case "FR":
                return new String[]{"CNI"};
            case "IT":
                return new String[]{"CDI"};
            case "AR":
                return new String[]{"DNI", "CBU"};
            default:
                return new String[]{"DNI"};
        }
    }
}
