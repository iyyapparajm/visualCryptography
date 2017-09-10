package gui;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import util.ImageUtils;
import StucturesSegMent.BackgroundDetector;
import StucturesSegMent.GroupSegment;

public class Reciever {

	private JFrame frmWaterMarking;
	private BufferedImage origImage,origThumb,supPixThumb,bgThumb,groupedThumb,gausImg;
	public static int THUMB_MAX_WIDTH = 400;
    public static int THUMB_MAX_HEIGHT = 300;
    File file=null;
    ImagePanel orgImgPan;
    ImagePanel backImgPan;
    ImagePanel superImgPan;
    ImagePanel groupImgPan;
    boolean org=false;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Reciever window = new Reciever();
					window.frmWaterMarking.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}			}
		});
	}
	
	public Reciever() {
		initialize();
	}
	
	private void initialize() {
		frmWaterMarking = new JFrame();
		frmWaterMarking.setTitle("Visual Cryptography");
		frmWaterMarking.setBounds(100, 100, 850, 700);
		frmWaterMarking.setLocationRelativeTo(frmWaterMarking.getRootPane());
		frmWaterMarking.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWaterMarking.getContentPane().setLayout(null);
		
		
		final JMenuItem mntmSegmentedGroup1 = new JMenuItem("Concatenate Image");
		JMenuBar menuBar = new JMenuBar();
	    menuBar.setBounds(0, 0, 834, 23);
	    frmWaterMarking.getContentPane().add(menuBar);
	   
	        
	        JMenu mnProcess = new JMenu("Process");
	        menuBar.add(mnProcess);
	        
	        mntmSegmentedGroup1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		Imageconcat ic=new Imageconcat();
	        		
	        		JOptionPane.showMessageDialog(null, "Message decrypted and saved(FinalImg.jpg)");
	        		
	        	}
	        });
	        mnProcess.add(mntmSegmentedGroup1);	 
	        
	        JMenuItem mntmDecodeMessage = new JMenuItem("Decode Message");
	        mntmDecodeMessage.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		DecodeMessage dm=new DecodeMessage();
	        		
	        	}
	        });
	        mnProcess.add(mntmDecodeMessage);
	}
	
     public void backGroundProcess(){
    	 int width;
    	   int height;
    	   try{
    	 GroupSegment grp=new GroupSegment();
    	 BufferedImage image=grp.groupImage(origImage);
         image = ImageIO.read(file);
         width = image.getWidth();
         height = image.getHeight();
         
         for(int i=0; i<height; i++){         
            for(int j=0; j<width; j++){
            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed() * 0.299);
               int green = (int)(c.getGreen() * 0.587);
               int blue = (int)(c.getBlue() *0.114);
               Color newColor = new Color(red+green+blue,
               
               red+green+blue,red+green+blue);               
               image.setRGB(j,i,newColor.getRGB());
            }
         }
         
    	 
         /////set image/////
         backImgPan=new ImagePanel("Greyscale Image",image);
         backImgPan.setBounds(0, 344, THUMB_MAX_WIDTH+10, THUMB_MAX_HEIGHT+10);
         frmWaterMarking.getContentPane().add(backImgPan);
         frmWaterMarking.repaint();
        
         TexuredOutPut segout=new TexuredOutPut(image);
         
    	   }catch(Exception h){
    		   
    	   }
	 }
}
