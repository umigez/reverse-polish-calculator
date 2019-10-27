package gezer.calc.elements;

public class OperatorToken extends InputToken {
    private final String stringReprestation;

    public OperatorToken(String stringReprestation) {
        this.stringReprestation = stringReprestation;
    }

    @Override
    public String toString() {
        return stringReprestation;
    }
}
