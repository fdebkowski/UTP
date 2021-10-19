package zad1;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class ListCreator <SelType> {

    private List<SelType> li;

    public ListCreator(List<SelType> li) {
        this.li = li;
    }

    public static <SelType> ListCreator<SelType> collectFrom(List<SelType> li) {
        return new ListCreator<>(li);
    }

    public ListCreator<SelType> when(Function<SelType, Boolean>fun) {
        List<SelType> liWhen = new LinkedList<>();
        for (SelType el: this.li) {
            if (fun.apply(el)) {
                liWhen.add(el);
            }
        }
        li = liWhen;
        return this;
    }

    public <MapRes> List<MapRes> mapEvery(Function<SelType, MapRes> lam) {
        List<MapRes> liMap = new LinkedList<>();
        this.li.forEach(o -> {
            MapRes oNew = lam.apply(o);
            liMap.add(oNew);
        });
        return liMap;
    }
}
