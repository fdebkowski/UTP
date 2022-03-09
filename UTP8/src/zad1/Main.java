package zad1;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    static ArrayList<String> words = new ArrayList<String>();
    static int maxQty = 0;

    public static void main(String[] args) {
        try {
            URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
            Scanner sc = new Scanner(url.openStream());

            while (sc.hasNext()) {
                words.add(sc.next());
            }

            List<ArrayList<String>> anagrams = getSortedByAnQty();

            for (List<String> wlist : anagrams) {
                for (String word : wlist) {
                    System.out.print(word);
                    if (wlist.indexOf(word) != wlist.size() - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String sortChars(String w) {
        char[] wArr = w.toLowerCase().toCharArray();
        Arrays.sort(wArr);
        return String.valueOf(wArr);
    }

    public static boolean areAnagrams(String w, String ana) {
        return sortChars(w).equals(sortChars(ana));
    }

    public static List<ArrayList<String>> getSortedByAnQty() {

        ArrayList<ArrayList<String>> out = new ArrayList<>();

        for (String word : words) {
            boolean match = false;

            for (ArrayList<String> list : out) {
                String listWord = list.get(0);

                if (areAnagrams(listWord, word)) {
                    list.add(word);
                    match = true;
                    break;
                }
            }

            if (!match) {
                ArrayList<String> newList = new ArrayList<>();
                newList.add(word);
                out.add(newList);
            }
        }

        for (ArrayList<String> list : out) {
            if (list.size() > maxQty)
                maxQty = list.size();
        }

        return out
                .stream()
                .filter(list -> list.size() >= maxQty)
                .collect(Collectors.toList());
    }
}
