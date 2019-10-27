package gezer.calc.elements;

public abstract class ArithmeticOperator extends OperatorToken {
    public ArithmeticOperator(String stringReprestation) {
        super(stringReprestation);
    }

    public abstract NumberToken perform(NumberToken... numbers);
    public abstract int numberOfParameters();
}
