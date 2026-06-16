package generators.accounts;

import java.util.Random;

public class Argentina {

    private static final Random RANDOM = new Random();

    private static final int[] BANK_WEIGHTS = {7, 1, 3, 9, 7, 1, 3};
    private static final int[] ACCOUNT_WEIGHTS = {3, 9, 7, 1, 3, 9, 7, 1, 3, 9, 7, 1, 3};

    public static String generateRandomCBU() {
        String bankCode = String.format("%03d", RANDOM.nextInt(1000));
        String branchCode = String.format("%04d", RANDOM.nextInt(10000));

        String firstSeven = bankCode + branchCode;
        int firstCheckDigit = calculateCheckDigit(firstSeven, BANK_WEIGHTS);
        String firstBlock = firstSeven + firstCheckDigit;

        String accountBase = String.format("%013d", Math.abs(RANDOM.nextLong() % 10_000_000_000_000L));
        int secondCheckDigit = calculateCheckDigit(accountBase, ACCOUNT_WEIGHTS);
        String secondBlock = accountBase + secondCheckDigit;

        return firstBlock + secondBlock;
    }

    private static int calculateCheckDigit(String number, int[] weights) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            int digit = number.charAt(i) - '0';
            sum += digit * weights[i];
        }
        int mod = sum % 10;
        return mod == 0 ? 0 : 10 - mod;
    }
}