/**
 * @author Dębkowski Franciszek S23061
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

public class CustomersPurchaseSortFind {
    ArrayList<Purchase> purchaseList = new ArrayList<>();
    ArrayList<Purchase> sorted;

    public CustomersPurchaseSortFind() {
    }

    public void readFile(String s) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(s));
            String line;

            while ((line = br.readLine()) != null) {
                this.purchaseList.add(new Purchase(line));
            }

            sorted = new ArrayList<>(purchaseList);

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showSortedBy(String s) {
        System.out.println(s);
        if (s.equals("Nazwiska")) {
            sorted.sort(Comparator.comparing(Purchase::getLastName).thenComparing(Comparator.comparing(Purchase::getId)));
            sorted.forEach(purchase -> System.out.println(purchase.toString()));
        } else if (s.equals("Koszty")) {
            sorted.sort(Comparator.comparing(Purchase::getCost).reversed());
            sorted.forEach(purchase -> System.out.println(purchase.toString(true)));
        }
        System.out.println();
    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        for (Purchase p : purchaseList) {
            if (p.id.equals(id)) {
                System.out.println(p.toString());
            }
        }
        System.out.println();
    }

}
