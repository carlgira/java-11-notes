package com.carlgira.localization;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Main {

    public void properties() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("messages.properties"));

        System.out.println(props.get("user"));
        System.out.println(props.get("password"));
        System.out.println(props.getProperty("password", "default"));
    }

    public void locales(){
        Locale usLocale = Locale.US;
        Locale franceLocale = Locale.FRANCE;
        Locale currentLocale = Locale.getDefault();

        Locale.setDefault(Locale.US);
        Locale.setDefault(Locale.Category.DISPLAY, Locale.UK);
        Locale.setDefault(Locale.Category.FORMAT, Locale.UK);
    }

    public void resourcesBundle(){
        Locale.setDefault(Locale.US);
        // Locale.setDefault(Locale.Category.FORMAT, Locale.UK);
        ResourceBundle messages = ResourceBundle.getBundle("messages", Locale.FRANCE);

        Object o = messages.getObject("");
        String value = messages.getString("");
        String[] values = messages.getStringArray("");
        Enumeration<String> keys = messages.getKeys();
    }


    public static void numbers(){
        NumberFormat nfCurrency = NumberFormat.getCurrencyInstance(Locale.UK);
        double value = 1_000_000.01;
        System.out.println(nfCurrency.format(value));

        NumberFormat nfPercentage = NumberFormat.getPercentInstance(Locale.FRANCE);
        System.out.println(nfPercentage.format(value));
    }


    public void dates(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.UK);
        DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);

        /**
         * SHORT Numeric as 12.13.52 or 3:30 pm
         * FULL Tuesday April 12 1952 AD or 3:30:42 PST
         */

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime.of(2022, 3, 1, 5, 55, 00, 00);
        LocalDateTime.of(2022, 3, 1, 5, 55, 00);
        LocalDateTime.of(2022, 3, 1, 5, 55);
        LocalDateTime.of(2022, 3, 1, 5, 55);
        LocalDateTime.parse("2021-02-01", DateTimeFormatter.ISO_DATE);

        System.out.println(localDateTime.format(dateTimeFormatter));


        LocalDate localDate = LocalDate.of(2022, 3, 1);
        dateTimeFormatter.format(localDate);
    }

    public static void main(String[] args) throws IOException {

    }
}
