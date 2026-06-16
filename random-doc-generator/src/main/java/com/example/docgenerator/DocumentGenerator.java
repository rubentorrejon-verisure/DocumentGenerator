import generators.dni.DniGenerator;

public class DocumentGenerator {

    public static String generateDocument(String country, String documentType) {
        switch (documentType.toLowerCase()) {
            case "dni":
                return DniGenerator.generate(country);
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado: " + documentType);
        }
    }
}
