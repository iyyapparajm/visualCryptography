package gui;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ImagePanel(String title,BufferedImage img) {
		setLayout(new FlowLayout(FlowLayout.CENTER));		
		ImageIcon fromIcon = new ImageIcon(img);
		JLabel label = new JLabel("");
		label.setIcon(fromIcon);
		label.setBounds(10, 11, 430, 278);
		add(label);
		label.setBorder(BorderFactory.createTitledBorder(title));
//		createLayout(x, y, width, hight, title, img,frame);	
//		setVisible(true);
		}
//	 public void createLayout(int x,int y,int width,int hight,String title,BufferedImage img,JFrame frame) { 
//		    setBounds(x,y,width,hight);
//	        setLayout(new FlowLayout(FlowLayout.CENTER));
//	        ImageIcon fromIcon = new ImageIcon(img);
//	        JLabel fromLabel = new JLabel("");
//	        fromLabel.removeAll();	       
//	        fromLabel.setIcon(fromIcon);
//	        fromLabel.setBorder(BorderFactory.createTitledBorder(title));
//	        fromLabel.repaint();
//	        add(fromLabel);
//	        repaint();
//	        frame.getContentPane().add(this);       
//	        frame.repaint();	        
//	    }
}
