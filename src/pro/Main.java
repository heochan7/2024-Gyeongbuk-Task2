package pro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends Template{

	JLabel title = new JLabel("시외버스 예약 시스템", JLabel.CENTER);
	
	JPanel p1 = new JPanel(new GridLayout(4,1,0,10));
	String q[] = {"로그인","비회원 예매하기","비회원 에매 확인 및 변경","노선 보기"};
	String q1[] = {"로그아웃","예매하기","예매 확인 및 변경","노선 보기"};
	JButton btn[] = new JButton[4];
	
	JLabel img = new JLabel();
	ImageIcon icon[] = new ImageIcon[4];
	
	public Main() {
		
		for(int i=0; i<4; i++) {
			icon[i]=new ImageIcon("./datafiles/홍보/"+(i+1)+".gif");
		}
		
		title.setFont(title.getFont().deriveFont(20f));
		p1.setOpaque(false);
		
		title.setBounds(0, 10, 435, 30);
		logo.setBounds(20,10,50,40);
		p1.setBounds(20, 60, 400, 190);
		img.setBounds(20, 260, 400, 140);
		
		add(title);
		add(logo);
		add(p1);
		add(img);
		
		loginCk();
		loop();
		
		setSize(450, 450);
		setTitle("메인");
		setVisible(true);
		
		btn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btn[0].getText().equals("로그인")) {
					new Login();					
					btn[0].setText("로그아웃");
				}else if(btn[0].getText().equals("로그아웃")) {					
					loginCk();
				}
			}
		});
		btn[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(btn[1].getText().equals("비회원 예매하기")) {
					new GuestLogin(1);
					dispose();
				}else {
					new Ticketting();
					dispose();
				}
			}
		});
		btn[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(btn[2].getText().equals("비회원 예매 확인 및 변경")) {
					new GuestLogin(2);
					dispose();
				}else {
					
				}
			}
		});
		btn[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				
			}
		});
	}
	
	void loginCk() {
		p1.removeAll();
		
		if(Login.no==0) {
			for(int i=0; i<q.length; i++) {
				btn[i]=new JButton(q[i]);
				p1.add(btn[i]);
			}
		}else {
			for(int i=0; i<q1.length; i++) {
				btn[i]=new JButton(q1[i]);
				p1.add(btn[i]);
			}
		}
		logo.repaint();
		p1.updateUI();
	}
	
	void loop() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int n = 0;
			@Override
			public void run() {
				img.setIcon(icon[n]);
				n++;
				n%=4;
			}
		};
		timer.schedule(task, 0, 2000);
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
