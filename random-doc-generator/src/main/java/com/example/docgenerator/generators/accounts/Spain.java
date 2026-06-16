package generators.accounts;

import java.util.Random;

public class Spain {
    private static final Random random = new Random();
    
    /**
     * Generates a random valid Spanish IBAN (International Bank Account Number)
     * Format: ES + 2 check digits + 20 digits
     * Structure: ES + 2 digits + 4 digits bank code + 4 digits branch code + 1 check digit + 11 account number
     * 
     * @return A randomly generated Spanish IBAN
     */
    public static String generateRandomIBAN() {
        // Generate 20 random digits for the account part
        StringBuilder ibanNumber = new StringBuilder("ES");
        
        // Generate 4 digits for bank code
        ibanNumber.append(String.format("%04d", random.nextInt(10000)));
        
        // Generate 4 digits for branch code
        ibanNumber.append(String.format("%04d", random.nextInt(10000)));
        
        // Generate 1 check digit
        ibanNumber.append(random.nextInt(10));
        
        // Generate 11 digits for account number
        ibanNumber.append(String.format("%011d", Math.abs(random.nextLong() % 100000000000L)));
        
        // Calculate check digits
        String checkDigits = calculateCheckDigits(ibanNumber.toString());
        
        // Replace the XX with calculated check digits
        ibanNumber.replace(2, 4, checkDigits);
        
        return formatIBAN(ibanNumber.toString());
    }
    
    /**
     * Calculates the IBAN check digits using mod-97 algorithm
     * 
     * @param iban The IBAN without check digits
     * @return The 2-digit check code
     */
    private static String calculateCheckDigits(String iban) {
        // Move first 4 characters to the end
        String rearranged = iban.substring(4) + iban.substring(0, 4);
        
        // Replace letters with numbers (A=10, B=11, ..., Z=35)
        StringBuilder numeric = new StringBuilder();
        for (char c : rearranged.toCharArray()) {
            if (Character.isLetter(c)) {
                numeric.append(Character.getNumericValue(c));
            } else {
                numeric.append(c);
            }
        }
        
        // Calculate mod 97
        long remainder = 0;
        for (char digit : numeric.toString().toCharArray()) {
            remainder = (remainder * 10 + Character.getNumericValue(digit)) % 97;
        }
        
        // Check digits are 98 - remainder
        int checkDigit = 98 - (int) remainder;
        return String.format("%02d", checkDigit);
    }
    
    /**
     * Formats the IBAN with spaces for readability
     * Format: ESXX XXXX XXXX XXXX XXXX XXXX
     * 
     * @param iban The unformatted IBAN
     * @return The formatted IBAN with spaces
     */
    private static String formatIBAN(String iban) {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < iban.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                formatted.append(" ");
            }
            formatted.append(iban.charAt(i));
        }
        return formatted.toString();
    }
}
