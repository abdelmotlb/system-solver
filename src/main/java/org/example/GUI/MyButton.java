package org.example.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class MyButton extends JButton {
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 0;

    public boolean isOver() {
        return this.over;
    }

    public void setOver(boolean var1) {
        this.over = var1;
    }

    public void setColor(Color var1) {
        this.color = var1;
        this.setBackground(var1);
    }

    public Color getColorOver() {
        return this.colorOver;
    }

    public void setColorOver(Color var1) {
        this.colorOver = var1;
    }

    public Color getBorderColor() {
        return this.borderColor;
    }

    public Color getColorClick() {
        return this.colorClick;
    }

    public void setColorClick(Color var1) {
        this.colorClick = var1;
    }

    public void setBorderColor(Color var1) {
        this.borderColor = var1;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int var1) {
        this.radius = var1;
    }

    public MyButton(String var1) {
        this.setColor(Color.WHITE);
        this.colorOver = new Color(74, 75, 77);
        this.colorClick = new Color(152, 184, 144);
        this.borderColor = new Color(30, 50, 54);
        this.setContentAreaFilled(false);
        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent var1) {
                MyButton.this.setBackground(MyButton.this.colorOver);
                MyButton.this.over = true;
            }

            public void mouseExited(MouseEvent var1) {
                MyButton.this.setBackground(MyButton.this.color);
                MyButton.this.over = false;
            }

            public void mousePressed(MouseEvent var1) {
                MyButton.this.setBackground(MyButton.this.colorClick);
            }

            public void mouseReleased(MouseEvent var1) {
                if (MyButton.this.over) {
                    MyButton.this.setBackground(MyButton.this.colorOver);
                } else {
                    MyButton.this.setBackground(MyButton.this.color);
                }

            }
        });
        this.setText(var1);
    }

    public MyButton() {
        this.setColor(Color.WHITE);
        this.colorOver = new Color(74, 75, 77);
        this.colorClick = new Color(152, 184, 144);
        this.borderColor = new Color(1666684);
        this.setContentAreaFilled(false);
        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent var1) {
                MyButton.this.setBackground(MyButton.this.colorOver);
                MyButton.this.over = true;
            }

            public void mouseExited(MouseEvent var1) {
                MyButton.this.setBackground(MyButton.this.color);
                MyButton.this.over = false;
            }

            public void mousePressed(MouseEvent var1) {
                MyButton.this.setBackground(MyButton.this.colorClick);
            }

            public void mouseReleased(MouseEvent var1) {
                if (MyButton.this.over) {
                    MyButton.this.setBackground(MyButton.this.colorOver);
                } else {
                    MyButton.this.setBackground(MyButton.this.color);
                }

            }
        });
    }

    protected void paintComponent(Graphics var1) {
        Graphics2D var2 = (Graphics2D)var1;
        var2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        var2.setColor(this.borderColor);
        var2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), this.radius, this.radius);
        var2.setColor(this.getBackground());
        var2.fillRoundRect(2, 2, this.getWidth() - 4, this.getHeight() - 4, this.radius, this.radius);
        super.paintComponent(var1);
    }
}

