package pro;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Login extends Template{
	static int no=0;
	
	JLabel title = new JLabel("로그인", JLabel.CENTER);
	JPanel p1 = new JPanel(new GridLayout(2,2,-100, 10));
	JLabel lb1 = new JLabel("아이디");
	JLabel lb2 = new JLabel("비밀번호");
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	
	JPanel p2 = new JPanel(new GridLayout(2,4));
	JLabel num[] = new JLabel[8];
	JLabel reset = new JLabel(new ImageIcon(new ImageIcon("./datafiles/새로고침.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
	String number = "";
	
	
	Tf tf3 = new Tf("자동 입력 방지 문자를 입력해주세요.");
	JButton btn1 = new JButton("로그인");
	
	
	public Login() {
		
		p1.setOpaque(false);
		p2.setOpaque(false);
		
		title.setFont(title.getFont().deriveFont(20f));

		title.setBounds(0,10, 385, 30);
		logo.setBounds(5,10, 50,40);
		p1.setBounds(5, 60, 365, 70);
		p2.setBounds(50, 150, 250, 100);
		reset.setBounds(310, 180, 30,30);
		tf3.setBounds(45, 260, 300, 30);
		btn1.setBounds(45, 300, 300, 30);
		
		add(title);
		add(logo);
		add(p1);
		add(p2);
		add(reset);
		add(tf3);
		add(btn1);
		
		p1.add(lb1);
		p1.add(tf1);
		p1.add(lb2);
		p1.add(tf2);
		
		
		resetF();
		
		setSize(400, 400);
		setVisible(true);
		setTitle("로그인");
		
		reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				resetF();
			}
		});
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tf1.getText().equals("")||tf2.getText().equals("")) {
					Msg.err("빈칸이 있습니다.");
					return;
				}
				mgr.sql="select u_no, u_nickname from user where u_id='"+tf1.getText()+"' and u_pw='"+tf2.getText()+"'";
				list=mgr.a(2);
				
				if(list.size() == 0 && ! tf1.getText().equals("admin")) {
					Msg.err("일치하는 회원이 없습니다.");
					return;
				}

				if(! number.equals(tf3.getText())) {
					Msg.err("자동 입력 방지 문자가 틀립니다.");
					resetF();
					
					return;
				}
				
				if(tf1.getText().equals("admin")&&tf2.getText().equals("1234")) {
					Msg.info("관리자님 환영합니다.");
//					new RouteAdd();
					return;
				}	
				
				bean = list.get(0);
				no = Integer.parseInt(bean.getA().get(0));
				Msg.info(bean.getA().get(1)+"님 환영합니다.");
				new Main();
				dispose();
			}
		});
	}
	
	void resetF() {
		p2.removeAll();
		
		for(int i=0; i<8; i++) {
			num[i]=new JLabel("",JLabel.CENTER);
			num[i].setBorder(new LineBorder(Color.black));
			num[i].setFont(num[i].getFont().deriveFont(20f));
			p2.add(num[i]);
		}
		
		Random r1 = new Random();
		int n[] = new int[4];
		
		for(int i=0; i<4; i++) {
			n[i]=r1.nextInt(10);
			for(int j=0; j<i; j++) {
				if(n[i] == n[j]) {
					i--;
				}
			}
		}
		number = "";
		
		for(int i=0; i<4; i++) {
			if(r1.nextInt()%2==0) {
				num[i].setText(n[i]+"");
			}else {
				num[i+4].setText(n[i]+"");
			}
			number+=n[i]+"";
		}
		
		p2.updateUI();
		
	}
	
	public static void main(String [] args) {
		new Login();
	}
}
