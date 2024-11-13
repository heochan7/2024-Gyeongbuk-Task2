package pro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class Tf extends JTextField{
	String t;
	
	public Tf(String a ) {
		t = a;
		repaint();
		
		addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				t = a;
				repaint();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				t = "";
				repaint();
			}
		});
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		if(getText().equals("")) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawString(t, (getWidth()/2)-(t.length()*5), 20);
		}
	}
}
