package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*<--
 *  niezbędne importy
 */
public class Main {
    public static void main(String[] args) {
        /*
         * <--
         * definicja operacji w postaci lambda-wyrażeń:
         * - flines - zwraca listę wierszy z pliku tekstowego
         * - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów
         * listy napisów)
         * - collectInts - zwraca listę liczb całkowitych zawartych w napisie
         * - sum - zwraca sumę elmentów listy liczb całkowitych
         */
        Function<String, List<String>> flines = (filePath) -> {
            ArrayList<String> list = new ArrayList<String>();
            try {
                FileReader fr = new FileReader(filePath);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    list.add(line);
                }
                br.close();
                return list;
            } catch (IOException e) {
                e.printStackTrace();
                return list;
            }
        };

        Function<List<String>, String> join = (list) -> String.join("", list);

        Function<String, List<Integer>> collectInts = (str) -> {
            List<Integer> ints = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(str);

            while (matcher.find()) {
                ints.add(Integer.parseInt(matcher.group()));
            }

            return ints;
        };

        Function<List<Integer>, Integer> sum = (ints) -> {
            Integer result = 0;

            for (Integer n : ints) {
                result += n;
            }

            return result;
        };

        String fname = System.getProperty("user.home") + "/LamComFile.txt";
        InputConverter<String> fileConv = new InputConverter<>(fname);
        List<String> lines = fileConv.convertBy(flines);
        String text = fileConv.convertBy(flines, join);
        List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
        Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

        System.out.println(lines);
        System.out.println(text);
        System.out.println(ints);
        System.out.println(sumints);

        List<String> arglist = Arrays.asList(args);
        InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
        sumints = slistConv.convertBy(join, collectInts, sum);
        System.out.println(sumints);

    }
}
