package com.kurotkin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Vitaly on 24.05.2017.
 */
public class Parser {
    private static SimpleDateFormat format = new SimpleDateFormat();

    public static Double getVal(HashMap<Integer, String> p, int n) {
        String[] s = p.get(n).split(",");
        return Double.parseDouble(s[2]);
    }

    public static Date getDate(HashMap<Integer, String> p, int n) {
        format.applyPattern("yy/MM/dd HH:mm:ss");
        String[] s = p.get(n).split(",");
        try {
            return format.parse(s[3] + " " + s[4]);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Double getValStr(HashMap<Integer, String> p, int n) {
        String[] s = p.get(n).split(",");
        return Double.parseDouble(s[2]);
    }

    public static Date getDateStr(HashMap<Integer, String> p, int n) {
        format.applyPattern("yy/MM/dd HH:mm:ss");
        String[] s = p.get(n).split(",");
        try {
            return format.parse(s[3] + " " + s[4]);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
