/**
 * @author Dębkowski Franciszek S23061
 */

package zad1;


public interface Mapper<SelType, MapRes> {
    MapRes map(SelType arg);// Uwaga: interfejs musi być sparametrtyzowany
}  
