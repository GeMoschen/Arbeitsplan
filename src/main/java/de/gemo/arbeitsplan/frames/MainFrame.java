package de.gemo.arbeitsplan.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.gemo.arbeitsplan.exceptions.PersonAlreadyExistsException;
import de.gemo.arbeitsplan.manager.PersonManager;
import de.gemo.arbeitsplan.units.Person;
import de.gemo.arbeitsplan.views.WeekView;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = -6464965947050335791L;

    private WeekView view;

    public MainFrame() {
        // call super
        super("Arbeitsplan");

        // remove layout
        this.getContentPane().setLayout(null);

        // set un-resizable
        this.setResizable(false);

        // init UI
        this.initUI();

        // pack window
        this.pack();

        // set size
        this.setSize(800, 600);

        // set location
        this.setLocationRelativeTo(null);

        // set close-operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initUI() {
        this.initMenu();

        this.view = new WeekView(this.getContentPane());
    }

    private void initMenu() {
        // /////////////////////////////
        // create
        // /////////////////////////////
        JMenuBar menubar = new JMenuBar();
        menubar.setSize(800, 25);
        menubar.setLocation(0, 0);

        // /////////////////////////////
        // menu "Datei"
        // /////////////////////////////
        JMenu file = new JMenu("Datei");

        JMenuItem item_exit = new JMenuItem("Beenden");
        file.add(item_exit);

        // add to menubar
        menubar.add(file);

        // /////////////////////////////
        // menu "Bearbeiten"
        // /////////////////////////////
        JMenu edit = new JMenu("Bearbeiten");

        // add items
        JMenuItem item_person_add = new JMenuItem("Neuer Mitarbeiter");
        item_person_add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    PersonManager.add(new Person("Person #" + (PersonManager.getSize()), 40));
                    view.refreshUI();
                } catch (PersonAlreadyExistsException e1) {
                    e1.printStackTrace();
                }
            }
        });
        edit.add(item_person_add);

        // add to menubar
        menubar.add(edit);

        // /////////////////////////////
        // add menubar
        // /////////////////////////////
        this.getContentPane().add(menubar);

    }
}
