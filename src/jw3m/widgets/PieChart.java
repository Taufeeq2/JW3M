package jw3m.widgets;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
 
public class PieChart extends JPanel {
 
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
 
    Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
    arc.setFrame(50, 10, 150, 150);
 
    // 0 - 80 degrees
    arc.setAngleStart(0);
    arc.setAngleExtent(80);
    g2.setColor(Color.gray);
    g2.draw(arc);
    g2.setColor(Color.red);
    g2.fill(arc);
 
    // 80 - 200 degrees
    arc.setAngleStart(80);
    arc.setAngleExtent(120);
    g2.setColor(Color.gray);
    g2.draw(arc);
    g2.setColor(Color.blue);
    g2.fill(arc);
 
    // 200 - 360 degrees
    arc.setAngleStart(200);
    arc.setAngleExtent(160);
    g2.setColor(Color.gray);
    g2.draw(arc);
    g2.setColor(Color.green);
    g2.fill(arc);
 
  }
 
  public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("PieChart Example ");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBackground(Color.white);
    frame.setSize(300, 200);
 
    PieChart panel = new PieChart();
 
    frame.add(panel);
 
    frame.setVisible(true);
  }
}