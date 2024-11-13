package pro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;

import javax.swing.JLabel;

public class Diamond extends JLabel{
	Color co;
	Color fo =Color.black;
	String a = "";
	public Diamond(String a) {
		setText(a);
		setHorizontalAlignment(JLabel.CENTER);
		
	}
	
	@Override
	public void setBackground(Color bg) {
		// TODO Auto-generated method stub
		super.setBackground(bg);
		co = bg;
		repaint();
	}
	@Override
	public void setForeground(Color fg) {
		// TODO Auto-generated method stub
		super.setForeground(fg);
		fo = fg;
		repaint();
	}
	
	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		super.setText(text);
		a =text;
		repaint();
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();

        int centerX = width / 2;
        int centerY = height / 2;

        int diamondWidth = 80;
        int diamondHeight = 80;

        int[] xPoints = {
            centerX,
            centerX + diamondWidth / 2,
            centerX,
            centerX - diamondWidth / 2
        };

        int[] yPoints = {
            centerY - diamondHeight / 2,
            centerY,
            centerY + diamondHeight / 2,
            centerY
        };
        g2d.setColor(Color.black);
        g2d.drawPolygon(xPoints, yPoints, 4);
        g2d.setColor(co);
        g2d.fillPolygon(xPoints, yPoints, 4);
        g2d.setColor(fo);
        g2d.drawString(a, centerX-(a.length()*5), centerY);
	}
}
