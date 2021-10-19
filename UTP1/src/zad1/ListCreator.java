/**
 * @author Dębkowski Franciszek S23061
 */

package zad1;


import java.util.LinkedList;
import java.util.List;

public class ListCreator<SelType> {

    private List<SelType> li;

    public ListCreator(List<SelType> li) {
        this.li = li;
    }

    public static <SelType> ListCreator<SelType> collectFrom(List<SelType> li) {
        return new ListCreator<>(li);
    }

    public ListCreator<SelType> when(Selector<SelType> selector) {
        List<SelType> liWhen = new LinkedList<>();
        for (SelType o : this.li) {
            if (selector.select(o)) {
                liWhen.add(o);
            }
        }
        li = liWhen;
        return this;
    }

    public <MapRes> List<MapRes> mapEvery(Mapper<SelType, MapRes> mapper) {
        List<MapRes> liMap = new LinkedList<>();
        for (SelType o : li) {
            liMap.add(mapper.map(o));
        }
        return liMap;
    }


    // Uwaga: klasa musi być sparametrtyzowana
}  
