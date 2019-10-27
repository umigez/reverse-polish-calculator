package gezer.calc.elements;

public class ClearOperator extends OperatorToken {
    public static final ClearOperator INSTANCE = new ClearOperator();

    public ClearOperator() {
        super("clear");
    }
}
