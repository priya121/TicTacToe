package ttt.inputOutput;

public class DigitValidator {

    public DigitValidator() {
    }

    public boolean check(String userInput) {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
}
