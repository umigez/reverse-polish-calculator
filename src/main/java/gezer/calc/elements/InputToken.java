package gezer.calc.elements;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public abstract class InputToken {

    @NotNull
    public static InputToken ofToken(String token) {
        try {
            // Try a number first
            BigDecimal number = new BigDecimal(token);
            return NumberToken.from(number);
        } catch (NumberFormatException e) {
            // Not a number - check if operator
            if (AdditionOperator.INSTANCE.toString().equals(token)) {
                return AdditionOperator.INSTANCE;
            } else if (SubtractionOperator.INSTANCE.toString().equals(token)) {
                return SubtractionOperator.INSTANCE;
            } else if (MultiplicationOperator.INSTANCE.toString().equals(token)) {
                return MultiplicationOperator.INSTANCE;
            } else if (DivisionOperator.INSTANCE.toString().equals(token)) {
                return DivisionOperator.INSTANCE;
            } else if (SquareRootOperator.INSTANCE.toString().equals(token)) {
                return SquareRootOperator.INSTANCE;
            } else if (ClearOperator.INSTANCE.toString().equals(token)) {
                return ClearOperator.INSTANCE;
            } else if (UndoOperator.INSTANCE.toString().equals(token)) {
                return UndoOperator.INSTANCE;
            }
        }

        throw new IllegalArgumentException("Unknown input " + token);
    }
}
