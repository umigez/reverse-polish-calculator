package gezer.calc.elements;

import java.math.BigDecimal;

public class MultiplicationOperator extends ArithmeticOperator {
    public static final MultiplicationOperator INSTANCE = new MultiplicationOperator();

    private MultiplicationOperator() {
        super("*");
    }

    @Override
    public NumberToken perform(NumberToken... numbers) {
        BigDecimal sum = numbers[1].getNumber().multiply(numbers[0].getNumber());

        return NumberToken.from(sum);
    }

    @Override
    public int numberOfParameters() {
        return 2;
    }
}
