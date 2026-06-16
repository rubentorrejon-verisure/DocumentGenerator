public class App {
    public static void main(String[] args) {
        String dni = DocumentGenerator.generateDocument("ES", "dni");
        System.out.println("DNI generado: " + dni);
    }
}