package pro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Template extends JFrame{
	
	JLabel logo = new JLabel(new ImageIcon(new ImageIcon("./datafiles/로고.png").getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH)));
	
	ArrayList<Bean>list, list1;
	Bean bean, bean1;
	DBMgr mgr = new DBMgr();
	
	public Template() {
		
		logo = new JLabel(new ImageIcon(new ImageIcon("./datafiles/로고.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))) {
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);
				if(! Login.no.equals("")) {
					g.setColor(new Color(255,0,0,100));
					g.fillRect(0,0,getWidth(), getHeight());
				}
			}
		};
		
		setLayout(null);
		setIconImage(new ImageIcon("./datafiles/로고.png").getImage());
		getContentPane().setBackground(Color.white);
	}
}
