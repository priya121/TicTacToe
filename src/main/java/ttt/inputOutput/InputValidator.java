package ttt.inputOutput;

public class InputValidator {

    private IO io;

    public InputValidator(IO io){
        this.io = io;
    }
    public boolean checksDigit(String userInput) {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException e) {
            io.showOutput("Please enter a valid number");
        }
        return false;
    }
}
