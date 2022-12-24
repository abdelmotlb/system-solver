package org.example.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class GlobalFrame extends JFrame {
    public static int precision;
    public static boolean useScaling = true; // GlobalFrame.useScaling
    public static final Color usedColor = new Color(0xFFFFFF);
    public static final Color secUsedColor = new Color(0x051F2A);
    public static final Color background = new Color(0x063548);
    public static JPanel Logic;



    // constructor
    public GlobalFrame() {

        createLogo();

        createPanel();

        updateFrameSettings();

        createScroller();

        new Navigator();

    }

    public void createLogo(){
        // photo of app
        String IconLoc = "src/GUI/logo.png";
        ImageIcon img = new ImageIcon(IconLoc);
        this.setIconImage(img.getImage());
    }

    public void createPanel(){
        // logic part
        Logic = new JPanel();
        Logic.setBounds(0, 100, 1400, 800);
        Logic.setBackground(background);
        Logic.setOpaque(true);
        Logic.setLayout(new BorderLayout());
        Logic.setBorder(new EmptyBorder(800, 1400, 0, 0));
    }

    public void updateFrameSettings(){
        // frame settings
        this.setTitle("Matrix solver");
        this.setSize(1400, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createScroller(){
        // scroll initializer
        JScrollPane scroller = new JScrollPane(Logic);
        this.add(BorderLayout.CENTER, scroller);
        scroller.setWheelScrollingEnabled(true);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setVisible(true);
    }

}
