package de.gemo.arbeitsplan.units;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Person implements Comparable<Person> {
    private final String name;
    private final Set<Day> days;
    private int hoursPerWeek;
    private int hashCode;

    public Person(String name, int hoursPerWeek) {
        this.name = name;
        this.days = Collections.synchronizedSet(new HashSet<Day>());
        this.hoursPerWeek = hoursPerWeek;
        this.hashCode = this.name.hashCode();
    }

    public int getHoursPerWeek() {
        return this.hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public String getName() {
        return this.name;
    }

    public boolean removeDay(Day day) {
        return this.days.remove(day);
    }

    public boolean addDay(Day day) {
        return this.days.add(day);
    }

    public List<Day> getDaysBetween(Date start, Date end) {
        List<Day> list = new ArrayList<Day>();
        for (Day day : this.days) {
            if ((day.getDate().after(start) || day.getDate().equals(start)) && (day.getDate().before(end) || day.getDate().equals(end))) {
                list.add(day);
            }
        }
        return list;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj instanceof Person) {
            Person person = (Person) obj;
            if (person.name.equalsIgnoreCase(this.name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "{ Person [ Name=" + this.name + ", hoursPerWeek=" + this.hoursPerWeek + "  ] }";
    }

    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }

}
