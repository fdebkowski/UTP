/**
 * @author Dębkowski Franciszek S23061
 */

package zad1;


public interface Selector<SelType> { // Uwaga: interfejs musi być sparametrtyzowany
    boolean select(SelType arg);
}  
