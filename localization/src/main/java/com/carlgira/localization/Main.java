package com.carlgira.localization;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Main {

    public void properties() throws IOException {


        Properties props = new Properties();
        props.load(new FileInputStream("messages.properties"));

        System.out.println(props.get("user"));
        System.out.println(props.get("password"));
    }

    public void resourcesBundle(){
        Locale usLocale = Locale.US;
        Locale franceLocale = Locale.FRANCE;
        Locale currentLocale = Locale.getDefault();

        Locale.setDefault(Locale.Category.DISPLAY, Locale.UK);
        Locale.setDefault(Locale.Category.FORMAT, Locale.UK);

        ResourceBundle messages = ResourceBundle.getBundle("messages", currentLocale);

        currentLocale = franceLocale;
        messages = ResourceBundle.getBundle("messages", currentLocale);
        messages.getObject("");
        messages.getString("");
        messages.getStringArray("");
        messages.getKeys();
    }


    public static void currency(){
        NumberFormat nfCurrency = NumberFormat.getCurrencyInstance(Locale.UK);

        double value = 1_000_000.01;
        System.out.println(nfCurrency.format(value));
    }




    public void dates(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);

        /**
         * SHORT Numeric as 12.13.52 or 3:30 pm
         * FULL Tuesday April 12 1952 AD or 3:30:42 PST
         */

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(dateTimeFormatter));

    }


    public static void main(String[] args) throws IOException {

        var x = LocalDate.of(2022, 3, 1);
        var y = LocalDateTime.of(2022, 3, 1, 5, 55, 00);
        //var f = DateTimeFormatter.ofPattern("MMMM ' at ' h ' ");
        //System.out.print(x.format(f));
        //System.out.print(y.format(f));

        System.out.println(new Locale("en_US", "US"));



    }
}
