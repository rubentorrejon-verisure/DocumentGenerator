import generators.dni.Spain;
import generators.dni.France;
import generators.dni.Italy;
import generators.dni.Argentina;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class DocumentGenerator {

    private static final Map<String, Supplier<String>> GENERATORS = Map.of(
        key("ES", "dni"), Spain::generate,
        key("AR", "dni"), Argentina::generate,
        key("FR", "cni"), France::generate,
        key("IT", "cdi"), Italy::generate,
        key("ES", "iban"), generators.accounts.Spain::generateRandomIBAN,
        key("AR", "cbu"), generators.accounts.Argentina::generateRandomCBU
    );

    private static final Map<String, String> COUNTRY_NAMES = Map.of(
        "ES", "España",
        "FR", "Francia",
        "IT", "Italia",
        "AR", "Argentina"
    );

    private static final Map<String, String[]> AVAILABLE_DOCUMENTS = Map.of(
        "ES", new String[]{"DNI", "IBAN"},
        "FR", new String[]{"CNI"},
        "IT", new String[]{"CDI"},
        "AR", new String[]{"DNI", "CBU"}
    );

    public static String generateDocument(String country, String documentType) {
        String normalizedCountry = country.toUpperCase(Locale.ROOT);
        String normalizedDocumentType = documentType.toLowerCase(Locale.ROOT);

        Supplier<String> generator = GENERATORS.get(key(normalizedCountry, normalizedDocumentType));
        if (generator != null) {
            return generator.get();
        }

        throw buildGenerationError(country, documentType, normalizedDocumentType);
    }

    public static String getCountryName(String country) {
        String countryName = COUNTRY_NAMES.get(country.toUpperCase(Locale.ROOT));
        if (countryName == null) {
            throw new IllegalArgumentException("País no soportado: " + country);
        }
        return countryName;
    }

    public static String[] getAvailableDocuments(String country) {
        String[] documents = AVAILABLE_DOCUMENTS.get(country.toUpperCase(Locale.ROOT));
        if (documents == null) {
            return new String[]{"DNI"};
        }
        return documents.clone();
    }

    private static IllegalArgumentException buildGenerationError(String country, String documentType, String normalizedDocumentType) {
        switch (normalizedDocumentType) {
            case "dni":
                return new IllegalArgumentException("No existe generador de DNI para el país: " + country);
            case "cni":
                return new IllegalArgumentException("No existe generador de CNI para el país: " + country);
            case "cdi":
                return new IllegalArgumentException("No existe generador de CDI para el país: " + country);
            case "iban":
                return new IllegalArgumentException("No existe generador de IBAN para el país: " + country);
            case "cbu":
                return new IllegalArgumentException("No existe generador de CBU para el país: " + country);
            default:
                return new IllegalArgumentException("Tipo de documento no soportado: " + documentType);
        }
    }

    private static String key(String country, String documentType) {
        return country + ":" + documentType;
    }
}
