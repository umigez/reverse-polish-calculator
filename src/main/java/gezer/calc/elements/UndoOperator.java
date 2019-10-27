package gezer.calc.elements;

public class UndoOperator extends OperatorToken {
    public static final UndoOperator INSTANCE = new UndoOperator();

    public UndoOperator() {
        super("undo");
    }
}
