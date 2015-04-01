package de.gemo.arbeitsplan.views;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import de.gemo.arbeitsplan.manager.PersonManager;
import de.gemo.arbeitsplan.units.Day;
import de.gemo.arbeitsplan.units.Person;

public class WeekView {

    private final Container MAIN;

    private List<JComponent> components;

    public WeekView(Container container) {
        this.MAIN = container;

        // init GUI
        this.refreshUI();
    }

    private void addComponent(JComponent component) {
        MAIN.add(component);
        this.components.add(component);
    }

    public void refreshUI() {
        if (this.components != null) {
            for (JComponent component : this.components) {
                MAIN.remove(component);
            }
        }
        this.initUI();
    }

    private void initUI() {
        // create fresh list
        this.components = new ArrayList<JComponent>();

        // get today and clear time of day
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        // start of the week
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        Date startDate = cal.getTime();

        // end of the week
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date endDate = cal.getTime();

        // create topics
        this.createTopics(cal, startDate);

        // create persons
        int index = 0;
        Set<Person> persons = PersonManager.getAll();
        List<Person> list = new ArrayList<Person>();
        list.addAll(persons);
        Collections.sort(list);
        for (Person person : list) {
            this.createPerson(index, person.getName(), startDate, endDate);
            index++;
        }

        MAIN.repaint();
        MAIN.invalidate();
    }

    private JButton addButton(String label, int width, int height) {
        JButton button = new JButton(label);
        button.setSize(width, height);
        this.addComponent(button);
        return button;
    }

    private JLabel addLabel(String caption, int width, int height) {
        JLabel label = new JLabel(caption);
        label.setSize(width, height);
        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.addComponent(label);
        return label;
    }

    private void createTopics(Calendar cal, Date startDate) {
        // name
        JLabel lbl_name = this.addLabel("Name", 150, 25);
        lbl_name.setLocation(0, 25);
        lbl_name.setHorizontalAlignment(JLabel.CENTER);
        lbl_name.setOpaque(true);
        lbl_name.setBackground(new Color(22, 54, 92));
        lbl_name.setForeground(Color.WHITE);

        cal.setTime(startDate);
        for (int i = 0; i < 7; i++) {
            // name
            JLabel lbl_day = this.addLabel(Day.parseToString(cal.getTime()), 90, 25);
            lbl_day.setLocation(150 + i * 90, 25);
            lbl_day.setHorizontalAlignment(JLabel.CENTER);
            lbl_day.setOpaque(true);
            lbl_day.setBackground(new Color(22, 54, 92));
            lbl_day.setForeground(Color.WHITE);

            cal.add(Calendar.DAY_OF_WEEK, 1);
        }

    }

    private void createPerson(int index, String name, Date startDate, Date endDate) {
        JLabel lbl_name = this.addLabel(name, 150, 23);
        lbl_name.setLocation(0, 50 + index * 23);
        lbl_name.setHorizontalAlignment(JLabel.CENTER);
    }

}
