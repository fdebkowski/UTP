package zad1;

import java.util.ListResourceBundle;

public class BundleFrom_en_gb extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return new Object[][]{
                {"mountains","góry" },
                {"sea", "morze"},
                {"lake", "jezioro"},
                {"Japan", "Japonia"},
                {"United States", "Stany Zjednoczone Ameryki"},
                {"Italy", "Włochy"},
                {"ds", '.'},
                {"gs", ','}
        };
    }
}