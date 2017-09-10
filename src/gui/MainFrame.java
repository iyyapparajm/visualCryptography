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
import javax.swing.JLabel;
import java.awt.Font;

public class MainFrame {

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
    JLabel lblCheckYourMail ;
    JLabel lblNewLabel ;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame window = new MainFrame();
					window.frmWaterMarking.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}			}
		});
	}
	
	public MainFrame() {
		initialize();
	}
	
	private void initialize() {
		frmWaterMarking = new JFrame();
		frmWaterMarking.setTitle("Visual Cryptography");
		frmWaterMarking.setBounds(100, 100, 850, 700);
		frmWaterMarking.setLocationRelativeTo(frmWaterMarking.getRootPane());
		frmWaterMarking.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWaterMarking.getContentPane().setLayout(null);
	
		JMenuBar menuBar = new JMenuBar();
	    menuBar.setBounds(0, 0, 834, 23);
	    frmWaterMarking.getContentPane().add(menuBar);
	    JMenu mnFile = new JMenu("File");
	    menuBar.add(mnFile);
	        
	        JMenuItem mntmOpen = new JMenuItem("Open");
	        mntmOpen.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent actionevent) {
	        		if(org)
	        		
	        		frmWaterMarking.remove(orgImgPan);
	        		String[] extension={".jpg",".png",".bmp",".jpeg"}; 
	        		JFileChooser fc=new JFileChooser(System.getProperty("user.dir"));
					fc.addChoosableFileFilter(new ExtensionFileFilter(extension,"Image Files(*.jpg,.png,.bmp,.jpeg"));
					fc.showOpenDialog(frmWaterMarking);
				
					try{		   
	        		file=fc.getSelectedFile();
	        		BufferedImage image=null;
	        
	        	     image=ImageIO.read(file);
	        	     origImage=image;
	        	     Dimension thumbSize = ImageUtils.determineSize(image.getWidth(), image.getHeight(), THUMB_MAX_WIDTH, THUMB_MAX_HEIGHT);
		             origThumb = ImageUtils.getScaledInstance(image, thumbSize.width, thumbSize.height, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		             orgImgPan=new ImagePanel("Original Image",origThumb);
		             orgImgPan.setBounds(0, 34, THUMB_MAX_WIDTH+10, THUMB_MAX_HEIGHT+10);
		             frmWaterMarking.getContentPane().add(orgImgPan);
		             frmWaterMarking.repaint();
		             org=true;
	        		}
					catch(NullPointerException nulex){
						JOptionPane.showMessageDialog(frmWaterMarking, "Please Choose some Image File");
					}
	        		catch(Exception ex){
	        			ex.printStackTrace();        			
	        		}	        		         			
	                }
	        
	        });
	        mnFile.add(mntmOpen);
	        	        
	        JMenuItem mntmExit = new JMenuItem("Exit");
	        mntmExit.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		System.exit(1);
	        	}
	        });
	        mnFile.add(mntmExit);
	        
	        JMenu mnProcess = new JMenu("Process");
	        menuBar.add(mnProcess);
	        
	        JMenuItem mntmSuperPixelExtraction = new JMenuItem("Grayscale");
	        mntmSuperPixelExtraction.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent actionevent) {
	        			        		
	        		backGroundProcess();
	             	}
	        });
	        mnProcess.add(mntmSuperPixelExtraction);
	        
	        JMenuItem mntmSegmentGrouping = new JMenuItem("Encrypt the Image");
	        mntmSegmentGrouping.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent actionevent) {
	        		ImageEncryptAndDecrypt r =new ImageEncryptAndDecrypt();
	        		r.encrypt_split();
	        		JOptionPane.showMessageDialog(null, "Splitted Image is encrypted");
	        	}
	        });
	        
	        JMenuItem mntmSplitImage = new JMenuItem("Split Image");
	        mntmSplitImage.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		
	        	ImageSplitTest st=new ImageSplitTest(file);	
	        	JOptionPane.showMessageDialog(null, "Image is Successfully Splitted");
	        	}
	        });
	        mnProcess.add(mntmSplitImage);
	        mnProcess.add(mntmSegmentGrouping);
	        

	        JMenuItem mntmSegmentedGroup = new JMenuItem("Send Splitted Image to email id");
	        mntmSegmentedGroup.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		EmailAttachmentSender es=new EmailAttachmentSender();
	        		es.send();
	        		JOptionPane.showMessageDialog(null, "Encrypted Images have been sent");
	        		lblCheckYourMail.setVisible(true);
	        		lblNewLabel.setVisible(true);
	        	}
	        });
	        mnProcess.add(mntmSegmentedGroup);	
	        
	        JMenuItem mntmEncodeMessage = new JMenuItem("EmbedMessage");
	        mntmEncodeMessage.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		EmbedMessage et=new EmbedMessage();
	        		
	        	}
	        });
	        mnProcess.add(mntmEncodeMessage);
	        
	    
	       
	        
	         lblCheckYourMail = new JLabel("Check Your Mail, download the part of images and ");
	        lblCheckYourMail.setFont(new Font("Tahoma", Font.ITALIC, 14));
	        lblCheckYourMail.setForeground(Color.BLUE);
	        lblCheckYourMail.setBounds(456, 115, 354, 41);
	        lblCheckYourMail.setVisible(false);
	        frmWaterMarking.getContentPane().add(lblCheckYourMail);
	        
	         lblNewLabel = new JLabel("paste in encrypt1 folder in your project");
	        lblNewLabel.setFont(new Font("Dialog", Font.ITALIC, 14));
	        lblNewLabel.setForeground(Color.BLUE);
	        lblNewLabel.setBounds(466, 167, 292, 41);
	        lblNewLabel.setVisible(false);
	        frmWaterMarking.getContentPane().add(lblNewLabel);
	        
	  
	  
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
