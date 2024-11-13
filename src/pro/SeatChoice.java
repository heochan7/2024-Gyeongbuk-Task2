package pro;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SeatChoice extends Template{

	JLabel lb1 = new JLabel("");
	JLabel lb2 = new JLabel();

	JLabel aa[] = new JLabel[99];
	
	JButton btn1 = new JButton("선택 완료");
	
	public SeatChoice() {
		init();
	}
	
	public SeatChoice(JFrame frame, String[] type) {
		lb1.setText(type[0]+"->"+type[1]);
		lb2.setText(type[2]+" "+type[3]);
		
		lb1.setBounds(0,0,100,30);
		lb2.setBounds(0,30, 120, 20);
		
		add(lb1);
		add(lb2);
		
		init();
	}
	
	// 출발, 종점, 날짜 ,출발 시간, 등급, 구분 (직통 경유)
	public SeatChoice(String[] type) {
		lb1.setFont(lb1.getFont().deriveFont(20.f));
		lb2.setFont(lb2.getFont().deriveFont(12.f));
		
		lb1.setText(type[0]+"->"+type[1]);
		lb2.setText(type[2]+" "+type[3]);
		
		int x = 10;
		int y = 60;
		
		if(type[4].equals("1")) {
			for(int i=0; i<45; i++) {
				aa[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/좌석.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
				aa[i].setBounds(x, y, 20,20);
				add(aa[i]);
			}
		}else if(type[4].equals("2")) {
			for(int i=0; i<27; i++) {
				aa[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/좌석.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
				aa[i].setBounds(x, y, 20,20);
				add(aa[i]);
			}
		}else {
			for(int i=0; i<27; i++) {
				aa[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/좌석.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
				aa[i].setBounds(x, y, 20,20);
				add(aa[i]);
			}
		}
		
		lb1.setBounds(10,0,100,30);
		lb2.setBounds(10,30, 120, 20);
		
		add(lb1);
		add(lb2);
		
		init();
	}
	
	public void init () {
		setSize(400, 800);
		setVisible(true);
		setTitle("좌석 선택");
	}
	
	public static void main(String[] args) {
		String q[] = {"서울", "세종", "2024-06-15" ,"08:00" ,"1" ,"1"};
		new SeatChoice(q);
		
	}
	
}
