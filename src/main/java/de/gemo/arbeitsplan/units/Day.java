package de.gemo.arbeitsplan.units;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Day implements Comparable<Day> {

    private static SimpleDateFormat FORMAT_DD_MM_YYYY = new SimpleDateFormat("dd.MM.YYYY");
    private static int COUNT_ID = 0;

    private final int ID;
    private Date date;

    public static Date parseToString(String text) throws ParseException {
        return FORMAT_DD_MM_YYYY.parse(text);
    }

    public static String parseToString(Date date) {
        return FORMAT_DD_MM_YYYY.format(date);
    }

    public Day(Date date) {
        this.ID = Day.COUNT_ID++;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return this.ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj instanceof Day) {
            Day day = (Day) obj;
            if (day.ID == this.ID) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "{ Day [ ID=" + this.ID + ", Date=" + Day.parseToString(this.date) + " ] }";
    }

    public int compareTo(Day other) {
        return this.ID - other.ID;
    }

}
