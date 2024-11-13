package pro;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Route extends Template {
    
    JLabel map = new JLabel(new ImageIcon(new ImageIcon("./datafiles/지도.png").getImage().getScaledInstance(500, 750, Image.SCALE_SMOOTH)));
    
    JLabel aa[] = new JLabel[30];
    double arr[][];
    String no[][];
    double route[][];
    
    public Route() {
        route();
        init();
    }
    
    void route() {
    	arr = new double[30][29];
        no = new String[30][29];
        
        
        mgr.sql = "select * from location";
        list = mgr.a(4);
        
        for (int i = 0; i < list.size(); i++) {
            bean = list.get(i);
            
            mgr.sql = "select * from location where l_no!='" + bean.getA().get(0) + "'";
            list1 = mgr.a(4);
            for (int j = 0; j < list1.size(); j++) {
                bean1 = list1.get(j);
                double route = Math.sqrt(Math.pow(Integer.parseInt(bean.getA().get(2)) - Integer.parseInt(bean1.getA().get(2)), 2) +
                                         Math.pow(Integer.parseInt(bean.getA().get(3)) - Integer.parseInt(bean1.getA().get(3)), 2));
                no[i][j] = bean1.getA().get(0);
                arr[i][j] = route;
            }
            
            for (int j = 0; j < 28; j++) {
                int n = j;
                for (int k = j + 1; k < 29; k++) {
                    if (arr[i][n] > arr[i][k]) {
                        n = k;
                    }
                }
                double temp = arr[i][j];
                arr[i][j] = arr[i][n];
                arr[i][n] = temp;
                
                String tempString = no[i][j];
                no[i][j] = no[i][n];
                no[i][n] = tempString;
            }
        }
    }
    
    void init() {
    	map = new JLabel(new ImageIcon(new ImageIcon("./datafiles/지도.png").getImage().getScaledInstance(500, 750, Image.SCALE_SMOOTH))) {
    		@Override
    		protected void paintComponent(Graphics g) {
    			// TODO Auto-generated method stub
    			super.paintComponent(g);
    			Graphics2D g2 = (Graphics2D)g;
    			g2.setColor(Color.white);
    			
    			for(int i=0; i<30; i++) {
    				mgr.sql="select * from location where l_no='"+(i+1)+"'";
    				list=mgr.a(4);
    				bean = list.get(0);
    				for(int j=0; j<6; j++) {
    					mgr.sql="select * from location where l_no='"+no[i][j]+"'";
    					list1=mgr.a(4);
    					bean1 = list1.get(0);
    					
    					int x1 = Integer.parseInt(bean.getA().get(2));
                        int y1 = Integer.parseInt(bean.getA().get(3));
                        int x2 = Integer.parseInt(bean1.getA().get(2));
                        int y2 = Integer.parseInt(bean1.getA().get(3));
                        g2.setStroke(new BasicStroke(2));
                        g2.drawLine(x1, y1, x2, y2);
    				}
    			}
    		}
    	};
    	
    	mgr.sql="select * from location";
    	list=mgr.a(4);
    	
    	for(int i=0; i<list.size(); i++) {
    		bean = list.get(i);
    		 aa[i] = new JLabel("●");
    		 aa[i].setFont(new Font("맑은 고딕",4,8));
             aa[i].setBounds(Integer.parseInt(bean.getA().get(2))-5, Integer.parseInt(bean.getA().get(3))-5, 10,10);
             map.add(aa[i]);
    	}
    	
        map.setBounds(0,0,500,750);
        
        add(map);
        
        setSize(530, 830);
        setVisible(true);
        setTitle("노선 보기");
    }
    
    void routeSetting() {
    	
    }
    
    public static void main(String[] args) {
        new Route();
    }
}
