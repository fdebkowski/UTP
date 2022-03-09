package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Database {

    String url;
    TravelData travelData;
    String driverClassName = "com.mysql.cj.jdbc.Driver";
    String username = "root";
    String password = "root";
    Statement st;
    Connection con;
    DefaultTableModel model;
    String[][] data;
    int count;
    ResultSet res;
    JTable table;

    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(
                    url, username, password);
            con.setAutoCommit(true);
            st = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            String query = "CREATE TABLE ENTRIES(" +
                    "Country varchar(255)," +
                    "ArrDate varchar(255)," +
                    "DepDate varchar(255)," +
                    "Place varchar(255)," +
                    "Price float," +
                    "Currency varchar(255)" +
                    ");";
            st.executeUpdate(query);

            List<TravelEntry> entries = travelData.getTravelEntries();

            for (TravelEntry travelEntry : entries) {
                query = "INSERT INTO entries VALUES (\'" +
                        travelEntry.country + "\', \'" +
                        travelEntry.arrDate + "\', \'" +
                        travelEntry.depDate + "\', \'" +
                        travelEntry.place + "\', " +
                        travelEntry.price + ", \'" +
                        travelEntry.currency +
                        "\');";
                st.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String[][] getTranslatedData (Locale destLocale, int count, ResultSet res) throws SQLException {
        String[][] data = new String[count][6];
        Locale.setDefault(destLocale);
        ResourceBundle bundle = ResourceBundle.getBundle("zad1.BundleTo");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator((char)bundle.getObject("ds"));
        symbols.setGroupingSeparator((char)bundle.getObject("gs"));
        DecimalFormat df = new DecimalFormat("###,###.00", symbols);
        res.first();
        int i = 0;
        do {
            String country = res.getString("Country");
            String arrDate = res.getString("ArrDate");
            String depDate = res.getString("DepDate");
            String place = res.getString("Place");
            float price = res.getFloat("Price");
            String currency = res.getString("Currency");
            data[i][0] = bundle.getString(country);
            data[i][1] = arrDate;
            data[i][2] = depDate;
            data[i][3] = bundle.getString(place);
            data[i][4] = df.format(price).toString();
            data[i][5] = currency;
            i++;
        } while (res.next());
        return data;
    }

    public void showGui() {
        try {
            String query;
            count = 0;
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            query = "SELECT * FROM entries";
            res = statement.executeQuery(query);
            if (res != null) {
                res.last();
                count = res.getRow();
            }

            String[] columns = {"Country", "ArrDate", "DepDate", "Place", "Price", "Currency"};
            data = getTranslatedData(Locale.getDefault(), count, res);

            model = new DefaultTableModel(data, columns);
            table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);
            JScrollPane pane = new JScrollPane(table);
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            JButton button_pl = new JButton("pl");
            JButton button_en = new JButton("en");
            button_en.addActionListener(e -> {
                try {
                    data = getTranslatedData(new Locale("en_gb"), count, res);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                model = new DefaultTableModel(data, columns);
                table = new JTable(model);
                table.repaint();
            });
            button_pl.addActionListener(e -> {
                try {
                    data = getTranslatedData(new Locale("pl_pl"), count, res);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                model = new DefaultTableModel(data, columns);
                table = new JTable(model);
                table.repaint();
            });
            panel.add(pane);
            panel.add(button_en);
            panel.add(button_pl);
            f.add(panel);
            f.setSize(600, 300);
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}