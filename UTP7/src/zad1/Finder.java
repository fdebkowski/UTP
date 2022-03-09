/**
 *
 *  @author Dębkowski Franciszek S23061
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
    ArrayList<String> lines = new ArrayList();
    Pattern ifPattern = Pattern.compile("(?:^|\\}\\s*else)\\s+if",Pattern.MULTILINE);

    public Finder(String f) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;

            while((line = br.readLine()) != null) {
                this.lines.add(line);
            }

            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getIfCount(){
        int n = 0;
        boolean inComment = false;
        for (String line : lines) {
            Matcher matcher = ifPattern.matcher(line);
            if (inComment){
                if (line.startsWith("*/")){
                    inComment = false;
                    continue;
                } else
                    continue;
            }
            if (line.startsWith("//"))
                continue;
            if (line.startsWith("/**")){
                inComment = true;
                continue;
            }
            while (matcher.find())
                n++;
        }
        return n;
    }

    public int getStringCount(String arg){
        int n = 0;
        Pattern pattern = Pattern.compile(arg);
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (line.startsWith("//"))
                continue;
            while (matcher.find())
                n++;
        }
        return n;
    }


}
