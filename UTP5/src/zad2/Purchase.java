/**
 *
 *  @author Dębkowski Franciszek S23061
 *
 */

package zad2;


import java.util.Collection;

public class Purchase {
    String id;
    String lastName;
    String firstName;
    String product;
    float price;
    float qty;

    public Purchase(String toParse) {
        String[] arr = toParse.split(";");

        this.id = arr[0];

        String[] sArr = arr[1].split(" ");
        this.lastName = sArr[0];
        this.firstName = sArr[1];

        this.product = arr[2];
        this.price = Float.parseFloat(arr[3]);
        this.qty = Float.parseFloat(arr[4]);
    }

    public float getCost(){
        return this.price*this.qty;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String toString(boolean extended){
        String s = String.join(
                ";",
                this.id,
                this.lastName + " " + this.firstName,
                this.product,
                Float.toString(this.price),
                Float.toString(this.qty)
        );

        if (extended){
            return s + " (koszt: " + getCost() + ")";
        } else {
            return s;
        }
    }

    public String toString() {
        return this.toString(false);
    }
}
