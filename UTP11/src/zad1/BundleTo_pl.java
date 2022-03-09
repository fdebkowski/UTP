package zad1;

import java.util.ListResourceBundle;

public class BundleTo_pl extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return new Object[][]{
                {"góry","góry" },
                {"morze", "morze"},
                {"jezioro", "jezioro"},
                {"Japonia", "Japonia"},
                {"Stany Zjednoczone Ameryki", "Stany Zjednoczone Ameryki"},
                {"Włochy", "Włochy"},
                {"ds", ','},
                {"gs", ' '}
        };
    }
}
