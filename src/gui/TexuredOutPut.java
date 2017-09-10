package gui;

import java.awt.Dimension;
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

import util.ImageUtils;


public class TexuredOutPut {

	private JFrame frmTexuredImage;
	BufferedImage segOut;
	public static int THUMB_MAX_WIDTH = 400;
    public static int THUMB_MAX_HEIGHT = 300;
    ImagePanel segImgPan;

	/**
	 * Create the application.
	 */
	public TexuredOutPut(BufferedImage segOut) {
		this.segOut=segOut;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTexuredImage = new JFrame();
		frmTexuredImage.setTitle("Textured Image");
		frmTexuredImage.setBounds(100, 100, 450, 402);
		frmTexuredImage.setLocationRelativeTo(frmTexuredImage.getRootPane());
		frmTexuredImage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTexuredImage.getContentPane().setLayout(null);
		Dimension thumbSize = ImageUtils.determineSize(segOut.getWidth(), segOut.getHeight(), THUMB_MAX_WIDTH, THUMB_MAX_HEIGHT);
      
		BufferedImage bgThumbImage = ImageUtils.getScaledInstance(segOut, thumbSize.width, thumbSize.height, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
        segImgPan=new ImagePanel("Texured Image",segOut);
        segImgPan.setBounds(10, 54, THUMB_MAX_WIDTH+10, THUMB_MAX_HEIGHT+10);
        frmTexuredImage.getContentPane().add(segImgPan);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 434, 21);
        frmTexuredImage.getContentPane().add(menuBar);
        
        JMenu mnSave = new JMenu("Save");
        menuBar.add(mnSave);
        
        JMenuItem mntmSaveImage = new JMenuItem("Save Image");
        mntmSaveImage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String[] extension={".jpg",".png",".bmp",".jpeg"}; 
        		JFileChooser fc=new JFileChooser();
        		fc.addChoosableFileFilter(new ExtensionFileFilter(extension,"Image Files(*.jpg,.png,.bmp,.jpeg"));
				fc.showSaveDialog(frmTexuredImage);
        		 File output =fc.getSelectedFile(); 
        		try {
        	            ImageIO.write(segOut, "jpg", output);
        	        } catch (IOException ex) {
        	            ex.printStackTrace();
        	        }
        	}
        });
        mnSave.add(mntmSaveImage);
        frmTexuredImage.setVisible(true);
		
	}
}
