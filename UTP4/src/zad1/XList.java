package zad1;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends LinkedList<T> {
    private XList() {
    }

    public XList(Object... objects) {
        this.addAll(XList.of(objects));
    }

    public static <T> XList<T> of(Object... objects) {
        XList xList = new XList<>();

        boolean combine = false;
        if (objects.length > 1) {
            combine = true;

            for (Object object : objects) {
                if (!(object instanceof Collection || object.getClass().isArray())) {
                    combine = false;
                    break;
                }
            }
        }

        for (Object object : objects) {
            if (object instanceof Collection && !combine)
                ((Collection) object).forEach(o -> xList.addAll(XList.of(o)));
            else if (object.getClass().isArray() && !combine)
                Arrays.stream(((Object[]) object)).forEach(o -> xList.addAll(XList.of(o)));
            else {
                if (combine) {
                    xList.add(XList.of(object));
                } else {
                    xList.add(object);
                }
            }
        }

        return xList;
    }

    public static XList<String> charsOf(String s) {
        return XList.of(s.split(""));
    }

    public static XList<String> tokensOf(String s) {
        return XList.tokensOf(s, " ");
    }

    public static XList<String> tokensOf(String string, String sep) {
        return XList.of(string.split(sep));
    }

    private static <T> void combine(XList<XList<T>> ori, XList<XList<T>> res, int d, XList<T> current) {
        if (d == ori.size()) {
            res.add(current);
            return;
        }
        XList<T> currentCollection = ori.get(d);
        for (T element : currentCollection) {
            XList<T> copy = XList.of(current);
            copy.add(element);
            combine(ori, res, d + 1, copy);
        }
    }

    public XList<T> union(Object... objects) {
        XList result = new XList(this);
        result.addAll(XList.of(objects));
        return result;
    }

    public XList diff(Object... objects) {
        XList result = new XList(this);
        result.removeAll(XList.of(objects));
        return result;
    }

    public XList<T> unique() {
        XList<T> newXList = new XList<>();

        for (T o : this) {
            if (!newXList.contains(o)) {
                newXList.add(o);
            }
        }

        return newXList;
    }

    public XList<XList<T>> combine() {
        if (this.isEmpty()) {
            return new XList<>();
        } else {
            XList<XList<T>> res = new XList<>();
            combine((XList<XList<T>>) this, res, 0, new XList<>());
            return res;
        }
    }

    public <K> XList<String> collect(Function<XList<K>, String> function) {
        return ((XList<XList<K>>) this).stream().map(function).collect(Collectors.toCollection(XList::new));
    }

    public String join(String separator) {
        return this.stream()
                .map(Object::toString)
                .collect(Collectors.joining(separator));
    }

    public String join() {
        return join("");
    }

    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i < this.size(); i++) {
            consumer.accept(this.get(i), i);
        }
    }

}
