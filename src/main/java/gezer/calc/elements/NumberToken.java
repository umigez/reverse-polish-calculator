package gezer.calc.elements;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class NumberToken extends InputToken {
    private final BigDecimal number;

    private NumberToken(@NotNull BigDecimal number) {
        Objects.requireNonNull(number);
        this.number = number.setScale(15, RoundingMode.HALF_EVEN);
    }

    public static NumberToken from(BigDecimal number) {
        return new NumberToken(number);
    }

    public BigDecimal getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number.setScale(10, RoundingMode.HALF_EVEN).stripTrailingZeros().toPlainString();
    }
}
