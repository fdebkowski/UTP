package zad1;

import java.text.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class TravelEntry {
    String locale;
    String country;
    String arrDate;
    String depDate;
    String place;
    float price;
    String currency;

    SimpleDateFormat defaultSDF = new SimpleDateFormat("yyyy-MM-dd");
    ResourceBundle bundle;


    public TravelEntry(String locale, String country, String arrDate, String depDate, String place, float price, String currency) {
        this.locale = locale;
        bundle = ResourceBundle.getBundle("zad1.BundleFrom", new Locale(locale));
        this.country = bundle.getString(country);
        this.arrDate = arrDate;
        this.depDate = depDate;
        this.place = bundle.getString(place);
        this.price = price;
        this.currency = currency;
    }

    public String getTravelEntry(String stringLocale, String stringFormat) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(stringFormat);
        Locale destLocale = new Locale(stringLocale);
        Locale.setDefault(destLocale);
        bundle = ResourceBundle.getBundle("zad1.BundleTo");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator((char)bundle.getObject("ds"));
        symbols.setGroupingSeparator((char)bundle.getObject("gs"));
        DecimalFormat df = new DecimalFormat("###,###.00", symbols);
        return bundle.getString(country) + " " + dateFormat.format(defaultSDF.parse(arrDate)) + " " + dateFormat.format(defaultSDF.parse(depDate)) + " " + bundle.getString(place) + " " + df.format(price) + " " + currency;
    }

}
