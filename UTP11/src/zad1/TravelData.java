package zad1;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.*;

public class TravelData {
    List<TravelEntry> travelEntries = new ArrayList<>();

    public TravelData(File dataDir) {
        for (File file : Objects.requireNonNull(dataDir.listFiles())) {
            if (file.isFile() && file.toString().toLowerCase().endsWith(".tsv")) {
                try {
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] entry = line.split("\t");
                        Locale locale = new Locale(entry[0]);
                        Locale.setDefault(locale);
                        ResourceBundle bundle = ResourceBundle.getBundle("zad1.BundleTo");
                        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                        symbols.setDecimalSeparator((char)bundle.getObject("ds"));
                        symbols.setGroupingSeparator((char)bundle.getObject("gs"));
                        DecimalFormat df = new DecimalFormat("###,###.00", symbols);
                        travelEntries.add(new TravelEntry(entry[0], entry[1], entry[2], entry[3], entry[4],
                                df.parse(entry[5]).floatValue(), entry[6]));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat){
        ArrayList<String> out = new ArrayList<>();
        for (TravelEntry entry : travelEntries) {
            try {
                out.add(entry.getTravelEntry(locale, dateFormat));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return out;
    }

    public List<TravelEntry> getTravelEntries() {
        return travelEntries;
    }
}
