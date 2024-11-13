package pro;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class Ticketting extends Template{

	static String date = "2024-06-15";
	
	JLabel title = new JLabel("고속버스 예매", JLabel.CENTER);
	
	JLabel lb1= new JLabel("날짜");
	JLabel lb2 = new JLabel("\uD83D\uDCC5 날짜를 선택하세요.");
	
	JLabel a1 = new JLabel("출발지");
	JTextField tf1 = new JTextField();
	JLabel a2 = new JLabel("도착지");
	JTextField tf2 = new JTextField();
	
	JScrollPane jsp1 = new JScrollPane();
	JTable tb1 = new JTable();
	String co1[] = {"No","이름"};
	Object ro1[][];
	
	JScrollPane jsp2 = new JScrollPane();
	JTable tb2 = new JTable();
	String co2[] = {"No","이름"};
	Object ro2[][];
	
	String search1 = "";
	String search2 = "";
	
	String no1 = "";
	String no2 = "";
	
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	DecimalFormat df = new DecimalFormat("#,###");
	
	JLabel way = new JLabel();
	JLabel b1 = new JLabel("등급");
	String q[] = {"일반","우등","vip"};
	JLabel price = new JLabel();
	JComboBox cb = new JComboBox(q);
	JLabel b2 = new JLabel("구분");
	String q1[] = {"직통","경유"};
	JComboBox cb1 = new JComboBox(q1);
	JScrollPane jsp3 = new JScrollPane();
	JTable tb3 = new JTable();
	String co[]= {"No","출발시간","도착시간","좌석"};
	Object ro[][];
	
	JLabel b3 = new JLabel("좌석을 선택해주세요.");
	JButton btn1 = new JButton("예약완료");
	
	JFrame frame ;
	
	public Ticketting() {

		frame = this;
		
		if(date.equals("")) {
			lb2.setText("\ud83d\udcc5 날짜를 선택하세요.");
		}else {
			lb2.setText("\uD83D\uDCC5 "+date);
		}
		
		title.setFont(title.getFont().deriveFont(20f));
		lb1.setFont(lb1.getFont().deriveFont(14.f));
		lb2.setFont(lb2.getFont().deriveFont(14.f));
		
		logo.setBounds(5,5,50,40);
		title.setBounds(0, 10, 435, 30);
		lb1.setBounds(10, 50, 50, 20 );
		lb2.setBounds(60, 50, 140, 20);
		
		a1.setBounds(5, 80, 50, 30);
		tf1.setBounds(60, 80, 130, 30);
		a2.setBounds(230, 80, 50, 30);
		tf2.setBounds(280, 80, 130, 30);
		jsp1.setBounds(5, 120, 190, 380);
		jsp2.setBounds(230, 120, 190, 380);
		
		add(title);
		add(logo);
		add(lb1);
		add(lb2);
		add(a1);
		add(a2);
		add(tf1);
		add(tf2);
		add(jsp1);
		add(jsp2);
	
		set();
		
		setSize(450, 550);
		setVisible(true);
		setTitle("예매");
	
		lb2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				new CalendarSelection();
				dispose();
			}
		});
		
		tf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					search1 = "where l_name like '%"+tf1.getText()+"%'";
					set();
				}
			}
		});
		
		tf2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					search2 = "where l_name like '%"+tf2.getText()+"%'";
					set();
				}
			}
		});
		
		tb1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(date.equals("")) {
					Msg.err("출발날짜를 선택하세요.");
					tb1.clearSelection();
					return;
				}
				no1 = tb1.getValueAt(tb1.getSelectedRow(), 1)+"";
				set1();
			}
		});
		tb2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(date.equals("")) {
					Msg.err("출발날짜를 선택하세요.");
					tb2.clearSelection();
					return;
				}
				if(no1.equals("")) {
					Msg.err("출발지를 먼저 선택하세요.");
					tb2.clearSelection();
					return;
				}
				no2 = tb2.getValueAt(tb2.getSelectedRow(), 1)+"";
				set1();
			}
		});
	}
	
	void set() {
		DefaultTableModel model1 = new DefaultTableModel(ro1, co1) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		DefaultTableCellRenderer dtc1 = new DefaultTableCellRenderer();
		dtc1.setBackground(Color.blue);
		dtc1.setForeground(Color.white);
		dtc1.setHorizontalAlignment(SwingUtilities.CENTER);
		
		tb1=new JTable(model1);
		mgr.sql="SELECT l_no, l_name FROM location "+search1+";";
		list=mgr.a(2);
		if(list.size() == 0) {
			Msg.err("검색된 역이 없습니다.");
			return;
		}
		
		for(int i=0; i<list.size(); i++) {
			bean = list.get(i);
			model1.addRow(new Object[] {(i+1), bean.getA().get(1)});
		}
		for(int i=0; i<tb1.getColumnCount(); i++) {
			tb1.getColumn(co1[i]).setHeaderRenderer(dtc1);
		}
		
		DefaultTableModel model2 = new DefaultTableModel(ro2, co2) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		DefaultTableCellRenderer dtc2 = new DefaultTableCellRenderer();
		dtc2.setBackground(Color.blue);
		dtc2.setForeground(Color.white);
		dtc2.setHorizontalAlignment(SwingUtilities.CENTER);
		
		tb2=new JTable(model2);
		mgr.sql="SELECT l_no, l_name FROM location "+search2+";";
		list=mgr.a(2);
		
		if(list.size() == 0) {
			Msg.err("검색된 역이 없습니다.");
			return;
		}
		
		for(int i=0; i<list.size(); i++) {
			bean = list.get(i);
			model2.addRow(new Object[] {(i+1), bean.getA().get(1)});
		}
		for(int i=0; i<tb2.getColumnCount(); i++) {
			tb2.getColumn(co2[i]).setHeaderRenderer(dtc2);
		}
		jsp1.setViewportView(tb1);
		jsp2.setViewportView(tb2);
	}
	
	void set1() {
		if(! no1.equals("") && ! no2.equals("")) {
			if(no1.equals(no2)) {
				Msg.err("출발지와 도착지가 같습니다.");
				return;
			}
			set2();
			
			b3.setEnabled(false);
			
			way.setFont(way.getFont().deriveFont(20.f));
			price.setFont(way.getFont().deriveFont(20.f));
			b3.setIcon(new ImageIcon(new ImageIcon("./datafiles/좌석.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			
			price.setHorizontalAlignment(JLabel.RIGHT);
			
			way.setBounds(500, 60, 100, 30);
			price.setBounds(750, 60, 100,30);
			b1.setBounds(500, 90, 40, 30);
			cb.setBounds(540, 90, 120, 30);
			b2.setBounds(700, 90, 40, 30);
			cb1.setBounds(740, 90, 120, 30);
			jsp3.setBounds(500, 140, 360, 320);
			b3.setBounds(500, 470, 200, 30);
			btn1.setBounds(740, 470, 120, 30);
			
			add(way);
			add(price);
			add(b1);
			add(cb);
			add(b2);
			add(cb1);
			add(jsp3);
			add(b3);
			add(btn1);
			
			setSize(900, 550);
			title.setBounds(0, 10, 885, 30);
		}
		cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				set2();
			}
		});
		
		cb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				set2();
			}
		});
		
		b3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(b3.isEnabled()) {
					// 출발, 종점, 날짜 ,출발 시간, 등급, 구분 (직통 경유) 
					String a[] = {no1,no2, date, tb3.getValueAt(tb3.getSelectedRow(), 1)+"", (cb.getSelectedIndex()+1)+"", (cb1.getSelectedIndex()+1)+""};
					for(int i=0; i<a.length; i++) {
						System.out.print(a[i]+" ");
					}
					System.out.println();
					setVisible(false);
					
					new SeatChoice(frame,a);
				}
			}
		});
	}
	void set2() {
		DefaultTableModel model3 = new DefaultTableModel(ro, co) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		DefaultTableCellRenderer dtc3 = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				// TODO Auto-generated method stub
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if(row == tb3.getSelectedRow()) {
					c.setBackground(Color.yellow);
				}else {
					c.setBackground(Color.white);
				}
				return c;
			}
		};
		DefaultTableCellRenderer bg = new DefaultTableCellRenderer();
		bg.setBackground(Color.blue);
		bg.setForeground(Color.white);
		bg.setHorizontalAlignment(SwingUtilities.CENTER);
		
		int distance = 0;
		
		if(cb1.getSelectedIndex()==0) {
			mgr.sql="SELECT * FROM location where l_name = '"+no1+"' or l_name = '"+no2+"';";
			list=mgr.a(4);
			bean=list.get(0);
			bean1 = list.get(1);
			way.setText(no1+"→"+no2);
			distance = (int)(Math.sqrt(Math.pow(Integer.parseInt(bean.getA().get(2))-Integer.parseInt(bean1.getA().get(2)), 2)+Math.pow(Integer.parseInt(bean.getA().get(3))-Integer.parseInt(bean1.getA().get(3)), 2)))/2;
		}else {
			// 경유 보류..
		}
		int price1 = 0;
		
		price1 = distance*100;
		
		if(cb.getSelectedIndex()==1) {
			price1 = (int) (price1 * 1.2);
		}else if(cb.getSelectedIndex() == 2){
			price1 = (int) (price1 * 1.5);
		}
		
		if(! Login.no.equals("")) {
			mgr.sql="select u_birth from user where u_no='"+Login.no+"'";
			list=mgr.a(1);
			bean = list.get(0);
			LocalDate d1 = LocalDate.now();
			LocalDate d2 = LocalDate.parse(bean.getA().get(0));
			
			int age = d1.getYear()-d2.getYear();
			
			if(d1.compareTo(d2.plusYears(age))>0) {
				age--;
			}
			if(age<13) {
				price1= (int)(price1*0.5);
			}else if(age<19){
				price1= (int)(price1*0.2);
				System.out.println(65);
				price1 =(int)(price1*0.4);
			}
		}
		
		price.setText(df.format(price1));
		
		tb3 = new JTable(model3);
		tb3.setDefaultRenderer(Object.class, dtc3);
		tb3.getTableHeader().setDefaultRenderer(bg);
		
		LocalTime time;
		if(date.equals(sf.format(new Date()))){
			time = LocalTime.now().truncatedTo(ChronoUnit.HOURS).plusHours(1);
		}else {
			time = LocalTime.parse("06:00");
		}
		
		int t = Integer.parseInt(time.toString().substring(0, 2));
		
		for(int i=0; i<17-(t-6) ; i++) {
			String a = String.format("%02d:00", t); // 출발 시간
			
			int h = distance/60;
			int m = distance%60;
			
			mgr.sql="SELECT count(*) FROM ticket as t join location as l1 on l1.l_no=t.l_no1 join location as l2 on l2.l_no = t.l_no2 where time = '"+a+"' and date ='"+date+"' and rating ='"+(cb.getSelectedIndex()+1)+"' and division ='"+(cb1.getSelectedIndex()+1)+"' and l1.l_name = '"+no1+"' and  l2.l_name = '"+no2+"';";
			list=mgr.a(1);
			bean = list.get(0);
			int seat = 0;
			if(cb.getSelectedIndex() == 0) {
				seat = 45;
			}else if(cb.getSelectedIndex() == 1) {
				seat = 27;
			}else {
				seat = 10;
			}
			
			model3.addRow(new Object[] {(i+1), a, String.format("%02d:%02d", h+t, m), bean.getA().get(0)+"/"+seat});
			t++;
		}
		jsp3.setViewportView(tb3);
		
		tb3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				tb3.repaint();
				b3.setEnabled(true);
			}
		});
	}
	
	public static void main(String[] args) {
		new Ticketting();
	}

}
