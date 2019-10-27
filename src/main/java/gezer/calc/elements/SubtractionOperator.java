package gezer.calc.elements;

import java.math.BigDecimal;

public class SubtractionOperator extends ArithmeticOperator {
    public static final SubtractionOperator INSTANCE = new SubtractionOperator();

    private SubtractionOperator() {
        super("-");
    }

    @Override
    public NumberToken perform(NumberToken... numbers) {
        BigDecimal sum = numbers[1].getNumber().subtract(numbers[0].getNumber());

        return NumberToken.from(sum);
    }

    @Override
    public int numberOfParameters() {
        return 2;
    }
}
