package hwss1502;

public class CreditsException extends RuntimeException {
    public CreditsException(String message) {
        super(message);
    }
    public static void checkCredits(int credits) throws CreditsException {
        if  (credits < 0 || credits > 10) {
            throw new CreditsException("Invalid credits!");
        }
    }
}
