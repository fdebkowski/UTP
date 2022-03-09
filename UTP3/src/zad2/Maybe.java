package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    T val;

    public Maybe() {
    }

    public Maybe(T val) {
        this.val = val;
    }

    public static <N> Maybe<N> of(N x) {
        return new Maybe<N>(x);
    }

    public void ifPresent(Consumer cons) {
        if (val != null)
            cons.accept(val);
    }

    public <N> Maybe<N> map(Function<T, N> func) {
        if (val != null)
            return new Maybe((N)func.apply(val));
        else
            return new Maybe<>();
    }

    public T get() throws NoSuchElementException {
        if (val == null)
            throw new NoSuchElementException(" maybe is empty");
        return val;
    }

    public boolean isPresent() {
        return val != null;
    }

    public T orElse(T defVal) {
        if (val != null)
            return val;
        else
            return defVal;
    }

    public Maybe<T> filter(Predicate<T> pred) {
        if (pred.test(this.val) || val == null)
            return this;
        else
            return new Maybe<>();
    }

    @Override
    public String toString() {
        if (val != null)
            return "Maybe has value " + val;
        else
            return "Maybe is empty";
    }
}
