 package pro;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class CalendarSelection extends Template{

	int year1 = 0;
	int mon1 = 0;
	int day1 = 0;
	
	String fir = "";
	String sec = "";
	
	JLabel mon = new JLabel("",JLabel.CENTER);
	JLabel left = new JLabel("◀");
	JLabel right = new JLabel("▶");
	
	JPanel p1 = new JPanel(null);
	Diamond day[] = new Diamond[42];
	
	LocalDate select ;
	
	JButton btn1 = new JButton("선택완료");
	
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sf1 = new SimpleDateFormat("yyyy");
	SimpleDateFormat sf2 = new SimpleDateFormat("MM");
	SimpleDateFormat sf3 = new SimpleDateFormat("dd");
	
	public CalendarSelection() {
		p1.setOpaque(false);
		
		mon.setFont(mon.getFont().deriveFont(25.f));
		left.setFont(mon.getFont().deriveFont(25.f));
		right.setFont(mon.getFont().deriveFont(25.f));
		
		year1 = Integer.parseInt(sf1.format(new Date()));
		mon1 = Integer.parseInt(sf2.format(new Date()));
		day1 = Integer.parseInt(sf3.format(new Date()));
		
		select = LocalDate.now();
		
		mon.setText(year1+"년 "+mon1+"월");
		
		mon.setBounds(0, 10,685, 30);
		left.setBounds(250, 10, 30,30);
		right.setBounds(420, 10, 30,30);
		p1.setBounds(0, 50,685, 400);
		
		btn1.setBounds(300, 400, 100,30);
		
		add(mon);
		add(left);
		add(right);
		add(p1);
		add(btn1);
		
		set();
		arrow();
		
		setSize(700, 500);
		setVisible(true);
		setTitle("달력");
		
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Ticketting.date = select+"";
				new Ticketting();
			}
		});
		
		left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(left.isEnabled()) {
					mon1 --;
					set();
					arrow();
				}
			}
		});
		right.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(right.isEnabled()) {
					mon1 ++;
					set();
					arrow();
				}
			}
		});
	}
	
	
	void arrow() {
		
		if(mon1 == 12) {
			right.setEnabled(false);
		}else {
			right.setEnabled(true);
		}
		
		if(Integer.parseInt(sf2.format(new Date())) == mon1) {
			left.setEnabled(false);
		}else {
			left.setEnabled(true);
		}
	}
	
	void set() {
		p1.removeAll();
		
		Calendar cal = Calendar.getInstance();
		cal.set(year1, mon1-1, 1);
		
		mon.setText(year1+"년 "+mon1+"월");
		
		int start = cal.get(Calendar.DAY_OF_WEEK) -1 ;
		int end = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int x = 10; 
		int y = 0;
		for(int i=0; i<42; i++) {
			day[i]=new Diamond("");
			day[i].setBounds(x, y, 80,80);
			
			p1.add(day[i]);
			
			x += 84;
			if((i+1)%7==0) {
				y+=42;
				if(((i+1)/7) %2 == 0) {
					x = 10;
				}else {
					x = 52;
				}
			}
		}
		
		String w[] = {"일","월","화","수","목","금","토"};
		
		int d = 1;
		for(int i=0; i<42; i++) {
			if(i<start || i>end+start-1) {
				day[i].setVisible(false);
			}else {
				LocalDate d1 = LocalDate.now();
				LocalDate d2 = LocalDate.parse(String.format("%04d", year1)+"-"+String.format("%02d", mon1)+ "-"+String.format("%02d", d));
				if(d1.compareTo(d2)<=0) {
					if(d2.compareTo(select)==0) {
						day[i].setForeground(Color.red);
					}
					day[i].setBackground(Color.orange);
					day[i].setToolTipText(w[i%7]);
				}
				day[i].setText(d+"");
				day[i].repaint();
				d++;
				
				int n = i;
				day[n].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						super.mouseClicked(e);
						select = LocalDate.parse(year1+"-"+String.format("%02d", mon1)+"-"+String.format("%02d", Integer.parseInt(day[n].getText())));
						set();
					}
				});
			}
		}
		p1.updateUI();
	}
	
	public static void main(String[] args) {
		new CalendarSelection();
	}

}
