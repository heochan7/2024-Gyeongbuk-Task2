package pro;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class GuestLogin extends Template{
	
	JLabel title = new JLabel("비회원 정보입력",JLabel.CENTER);
	
	JPanel p1 = new JPanel(new GridLayout(2,2,-100, 10));
	JLabel lb1 = new JLabel("전화번호");
	JLabel lb2 = new JLabel("비밀번호");
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	
	JButton btn1 = new JButton("확인");
	
	public GuestLogin(int n) {

		title.setFont(title.getFont().deriveFont(20f));
		p1.setOpaque(false);
		
		title.setBounds(0, 10, 335, 30);
		p1.setBounds(5, 60, 320, 70);
		btn1.setBounds(5, 150, 320, 30);
		logo.setBounds(5,5,50,40);
		
		
		add(title);
		add(p1);
		add(btn1);
		add(logo);
		
		p1.add(lb1);
		p1.add(tf1);
		p1.add(lb2);
		p1.add(tf2);
		
		setSize(350, 250);
		setVisible(true);
		setTitle("비회원 정보입력");
		
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tf1.getText().equals("")||tf2.getText().equals("")) {
					Msg.err("빈칸이 있습니다.");
					return;
				}
				if(! tf1.getText().matches("010-\\d{4}-\\d{4}")) {
					Msg.err("전화번호 형식을 확인해주세요.");
					return;
				}
				if(! tf2.getText().matches("\\d{4}")) {
					Msg.err("비밀번호는 숫자 4자리로 입력해주세요.");
					return;
				}
				
				if(n==1) {
					new Ticketting();
					dispose();
				}else {
					
				}
				
			}
		});
	}
	
	public static void main(String [] args) {
		new GuestLogin(1);
	}
}
