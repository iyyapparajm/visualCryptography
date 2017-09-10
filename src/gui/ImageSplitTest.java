package gui;

import javax.imageio.ImageIO; 

import java.awt.image.BufferedImage;  
import java.io.*;  
import java.awt.*;  
  
public class ImageSplitTest {
	
//	private BufferedImage origImage;
	public ImageSplitTest(File filename) {  
		
		try{
         
        FileInputStream fis = new FileInputStream(filename);  
        BufferedImage image = ImageIO.read(fis); 
        	
        System.out.println("receiv");
        int rows = 4; 
        int cols = 4;  
        int chunks = rows * cols;  
  
        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;  
        int count = 0;  
        BufferedImage imgs[] = new BufferedImage[chunks]; 
        for (int x = 0; x < rows; x++) {  
            for (int y = 0; y < cols; y++) {  
                //Initialize the image array with image chunks  
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());  
  
                //draws the image chunk  
                Graphics2D gr = imgs[count++].createGraphics();  
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
            }  
        }  
        System.out.println("Splitting done");  
  
        //writing mini images into image files  
        for (int i = 0; i < imgs.length; i++) {  
            ImageIO.write(imgs[i], "jpg", new File("imgs/img" + i + ".jpg"));  
            
        }  
        System.out.println("Mini images created");  
    
		}catch(Exception h){
    	h.printStackTrace();
    }
}
	
	
public static void main(String args[])
{
	
	

	
}
}