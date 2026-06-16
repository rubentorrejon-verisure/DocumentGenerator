public class App {
    public static void main(String[] args) {

        String pais = "FR";
        String documentType = "dni";
        
        String document = DocumentGenerator.generateDocument(pais, documentType);
        String countryName = DocumentGenerator.getCountryName(pais);
        System.out.println("Documento para " + countryName + " generado: " + document);
    }
}