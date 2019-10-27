package gezer.calc.elements;

import java.math.BigDecimal;

public class SquareRootOperator extends ArithmeticOperator {
    public static final SquareRootOperator INSTANCE = new SquareRootOperator();

    private SquareRootOperator() {
        super("sqrt");
    }

    @Override
    public NumberToken perform(NumberToken... numbers) {
        //Java 9 has sqrt on BigDecimal directly
        double sqrt = Math.sqrt(numbers[0].getNumber().doubleValue());
        BigDecimal r = BigDecimal.valueOf(sqrt);

        return NumberToken.from(r);
    }

    @Override
    public int numberOfParameters() {
        return 1;
    }
}
