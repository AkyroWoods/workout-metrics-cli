package com.akyro;

public class FormatUtils {

 public static String formatNumber(double value) {
        if (value == (long) value) {
            return String.format("%,d", (long) value);
        } else {
            return String.format("%,.2f", value);
        }
    }
    
    public static String formatPercent(double value) {
        if (Double.isNaN(value) || value < 0) {
            return "No data available";
        }
        double percent =  value * 100;
         if (percent == (long) percent) {
            return String.format("%d%%", (long) percent);
        } else {
            return String.format("%.2f%%", percent);
        }
    }
}
