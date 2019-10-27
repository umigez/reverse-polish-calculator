package gezer.calc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    private Calculator c;

    @Before
    public void before() {
        c = new Calculator();
    }

    @Test
    public void emptyStack() throws NotEnoughNumbersException {
        String result = c.processInput("");

        assertEquals("", result);
    }

    @Test
    public void pushDoubles() throws NotEnoughNumbersException {
        String result = c.processInput("5.2546 2.54134846");

        assertEquals("5.2546 2.54134846", result);
    }

    @Test
    public void pushBigNumber() throws NotEnoughNumbersException {
        String result = c.processInput("123456789123456789");

        assertEquals("123456789123456789", result);
    }

    @Test
    public void operateOnLargeDouble() throws NotEnoughNumbersException {
        String result = c.processInput("45.5 2.25 *");

        assertEquals("102.375", result);
    }

    @Test
    public void roundLongDecimalPlaces() throws NotEnoughNumbersException {
        String result = c.processInput("5.12345678912");

        assertEquals("5.1234567891", result);
    }

    @Test
    public void pushInts() throws NotEnoughNumbersException {
        String result = c.processInput("5 2");

        assertEquals("5 2", result);
    }

    @Test
    public void addition() throws NotEnoughNumbersException {
        String r = c.processInput("2 5 +");
        assertEquals("7", r);
    }

    @Test
    public void subtraction() throws NotEnoughNumbersException {
        String r = c.processInput("5 2 -");
        assertEquals("3", r);
    }

    @Test
    public void multiplication() throws NotEnoughNumbersException {
        String r = c.processInput("5 2 *");
        assertEquals("10", r);
    }

    @Test
    public void division() throws NotEnoughNumbersException {
        String r = c.processInput("10 2 /");
        assertEquals("5", r);
    }

    @Test
    public void squareRoot() throws NotEnoughNumbersException {
        String r = c.processInput("9 sqrt");
        assertEquals("3", r);
    }

    @Test
    public void squareRootIrrational() throws NotEnoughNumbersException {
        String r = c.processInput("2 sqrt");
        assertEquals("1.4142135624", r);
    }

    @Test
    public void clear() throws NotEnoughNumbersException {
        String r = c.processInput("2 3 5 7");
        assertEquals("2 3 5 7", r);

        r = c.processInput("clear");
        assertEquals("", r);
    }

    @Test
    public void undo() throws NotEnoughNumbersException {
        String r = c.processInput("5 4 3 2");
        assertEquals("5 4 3 2", r);

        r = c.processInput("undo undo *");
        assertEquals("20", r);

        r = c.processInput("5 *");
        assertEquals("100", r);

        r = c.processInput("undo");
        assertEquals("20 5", r);
    }

    @Test
    public void undoOperator() throws NotEnoughNumbersException {
        String r = c.processInput("5 4");
        assertEquals("5 4", r);

        r = c.processInput("*");
        assertEquals("20", r);

        r = c.processInput("undo");
        assertEquals("5 4", r);
    }

    @Test
    public void clearAndUndo() throws NotEnoughNumbersException {
        String r = c.processInput("5 4");
        assertEquals("5 4", r);

        r = c.processInput("3 2");
        assertEquals("5 4 3 2", r);

        r = c.processInput("clear");
        assertEquals("", r);

        r = c.processInput("undo");
        assertEquals("5 4 3 2", r);

        r = c.processInput("undo");
        assertEquals("5 4 3", r);

        r = c.processInput("undo");
        assertEquals("5 4", r);
    }

    @Test
    public void divideAndMultiple() throws NotEnoughNumbersException {
        String r = c.processInput("7 12 2 /");
        assertEquals("7 6", r);

        r = c.processInput("*");
        assertEquals("42", r);

        r = c.processInput("4 /");
        assertEquals("10.5", r);
    }

    @Test
    public void multipleAndMinus() throws NotEnoughNumbersException {
        String r = c.processInput("1 2 3 4 5");
        assertEquals("1 2 3 4 5", r);

        r = c.processInput("*");
        assertEquals("1 2 3 20", r);

        r = c.processInput("clear 3 4 -");
        assertEquals("-1", r);
    }

    @Test
    public void severalMultiple() throws NotEnoughNumbersException {
        String r = c.processInput("1 2 3 4 5");
        assertEquals("1 2 3 4 5", r);

        r = c.processInput("* * * *");
        assertEquals("120", r);
    }

    @Test
    public void notEnoughNumbers() {
        try {
            c.processInput("1 2 3 * 5 + * * 6 5");
        } catch (NotEnoughNumbersException e) {
            assertEquals(e.getMessage(), "operator * (position: 15): insufficient parameters");
        }
        assertEquals("11", c.toString());

        try {
            c.processInput("clear");
            c.processInput("8 + 6 5");
        } catch (NotEnoughNumbersException e) {
            assertEquals(e.getMessage(), "operator + (position: 3): insufficient parameters");
        }
        assertEquals("8", c.toString());
    }
}