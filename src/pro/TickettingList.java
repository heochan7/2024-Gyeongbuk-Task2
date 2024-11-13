package pro;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

public class TickettingList extends Template{

	JLabel title = new JLabel("예매 내역",JLabel.CENTER);
	JLabel name = new JLabel();
	JLabel lb1 = new JLabel();
	JLabel lb2 = new JLabel();
	JLabel lb3 = new JLabel();
	
	public TickettingList() {
		
		title = new JLabel();
		
		title.setFont(title.getFont().deriveFont(20.f));
		
		title.setBounds(0, 10, 685, 30);
		
		add(title);
		
		setSize(700, 500);
		setVisible(true);
		setTitle("예매 내역");
	}
	
	public static void main(String[] args) {
		new TickettingList();
	}

}
