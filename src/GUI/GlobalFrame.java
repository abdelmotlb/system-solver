package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class GlobalFrame extends JFrame implements ActionListener {

    private int NumofEquations;
    private JPanel Logic;
    private JLabel LogicLabel;
    private JTextField txtOfNum;
    private JButton SubmitSize;



    //constructor
    public GlobalFrame(){

        // photo of app
        String IconLoc = "D:/university/y2.term1/Numerical/project/phase1/untitled/src/GUI/logo.png";
        ImageIcon img = new ImageIcon(IconLoc);
        this.setIconImage(img.getImage());

        // headerLabel
        JLabel header = new JLabel("Matrix solver");
        header.setBounds(0, 0, 1400, 100);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.CENTER);
        header.setBackground(new Color(0x000000));
        header.setForeground(new Color(0xFFFFFF));
        header.setFont( new Font("Times New Roman", Font.PLAIN, 52) );
        header.setOpaque(true);

        // logic part

        Logic = new JPanel();
        Logic.setBounds(0, 100, 1400, 700);
        Logic.setOpaque(true);
        Logic.setLayout(null);

        LogicLabel = new JLabel();
        LogicLabel.setBounds(0, 100, 1400, 100);
        LogicLabel.setText("Enter the number of equations");
        LogicLabel.setFont(new Font( "Times New Roman", Font.PLAIN, 50 ));
        LogicLabel.setHorizontalAlignment(JLabel.CENTER);

        txtOfNum = new JTextField();
        txtOfNum.setBounds(600, 250, 200, 100);
        txtOfNum.setHorizontalAlignment(JTextField.CENTER);
        txtOfNum.setFont( new Font( "Times New Roman", Font.PLAIN, 50 ) );

        SubmitSize = new JButton("GO!");
        SubmitSize.setBounds(650, 400, 100, 50);
        SubmitSize.setBackground(new Color(0x263D88));
        SubmitSize.setForeground(new Color(0xFFFFFF));
        SubmitSize.addActionListener(this);

        // frame settings
        this.setTitle("Matrix solver");
        this.setSize(1400, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        Logic.add(LogicLabel);
        Logic.add(txtOfNum);
        Logic.add(SubmitSize);
        this.add(Logic);
        this.add(header);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == SubmitSize ){
            NumofEquations = parseInt(txtOfNum.getText());

            LogicLabel.setVisible(false);
            txtOfNum.setVisible(false);
            SubmitSize.setVisible(false);

            new GridMatrix(Logic, NumofEquations);

            //System.out.println(NumofEquations);
        }
    }


    // Entering the number of equations:
//    public int NumOfEqns(){
//
//    }


}
