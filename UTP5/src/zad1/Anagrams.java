/**
 * @author Dębkowski Franciszek S23061
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Anagrams {
    private final List<String> wordsList;

    public Anagrams(String allWords) {
        this.wordsList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(allWords));

            br.lines().forEach(line -> this.wordsList.addAll(Arrays.asList(line.split(" "))));

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String sortChars(String w) {
        char[] wArr = w.toLowerCase().toCharArray();
        Arrays.sort(wArr);
        return String.valueOf(wArr);
    }

    public boolean areAnagrams(String w, String ana) {
        return sortChars(w).equals(sortChars(ana));
    }

    public List<ArrayList<String>> getSortedByAnQty() {
        ArrayList<ArrayList<String>> out = new ArrayList<>();

        for (String word : this.wordsList) {
            boolean match = false;

            for (ArrayList<String> list : out) {
                String listWord = list.get(0);

                if (this.areAnagrams(listWord, word)) {
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

        return out
                .stream()
                .sorted((listA, listB) -> {
                    if (listB.size() - listA.size() != 0)
                        return listB.size() - listA.size();
                    else
                        return listA.get(0).compareTo(listB.get(0));
                })
                .collect(Collectors.toList());
    }

    public String getAnagramsFor(String word) {
        ArrayList<String> list = this
                .getSortedByAnQty()
                .stream()
                .filter((el) -> this.areAnagrams(word, el.get(0)))
                .findAny()
                .orElse(null);

        if (list == null) {
            return word + ": null";
        }

        ((List<String>) list).remove(word);

        return word + ": " + list;
    }
}