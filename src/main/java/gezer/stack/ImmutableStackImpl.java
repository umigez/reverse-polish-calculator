package gezer.stack;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class ImmutableStackImpl<T> implements ImmutableStack<T> {
    private final T head;
    private final ImmutableStack<T> tail;

    private ImmutableStackImpl(final T head, final ImmutableStack<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    static <U> ImmutableStack<U> empty() {
        return new EmptyStack<>();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public T peek() {
        return this.head;
    }

    @Override
    public ImmutableStack<T> pop() {
        return this.tail;
    }

    @Override
    public ImmutableStack<T> push(T value) {
        return new ImmutableStackImpl<>(value, this);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new StackIterator<>(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (T t : this) {
            sb.insert(0, t.toString() + " ");
        }

        return sb.toString().trim();
    }

    private static class StackIterator<U> implements Iterator<U> {
        private ImmutableStack<U> stack;

        public StackIterator(final ImmutableStack<U> stack) {
            this.stack = stack;
        }

        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override
        public U next() {
            U result = this.stack.peek();
            this.stack = this.stack.pop();
            return result;
        }

        @Override
        public void remove() {
        }
    }

    private static class EmptyStack<U> implements ImmutableStack<U> {
        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public U peek() {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public ImmutableStack<U> pop() {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public ImmutableStack<U> push(U value) {
            return new ImmutableStackImpl<>(value, this);
        }

        @NotNull
        @Override
        public Iterator<U> iterator() {
            return new StackIterator<>(this);
        }

        @Override
        public String toString() {
            return "";
        }
    }
}
