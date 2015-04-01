package de.gemo.arbeitsplan.core;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.gemo.arbeitsplan.frames.MainFrame;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        // UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");

        new MainFrame().show();
    }

}
