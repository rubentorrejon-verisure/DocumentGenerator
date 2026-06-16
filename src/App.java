public class App {
    public static void main(String[] args) {
        String pais = "ES";
        String dni = DocumentGenerator.generateDocument(pais, "dni");
        System.out.println("Documento para " + pais + " generado: " + dni);
    }
}