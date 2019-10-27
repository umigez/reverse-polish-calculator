package gezer.calc.elements;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivisionOperator extends ArithmeticOperator {
    public static final DivisionOperator INSTANCE = new DivisionOperator();

    private DivisionOperator() {
        super("/");
    }

    @Override
    public NumberToken perform(NumberToken... numbers) {
        BigDecimal sum = numbers[1].getNumber().divide(numbers[0].getNumber(), RoundingMode.HALF_EVEN);

        return NumberToken.from(sum);
    }

    @Override
    public int numberOfParameters() {
        return 2;
    }
}
