package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class GlobalFrame extends JFrame implements ActionListener {
    public static int precision;
    public static boolean useScaling = true;
    public static final Color usedColor = new Color(0x103780);
    public static final Color secUsedColor = new Color(0x1E59C7);


    private int NumofEquations;
    private JPanel Logic;
    private JLabel NumEqLabel;
    private JTextField txtOfNum;
    private JLabel precisionLabel;
    private JTextField txtOfPrecision;
    private JLabel scalingLabel;
    private JButton scalingBut;
    private JButton SubmitSize;

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
        Logic.setBounds(0, 100, 1400, 700);
        Logic.setBackground(new Color(0x121436));
        Logic.setOpaque(true);
        Logic.setLayout(null);

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
        scalingBut.setBackground(Color.black);
        scalingBut.setForeground(new Color(0xFFFFFF));
        scalingBut.setFont(new Font("Arial", Font.BOLD, 20));
        scalingBut.addActionListener(this);


        // submission button
        SubmitSize = new JButton("GO!");
        SubmitSize.setBounds(1250, 600, 100, 50);
        SubmitSize.setBackground(usedColor);
        SubmitSize.setForeground(new Color(0xFFFFFF));
        SubmitSize.setFont(new Font("Arial", Font.BOLD, 20));
        SubmitSize.addActionListener(this);

        // frame settings
        this.setTitle("Matrix solver");
        this.setSize(1400, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        Logic.add(NumEqLabel);
        Logic.add(txtOfNum);
        Logic.add(precisionLabel);
        Logic.add(txtOfPrecision);
        Logic.add(scalingLabel);
        Logic.add(scalingBut);
        Logic.add(SubmitSize);
        this.add(Logic);
        this.add(header);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SubmitSize) {
            NumofEquations = parseInt(txtOfNum.getText());
            precision = parseInt(txtOfPrecision.getText());

            NumEqLabel.setVisible(false);
            txtOfNum.setVisible(false);
            precisionLabel.setVisible(false);
            txtOfPrecision.setVisible(false);
            SubmitSize.setVisible(false);
            scalingLabel.setVisible(false);
            scalingBut.setVisible(false);


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
