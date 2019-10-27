package gezer.calc.elements;

import java.math.BigDecimal;

public class AdditionOperator extends ArithmeticOperator {
    public static final AdditionOperator INSTANCE = new AdditionOperator();

    private AdditionOperator() {
        super("+");
    }

    @Override
    public NumberToken perform(NumberToken... numbers) {
        BigDecimal sum = numbers[0].getNumber().add(numbers[1].getNumber());

        return NumberToken.from(sum);
    }

    @Override
    public int numberOfParameters() {
        return 2;
    }
}
