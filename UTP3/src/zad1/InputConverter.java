package zad1;

import java.util.function.Function;

public class InputConverter <T> {
    T input;

    public InputConverter(T input){
        this.input = input;
    }

    public <C> C convertBy(Function... functions) {
        Object input = this.input;
        Object next = null;

        for (Function f : functions) {
            next = f.apply(input);
            input = next;
        }

        return (C) next;
    }

}
