package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class GlobalFrame extends JFrame implements ActionListener {
    public static int precision;
    public static boolean useScaling = true; // GlobalFrame.useScaling
    public static final Color usedColor = new Color(0xFFFFFF);
    public static final Color secUsedColor = new Color(0x051F2A);
    public static final Color background = new Color(0x063548);


    private int NumofEquations;
    private JPanel Logic;
    private JLabel NumEqLabel;
    private JTextField txtOfNum;
    private JLabel precisionLabel;
    private JTextField txtOfPrecision;
    private JLabel scalingLabel;
    private JButton scalingBut;
    private JButton SubmitSize;
    private JButton tempBut;

    // constructor
    public GlobalFrame() {




        // photo of app
        String IconLoc = "src/GUI/logo.png";
        ImageIcon img = new ImageIcon(IconLoc);
        this.setIconImage(img.getImage());

        // headerLabel
        JLabel header = new JLabel("System solver");
        header.setBounds(0, 0, 1400, 100);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.CENTER);
        header.setBackground(usedColor);
        header.setForeground(new Color(0xFFFFFF));
        header.setFont(new Font("Times New Roman", Font.PLAIN, 52));
        header.setOpaque(true);

        // logic part
        Logic = new JPanel();
        Logic.setBounds(0, 100, 1400, 800);
        Logic.setBackground(background);
        Logic.setOpaque(true);
        Logic.setLayout(new BorderLayout());
        Logic.setBorder(new EmptyBorder(800, 1400, 0, 0));
        //        Logic.setLayout(null);


//        // temp but
//        JButton but = new JButton("temp");
//        setBounds(1500, 900, 200, 100);

        // get number of equations
        NumEqLabel = new JLabel();
        NumEqLabel.setBounds(0, 20, 1400, 100);
        NumEqLabel.setText("        → Enter the number of equations");
        NumEqLabel.setFont(new Font("Times New Roman", Font.ITALIC, 50));
        NumEqLabel.setForeground(usedColor);

        txtOfNum = new JTextField();
        txtOfNum.setBounds(550, 150, 300, 70);
        txtOfNum.setHorizontalAlignment(JTextField.CENTER);
        txtOfNum.setFont(new Font("Times New Roman", Font.ITALIC, 50));

        // get precision
        precisionLabel = new JLabel();
        precisionLabel.setBounds(0, 250, 1400, 100);
        precisionLabel.setText("        → Enter precision used");
        precisionLabel.setForeground(usedColor);
        precisionLabel.setFont(new Font("Times New Roman", Font.ITALIC, 50));


        txtOfPrecision = new JTextField();
        txtOfPrecision.setBounds(550, 380, 300, 70);
        txtOfPrecision.setHorizontalAlignment(JTextField.CENTER);
        txtOfPrecision.setFont(new Font("Times New Roman", Font.ITALIC, 50));

        // check scaling
        scalingLabel = new JLabel();
        scalingLabel.setBounds(0, 480, 1400, 100);
        scalingLabel.setText("        → click to toggle");
        scalingLabel.setFont(new Font("Times New Roman", Font.ITALIC, 50));
        scalingLabel.setForeground(usedColor);

        scalingBut = new JButton("use Scaling");
        scalingBut.setBounds(550, 600, 300, 50);
        scalingBut.setBackground(secUsedColor);
        scalingBut.setForeground(new Color(0xFFFFFF));
        scalingBut.setFont(new Font("Arial", Font.BOLD, 20));
        scalingBut.addActionListener(this);


        // submission button
        SubmitSize = new JButton("GO!");
        SubmitSize.setBounds(1150, 680, 200, 50);
        SubmitSize.setBackground(secUsedColor);
        SubmitSize.setForeground(usedColor);
        SubmitSize.setFont(new Font("Arial", Font.BOLD, 20));
        SubmitSize.addActionListener(this);

        tempBut = new JButton();
        tempBut.setBackground(background);
        tempBut.setEnabled(false);
        tempBut.setBorder(null);
        tempBut.setBounds(1300, 700, 1, 1);

        // frame settings
        this.setTitle("Matrix solver");
        this.setSize(1400, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Logic.add(NumEqLabel);
        Logic.add(txtOfNum);
        Logic.add(precisionLabel);
        Logic.add(txtOfPrecision);
        Logic.add(scalingLabel);
        Logic.add(scalingBut);
        Logic.add(SubmitSize);
        Logic.add(tempBut);



//        // scroll:
//        JScrollPane scrollPane = new JScrollPane(Logic, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//        scrollPane.setPreferredSize(new Dimension(1200, 400));
//        scrollPane.setVisible(true);
//        Logic.add(scrollPane);
//        this.add(S);

//        this.add(Logic);
        JScrollPane scroller = new JScrollPane(Logic);
        this.add(BorderLayout.CENTER, scroller);
        scroller.setWheelScrollingEnabled(true);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//        this.add(header);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SubmitSize) {
            try { NumofEquations = parseInt(txtOfNum.getText()); }
            catch(NumberFormatException ea) { NumofEquations = 5; } // default NumOfEquations

            try { precision = parseInt(txtOfPrecision.getText()); }
            catch(NumberFormatException ea) { precision = 8; } // default precision

            NumEqLabel.setVisible(false);
            txtOfNum.setVisible(false);
            precisionLabel.setVisible(false);
            txtOfPrecision.setVisible(false);
            SubmitSize.setVisible(false);
            scalingLabel.setVisible(false);
            scalingBut.setVisible(false);
            tempBut.setVisible(false);


            new GridMatrix(Logic, NumofEquations);

            // System.out.println(NumofEquations);
        }
        else if( e.getSource() == scalingBut ){
            if( useScaling ){
                useScaling = false;
                scalingBut.setText("No scaling");
            }else {
                useScaling = true;
                scalingBut.setText("use scaling");
            }
        }
    }

}
