import generators.dni.Spain;
import generators.dni.France;

public class DocumentGenerator {

    public static String generateDocument(String country, String documentType) {
        switch (documentType.toLowerCase()) {
            case "dni":
                switch (country.toUpperCase()) {
                    case "ES":
                        return Spain.generate(country);
                    case "FR":
                        return France.generate(country);
                    default:
                        throw new IllegalArgumentException("No existe generador de DNI para el país: " + country);
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
            default:
                throw new IllegalArgumentException("País no soportado: " + country);
        }
    }
}
