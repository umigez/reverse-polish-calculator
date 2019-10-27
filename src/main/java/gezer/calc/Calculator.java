package gezer.calc;

import gezer.calc.elements.*;
import gezer.stack.ImmutableStack;

public class Calculator {
    private ImmutableStack<NumberToken> currentStack = ImmutableStack.empty();
    private ImmutableStack<ImmutableStack<NumberToken>> previousStacks = ImmutableStack.empty();

    public String processInput(String input) throws NotEnoughNumbersException {
        if (input != null && input.trim().length() > 0) {
            String[] tokenizesArray = input.trim().split("\\s");

            int count = 0;
            for (String token : tokenizesArray) {
                try {
                    processToken(token);
                } catch (NotEnoughNumbersException e) {
                    // I know the position here is not correct is using multiple digit numbers...
                    String msg = String.format("operator %s (position: %d): insufficient parameters",
                            token, count * 2 + 1);
                    throw new NotEnoughNumbersException(msg, e.getCause());
                }
                count++;
            }
        }

        return currentStack.toString();
    }

    @Override
    public String toString() {
        return currentStack.toString();
    }

    private void processToken(String token) throws NotEnoughNumbersException {
        InputToken inputToken = InputToken.ofToken(token);
        if (inputToken.getClass() == NumberToken.class) {
            pushNumber((NumberToken) inputToken);
        } else {
            processOperator((OperatorToken) inputToken);
        }
    }

    private void processOperator(OperatorToken operator) throws NotEnoughNumbersException {
        if (operator instanceof ArithmeticOperator) {
            performArithmetic((ArithmeticOperator) operator);
        } else if (operator == ClearOperator.INSTANCE) {
            clearStack();
        } else if (operator == UndoOperator.INSTANCE) {
            undoStack();
        } else {
            throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    private void performArithmetic(ArithmeticOperator operator) throws NotEnoughNumbersException {
        final ImmutableStack<NumberToken> _currentStack = currentStack;
        final int numOfParameters = operator.numberOfParameters();

        NumberToken[] parameters = popParameters(numOfParameters); //throws if not enough params
        NumberToken result = operator.perform(parameters);

        previousStacks = previousStacks.push(_currentStack);
        currentStack = currentStack.push(result);
    }

    private void undoStack() {
        if (!previousStacks.isEmpty()) {
            currentStack = previousStacks.peek();
            previousStacks = previousStacks.pop();
        }
    }

    private void clearStack() {
        previousStacks = previousStacks.push(currentStack);
        currentStack = ImmutableStack.empty();
    }

    /**
     * Pops the request number of params. If the stack is not deep enough, an exception is thrown and the stack is
     * not changed.
     *
     * @param numOfParameters num of params to pop
     * @throws NotEnoughNumbersException if the stack is not deep enough
     */
    private NumberToken[] popParameters(int numOfParameters) throws NotEnoughNumbersException {
        // Check if our stack is deep enough, without changing the currentStack
        try {
            ImmutableStack<NumberToken> checkStack = currentStack;
            for (int i = 0; i < numOfParameters; i++) {
                checkStack = checkStack.pop();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new NotEnoughNumbersException("", e);
        }

        NumberToken[] numberTokenList = new NumberToken[numOfParameters];
        for (int i = 0; i < numOfParameters; i++) {
            numberTokenList[i] = popStack();
        }

        return numberTokenList;
    }

    private void pushNumber(NumberToken number) {
        // Make sure to save the previous stack when push, so we can undo
        previousStacks = previousStacks.push(currentStack);
        currentStack = currentStack.push(number);
    }

    private NumberToken popStack() {
        // Does NOT save the previous stack!
        NumberToken n = currentStack.peek();
        currentStack = currentStack.pop();

        return n;
    }
}
