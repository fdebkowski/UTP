package zad1;

import java.util.ListResourceBundle;

public class BundleTo_en_gb extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return new Object[][]{
                {"góry", "mountains"},
                {"morze", "sea"},
                {"jezioro", "lake"},
                {"Japonia", "Japan"},
                {"Stany Zjednoczone Ameryki", "United States"},
                {"Włochy", "Italy"},
                {"ds", '.'},
                {"gs", ','}
        };
    }
}