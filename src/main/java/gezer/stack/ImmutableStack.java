package gezer.stack;

public interface ImmutableStack<T> extends Iterable<T> {
    ImmutableStack<T> push(T value);


    /**
     *
     * @return the new stack after the pop operation.
     * @throws IndexOutOfBoundsException if the stack is empty.
     */
    ImmutableStack<T> pop();

    /**
     *
     * @return the top value of the stack.
     * @throws IndexOutOfBoundsException if the stack is empty.
     */
    T peek();

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    boolean isEmpty();

    static <U> ImmutableStack<U> empty() {
        return ImmutableStackImpl.empty();
    }
}
